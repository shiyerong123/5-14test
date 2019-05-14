package com.jk.service;

import com.jk.mapper.BookMapper;
import com.jk.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public HashMap<String, Object> findBook(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer total = bookMapper.findCountBook();
        Integer start = (page-1)*rows;
        List<Book> findBook = bookMapper.findBook(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",findBook);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> getEcharts(String typeName) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if(typeName == null){
            List<String> names = bookMapper.findName();
            List<Integer> list = new ArrayList<>();
            for (String name:names){
                Integer count = bookMapper.getCount(name);
                list.add(count);
            }
            hashMap.put("names",names);
            hashMap.put("values",list);
        }else{
            List<String> names = bookMapper.findNameByTypeName(typeName);
            List<Integer> values = bookMapper.findValueByTypeName(typeName);
            hashMap.put("names",names);
            hashMap.put("values",values);
        }

        return hashMap;
    }
}
