package com.haier.hairy.rmp.config;

import com.haier.hairy.common.apollo.ApolloConfigContext;

/**
 * @Author: Xie Hao
 * @Create: 2019-08-02 09:18
 **/
public class CommConfig {
    /**
     * 文件基础路径
     */
    public static final String FILE_BASE_PATH = ApolloConfigContext.getConfig("file.base.path");

    /**
     * excel文件路径
     */
    public static final String EXCEL_PATH = FILE_BASE_PATH + "excel/warn/";
}
