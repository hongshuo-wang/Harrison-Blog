package com.harrison.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.writer.ObjectWriterProvider;
import com.alibaba.fastjson2.writer.ObjectWriters;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author: Harrison
 * @date: 2023/2/13 0:24
 * @Description: 跨域配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    /**
     * 使用@Bean注入fastJsonHttpMessageConvert
     * @return
     */
    @Bean
    public HttpMessageConverter<?> fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //1.需要定义一个Convert转换消息的对象
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectWriterProvider provider = new ObjectWriterProvider();
        JSON.config(JSONWriter.Feature.WriteLongAsString);
//        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
//        config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
        fastConverter.setFastJsonConfig(config);
        fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
        fastConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        return fastConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, fastJsonHttpMessageConverters());
    }

}