package com.kingstar.ksfdatatransfer.mapper.one;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kingstar.ksfdatatransfer.entity.DictionaryEntity;
import com.kingstar.ksfdatatransfer.entity.OuBranchInfoA;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 11:01
 */
@Mapper
public interface DictionaryEntityMapper extends BaseMapper<DictionaryEntity> {
    Integer querySubitemSort();

    void saveAll(List<DictionaryEntity> list);

    List<String> queryExistedCodes(List<OuBranchInfoA> list);

    void updateAll(List<OuBranchInfoA> list);
}
