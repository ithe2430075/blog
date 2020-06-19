package com.team.blog_02.Config;

import com.team.blog_02.Serivce.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
      /**
       * 注入我们自己定义的用户信息获取对象
       */
//      @Qualifier("myUserDetailsService")
      @Autowired
      private MyUserDetailsService myUserDetailsService;
      @Override
      public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String username = authentication.getName();
            String password = (String) authentication.getCredentials();
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            System.out.println(authorities);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities() );
      }
      @Override
      public boolean supports(Class<?> authentication) {
            // TODO Auto-generated method stub
            // 这里直接改成retrun true;表示是支持这个执行
            return true;
      }
}