package com.kingstar.ksfdatatransfer.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description 这里的@TableName指定了SQL执行时所佣的表
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 8:45
 */
@TableName("ks_auth.tb_constant")
public class TbConstant {
    private String id;
    private String currentValue;

    public TbConstant() {
    }

    public TbConstant(String id, String currentValue) {
        this.id = id;
        this.currentValue = currentValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }
}
