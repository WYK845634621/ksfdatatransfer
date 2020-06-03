package com.kingstar.ksfdatatransfer.task;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kingstar.ksfdatatransfer.elk.ClientInfoRepository;
import com.kingstar.ksfdatatransfer.entity.DictionaryEntity;
import com.kingstar.ksfdatatransfer.entity.OuBranchInfoA;
import com.kingstar.ksfdatatransfer.entity.OuCustInfoA;
import com.kingstar.ksfdatatransfer.entity.TbConstant;
import com.kingstar.ksfdatatransfer.mapper.one.DictionaryEntityMapper;
import com.kingstar.ksfdatatransfer.mapper.one.TbConstantMapper;
import com.kingstar.ksfdatatransfer.mapper.two.OuBranchInfoAMapper;
import com.kingstar.ksfdatatransfer.mapper.two.OuCustInfoAMapper;
import com.kingstar.ksfdatatransfer.pojo.ClientInfo;
import com.kingstar.ksfdatatransfer.service.OuBranchInfoAService;
import com.kingstar.ksfdatatransfer.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 9:28
 */
@Component
@EnableScheduling
@Slf4j
public class SyncDataTask {

    @Resource
    private OuBranchInfoAService ouBranchInfoAService;

    @Resource
    private TbConstantMapper tbConstantMapper;

    @Resource
    private DictionaryEntityMapper dictionaryEntityMapper;

    @Resource
    private OuCustInfoAMapper ouCustInfoAMapper;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private ClientInfoRepository clientInfoRepository;


    @Value("${custom.id_yyb}")
    private String id_yyb ;
    @Value("${custom.id_client}")
    private String id_client;
    @Value("${custom.index_cust}")
    private String index;


    //同步营业部
    @Scheduled(cron = "0 0/10 * * * ? ")
    @Transactional("transactionManagerOne")
    public void syncYYB(){
        TbConstant yyb = tbConstantMapper.selectById(id_yyb);
        if (Objects.isNull(yyb) || StringUtils.isEmpty(yyb.getCurrentValue())){
            //营业部的code是3位,所以最多有1000条可以全
            List<OuBranchInfoA> all = ouBranchInfoAService.all(null);
            //插入数据字典的营业部信息
            List<DictionaryEntity> list = packYyb(all);
            dictionaryEntityMapper.saveAll(list);
            //更新记录
            addOrUpdate(id_yyb, true);
            log.info("YYB first save all successed......");

        }else {
            String lsteUpdateTime = yyb.getCurrentValue();
            //截取日期  时间
            String date = lsteUpdateTime.substring(0,8);
            String time = lsteUpdateTime.substring(8);
            log.info("YYB 日期:" + date + " 时间:" + time);
            //根据日期和时间从原始表增量查询到营业部信息
            OuBranchInfoA entity = new OuBranchInfoA(date,time);
            List<OuBranchInfoA> list = ouBranchInfoAService.all(entity);
            log.info("YYB 增量的size:" + list.size());
            //从字典表查出in(ids)  得到list  剔除和筛选出两部分: 已存在的和未存在的
            List<String> existedCodes = dictionaryEntityMapper.queryExistedCodes(list);
            List<OuBranchInfoA> needToInsert = new ArrayList<>();
            List<OuBranchInfoA> needToupdate = new ArrayList<>();
            //筛选是新增还是更新
            for (OuBranchInfoA ou : list){
                if (existedCodes.contains(ou.getBranchCode())){
                    needToupdate.add(ou);
                }else {
                    needToInsert.add(ou);
                }
            }
            log.info("YYB 需要去新增的:" + needToInsert.size());
            //对于已存在的进行批量更新,对于未存在的进行插入
            if (!CollectionUtils.isEmpty(needToInsert)){
                List<DictionaryEntity> dictionaryEntities = packYyb(needToInsert);
                dictionaryEntityMapper.saveAll(dictionaryEntities);
            }
            log.info("YYB 需要去更新的:" + needToupdate.size());
            if (!CollectionUtils.isEmpty(needToupdate)){
                dictionaryEntityMapper.updateAll(needToupdate);
            }
            addOrUpdate(id_yyb,false);
        }


        log.info("sync YYB finished......");
    }


