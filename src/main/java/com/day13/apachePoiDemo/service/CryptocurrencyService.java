package com.day13.apachePoiDemo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CryptocurrencyService {
    void importToDb(List<MultipartFile> multipleFiles);
}
