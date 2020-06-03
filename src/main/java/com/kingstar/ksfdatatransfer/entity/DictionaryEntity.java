package com.kingstar.ksfdatatransfer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 10:18
 */
@TableName("bs_dictionary.ts_dictionary_repo")
public class DictionaryEntity {
    @TableId(
            type = IdType.UUID
    )
    @TableField("id")
    private String id;
    @TableField("parent_code")
    private String parentCode;
    @TableField("code")
    private String code;
    @TableField("value")
    private String value;
    @TableField("icon")
    private String icon;
    @TableField("depth")
    private Integer depth;
    @TableField("type")
    private String type;
    @TableField("microservice")
    private String microservice;
    @TableField("tree_group")
    private String treeGroup;
    @TableField("orderr")
    private Integer orderr;
    @TableField("parent_ident")
    private String parentIdent;
    @TableField("ident_id")
    private String identId;
    @TableField("relationship")
    private String relationship;
    @TableField("remark")
    private String remark;
    @TableField("remark1")
    private String remark1;
    @TableField("remark2")
    private String remark2;
    @TableField("bus_type")
    private String busType;
    @TableField("subitem_sort")
    private int subitemSort;
    @TableField("dic_type")
    private String dicType;

    private Integer dataStatus;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @TableField("create_time")
    private Date createTime;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @TableField("modify_time")
    private Date modifyTime;

    public DictionaryEntity() {
        
    }

    public DictionaryEntity(String code, String value, int subitemSort) {
        this.code = code;
        this.value = value;
        this.subitemSort = subitemSort;

        String id = UUID.randomUUID().toString().replaceAll("-", "");
        this.setId(id);
        this.setParentCode("11");
        this.setParentIdent("00e0760b55324b64aa00a65c38d38692");
        this.setTreeGroup("dic");
        this.setOrderr(subitemSort);
        this.setType("1");
        this.setDepth(3);
        this.setMicroservice("common");
        this.setIdentId(id);
        this.setRelationship("0_4ad1b52801344486a649dc279d2a7064_a1b186e6efd34ec6a7e8235da5332115_00e0760b55324b64aa00a65c38d38692_"+id);
        this.setDataStatus(1);
        this.setCreateTime(new Date());
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMicroservice() {
        return microservice;
    }

    public void setMicroservice(String microservice) {
        this.microservice = microservice;
    }

    public String getTreeGroup() {
        return treeGroup;
    }

    public void setTreeGroup(String treeGroup) {
        this.treeGroup = treeGroup;
    }

    public Integer getOrderr() {
        return orderr;
    }

    public void setOrderr(Integer orderr) {
        this.orderr = orderr;
    }

    public String getParentIdent() {
        return parentIdent;
    }

    public void setParentIdent(String parentIdent) {
        this.parentIdent = parentIdent;
    }

    public String getIdentId() {
        return identId;
    }

    public void setIdentId(String identId) {
        this.identId = identId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public int getSubitemSort() {
        return subitemSort;
    }

    public void setSubitemSort(int subitemSort) {
        this.subitemSort = subitemSort;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
