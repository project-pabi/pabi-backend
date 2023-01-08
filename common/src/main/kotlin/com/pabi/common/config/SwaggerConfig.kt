package com.pabi.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseBuilder
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import java.util.*

@Configuration
@EnableWebMvc
class SwaggerConfig {

    @Bean
    fun swaggerApi(): Docket {
        val commonResponse = setCommonResponse()
        return Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
            .globalResponses(HttpMethod.GET, commonResponse)
            .globalResponses(HttpMethod.POST, commonResponse)
            .globalResponses(HttpMethod.PUT, commonResponse)
            .globalResponses(HttpMethod.PATCH, commonResponse)
            .globalResponses(HttpMethod.DELETE, commonResponse).consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .apiInfo(swaggerInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.pabi"))
            .paths(PathSelectors.any()).build()
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList<SecurityScheme>(apiKey()))
    }

    private fun setCommonResponse(): List<Response> {
        val list: MutableList<Response> = ArrayList()
        list.add(ResponseBuilder().code("200").description("정상 처리(성공)").build())
        list.add(
            ResponseBuilder().code("401").description("토큰 만료 또는 비정상 토큰 또는 권한 없음").build()
        )
        list.add(ResponseBuilder().code("404").description("존재하지 않는 api 요청 ").build())
        list.add(ResponseBuilder().code("500").description("내부 서버 오류(문의 필요)").build())
        return list
    }

    private fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth())
            .forPaths(PathSelectors.any()).build()
    }

    private fun defaultAuth(): List<SecurityReference>? {
        val authorizationScope = AuthorizationScope(
            "global", "accessEverything"
        )
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return Arrays.asList(SecurityReference("JWT", authorizationScopes))
    }

    private fun getConsumeContentTypes(): Set<String>? {
        val consumes: MutableSet<String> = HashSet()
        consumes.add("application/json;charset=UTF-8")
        consumes.add("application/x-www-form-urlencoded")
        return consumes
    }

    private fun getProduceContentTypes(): Set<String>? {
        val produces: MutableSet<String> = HashSet()
        produces.add("application/json;charset=UTF-8")
        return produces
    }

    private fun swaggerInfo(): ApiInfo {
        return ApiInfoBuilder().title("PA-BI User API DOCS")
            .description("파비 프로젝트 User 서비스 API 문서 입니다.").build()
    }
}
