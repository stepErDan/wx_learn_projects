package com.dingx.personal.common.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.dingx.personal.common.interceptor.ValidateHandlerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token 授权拦截器
        registry.addInterceptor(validateHandlerInterceptor()).addPathPatterns("/rest/**").addPathPatterns("/api/**").excludePathPatterns("/v1/sso/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public ValidateHandlerInterceptor validateHandlerInterceptor(){
        ValidateHandlerInterceptor validateHandlerInterceptor = new ValidateHandlerInterceptor();
        return validateHandlerInterceptor;
    }

    //解决返回字符串存在""
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverterExtension();
        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullBooleanAsFalse,  //boolean null返回false
                SerializerFeature.WriteNullStringAsEmpty,   //字符串null返回空字符串
                SerializerFeature.WriteNullListAsEmpty,     //空字段保留
                SerializerFeature.WriteMapNullValue);
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        //解决Long转json精度丢失的问题
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        serializeConfig.put(BigDecimal.class,ToStringSerializer.instance);
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        //序列化LocalDateTime
        serializeConfig.put(LocalDateTime.class, LocalDateTimeSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4.将converter赋值给HttpMessageConverter
        HttpMessageConverter<?> converter = fastConverter;
        // 5.返回HttpMessageConverters对象
        return new HttpMessageConverters(converter, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    public class FastJsonHttpMessageConverterExtension extends FastJsonHttpMessageConverter {
        FastJsonHttpMessageConverterExtension() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
            mediaTypes.add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"));
            setSupportedMediaTypes(mediaTypes);
        }
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converters.add(converter);
    }

        // 辣鸡，弃用 2020年5月31日 21:46:06
//    @Bean
//    public SSOSpringInterceptor ssoInterceptor(){
//        SSOSpringInterceptor ssoInterceptor = new SSOSpringInterceptor();
//        ssoInterceptor.setHandlerInterceptor(new LoginHandlerInterceptor());
//        return ssoInterceptor;
//    }
}