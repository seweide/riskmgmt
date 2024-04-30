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
import com.haier.hairy.common.apollo.ApolloConfigContext;
import com.haier.hairy.credit.response.wanlian.RiskWarningResponse;
import com.haier.hairy.mts.response.SendEmailResponse;
import com.haier.hairy.rmp.config.CommConfig;
import com.haier.hairy.rmp.config.OrikaConfig;
import com.haier.hairy.rmp.dto.EmailReq;
import com.haier.hairy.rmp.dto.excel.DtsWarningSumRow;
import com.haier.hairy.rmp.entity.*;
import com.haier.hairy.rmp.enums.NoticeStatusEnum;
import com.haier.hairy.rmp.enums.ReportTypeEnum;
import com.haier.hairy.rmp.enums.TaskStatusEnum;
import com.haier.hairy.rmp.service.*;
import com.haier.hairy.rmp.service.cbp.CbpService;
import com.haier.hairy.rmp.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 万链指数预警任务
 *
 * @author Xie hao
 * @since 2019-07-22
 */
@Slf4j
@Component
public class DTSWarningJob {

    private static final String HIT = "命中";
    private static final String MISS = "未命中";

    @Autowired
    private CbpService cbpService;
    @Resource
    private EmailService emailService;
    @Autowired
    private ITaskRecordService taskRecordService;
    @Autowired
    private IDtsMonthSumService dtsMonthSumService;
    @Autowired
    private IDtsWarningSumService dtsWarningSumService;


    /**
	* "0 0 3 10 * ?" 每月10日上午03:00触发
	*/
//    @Scheduled(cron = "0 0 3 10 * ?")
//    public void execute() {
//        log.info("定时任务开始......");
//        Date startTime = new Date();
//        Date reportDate = DateUtil.beginOfDay(startTime);
//
//	   //1. 任务信息落库
//        TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
//        taskRecordEntity.setReportDate(reportDate);
//        taskRecordEntity.setReportType(ReportTypeEnum.DTS_WARNING.getCode());
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
//        log.info("通过CBD接口获取经销商授信信息共计{}条", dealerList.size());
//
//	   dealerList.parallelStream().forEach(f ->  log.info("经销商授信信息-企业名称："+f.getCompanyName()));
//
//        //3,通过CBD接口获取经销商贷款信息
//	   List<RCTLoanVo> loanList = cbpService.queryLoanDetailByRCT();
//	   if (loanList == null || loanList.size() == 0) {
//		  log.warn("信贷接口查询无数据");
//	   } else {
//		  log.info("通过CBD接口获取经销商贷款信息共计{}条", loanList.size());
//	   }
//
//        //4. 汇总信息生成Excel
//        String excelFilePath = genDTSRiskWarningExcel(reportDate, dealerList,loanList);
//        Date endTime = new Date();
//        taskRecordEntity.setEndTime(endTime);
//        taskRecordEntity.setTaskStatus(TaskStatusEnum.SUCCESS.getCode());
//        taskRecordService.saveOrUpdate(taskRecordEntity);
//
//        //5. 发送预警邮件
//	   log.info("发送预警邮件{}",ApolloConfigContext.getConfig("warning.email.to.address"));
//        SendEmailResponse emailResponse = emailService.send(EmailReq.builder()
//                .subject("风控预警")
//                .content("DTS风控预警-万链指数")
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
//        log.info("定时任务结束......");
//    }

