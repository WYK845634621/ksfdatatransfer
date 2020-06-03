package com.kingstar.ksfdatatransfer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingstar.ksfdatatransfer.entity.DictionaryEntity;
import com.kingstar.ksfdatatransfer.mapper.one.DictionaryEntityMapper;
import com.kingstar.ksfdatatransfer.service.DictionaryEntityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 13:45
 */
@Service
public class DictionaryEntityServiceImpl extends ServiceImpl<DictionaryEntityMapper, DictionaryEntity> implements DictionaryEntityService {

    @Resource
    private DictionaryEntityMapper dictionaryEntityMapper;

    @Override
    public int querySubitemSort() {
        return dictionaryEntityMapper.querySubitemSort();
    }
}
