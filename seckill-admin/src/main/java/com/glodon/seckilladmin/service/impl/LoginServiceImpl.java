package com.glodon.seckilladmin.service.impl;

import com.glodon.seckilladmin.domain.UserAdmin;
import com.glodon.seckilladmin.mapper.UserAdminMapper;
import com.glodon.seckilladmin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @author lic-s
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserAdminMapper userAdminMapper;



    @Override
    public UserAdmin selectByRootName(String username) {
        return userAdminMapper.selectByRootName(username);
    }
}