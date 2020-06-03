package com.kingstar.ksfdatatransfer.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/15 10:00
 */
@Document(indexName="ks_auth_cust_info")
public class ClientInfo {
    @Id
    private String code;
    private String name;
    private Integer status;
    private String createDateTime;

    public ClientInfo() {
    }

    public ClientInfo(String code, String name, String dt) {
        this.code = code;
        this.name = name;
        this.createDateTime = dt;
        this.status = 1;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }
}
