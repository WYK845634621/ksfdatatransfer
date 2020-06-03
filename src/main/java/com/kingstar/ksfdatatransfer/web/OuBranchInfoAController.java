package com.kingstar.ksfdatatransfer.web;

import com.kingstar.ksfdatatransfer.entity.OuBranchInfoA;
import com.kingstar.ksfdatatransfer.service.OuBranchInfoAService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 9:18
 */
@RestController
@RequestMapping("/oubranchinfoa")
public class OuBranchInfoAController {

    @Resource
    private OuBranchInfoAService ouBranchInfoAService;


    @GetMapping("/all")
    public List<OuBranchInfoA> all(){
        return ouBranchInfoAService.all(null);
    }

}
