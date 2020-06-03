package com.kingstar.ksfdatatransfer.web;

import com.kingstar.ksfdatatransfer.entity.OuCustInfoA;
import com.kingstar.ksfdatatransfer.mapper.two.OuCustInfoAMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/18 13:56
 */
@RestController
@RequestMapping("/oucustinfoa")
@Slf4j
public class OuCustInfoAController {

    @Resource
    private OuCustInfoAMapper ouCustInfoAMapper;

    @GetMapping("/all")
    public List<OuCustInfoA> all(){
        String dd = "";
        String tt = "";
        int start = 0;
        int end = 0;
        int page = 1;
        boolean flag = true;
        List<OuCustInfoA> as = new ArrayList<>();
        while (flag){
            start = (page -1)*10 + 1;
            end = page*10;
            log.info("page=" + page + "  start=" + start + "  end=" + end);
            log.info("dd:" + dd + "  tt:" + tt);
            page ++;
            as = ouCustInfoAMapper.selectPage(start, end, dd, tt);
            log.info("查出的as的大小: " + as.size());
            if (!CollectionUtils.isEmpty(as) && as.size() == 10){

            }else {
                flag = false;
                dd = as.get(as.size() -1).getCreateDate();
                tt =as.get(as.size() -1).getCreateTime();
                log.info("进行设置 ---- dd:" + dd + "  tt:" + tt);
            }
        }

        return as;
    }
}
