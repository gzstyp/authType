package com.fwtai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-03 1:23
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@Configuration
@EnableResourceServer//表示是资源服务器
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter{

    //配置Security信息
    @Override
    public void configure(final HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/").permitAll()//首页可以访问
            .antMatchers("/api/**")//需要认证
            .authenticated();
    }
}













