    private void addOrUpdate(String id, boolean add) {
        String value = DateUtil.dateToString(new Date(), DateUtil.YYYYMMDDHMS);
        TbConstant tbConstant = new TbConstant(id, value);
        if (add){
            tbConstantMapper.insert(tbConstant);
        }else {
            tbConstantMapper.updateById(tbConstant);
        }

    }

    //    @Scheduled(cron = "0 0/1 * * * ? ")
    public void tt() throws ExecutionException, InterruptedException {
        elasticsearchTemplate.deleteIndex(index);
//        UpdateRequest req = new UpdateRequest();
//        req.index(index);
//        req.type("clientinfo");
//        req.id("98829848");
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "褚鹏飞999");
//        map.put("dt", "999");
//        req.doc(map);
//        UpdateResponse res = elasticsearchTemplate.getClient().update(req).get();
//        log.info("更新完成");
//        List<UpdateQuery> list = new ArrayList<>();
//        list.add(new UpdateQueryBuilder().withUpdateRequest(req).build());
//        elasticsearchTemplate.bulkUpdate(list);
        log.info("更新完成");

    }

    private int start = 0;
    private int end = 0;
    private String cd = "";
    private String ct = "";

    //同步客户  对于数据量很大的话,那就多跑几次定时任务 间隔缩小
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void syncClient() throws ExecutionException, InterruptedException {
        //先查更新记录是否存在
        TbConstant client = tbConstantMapper.selectById(id_client);
        //不存在的话就进行新增
        if (Objects.isNull(client) || StringUtils.isEmpty(client.getCurrentValue())){
            log.info("Client 信息开始新增......");
            //索引是否存在
            boolean b = elasticsearchTemplate.indexExists(index);
            if (!b){
                elasticsearchTemplate.createIndex(index);
                elasticsearchTemplate.createIndex(ClientInfo.class);
            }

            int start = 0;
            int end = 0;
            int page = 1;
            int pageSize = 1000;
            boolean flag = true;
            List<OuCustInfoA> as = new ArrayList<>();
            while (flag){
                start = (page -1)*pageSize + 1;
                end = page*pageSize;
                log.info("page=" + page + "  start=" + start + "  end=" + end);
                page ++;
                as = ouCustInfoAMapper.selectPage(start, end, "", "");
                log.info("查出的as的大小: " + as.size());
                if (!CollectionUtils.isEmpty(as) && as.size() == pageSize){
                }else {
                    flag = false;
                    log.info("这是最后一次");
                }
                //封装数据
                List<ClientInfo> cs = packClient(as);
                clientInfoRepository.saveAll(cs);
            }

            //更新记录
            addOrUpdate(id_client, true);
            log.info("Client 信息新增完成......");
        }else {     //更新
            log.info("Client 信息开始更新......");
            //更新
            String lsteUpdateTime = client.getCurrentValue();
            //截取日期  时间
            String date = lsteUpdateTime.substring(0,8);
            String time = lsteUpdateTime.substring(8);
            log.info("Client 日期:" + date + " 时间:" + time);
            OuCustInfoA ouCustInfoA = new OuCustInfoA(date,time);
            //这里是增量的
            List<OuCustInfoA> incres = ouCustInfoAMapper.all(ouCustInfoA);
            List<String> codes = getCodes(incres);
            log.info("Client 增量的size:" + codes.size());

            List<ClientInfo> needToInsert = new ArrayList<>();
            List<ClientInfo> needToupdate = new ArrayList<>();

            QueryBuilder queryBuilder = QueryBuilders.termsQuery("code",codes);
            //elastic根据code进行查询
            Iterable<ClientInfo> existed = clientInfoRepository.search(queryBuilder);
            //查到已经存在的
            List<String> existedCodes = getExistedCodes(existed);
            if (!CollectionUtils.isEmpty(existedCodes)){
                for (OuCustInfoA ou :incres){
                    if (existedCodes.contains(ou.getCustNo())){
                        needToupdate.add(new ClientInfo(ou.getCustNo(),ou.getCustName(),ou.getCreateDate()+ou.getCreateTime()));
                    }else {
                        needToInsert.add(new ClientInfo(ou.getCustNo(),ou.getCustName(),ou.getCreateDate()+ou.getCreateTime()));
                    }
                }
            }else {     //什么都不存在,那就全部新增
                for (OuCustInfoA ou :incres){
                    needToInsert.add(new ClientInfo(ou.getCustNo(),ou.getCustName(),ou.getCreateDate()+ou.getCreateTime()));
                }
            }
            if (!CollectionUtils.isEmpty(needToInsert)){
                log.info("Client 新增size:" + needToInsert.size());
                clientInfoRepository.saveAll(needToInsert);
            }
            //对于elastic的数据进行更新
            if (!CollectionUtils.isEmpty(needToupdate)){
                log.info("Client 更新size:" + needToupdate.size());
                for (ClientInfo c : needToupdate){
                    UpdateRequest req = new UpdateRequest();
                    req.index(index);
                    req.type("clientinfo");
                    req.id(c.getCode());
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", c.getName());
                    map.put("createDateTime", c.getCreateDateTime());
                    req.doc(map);
                    UpdateResponse res = elasticsearchTemplate.getClient().update(req).get();
                }
            }
            //更新上一次的更新时间
            addOrUpdate(id_client,false);

        }
        log.info("sync Client finished......");

    }


    //页面的排序是根据subitem_sort来排序的
    private List<DictionaryEntity> packYyb(List<OuBranchInfoA> all) {
        List<DictionaryEntity> list = new ArrayList<>();
        Integer subitem = dictionaryEntityMapper.querySubitemSort();
        if (null == subitem){
            subitem = 0;
        }
        for (OuBranchInfoA branchInfoA : all){
            subitem++;
            DictionaryEntity dictionary = new DictionaryEntity(branchInfoA.getBranchCode(),branchInfoA.getBranchName(),subitem);
            list.add(dictionary);
        }


        return list;
    }


    private List<ClientInfo> packClient(List<OuCustInfoA> all) {
        List<ClientInfo> list = new ArrayList<>();

        for (OuCustInfoA ou : all){
            ClientInfo c = new ClientInfo(ou.getCustNo(),ou.getCustName(),ou.getCreateDate()+ou.getCreateTime());
            list.add(c);
        }

        return list;
    }

    private List<String> getCodes(List<OuCustInfoA> incres){
        List<String> codes = new ArrayList<>();
        for (OuCustInfoA ou : incres){
            String code = ou.getCustNo();
            codes.add(code);
        }
        return codes;
    }

    private List<String> getExistedCodes(Iterable<ClientInfo> incres){
        List<String> codes = new ArrayList<>();
        for (ClientInfo c : incres){
            String code = c.getCode();
            codes.add(code);
        }
        return codes;
    }


    @Resource
    private OuBranchInfoAMapper ouBranchInfoAMapper;

    //    @Scheduled(cron = "0/20 * * * * ? ")
    @Transactional("transactionManagerTwo")
    //以下存在多个的话 指定那个事务就只有哪个起作用
    public void test(){
        DictionaryEntity entity = new DictionaryEntity();
        entity.setId("9eedd7a012fc49f4b0002822ae3bb22f");
        entity.setValue("update");
        dictionaryEntityMapper.updateById(entity);

        OuBranchInfoA ou = new OuBranchInfoA();
        ou.setBranchCode("722");
        ou.setBranchName("台州椒江区中山东路证券营业部update");
        UpdateWrapper<OuBranchInfoA> wrapper = new UpdateWrapper<>();
        wrapper.eq("BRANCH_CODE","722");
        ouBranchInfoAMapper.update(ou,wrapper);
        int i =10/0;
        log.info("test finished......");
    }

}

