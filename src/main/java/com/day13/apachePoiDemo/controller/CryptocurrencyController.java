package com.day13.apachePoiDemo.controller;

import com.day13.apachePoiDemo.model.Cryptocurrency;
import com.day13.apachePoiDemo.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/crypto")
public class CryptocurrencyController {

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @GetMapping(path = "/data")
    public List<Cryptocurrency> getAllData(){
        return cryptocurrencyService.getAllData();
    }

    @PostMapping(path = "/import-data")
    public  ResponseEntity<?>  importDataFromExcelToDb(@RequestPart(required = true)List<MultipartFile> files){
        cryptocurrencyService.importToDb(files);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
