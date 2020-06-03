package com.kingstar.ksfdatatransfer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingstar.ksfdatatransfer.entity.TbConstant;
import com.kingstar.ksfdatatransfer.mapper.one.TbConstantMapper;
import com.kingstar.ksfdatatransfer.service.TbConstantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 8:49
 */
@Service
public class TbConstantServiceImpl extends ServiceImpl<TbConstantMapper, TbConstant> implements TbConstantService {

    @Resource
    private TbConstantMapper tbConstantMapper;

}
