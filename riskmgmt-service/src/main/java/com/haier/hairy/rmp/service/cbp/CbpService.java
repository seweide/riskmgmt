package com.haier.hairy.rmp.service.cbp;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.cbp.biz.limit.dto.message.RCTLimitResMessage;
//import com.cbp.biz.limit.dto.message.RCTLimitVo;
//import com.cbp.biz.loan.dto.message.RCTLoanResMessage;
//import com.cbp.biz.loan.dto.message.RCTLoanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 信贷系统服务接口
 * @Author: Xie Hao
 * @Create: 2019-07-25 17:30
 **/
@Slf4j
@Service
public class CbpService {

    private static final String CONTENT_TYPE = "application/json";
    private static final String CBP_RESP_CODE_SUCCESS = "000";
    private static final String CBP_RS_ALL_LIMIT = "com.cbp.biz.limit.facade.rs.LimitResource/queryLimitByRCT";
    private static final String CBP_RS_ALL_LOAN = "com.cbp.biz.loan.facade.rs.LoanResource/queryLoanDetailByRCT";

    @Value("${cbp.facade.url}")
    private String cbpUrl;

//    public List<RCTLimitVo> queryLimitByRCT(){
//        RCTLimitResMessage resp = process(CBP_RS_ALL_LIMIT, RCTLimitResMessage.class);
//        if(CBP_RESP_CODE_SUCCESS.equals(resp.getRepCode())){
//            return resp.getList();
//        }
//        return null;
//    }

//    public List<RCTLoanVo> queryLoanDetailByRCT(){
//        RCTLoanResMessage resp = process(CBP_RS_ALL_LOAN, RCTLoanResMessage.class);
//        if(CBP_RESP_CODE_SUCCESS.equals(resp.getRepCode())){
//            return resp.getList();
//        }
//        return null;
//    }

    private <T> T process(String resourceUrl, Class handleClz){
        return (T) process(resourceUrl, null, handleClz);
    }

    private <T> T process(String resourceUrl, Map<String, String> params, Class handleClz){
        String respBodyStr = HttpUtil.createPost(cbpUrl + resourceUrl)
                .contentType(CONTENT_TYPE)
                .body(JSON.toJSONString(params))
                .execute().body();
        return (T)JSON.parseObject(respBodyStr, handleClz);
    }
}
