package com.haier.hairy.rmp.schedule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.cbp.biz.limit.dto.message.RCTLimitVo;
//import com.cbp.biz.loan.dto.message.RCTLoanVo;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.haier.hairy.common.apollo.ApolloConfigContext;
import com.haier.hairy.credit.response.wanlian.RiskWarningResponse;
import com.haier.hairy.mts.response.SendEmailResponse;
import com.haier.hairy.rmp.config.CommConfig;
import com.haier.hairy.rmp.config.OrikaConfig;
import com.haier.hairy.rmp.dto.EmailReq;
import com.haier.hairy.rmp.dto.excel.WlzsWarningDetailRow;
import com.haier.hairy.rmp.dto.excel.WlzsWarningListRow;
import com.haier.hairy.rmp.dto.excel.WlzsWarningSumRow;
import com.haier.hairy.rmp.entity.TaskRecordEntity;
import com.haier.hairy.rmp.entity.WlzsWarningDetailEntity;
import com.haier.hairy.rmp.entity.WlzsWarningListEntity;
import com.haier.hairy.rmp.entity.WlzsWarningSumEntity;
import com.haier.hairy.rmp.enums.NoticeStatusEnum;
import com.haier.hairy.rmp.enums.ReportTypeEnum;
import com.haier.hairy.rmp.enums.TaskStatusEnum;
import com.haier.hairy.rmp.service.*;
import com.haier.hairy.rmp.service.cbp.CbpService;
import com.haier.hairy.rmp.service.cdp.WlzsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 万链指数预警任务
 *
 * @author Xie hao
 * @since 2019-07-22
 */
