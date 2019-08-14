package com.glodon.seckilladmin.service;


import com.glodon.seckilladmin.domain.UserAdmin;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lic-s
 */
public interface LoginService {


    UserAdmin selectByRootName(String username);
}
