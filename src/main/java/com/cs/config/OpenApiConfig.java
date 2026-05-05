package com.cs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI 配置
 *
 * @author LivePulse
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI salesforceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Salesforce Connector API")
                        .description("Salesforce 连接器 - 同步 CRM 数据，支持 Account、Contact、Lead、Opportunity 等")
                        .version("1.2.0")
                        .contact(new Contact()
                                .name("LivePulse")
                                .email("question@creasourse.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server().url("http://localhost:23008").description("本地开发环境"),
                        new Server().url("https://api.livepulse.com").description("生产环境")
                ));
    }
}
