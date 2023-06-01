package com.community.dao;

import com.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {


    List<DiscussPost> selectDiscussPosts(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit); //offset，每页起始行的行号

    //@Param注解用于给参数取别名
    //如果只有一个参数， 并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId); //@Param给参数起别名
}
