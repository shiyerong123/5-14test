package com.jk.service;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@FeignClient("springcloud-service")
public interface BookService {
    @RequestMapping("findBook")
    @ResponseBody
    HashMap<String, Object> findBook(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows);
    @RequestMapping("getEcharts")
    @ResponseBody
    HashMap<String, Object> getEcharts(@RequestParam("typeName") String typeName);
}
