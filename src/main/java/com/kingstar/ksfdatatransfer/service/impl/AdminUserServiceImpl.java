package com.kingstar.ksfdatatransfer.service.impl;

import com.kingstar.ksfdatatransfer.entity.AdminUser;
import com.kingstar.ksfdatatransfer.mapper.one.AdminUserMapper;
import com.kingstar.ksfdatatransfer.service.AdminUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/13 18:41
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser one() {
        AdminUser adminUser = adminUserMapper.one();
        return adminUser;
    }

    @Override
    @Transactional("transactionManagerOne")
    public void updateOne() {
        try {
            adminUserMapper.updateOne();
            int i = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加上@Transactional:
     * 在这一层,如果捕获异常的话,是不会回滚的;不捕获异常才会回滚
     * 在上一次捕捉异常,这一层不捕捉,是会回滚的;同时上一层捕捉的异常会显示出来具体行
     * 因此,要想要回滚,这一层不能捕捉异常
     * 不加@Transactional:
     * 操作数据库成功,抛异常,不会回滚
     */
    @Override
    @Transactional(value = "transactionManagerOne")
    public void add() {
        adminUserMapper.updateOne();
        int i = 10 / 0;

    }
}