@Slf4j
@Component
public class WlzsWarningJob {

//    public static CountDownLatch countDownLatch;
//
//    @Autowired
//    private CbpService cbpService;
//    @Autowired
//    private WlzsService wlzsService;
//    @Autowired
//    private IWlzsWarningSumService wlzsWarningSumService;
//    @Autowired
//    private IWlzsWarningListService wlzsWarningListService;
//    @Autowired
//    private IWlzsWarningDetailService wlzsWarningDetailService;
//    @Resource
//    private EmailService emailService;
//    @Autowired
//    private ITaskRecordService taskRecordService;
//
//
//    @Scheduled(cron = "0 0 3 * * ?")
//    public void execute() {
//        log.info("定时任务开始......");
//        Date startTime = new Date();
//        Date reportDate = DateUtil.beginOfDay(startTime);
//
//        //1. 任务信息落库
//        TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
//        taskRecordEntity.setReportDate(reportDate);
//        taskRecordEntity.setReportType(ReportTypeEnum.WLZS_WARNING.getCode());
//        taskRecordEntity.setStartTime(startTime);
//        taskRecordEntity.setTaskStatus(TaskStatusEnum.PROCESSING.getCode());
//        taskRecordService.save(taskRecordEntity);
//
//        //2. 获取经销商授信信息和借据信息
//        List<RCTLimitVo> dealerList = cbpService.queryLimitByRCT();
//        if (dealerList == null || dealerList.size() == 0) {
//            log.warn("信贷接口查询无数据，任务退出");
//            return;
//        }
//        Map<String, String> statusMap = new ConcurrentHashMap<>(dealerList.size());
//        log.info("通过CBD接口获取经销商授信信息共计{}条", dealerList.size());
//
//	   dealerList.parallelStream().forEach(f ->  log.info("经销商授信信息-企业名称："+f.getCompanyName()));
//
//        List<RCTLoanVo> loanList = cbpService.queryLoanDetailByRCT();
//        if (loanList == null || loanList.size() == 0) {
//            log.warn("信贷接口查询无数据");
//        } else {
//            log.info("通过CBD接口获取经销商贷款信息共计{}条", loanList.size());
//        }
//
//        //3. 万链指数预警数据重复性检测,如果重复，将同日期的数据逻辑删除
//        wlzsService.handleDuplicateData(reportDate);
//
//        //4. 创建线程，获取万链指数信息并入库保存
//        countDownLatch = new CountDownLatch(dealerList.size());
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("thread-wlzs-runner-%d").build();
//        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 0L,
//                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
//        log.info("异步访问万链指数风险预警接口开始，共计{}个任务", dealerList.size());
//        for (RCTLimitVo dealer : dealerList) {
//            executorService.submit(new WlzsRiskWarningTask(dealer.getCompanyName(), reportDate, statusMap));
//        }
//
//        try {
//            countDownLatch.await(600L, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            log.info("异步访问万链接口等待全部执行完毕异常", e);
//        }
//
//        log.info("万链数据预警接口访问汇总统计-暂无预警：{}", statusMap.entrySet().stream()
//                .filter(entry -> entry.getValue().equals("1")).map(x -> x.getKey()).collect(Collectors.toList()));
//        log.info("万链数据预警接口访问汇总统计-业务异常：{}", statusMap.entrySet().stream()
//                .filter(entry -> entry.getValue().equals("2")).map(x -> x.getKey()).collect(Collectors.toList()));
//        log.info("万链数据预警接口访问汇总统计-系统异常：{}", statusMap.entrySet().stream()
//                .filter(entry -> entry.getValue().equals("3")).map(x -> x.getKey()).collect(Collectors.toList()));
//
//        //5. 汇总信息生成Excel
//        String excelFilePath = genRiskWarningExcel(reportDate, dealerList, loanList);
//        Date endTime = new Date();
//        taskRecordEntity.setEndTime(endTime);
//        taskRecordEntity.setTaskStatus(TaskStatusEnum.SUCCESS.getCode());
//        taskRecordService.saveOrUpdate(taskRecordEntity);
//
//        //6. 发送预警邮件
//        SendEmailResponse emailResponse = emailService.send(EmailReq.builder()
//                .subject("风控预警")
//                .content("RIS风控预警-万链指数")
//                .attachmentFilePathList(CollUtil.newArrayList(excelFilePath))
//                .emailAddrs(ApolloConfigContext.getConfig("warning.email.to.address"))
//                .requestNo(StringUtils.leftPad(taskRecordEntity.getId().toString(), 10, "0"))
//                .build()
//        );
//
//        taskRecordEntity.setNoticeStatus(
//                emailResponse.isSuccess() ? NoticeStatusEnum.SUCCESS.getCode() : NoticeStatusEnum.FAILURE.getCode());
//        taskRecordEntity.setNoticeTime(new Date());
//        taskRecordService.saveOrUpdate(taskRecordEntity);
//
//        executorService.shutdown();
//        log.info("定时任务结束......");
//    }
//
//    /**
//     * 生成风控预警Excel文件
//     *
//     * @param reportDate 报告日期
//     * @param dealerList 经销商授信信息
//     * @param loanList   经销商贷款订单信息
//     * @return
//     */
//    private String genRiskWarningExcel(Date reportDate, List<RCTLimitVo> dealerList, List<RCTLoanVo> loanList) {
//        Map<String, RCTLimitVo> dealerMap = dealerList.stream()
//                .collect(Collectors.toMap(RCTLimitVo::getCompanyName, a -> a, (k1, k2) -> k1));
//        Map<String, List<RCTLoanVo>> loanMap = loanList.stream()
//                .collect(Collectors.groupingBy(RCTLoanVo::getCompanyName));
//
//        //第一个tab汇总页数据准备
//        QueryWrapper<WlzsWarningSumEntity> sumWrapper = new QueryWrapper<>();
//        sumWrapper.lambda().eq(WlzsWarningSumEntity::getReportDate, reportDate);
//        List<WlzsWarningSumEntity> wlzsWarningSumEntityList = wlzsWarningSumService.list(sumWrapper);
//        List<WlzsWarningSumRow> sumSheetRowList = new ArrayList<>(wlzsWarningSumEntityList.size());
//        for (WlzsWarningSumEntity sumEntity : wlzsWarningSumEntityList) {
//            String corpName = sumEntity.getCorpName();
//            RCTLimitVo dealer = dealerMap.get(corpName);
//            WlzsWarningSumRow row = WlzsWarningSumRow.builder()
//                    .corpName(corpName)
//                    .creditApplyStatus(dealer.getApplyStatus())
//                    .hightRiskCnt(sumEntity.getHightRiskCnt())
//                    .middleRiskCnt(sumEntity.getMiddleRiskCnt())
//                    .lowRiskCnt(sumEntity.getLowRiskCnt())
//                    .build();
//            List<RCTLoanVo> dealerLoanList = loanMap.get(corpName);
//            if (dealerLoanList != null && dealerLoanList.size() != 0) {
//                dealerLoanList.sort(Comparator.comparing(RCTLoanVo::getLoanCode));
//                String loanListStr = dealerLoanList.stream()
//                        .map(loan -> loan.getLoanCode() + loan.getLoanStatus())
//                        .collect(Collectors.joining("// "));
//                row.setLoanListStr(loanListStr);
//            }
//            sumSheetRowList.add(row);
//        }
//
//        //第二个tab列表页数据装备
//        QueryWrapper<WlzsWarningListEntity> listWrapper = new QueryWrapper<>();
//        listWrapper.lambda().eq(WlzsWarningListEntity::getReportDate, reportDate);
//        List<WlzsWarningListEntity> wlzsWarningListEntityList = wlzsWarningListService.list(listWrapper);
//        List<WlzsWarningListRow> listSheetRowList = new ArrayList<>(wlzsWarningListEntityList.size());
//        wlzsWarningListEntityList.stream().forEach(entity ->
//                listSheetRowList.add(OrikaConfig.getMapper().map(entity, WlzsWarningListRow.class))
//        );
//
//
//        //第三个tab详情页数据准备
//        QueryWrapper<WlzsWarningDetailEntity> detailWrapper = new QueryWrapper<>();
//        detailWrapper.lambda().eq(WlzsWarningDetailEntity::getReportDate, reportDate);
//        List<WlzsWarningDetailEntity> wlzsWarningDetailEntityList = wlzsWarningDetailService.list(detailWrapper);
//        List<WlzsWarningDetailRow> detailSheetRowList = new ArrayList<>(wlzsWarningDetailEntityList.size());
//        wlzsWarningDetailEntityList.stream().forEach(entity ->
//                detailSheetRowList.add(OrikaConfig.getMapper().map(entity, WlzsWarningDetailRow.class))
//        );
//
//        //写入excel文件
//        //String fileName = "万链数据风险预警" + DatePattern.NORM_DATE_FORMAT.format(reportDate) + ".xlsx";
//        String fileName = "WanlianRiskWarning" + DatePattern.NORM_DATE_FORMAT.format(reportDate) + ".xlsx";
//        String fileFullPath = CommConfig.EXCEL_PATH + fileName;
//        log.info("生成万链指数预警文件：{}", fileFullPath);
//
//        try (OutputStream excelOut = new FileOutputStream(fileFullPath)) {
//            ExcelWriter writer = new ExcelWriter(excelOut, ExcelTypeEnum.XLSX);
//            Sheet sheet1 = new Sheet(1, 0, WlzsWarningSumRow.class);
//            sheet1.setSheetName("预警汇总");
//            sheet1.setColumnWidthMap(WlzsWarningSumRow.COL_WIDTH_MAP);
//            writer.write(sumSheetRowList, sheet1);
//            sheet1.getTableStyle();
//
//            Sheet sheet2 = new Sheet(2, 0, WlzsWarningListRow.class);
//            sheet2.setSheetName("预警列表");
//            sheet2.setColumnWidthMap(WlzsWarningListRow.COL_WIDTH_MAP);
//            writer.write(listSheetRowList, sheet2);
//
//            Sheet sheet3 = new Sheet(3, 0, WlzsWarningDetailRow.class);
//            sheet3.setSheetName("预警详情");
//            sheet3.setColumnWidthMap(WlzsWarningDetailRow.COL_WIDTH_MAP);
//            writer.write(detailSheetRowList, sheet3);
//
//            writer.finish();
//        } catch (Exception e) {
//            log.info("生成万链指数预警文件异常", e);
//        }
//
//        return fileFullPath;
//    }
//
//    /**
//     * 单个企业名称查询访问万链指数预警接口异步任务
//     */
//    private class WlzsRiskWarningTask implements Runnable {
//
//        private String corpName;
//        private Date reportDate;
//        private Map<String, String> statusMap;
//
//        public WlzsRiskWarningTask(String corpName, Date reportDate, Map<String, String> statusMap) {
//            this.corpName = corpName;
//            this.reportDate = reportDate;
//            this.statusMap = statusMap;
//        }
//
//        @Override
//        public void run() {
//            log.debug("进入异步任务:{}", corpName);
//            try {
//                RiskWarningResponse wlzsRiskWarning = wlzsService.riskWarning(corpName);
//                if (!wlzsRiskWarning.isSuccess()) {
//                    statusMap.put(corpName, "2");
//                    log.warn("万链指数预警-返回异常，跳过此企业[{}]继续执行", corpName);
//                    return;
//                }
//
//                if (wlzsRiskWarning.getRiskdesc() == null || wlzsRiskWarning.getRiskdesc().size() == 0) {
//                    statusMap.put(corpName, "1");
//                    log.info("万链指数预警-此企业[{}], 无预警信息", corpName);
//                    return;
//                }
//
//                wlzsService.saveRiskInfo(wlzsRiskWarning, corpName, reportDate);
//            } catch (Exception e) {
//                statusMap.put(corpName, "3");
//                log.warn("万链指数预警异常，跳过此企业[{}]继续执行", corpName, e);
//            } finally {
//                log.debug("结束异步任务:{} countDownLatch:{}", corpName, countDownLatch.getCount());
//                countDownLatch.countDown();
//            }
//        }
//    }
}
