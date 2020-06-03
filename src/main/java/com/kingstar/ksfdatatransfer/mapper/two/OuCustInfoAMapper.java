package com.kingstar.ksfdatatransfer.mapper.two;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kingstar.ksfdatatransfer.entity.OuCustInfoA;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/15 9:03
 */
@Mapper
public interface OuCustInfoAMapper extends BaseMapper<OuCustInfoA> {

    List<OuCustInfoA> all(OuCustInfoA ouCustInfoA);

    List<OuCustInfoA> allAfterTime(OuCustInfoA ouCustInfoA);

    List<OuCustInfoA> selectPage(@Param("start") Integer start, @Param("end") Integer end, @Param("createDate") String createDate, @Param("createTime") String createTime);

}
