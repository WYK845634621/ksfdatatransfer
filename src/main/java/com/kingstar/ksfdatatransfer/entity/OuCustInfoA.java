package com.kingstar.ksfdatatransfer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/15 9:00
 */
@TableName("BS_USER.OU_CUST_INFO_A")
public class OuCustInfoA {

    private String custNo;
    private String custName;
    private String createDate;
    private String createTime;

    public OuCustInfoA(String dd, String tt) {
        this.createDate = dd;
        this.createTime = tt;
    }

    public OuCustInfoA() {
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
