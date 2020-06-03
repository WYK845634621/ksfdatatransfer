package com.kingstar.ksfdatatransfer.web;

import com.kingstar.ksfdatatransfer.entity.AdminUser;
import com.kingstar.ksfdatatransfer.entity.TbConstant;
import com.kingstar.ksfdatatransfer.entity.UserBase;
import com.kingstar.ksfdatatransfer.service.AdminUserService;
import com.kingstar.ksfdatatransfer.service.TbConstantService;
import com.kingstar.ksfdatatransfer.service.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/13 18:38
 */
@RestController
public class DataController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private TbConstantService constantService;

    @GetMapping("/1")
    public String hello(){
        AdminUser adminUser = adminUserService.one();
        return adminUser.getId();
    }

    @GetMapping("/2")
    public String hello2(){
        UserBase userBase = userBaseService.one();
        return userBase.getName();
    }

    @GetMapping("/teansaction")
    public String transaction(){
        try {
            adminUserService.add();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping("/all")
    public List<TbConstant> all(){
        return constantService.list();
    }
}
