package com.glodon.seckilladmin.service;


import com.glodon.seckilladmin.domain.UserAdmin;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lic-s
 */
public interface LoginService {
    /**
     * 用户校验
     *
     * @param code
     * @param name
     * @param password
     * @param va
     * @return
     */
    boolean validate(  String code, String name, String password, String va) throws Exception;


    UserAdmin selectByRootName(String username);
}
