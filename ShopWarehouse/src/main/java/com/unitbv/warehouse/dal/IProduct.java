package com.unitbv.warehouse.dal;

import com.unitbv.warehouse.dao.Product;

import java.util.List;

public interface IProduct {

    Product addNewProduct(Product product);

    List<Product> getAllProducts();


}
