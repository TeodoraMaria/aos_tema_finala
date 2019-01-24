package com.unitbv.mi.userManagement.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "billing")
@RibbonClient(name = "billing")
public interface BillingServiceProxy {
    @GetMapping("/getProductsDetails")
    String getProductsDetails(@RequestParam("productsJson") String productsJson);

    @PostMapping("/addProduct")
    boolean addProduct(@RequestParam("productJson")String productJson);
}

