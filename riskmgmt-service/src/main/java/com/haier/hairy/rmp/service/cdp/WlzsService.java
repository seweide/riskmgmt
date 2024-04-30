package com.haier.hairy.rmp.service.cdp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haier.hairy.credit.response.wanlian.RiskWarningResponse;
import com.haier.hairy.credit.response.wanlian.dto.ListDto;
import com.haier.hairy.credit.response.wanlian.dto.Riskdesc;
import com.haier.hairy.credit.response.wanlian.dto.Risklist;
import com.haier.hairy.rmp.config.OrikaConfig;
import com.haier.hairy.rmp.dto.hry.HryResp;
import com.haier.hairy.rmp.entity.WlzsWarningDetailEntity;
import com.haier.hairy.rmp.entity.WlzsWarningListEntity;
import com.haier.hairy.rmp.entity.WlzsWarningSumEntity;
import com.haier.hairy.rmp.service.IWlzsWarningDetailService;
import com.haier.hairy.rmp.service.IWlzsWarningListService;
import com.haier.hairy.rmp.service.IWlzsWarningSumService;
import com.haier.hairy.rmp.util.HryGatewayUtils;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 万链指数服务
 * @Author: Xie Hao
 * @Create: 2019-07-24 17:13
 **/
@Slf4j
@Service
public class WlzsService {
    private static final String CDP_RESP_CODE_SUCCESS = "0";
    private static final String CHANNEL = "wlzs";
    private static final String API_RISK_WARNING = "wlzs.risk.warning";
    private static final String SERVICE_RISK_WARNING = "wanlianFacade.riskWarning";

    private static final String COMM_PROP_REPORT_DATE = "reportDate";
    private static final String COMM_PROP_CORP_NAME = "corpName";

    @Autowired
    private IWlzsWarningSumService wlzsWarningSumService;
    @Autowired
    private IWlzsWarningListService wlzsWarningListService;
    @Autowired
    private IWlzsWarningDetailService wlzsWarningDetailService;

    /**
     * 访问万链接口获取风险预警信息
     * @param corpName 企业名称
     * @return
     */
    @Retryable(backoff = @Backoff(delay=5000, maxDelay=50000, multiplier=3))
    public RiskWarningResponse riskWarning(String corpName) throws Exception{
        RiskWarningResponse resp = new RiskWarningResponse();
        Map<String, String> bizData = new HashMap<>(3);
        bizData.put("channel", CHANNEL);
        bizData.put("api", API_RISK_WARNING);
        bizData.put("corpName", corpName);
        Map<String, String> paramMap = new HashMap<>(2);
        paramMap.put("bizdata", JSON.toJSONString(bizData));
        paramMap.put("service", SERVICE_RISK_WARNING);
        HryResp hryResp = HryGatewayUtils.call(paramMap);
        if(CDP_RESP_CODE_SUCCESS.equals(hryResp.getCode())){
            resp = JSON.parseObject(hryResp.getBizJson(), RiskWarningResponse.class);
            if(!resp.isSuccess()){
                log.warn("HRY-GATEWAY-CDP请求失败，企业名：{} 原因：{}-{}",
                        corpName, resp.getStatus(), resp.getMessage());
            }
        }else{
            resp.setStatus(1);
            resp.setMessage("HRY-GATEWAY-CDP请求失败:" + corpName);
            log.warn("HRY-GATEWAY-CDP请求失败，企业名：{}，原因：{}", corpName, hryResp.getMsg());
        }
        return resp;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveRiskInfo(RiskWarningResponse riskWarningResp, String corpName, Date date){
        MappingContext context = new MappingContext.Factory().getContext();
        context.setProperty(COMM_PROP_REPORT_DATE, date);
        context.setProperty(COMM_PROP_CORP_NAME, corpName);

        MapperFacade mapper = OrikaConfig.getMapper();
        WlzsWarningSumEntity wlzsWarningSumEntity
                = mapper.map(riskWarningResp.getCount(), WlzsWarningSumEntity.class, context);
        wlzsWarningSumService.save(wlzsWarningSumEntity);

        List<Riskdesc> riskdescList = riskWarningResp.getRiskdesc();
        if(riskdescList != null && riskdescList.size() != 0){
            List<WlzsWarningListEntity> wlzsWarningListEntityList =
                    mapper.mapAsList(riskdescList, WlzsWarningListEntity.class, context);
            wlzsWarningListService.saveBatch(wlzsWarningListEntityList);
        }

        List<Risklist> risklists = riskWarningResp.getRisklist();
        if(risklists != null && risklists.size() != 0){
            List<ListDto> risklistList =  new ArrayList<>();
            risklists.stream().forEach(riskDetail -> risklistList.addAll(riskDetail.getList()));
            List<WlzsWarningDetailEntity> wlzsWarningDetailEntityList =
                    mapper.mapAsList(risklistList, WlzsWarningDetailEntity.class, context);
            wlzsWarningDetailService.saveBatch(wlzsWarningDetailEntityList);
        }

    }

    /**
     * 处理重复数据
     * @param reportDate
     */
    @Transactional(rollbackFor = {Exception.class})
    public void handleDuplicateData(Date reportDate) {
        QueryWrapper<WlzsWarningSumEntity> sumWrapper = new QueryWrapper<>();
        sumWrapper.lambda().eq(WlzsWarningSumEntity::getReportDate, reportDate);
        wlzsWarningSumService.remove(sumWrapper);

        QueryWrapper<WlzsWarningListEntity> listWrapper = new QueryWrapper<>();
        listWrapper.lambda().eq(WlzsWarningListEntity::getReportDate, reportDate);
        wlzsWarningListService.remove(listWrapper);

        QueryWrapper<WlzsWarningDetailEntity> detailWrapper = new QueryWrapper<>();
        detailWrapper.lambda().eq(WlzsWarningDetailEntity::getReportDate, reportDate);
        wlzsWarningDetailService.remove(detailWrapper);
    }
}
