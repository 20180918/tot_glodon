//存放主要交互逻辑js代码
// javascript 模块化
var seckill = {
    VAL: {
        seckillId: 0,
        intervX: 0
    },
    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/exposer/' + seckillId;
        },
        execution: function (seckillId, phone, md5) {
            return '/seckill/execution/' + seckillId + '/' + phone + '/' + md5;
        },
        isGrab: function (seckillId, phone) {
            return '/seckill/taskseckillresult/' + seckillId + '/' + phone;
        }
    },
    handleSeckillkill: function (seckillId, node) {
        //获取秒杀地址，控制显示逻辑 ，执行秒杀
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');//按钮
        $.post(seckill.URL.exposer(seckillId),
            {},
            function (result) {
                //在回调函数中，执行交互流程
                if (result && result['success']) {
                    var exposer = result['data'];
                    if (exposer['exposed']) {
                        //开启秒杀
                        //获取秒杀地址.
                        var md5 = exposer['md5'];
                        seckill.VAL.seckillId = seckillId;
                        var currentPhone = $.cookie('killPhone');
                        var killUrl = seckill.URL.execution(seckillId, currentPhone, md5);

                        //绑定一次点击事件
                        $('#killBtn').one('click', function () {
                            //执行秒杀请求
                            //1:先禁用按钮
                            $(this).addClass('disabled');
                            //2:发送秒杀请求执行秒杀

                            $.post(killUrl, {}, function (result) {
                                if (result && result['success']) {
                                    console.log(result)
                                    var killResult = result['data'];

                                    var state = killResult['state'];
                                    var stateInfo = killResult['stateInfo'];
                                    //3:显示秒杀结果

                                    if(killResult.state==2){
                                        node.html('<img style="width: 400px;height:400px;border-radius: 10px;position: fixed;z-index: 999;left: 40%;top: 30%;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565786577191&di=df161a4a09fdb35bbd3912981fe920fb&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160418%2Fda88020897c94bf8bf63218130fd6306_th.jpg">');
                                        setInterval(function () {
                                            seckill.isGrab()
                                        },1000)
                                    }
                                    else if(killResult.state==205){
                                        node.html('<span class="label label-success"> 未注册 </span>');
                                    }
                                    else if(killResult.state==206){
                                        node.html('<span class="label label-success"> 密钥验证不通过 </span>');
                                    }
                                    else if(killResult.state==400){
                                        node.html('<span class="label label-success"> 服务异常 </span>');
                                    }

                                }
                            });
                        });
                        node.show();
                    } else {
                        //未开启秒杀,
                        var now = exposer['now'];
                        var start = exposer['start'];
                        var end = exposer['end'];
                        //重新计算计时逻辑
                        seckill.countdown(seckillId, now, start, end);
                    }
                } else {

                }

            });
    },
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        //时间判断
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束!');
        } else if (nowTime < startTime) {
            //秒杀未开始,计时事件绑定
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                //时间格式
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                /*时间完成后回调事件*/
            }).on('finish.countdown', function () {

                seckill.handleSeckillkill(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handleSeckillkill(seckillId, seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录 , 计时交互
            //规划我们的交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            //验证手机号
            if (!seckill.validatePhone(killPhone)) {
                //绑定phone
                //控制输出
                var killPhoneModal = $('#killPhoneModal');
                //显示弹出层
                killPhoneModal.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log('inputPhone=' + inputPhone);//TODO
                    if (seckill.validatePhone(inputPhone)) {
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }
            //已经登录
            //计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断,计时交互
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result:' + result);
                }
            });


        }
    },
    isGrab: function () {

        var node = $('#seckill-box');
        var currentPhone = $.cookie('killPhone');
        $.post(seckill.URL.isGrab(seckill.VAL.seckillId, currentPhone),
            {},
            function (res) {
                result=res.data.state

                if (result == 0) {
                    console.log(秒杀成功);
                    node.html('<span class="label label-success">' + "秒杀成功" + '</span>');
                    setTimeout(function () {
                        location.href='/seckillkilled/list/'+$.cookie('killPhone');
                    },3000)

                } else {
                    if (result == 2) {
                        console.log(2);
                        node.html('<img style="width: 400px;height:400px;border-radius: 10px;position: fixed;z-index: 999;left: 40%;top: 30%;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565786577191&di=df161a4a09fdb35bbd3912981fe920fb&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160418%2Fda88020897c94bf8bf63218130fd6306_th.jpg">');
                    } else if (result == -1) {
                        console.log(-1);
                        node.html('<span class="label label-success">' + "没抢到" + '</span>');
                        setTimeout(function () {
                            location.href='/seckill/list';
                        },3000)
                    }else if (result == 1) {
                        console.log(1);
                        node.html('<span class="label label-success">' + "禁止重复操作" + '</span>');
                        setTimeout(function () {
                            location.href='/seckillkilled/list/'+$.cookie('killPhone');
                        },3000)
                    }
                }

            });
    }

}