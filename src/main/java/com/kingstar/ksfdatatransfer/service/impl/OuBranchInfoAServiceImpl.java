package com.kingstar.ksfdatatransfer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingstar.ksfdatatransfer.entity.OuBranchInfoA;
import com.kingstar.ksfdatatransfer.mapper.two.OuBranchInfoAMapper;
import com.kingstar.ksfdatatransfer.service.OuBranchInfoAService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 9:14
 */
@Service
public class OuBranchInfoAServiceImpl extends ServiceImpl<OuBranchInfoAMapper, OuBranchInfoA> implements OuBranchInfoAService {

    @Resource
    private OuBranchInfoAMapper ouBranchInfoAMapper;

    @Override
    public List<OuBranchInfoA> all(OuBranchInfoA ouBranchInfoA) {
        return ouBranchInfoAMapper.all(ouBranchInfoA);
    }

    @Override
    public List<OuBranchInfoA> allAfterTime(OuBranchInfoA ouBranchInfoA) {
        return ouBranchInfoAMapper.allAfterTime(ouBranchInfoA);
    }


}
