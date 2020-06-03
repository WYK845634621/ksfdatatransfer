package com.kingstar.ksfdatatransfer.mapper.two;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kingstar.ksfdatatransfer.entity.OuBranchInfoA;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 9:12
 */
@Mapper
public interface OuBranchInfoAMapper extends BaseMapper<OuBranchInfoA> {

    List<OuBranchInfoA> all(OuBranchInfoA ouBranchInfoA);

    List<OuBranchInfoA> allAfterTime(OuBranchInfoA ouBranchInfoA);
}
