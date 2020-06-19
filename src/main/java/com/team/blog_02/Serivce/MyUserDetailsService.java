package com.team.blog_02.Serivce;

import com.team.blog_02.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserDao userDao;
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          com.team.blog_02.Pojo.User user = userDao.getUser(username);
          String password = user.getPassword();
          List<String> role = userDao.getRole(user.getUid());
          //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
            //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
           return new User(username,password,true,true,true,false,
                   getAuthorities(role));
      }
    public Collection<? extends GrantedAuthority> getAuthorities(List<String> role) {
        StringBuilder authoritiesBuilder = new StringBuilder("");
        if (null != role) {
            for (String rolename : role) {
                authoritiesBuilder.append(",").append(rolename);
            }
        }
        String authoritiesStr = "";
        if(authoritiesBuilder.length()>0) {
            authoritiesStr = authoritiesBuilder.deleteCharAt(0).toString();
        }
//        logger.info("VUserDetails getAuthorities [authoritiesStr={} ", authoritiesStr);
        System.out.println(authoritiesStr+"!!!!!!!!!11");
        System.out.println(authoritiesStr+"~~~~~~~~~~~~~~~~~~~");
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesStr);
    }


}