package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users(username, email) VALUES(#{username}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectUserById(Long id);

    @Select("SELECT * FROM users")
    List<User> selectAllUsers();

    @Update("UPDATE users SET username=#{username}, email=#{email} WHERE id=#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(Long id);
}