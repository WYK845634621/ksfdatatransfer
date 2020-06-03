package com.kingstar.ksfdatatransfer.mapper.one;

import com.kingstar.ksfdatatransfer.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/13 18:39
 */
@Mapper
public interface AdminUserMapper {
    AdminUser one();

    void updateOne();

    void add();
}
