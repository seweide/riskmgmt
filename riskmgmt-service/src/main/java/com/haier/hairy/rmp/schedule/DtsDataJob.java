package com.haier.hairy.rmp.schedule;

import com.haier.hairy.common.apollo.ApolloConfigContext;
import com.haier.hairy.rmp.util.DateUtils;
import com.haier.hairy.rmp.util.ShellUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DTS数据Kettle同步任务
 *
 * @author Seweide
 * @since 2019-08-07
 */
@Slf4j
@Component
public class DtsDataJob {

    /**
	* 文件基础路径
	*/
    public static final String SHELL_NAME = ApolloConfigContext.getConfig("shell.name");
    public static final String SHELL_PATH = ApolloConfigContext.getConfig("shell.path");

    /**
	* "0 15 0 10 * ?" 每月10日上午00:15触发
	*/
    @Scheduled(cron = "0 15 0 10 * ?")
    public void execute() {
        log.info("定时任务开始......");
        Date startTime = new Date();

        //获取当前日期时间（yyyyMM）触发脚本
        try {
		  String getYM = DateUtils.getDateTimeString(startTime,DateUtils.MONTH_FORMAT);
		  SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");
		  //转换执行T-1月日期
		  Date currdate = sd.parse(getYM);
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(currdate);
		  calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);

		  getYM = sd.format(calendar.getTime());
		  String[] param = new String[]{getYM};
		  String result = ShellUtils.copyDatabase(SHELL_NAME,SHELL_PATH, param);
		  log.info("定时执行Kettle数据迁移Shell脚本，结果：{}", result);
	   } catch (IOException e) {
            log.info("定时执行Kettle数据迁移Shell脚本异常", e);
        } catch (ParseException e) {
		  log.info("转换执行T-1月日期异常", e);
	   }

	   log.info("定时任务结束......");
    }

}
