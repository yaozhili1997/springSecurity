package com.yzl.security.comfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: 姚志立
 * @Date: 2020/5/2 17:40
 * @Version: 1.0
 */

@Configuration
@EnableWebSecurity //启动 SpringSecurity 过滤器链功能
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Bean
    public PasswordEncoder passwordEncoder() {
// 设置默认的加密方式
        return new BCryptPasswordEncoder();
    }
    /**
     * 认证管理器：
     * 1、认证信息提供方式（用户名、密码、当前用户的资源权限）
     * 2、可采用内存存储方式，也可能采用数据库方式等
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户信息存储在内存中
        String password = passwordEncoder().encode("1234");
        logger.info("加密之后存储的密码：" + password);
        auth.inMemoryAuthentication().withUser("yzl")
                .password(password).authorities("ADMIN");
    }
    /**
     * 资源权限配置（过滤器链）:
     * 1、被拦截的资源
     * 2、资源所对应的角色权限
     * 3、定义认证方式：httpBasic 、httpForm
     * 4、定制登录页面、登录请求地址、错误处理方式
     * 5、自定义 spring security 过滤器
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
        http.formLogin()
                .loginPage("/login/page")
                .loginProcessingUrl("/login/form")//登陆表单提交处理url，默认是/login
                .usernameParameter("name")
                .passwordParameter("pwd")
                .and()
                .authorizeRequests() // 认证请求
                .antMatchers("/login/page").permitAll()//放行/login/page不需要认证可访问
                .anyRequest().authenticated() // 所有进入应用的HTTP请求都要进行认证
        ; // 分号`;`不要少了
    }
    /**
     * 一般针对静态资源放行
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/dist/**","/models/**","/plugins/**");
    }
}

