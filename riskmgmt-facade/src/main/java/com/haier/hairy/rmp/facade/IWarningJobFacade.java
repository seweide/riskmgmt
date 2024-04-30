package com.haier.hairy.rmp.facade;

import com.haier.hairy.rmp.request.ExcuteJobReq;
import com.haier.hairy.rmp.request.QueryJobReq;
import com.haier.hairy.rmp.response.ExcuteJobResp;
import com.haier.hairy.rmp.response.QueryJobResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 风控预警任务
 * @author xiehao
 */
@RequestMapping("/warningJobFacade")
@Api(value = "/warningJobFacade", description = "任务管理接口")
public interface IWarningJobFacade {

    @ApiOperation(value = "任务执行", response = ExcuteJobResp.class)
    @RequestMapping(value = "/excuteJob", method = RequestMethod.POST)
    ExcuteJobResp excuteJob(@ApiParam(value = "任务信息", required = true)
                            @Valid @RequestBody ExcuteJobReq request);

    @ApiOperation(value = "任务查询", response = QueryJobResp.class)
    @RequestMapping(value = "/queryJob", method = RequestMethod.POST)
    QueryJobResp queryJob(@ApiParam(value = "任务信息", required = true)
                                  @Valid @RequestBody QueryJobReq request);

}
