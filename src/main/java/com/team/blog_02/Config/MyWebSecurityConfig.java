package com.team.blog_02.Config;


import com.team.blog_02.Config.MyAuthenticationProvider;
import com.team.blog_02.Serivce.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author hejiajin
 * @date 2020/5/8 - 15:28
 */
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;  //注入我们自己的AuthenticationProvider
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //请求授权的规则
        http.authorizeRequests().
                antMatchers("/","/login","/login?l='zh_CN'","/login?l='en_US'","/tologin").
                permitAll()
                .antMatchers("/test").hasAnyRole("admin")
                 .antMatchers("/loginsuccess").hasAnyRole("user")
        .antMatchers("/logout").hasAnyRole("admin", "user");
        //定制了登录页的请求  以及登录表单的name  以及登录成功表单提交的请求
        http.formLogin().
                loginPage("/tologin")//定制登录页

                .usernameParameter("username")
                .passwordParameter("pwd")
                .loginProcessingUrl("/loginsuccess")
                .successForwardUrl("/loginsuccess")
        ;//登录时的请求

        http.logout().logoutSuccessUrl("/").logoutUrl("/logout");
        //定制记住我的name
        http.rememberMe().rememberMeParameter("re").rememberMeCookieName("renting");


//        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
//                withUser("hjj").password(new BCryptPasswordEncoder().encode("12345")).roles("vip1").and().
//                withUser("mml").password(new BCryptPasswordEncoder().encode("12345")).roles("vip3","vip2","vip1").and().
//                withUser("sg").password(new BCryptPasswordEncoder().encode("12345")).roles("vip2");
//        auth.userDetailsService(myUserDetailsService);

        auth.authenticationProvider(myAuthenticationProvider);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
//    @Bean
//    public  MyUserDetailsService myUserDetailsService(){
//        return  new MyUserDetailsService();
//    }





}
