package com.haier.hairy.rmp.facade.impl;

import com.haier.hairy.rmp.facade.IDtsDataJobFacade;
import com.haier.hairy.rmp.request.DtsDataSyncReq;
import com.haier.hairy.rmp.schedule.DtsDataJob;
import com.haier.hairy.rmp.util.ShellUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: Xie Hao
 * @Create: 2019-08-02 11:46
 **/
@Slf4j
@RestController
@Service("dtsDataJobFacade")
public class DtsDataJobFacadeImpl implements IDtsDataJobFacade {

    @Override
    public void sync(DtsDataSyncReq request) {
        try {
		  String[] param = new String[]{request.getMonth()};
		  String result = ShellUtils.copyDatabase(DtsDataJob.SHELL_NAME,DtsDataJob.SHELL_PATH, param);
		  log.info("手动执行Kettle数据迁移Shell脚本，结果：{}", result);
	   } catch (IOException e) {
		  log.info("手动执行Kettle数据迁移Shell脚本异常", e);
	   }

    }
}
