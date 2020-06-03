package com.kingstar.ksfdatatransfer.service;

import com.kingstar.ksfdatatransfer.entity.AdminUser;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/13 18:40
 */
public interface AdminUserService {
    AdminUser one();

    void updateOne();

    void add();
}