    /**
	* 生成风控预警Excel文件
	* @param reportDate 报告日期
	* @param dealerList 经销商授信信息
	* @return
	*/
//    private String genDTSRiskWarningExcel(Date reportDate, List<RCTLimitVo> dealerList, List<RCTLoanVo> loanList) {
//        Map<String, RCTLimitVo> dealerMap = dealerList.stream()
//                .collect(Collectors.toMap(RCTLimitVo::getCompanyName, a -> a, (k1, k2) -> k1));
//	   Map<String, List<RCTLoanVo>> loanMap = loanList.stream().collect(Collectors.groupingBy(RCTLoanVo::getCompanyName));
//
//        //第一个tab汇总页数据准备
//        List<DtsWarningSumRow> sumSheetRowList = new ArrayList<>(dealerList.size());
//
//        for (RCTLimitVo dealer : dealerList) {
//            String corpName = dealer.getCompanyName();
//		  String hitRules1 = calculateExecute(corpName,1);
//		  String hitRules2 = calculateExecute(corpName,2);
//		  String hitRules3 = calculateExecute(corpName,3);
//		  DtsWarningSumRow row = DtsWarningSumRow.builder()
//                    .corpName(corpName)
//				.hitRules1(hitRules1)
//				.hitRules2(hitRules2)
//				.hitRules3(hitRules3)
//                    .creditApplyStatus(dealer.getApplyStatus())
//                    .build();
//
//		  List<RCTLoanVo> dealerLoanList = loanMap.get(corpName);
//		  if (dealerLoanList != null && dealerLoanList.size() != 0) {
//			 dealerLoanList.sort(Comparator.comparing(RCTLoanVo::getLoanCode));
//			 String loanListStr = dealerLoanList.stream()
//				    .map(loan -> loan.getLoanCode() + loan.getLoanStatus())
//				    .collect(Collectors.joining("// "));
//			 row.setLoanListStr(loanListStr);
//		  }
//            sumSheetRowList.add(row);
//		  //封装落地数据
//		  DtsWarningSumEntity entity = new DtsWarningSumEntity();
//		  entity.setReportDate(reportDate);
//		  entity.setCorpName(corpName);
//		  entity.setHitRules1(hitRules1);
//		  entity.setHitRules2(hitRules2);
//		  entity.setHitRules3(hitRules3);
//		  dtsWarningSumService.save(entity);
//        }
//
//        //写入excel文件
//        String fileName = "DTSRiskWarning" + DatePattern.NORM_DATE_FORMAT.format(reportDate) + ".xlsx";
//        String fileFullPath = CommConfig.EXCEL_PATH + fileName;
//        log.info("生成DTS指数预警文件：{}", fileFullPath);
//
//        try (OutputStream excelOut = new FileOutputStream(fileFullPath)){
//            ExcelWriter writer = new ExcelWriter(excelOut, ExcelTypeEnum.XLSX);
//            Sheet sheet1 = new Sheet(1, 0, DtsWarningSumRow.class);
//            sheet1.setSheetName("预警汇总");
//            sheet1.setColumnWidthMap(DtsWarningSumRow.COL_WIDTH_MAP);
//            writer.write(sumSheetRowList, sheet1);
//            sheet1.getTableStyle();
//
//            writer.finish();
//        } catch (Exception e) {
//            log.info("生成DTS指数预警文件异常", e);
//        }
//
//        //执行落库处理
//
//
//        return fileFullPath;
//    }

