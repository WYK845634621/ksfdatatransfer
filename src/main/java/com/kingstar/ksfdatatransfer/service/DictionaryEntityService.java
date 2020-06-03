package com.kingstar.ksfdatatransfer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kingstar.ksfdatatransfer.entity.DictionaryEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 13:44
 */
public interface DictionaryEntityService extends IService<DictionaryEntity> {

    int querySubitemSort();

}
