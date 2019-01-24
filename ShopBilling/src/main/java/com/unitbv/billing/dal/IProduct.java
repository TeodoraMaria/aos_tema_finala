package com.unitbv.billing.dal;

import com.unitbv.billing.dao.Product;

import java.util.List;

public interface IProduct {

    List<Product> getProductsDetails(List<Product> products);

    Product addProduct(Product product);


}
