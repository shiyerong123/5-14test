package com.jk.mapper;

import com.jk.model.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface BookMapper {
    @Select("select count(1) from t_book")
    Integer findCountBook();
    @Select("select * from t_book limit #{start},#{rows}")
    List<Book> findBook(@Param("start") Integer start, @Param("rows") Integer rows);
     @Select("select category from t_test GROUP BY category")
    List<String> findName();
    @Select("select count(*) from t_test where category=#{value}")
    Integer getCount(String name);
    @Select("select t.name from t_test t where category=#{value} order by id")
    List<String> findNameByTypeName(String typeName);
    @Select("select t.value from t_test t where category=#{value} order by id")
    List<Integer> findValueByTypeName(String typeName);
}
