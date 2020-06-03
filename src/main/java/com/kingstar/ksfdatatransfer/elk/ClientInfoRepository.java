package com.kingstar.ksfdatatransfer.elk;

import com.kingstar.ksfdatatransfer.pojo.ClientInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/15 9:41
 */
@Repository
public interface ClientInfoRepository extends ElasticsearchRepository<ClientInfo, String> {
}
