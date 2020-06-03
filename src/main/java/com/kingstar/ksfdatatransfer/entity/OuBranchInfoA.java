package com.kingstar.ksfdatatransfer.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/13 19:10
 */
@TableName("BS_USER.OU_BRANCH_INFO_A")
public class OuBranchInfoA {
    private String branchCode;
    private String branchName;
    private String createDate;
    private String createTime;

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

    public OuBranchInfoA(String dd, String tt) {
        this.createDate = dd;
        this.createTime = tt;
    }

    public OuBranchInfoA() {
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
