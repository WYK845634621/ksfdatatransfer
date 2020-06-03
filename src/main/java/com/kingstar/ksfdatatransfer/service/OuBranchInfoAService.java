package com.kingstar.ksfdatatransfer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kingstar.ksfdatatransfer.entity.OuBranchInfoA;

import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 9:13
 */
public interface OuBranchInfoAService extends IService<OuBranchInfoA> {

    List<OuBranchInfoA> all(OuBranchInfoA ouBranchInfoA);

    List<OuBranchInfoA> allAfterTime(OuBranchInfoA ouBranchInfoA);

}
