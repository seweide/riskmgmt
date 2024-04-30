package com.haier.hairy.rmp.config;

import com.haier.hairy.common.apollo.ApolloConfigContext;
import com.haier.hairy.common.springboot.feign.HryFeignClientFactoryBean;
import com.haier.hairy.mts.facade.EmailFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册到eureka上的服务配置
 * @author xiehao
 */
@Configuration
public class EurekaServiceConfig {

    public static final String MTS_SERVICE_ID = ApolloConfigContext.getConfig("hry.mts.service.id");

    @Bean
    public HryFeignClientFactoryBean emailFacade() {
        return new HryFeignClientFactoryBean(EmailFacade.class, MTS_SERVICE_ID);
    }
}
