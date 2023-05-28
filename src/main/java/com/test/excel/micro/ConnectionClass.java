package com.test.excel.micro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.excel.response.Product;
//import com.test.excel.service.ExcelReader;
//import com.test.excel.service.ExcelReader1;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ConnectionClass {
    @Autowired
    private RestTemplate restTemplate;
    //    @Autowired
//    private ExcelReader excelReader;
//    @Autowired
//    private ExcelReader1 excelReader1;
    //    @Value("${spring.resources.static-locations}")
    String filePath = "C:\\Users\\lalit\\Downloads\\ExcelTest\\ExcelTest\\src\\main\\resources\\data.xlsx";

    String url = "https://www.1mg.com/otc/truebasics-joint-ease-tablet-otc508810";
//     "https://www.1mg.com/otc/truebasics-joint-ease-tablet-otc508810";

    public String getResponse() {
        String product = null;
//        String url1 =url;
        System.out.println("inside getResponse method");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        System.out.println("requestEntity " + requestEntity);
        product = restTemplate.exchange(requestEntity, String.class).getBody();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        product = restTemplate.exchange(url,HttpMethod.GET,null, String.class).getBody();
//        System.out.println("Product : "+product);
        return product;
    }

    public Product scriptFinder() {

        String htmlString = getResponse();
        Product products = new Product();

        Document document = Jsoup.parse(htmlString);
        List<String> scriptList = new ArrayList();
        Elements titleElements = document.select("title");

        // Select all <script> elements
        Elements scriptElements = document.select("script");

        // Extract the script data
        for (Element scriptElement : scriptElements) {
            String scriptData = scriptElement.html();
            scriptList.add(scriptData);
//            System.out.println("Script Data: " + scriptData);
        }
//        System.out.println("Size of script : "+scriptList.size());
        for (String jsonString : scriptList) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                products = objectMapper.readValue(jsonString, Product.class);
                for (Element scriptElement : titleElements) {
                    String scriptData = scriptElement.html();
//            scriptList.add(scriptData);
                    products.setTitle(scriptData);
//            System.out.println("Script Data: " + scriptData);
                }
                System.out.println("product : " + products);
                return products;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        System.out.println("product : " + products);
        return products;
    }

//    public Product getUrl(String urlDto) {
//        Product product = scriptFinder(urlDto);
//        return product;
//    }
}
