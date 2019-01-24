package com.unitbv.warehouse.bl;

import com.unitbv.warehouse.dal.ProductDal;
import com.unitbv.warehouse.dao.Product;

import java.util.List;

public class ProductBl {
    private static final String persistanceUnitName = "dbSource";
    private ProductDal accountDal;

    public ProductBl() {
        accountDal = new ProductDal(persistanceUnitName);
    }

    public Product addProduct(Product product) {
        if (product != null) {
            return accountDal.addNewProduct(product);
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return accountDal.getAllProducts();
    }


}
