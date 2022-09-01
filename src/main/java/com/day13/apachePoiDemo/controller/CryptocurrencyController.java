package com.day13.apachePoiDemo.controller;

import com.day13.apachePoiDemo.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/crypto")
public class CryptocurrencyController {

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @PostMapping(path = "/import-data")
    public  void  importDataFromExcelToDb(@RequestPart(required = true)List<MultipartFile> files){
        cryptocurrencyService.importToDb(files);
    }
}
