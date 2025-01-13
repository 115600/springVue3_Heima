package org.winkey.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.winkey.pojo.Article;

import java.util.List;

@Mapper
public interface articleMapper {
    //新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
    //分页查询，配置文件动态sql
    List<Article> list(Integer userId, Integer categoryId, String state);

    

}
