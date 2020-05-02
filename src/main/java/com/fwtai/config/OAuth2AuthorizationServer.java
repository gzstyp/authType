package com.fwtai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 认证服务器
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-03 0:58
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@Configuration
@EnableAuthorizationServer//该注解表示是认证服务器
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception{
        security.tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients();
    }

    //配置客户端的信息,实际项目中下面的信息是存在数据库里或配置文件里
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
            .withClient("clientApp").secret(passwordEncoder.encode("123456"))
            .authorizedGrantTypes("password","authorization_code","refresh_token","client_credentials","implicit")
            .authorities("READ_ONLY_CLIENT")
            .secret("read_write")
            .resourceIds("oauth2-resource")
            .redirectUris("http://www.baidu.com")
            .accessTokenValiditySeconds(12000)
            .refreshTokenValiditySeconds(240000);
    }

    //认证管理器
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.authenticationManager(authenticationManager);
    }
}







































