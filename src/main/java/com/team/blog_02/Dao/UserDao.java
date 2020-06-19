package com.team.blog_02.Dao;

import com.team.blog_02.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hejiajin
 * @date 2020/6/19 - 20:15
 */
@Repository
@Mapper
public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Options(useGeneratedKeys = true,keyProperty = "uid")
    public User getUser(String username);
    @Select("select role from roles where uid=#{uid}")
    public List<String> getRole(Integer uid);
}
