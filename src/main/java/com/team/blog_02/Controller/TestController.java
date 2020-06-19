package com.team.blog_02.Controller;

import org.apache.lucene.analysis.standard.UAX29URLEmailTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.security.Principal;

/**
 * @author hejiajin
 * @date 2020/6/19 - 16:35
 */
@Controller
public class TestController {
    @Autowired
    DataSource dataSource;


    @RequestMapping("/test")
    public String test(){

        return "test";
    }

    @RequestMapping("/loginsuccess")
    public String loginsuccess(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        System.out.println(userDetails);
        return "success";

    }
}
