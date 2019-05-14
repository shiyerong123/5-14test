package com.jk.controller;

import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @RequestMapping("quanbu")
    public String quanbu(String url){
        return url;
    }

    @RequestMapping("findBook")
    @ResponseBody
    public HashMap<String,Object> findBook(Integer page,Integer rows){
        return bookService.findBook(page,rows);
    }
    @RequestMapping("getEcharts")
    @ResponseBody
    public HashMap<String,Object> getEcharts(String typeName){
        return bookService.getEcharts(typeName);
    }

}
