package com.kingstar.ksfdatatransfer.mapper.two;

import com.kingstar.ksfdatatransfer.entity.UserBase;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/13 18:40
 */
@Mapper
public interface UserBaseMapper {
    UserBase one();
}
