package com.unitbv.mi.userManagement.proxies;

import com.unitbv.mi.userManagement.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "warehouse")
@RibbonClient(name = "warehouse")
public interface WarehouseServiceProxy {
    @GetMapping("/getAllProducts")
    String getAllProducts();

    @PostMapping("/addProduct")
    boolean addProduct(@RequestParam("productJson") String productJson);

}


