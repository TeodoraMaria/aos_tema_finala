package com.unitbv.billing.bl;

import com.unitbv.billing.dal.ProductDal;
import com.unitbv.billing.dao.Product;

import java.util.List;

public class ProductBl {
    private static final String persistanceUnitName = "dbSource";
    private ProductDal accountDal;

    public ProductBl() {
        accountDal = new ProductDal(persistanceUnitName);
    }


    public List<Product> getProductsDetails(List<Product> products) {
        if (!products.isEmpty() || products!=null)
            return accountDal.getProductsDetails(products);
        return null;
    }

    public Product addProduct(Product product) {
        if (product != null) {
            return accountDal.addProduct(product);
        }
        return null;
    }

}
