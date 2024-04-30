package com.haier.hairy.rmp.facade;

import com.haier.hairy.rmp.request.DtsDataSyncReq;
import com.haier.hairy.rmp.response.QueryJobResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;

/**
 * DTS数据任务
 * @author xiehao
 */
@RequestMapping("/dtsDataJobFacade")
@Api(value = "/dtsDataJobFacade", description = "DTS数据任务接口")
public interface IDtsDataJobFacade {

    @ApiOperation(value = "数据同步", response = QueryJobResp.class)
    @RequestMapping(value = "/sync", method = RequestMethod.POST)
    void sync(@ApiParam(value = "同步请求参数", required = true)
                                      @Valid @RequestBody DtsDataSyncReq request) throws IOException;

}
