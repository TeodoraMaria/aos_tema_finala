package com.unitbv.billing.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.unitbv.billing.bl.ProductBl;
import com.unitbv.billing.dao.Product;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class BillingController {

    @RequestMapping(value = "/getProductsDetails", method = RequestMethod.GET)
    @ResponseBody
    public String getAllProducts(@RequestParam("productsJson") String productsJson) {
        ProductBl productBl = new ProductBl();
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Product>>() {
        }.getType();
        List<Product> products = gson.fromJson(productsJson, listType);
        List<Product> response = productBl.getProductsDetails(products);
        return gson.toJson(response);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public boolean addProduct(@RequestParam("productJson") String productJson) {
        ProductBl productBl = new ProductBl();
        Gson gson = new GsonBuilder().create();
        Product product = gson.fromJson(productJson, Product.class);
        Product response = productBl.addProduct(product);
        if (response != null) {
            return true;
        } else {
            return false;
        }
    }
}
