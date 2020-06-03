package com.kingstar.ksfdatatransfer.service.impl;

import com.kingstar.ksfdatatransfer.entity.UserBase;
import com.kingstar.ksfdatatransfer.mapper.two.UserBaseMapper;
import com.kingstar.ksfdatatransfer.service.UserBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/13 18:42
 */
@Service
public class UserBaseServiceImpl implements UserBaseService {

    @Resource
    private UserBaseMapper userBaseMapper ;


    @Override
    public UserBase one() {

        return userBaseMapper.one();
    }
}
