package com.haier.hairy.rmp.facade.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.haier.hairy.rmp.config.OrikaConfig;
import com.haier.hairy.rmp.entity.TaskRecordEntity;
import com.haier.hairy.rmp.enums.ReportTypeEnum;
import com.haier.hairy.rmp.enums.StatusCode;
import com.haier.hairy.rmp.facade.IWarningJobFacade;
import com.haier.hairy.rmp.request.ExcuteJobReq;
import com.haier.hairy.rmp.request.QueryJobReq;
import com.haier.hairy.rmp.response.ExcuteJobResp;
import com.haier.hairy.rmp.response.JobRecord;
import com.haier.hairy.rmp.response.QueryJobResp;
import com.haier.hairy.rmp.schedule.DTSWarningJob;
import com.haier.hairy.rmp.schedule.WlzsWarningJob;
import com.haier.hairy.rmp.service.ITaskRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 预警任务接口实现
 *
 * @Author: Xie Hao
 * @Create: 2019-07-30 18:20
 **/
@Slf4j
@RestController
@Service("warningJobFacade")
public class WarningJobFacadeImpl implements IWarningJobFacade {

    private static ExecutorService executorService;

    @Autowired
    private ITaskRecordService taskRecordService;
    @Autowired
    private WlzsWarningJob wlzsWarningJob;
    @Autowired
    private DTSWarningJob dtsWarningJob;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-job-runner-%d").build();
        executorService = new ThreadPoolExecutor(2, 4, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    }

    @Override
    public ExcuteJobResp excuteJob(ExcuteJobReq request) {
        ExcuteJobResp resp = new ExcuteJobResp();
        Integer reportType = request.getReportType();
        if (!ReportTypeEnum.isExist(reportType)) {
            resp.setRet(StatusCode.PARAMETER_ERROR, "任务类型reportType必填且符合范围要求");
            return resp;
        }

        if (ReportTypeEnum.WLZS_WARNING.isEquals(reportType)) {
            executorService.submit(new WlzsWarningTask());
        }

        if (ReportTypeEnum.DTS_WARNING.isEquals(reportType)) {
		  executorService.submit(new DTSWarningTask());
	   }
        resp.setRet(StatusCode.SUCCESS, "预警任务已启动，执行中");
        return resp;
    }

    @Override
    public QueryJobResp queryJob(QueryJobReq request) {
        QueryJobResp resp = new QueryJobResp();
        Integer reportType = request.getReportType();
        if (!ReportTypeEnum.isExist(reportType)) {
            resp.setRet(StatusCode.PARAMETER_ERROR, "任务类型reportType必填且符合范围要求");
            return resp;
        }
        String reportDateStr = request.getReportDate();
        Date reportDate = null;
        if (StringUtils.isNotBlank(reportDateStr)) {
            try {
                reportDate = DateUtil.parse(reportDateStr, "yyyy-MM-dd");
            } catch (Exception e) {
                log.warn("任务日期参数错误");
            }
        }
        QueryWrapper<TaskRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TaskRecordEntity::getReportType, reportType);
        if (reportDate != null) {
            queryWrapper.lambda().eq(TaskRecordEntity::getReportDate, reportDate);
        }
        List<TaskRecordEntity> resultList = taskRecordService.list(queryWrapper);
        if (resultList != null && resultList.size() != 0) {
            List<JobRecord> data = OrikaConfig.getMapper().mapAsList(resultList, JobRecord.class);
            resp.setData(data);
        }
        return resp;
    }

    /**
     * 单个企业名称查询访问万链指数预警接口异步任务
     */
    private class WlzsWarningTask implements Runnable {

        @Override
        public void run() {
            try {
//                wlzsWarningJob.execute();
            } catch (Exception e) {
                log.warn("手动执行任务失败", e);
            }
        }
    }

    /**
	* DTS指数预警接口异步任务
	*/
    private class DTSWarningTask implements Runnable {

	   @Override
	   public void run() {
		  try {
//			 dtsWarningJob.execute();
		  } catch (Exception e) {
			 log.warn("手动执行任务失败", e);
		  }
	   }
    }
}
