package com.pixeltrice.springbootelasticsearchapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurgeProducts {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws IOException {
        String email = "diya@gatestautomation";
        Map<String, String> map = new HashMap<>();
        List<String> emails = Arrays.asList("soumya.dyavarashetty@gaiansolutions.com", "demo11.gaian@mailinator.com", "testingmp@mailinator.com", "Test25@mailinator.com", "testqa1144@mailinator.com", "abcd.127@mailinator.com", "soni.uma.po@gatestautomation.com", "hasinit610@gmail.com", "sivamudili7@gmail.com", "dharvik98@gmail.com", "testingmp@mailinator.com", "gaiansolutions.mp@mailinator.com", "singapore1@mailinator.com", "dubai1@mailinator.com", "saketh.rbk@gmail.com", "deb_jana_xp@gatestautomation.com", "spotify.gaian@mailinator.com", "nagra.gaian@mailinator.com", "spectrarep.gaian@mailinator.com", "hpe.gaian@mailinator.com", "ussiglobal.gaian@mailinator.com", "titantv.gaian@mailinator.com", "oxfordacademy@mailinator.com", "gretchen@mailinator.com", "gaiansolutions@mailinator.com", "microsoft@mailinator.com", "nagra.gaian@mailinator.com");
//        for (String email:emails) {
        String tenanatId = getTenanatId(email);
        List<Object> productIds = getProductIds(tenanatId);
        if(productIds.size() > 0){
//                System.out.println(">>>>>>>>tenanatId:"+tenanatId + " email: "+email);
//                System.out.println(productIds.toString());
            map.put(tenanatId, email);
        }
//        }
        System.out.println(map.toString());
        System.out.println(productIds.toString());
//        deleteProduct(productIds);
    }

    private static void deleteProduct(List<Object> productIds) {
        String deleteProductUrl = "http://ingress-gateway.gaiansolutions.com/marketplace-service/api/v1.0/products/{productId}";

        productIds.stream().forEach(product -> {
            String productId = String.valueOf(product);
            Map<String, Object> map = new HashMap<>();
            map.put(productId, product);
            restTemplate.delete(productId, map);
        });
    }

    private static List<Object> getProductIds(String tenantId) throws IOException {
        String productUrl = "http://ingress-gateway.gaiansolutions.com/marketplace-service/api/v1.0/products/tenant/{tenantId}?pageNo=0&pageSize=10&sortBy=id&sortOrder=ASCENDING";
        Map<String, String> map = new HashMap<>();
        map.put("tenantId", tenantId);
        String products = restTemplate.getForObject(productUrl, String.class, map);
        Map<String, Object> productsM = new ObjectMapper().readValue(products, HashMap.class);
        Object productsList = productsM.get("dtos");
        return Arrays.asList(productsList);

    }

    private static String getTenanatId(String email) throws IOException {
        String tenantUrl = "http://ingress-gateway.gaiansolutions.com/iam-service/v1.0/tenants/byEmail?emailId={email}";
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        String tenant = restTemplate.getForObject(tenantUrl, String.class, map);
        Map<String, Object> tenantM = new ObjectMapper().readValue(tenant, HashMap.class);
        Object tenantId = tenantM.get("_id");
        System.out.println(tenantId);
        return String.valueOf(tenantId);
    }
}