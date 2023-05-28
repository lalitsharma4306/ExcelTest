package com.test.excel.controller;

import com.test.excel.micro.ConnectionClass;
import com.test.excel.micro.UrlDto;
import com.test.excel.response.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ConnectionClass connectionClass;
    @GetMapping("/response")
    public ResponseEntity getResponse() {

        Product response = connectionClass.scriptFinder();
        return new ResponseEntity(response, HttpStatus.OK);
    }

//    @PostMapping("/readExcel")
//    public ResponseEntity getUrl(@RequestBody UrlDto urlDto) {
////        String url = urlDto.getUrl();
//        String url = "https://www.1mg.com/otc/truebasics-joint-ease-tablet-otc508810";
////        System.out.println(urlDto);
//        if(!url.equals("NA")) {
//            Product product = connectionClass.getUrl(url);
//            return new ResponseEntity(product, HttpStatus.OK);
//        }
//        return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
//    }
}