    /**
	* 预警判断规则
	* @param corpName
	* @param type
	* @return
	*/
    private String calculateExecute(String corpName,int type){
	   //根据企业名称获取所有DTS存量数据：
	   QueryWrapper<DtsMonthSumEntity> sumWrapper = new QueryWrapper<>();
	   sumWrapper.lambda().like(DtsMonthSumEntity::getKhmc, corpName);
	   List<DtsMonthSumEntity> dtsCorpNameList = dtsMonthSumService.list(sumWrapper);
	   if (dtsCorpNameList == null || dtsCorpNameList.size() == 0) {
		  log.warn("DTS存量接口查询无数据");
	   } else {
		  log.info("获取DTS存量数据信息共计{}条", dtsCorpNameList.size());
	   }

	   String result = MISS;

	   if (type == 1) {
		  for (DtsMonthSumEntity data:dtsCorpNameList){
			 //（a）	该客户提交信贷申请当月的最近1个月的提货金额等于“0”时；注：T-1月提货金额=0。
			 if (getTYM(0,1).equals(data.getRq())){
			      if (data.getThje().compareTo(BigDecimal.ZERO) == 0) {
					result = HIT;
				 }
			 }
		  }
	   }
	   if (type == 2) {
	       //本年6个月合计提货金额
		  int intiM = 1;
		  BigDecimal deliverySum = BigDecimal.ZERO;
		  BigDecimal lastYDeliverySum = BigDecimal.ZERO;
		  String[] mArray = new String[6];
		  String[] lastMArray = new String[6];
		  for (int i = 0;i < 6 ; i++){
			 mArray[i] = getTYM(0,intiM);
			 lastMArray[i] = getTYM(1,intiM);
			 intiM++;
		  }

		  for (DtsMonthSumEntity data:dtsCorpNameList){
			 if (Arrays.asList(mArray).contains(data.getRq()) && null != data.getThje()) {
				deliverySum = deliverySum.add(data.getThje());
			 }
		  }
		  for (DtsMonthSumEntity data:dtsCorpNameList){
			 if (Arrays.asList(lastMArray).contains(data.getRq()) && null != data.getThje()) {
				lastYDeliverySum = lastYDeliverySum.add(data.getThje());
			 }

		  }
		  if (deliverySum.compareTo(BigDecimal.ZERO) > 0 && lastYDeliverySum.compareTo(BigDecimal.ZERO) > 0 ) {
			 double abs = (deliverySum.subtract(lastYDeliverySum)).divide(lastYDeliverySum, 2, RoundingMode.HALF_UP).doubleValue();
			 if (format(deliverySum,lastYDeliverySum) <= -0.25) {
				result = HIT;
			 }
		  } else if(deliverySum.compareTo(BigDecimal.ZERO) == 0 && lastYDeliverySum.compareTo(BigDecimal.ZERO) > 0 ) {
			 result = HIT;
		  }
	   }
	   if (type == 3) {
		  //本年6个月合计提货金额
		  int intiM = 1;
		  BigDecimal deliverySum = BigDecimal.ZERO;
		  BigDecimal lastYDeliverySum = BigDecimal.ZERO;
		  String[] yArray = new String[12];
		  String[] lastYArray = new String[12];
		  for (int i = 0;i < 12 ; i++){
			 yArray[i] = getTYM(0,intiM);
			 yArray[i] = getTYM(1,intiM);
			 intiM++;
		  }

		  for (DtsMonthSumEntity data:dtsCorpNameList){
			 if (getTYM(0,intiM).equals(data.getRq()) && null != data.getThje()) {
				deliverySum = deliverySum.add(data.getThje());
			 }
		  }
		  for (DtsMonthSumEntity data:dtsCorpNameList){
			 if (getTYM(1,intiM).equals(data.getRq()) && null != data.getThje()) {
				lastYDeliverySum = lastYDeliverySum.add(data.getThje());
			 }
		  }
		  if (deliverySum.compareTo(BigDecimal.ZERO) > 0 && lastYDeliverySum.compareTo(BigDecimal.ZERO) > 0 ) {
			 double abs = (deliverySum.subtract(lastYDeliverySum)).divide(lastYDeliverySum, 2, RoundingMode.HALF_UP).doubleValue();
			 if (format(deliverySum,lastYDeliverySum) <= -0.25) {
				result = HIT;
			 }
		  } else if(deliverySum.compareTo(BigDecimal.ZERO) == 0 && lastYDeliverySum.compareTo(BigDecimal.ZERO) > 0 ) {
			 result = HIT;
		  }
	   }

	   return result;
    }

    private String getTYM(int yDate,int mDate){
	   Date startTime = new Date();
	   String getYM = DateUtils.getDateTimeString(startTime,DateUtils.MONTH_FORMAT);
        try{
		  SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");
		  //转换执行T-1月日期
		  Date currdate = sd.parse(getYM);
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(currdate);
		  calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-yDate);
		  calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-mDate);
		  getYM = sd.format(calendar.getTime());
	   } catch (ParseException e) {
		  log.info("获取T-1月月份时间异常", e);
	   }
	   return getYM;
    }

    /**
	* 返回比例的格式化值
	* @param bd1
	* @param bd2
	* @return
	*/
    private static double format(BigDecimal bd1, BigDecimal bd2) {
	   if (bd2.equals(BigDecimal.ZERO)) {
	       // 如果b是0，直接返回0
		  return 0d;
	   }
	   return (bd1.subtract(bd2)).divide(bd2, 2, RoundingMode.HALF_UP).doubleValue();
    }

}
