package com.jk.service;

import java.util.HashMap;

public interface BookService {
    HashMap<String, Object> findBook(Integer page, Integer rows);

    HashMap<String, Object> getEcharts(String typeName);
}
