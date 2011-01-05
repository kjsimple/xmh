package com.gydoc.xmh.service;

import com.gydoc.xmh.domain.User;

/**
 * 
 */
public interface UserService {

    boolean isSuperUserName(String userName);
    User login(String userName, String password, Long ledgerId);
    User updateUser(User u);

}
