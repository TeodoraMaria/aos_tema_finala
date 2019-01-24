package com.unitbv.warehouse.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unitbv.warehouse.bl.ProductBl;
import com.unitbv.warehouse.dao.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WarehouseController {
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    @ResponseBody
    public boolean addProduct(@RequestParam("productJson") String productJson) {
        ProductBl productBl = new ProductBl();
        Gson gson = new GsonBuilder().create();
        Product product = gson.fromJson(productJson,Product.class);
        product.generateProductCode();
        Product response = productBl.addProduct(product);
        if(response!=null){
            return true;
        }
        else{
            return false;
        }
    }

    @RequestMapping("/getAllProducts")
    @ResponseBody
    public List<Product> getAllProducts() {
        ProductBl productBl = new ProductBl();
        Gson gson = new GsonBuilder().create();
        List<Product> response = productBl.getAllProducts();
//        return gson.toJson(response);
        return response;
    }
}
