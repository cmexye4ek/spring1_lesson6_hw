package ru.gb.lesson6_homework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.lesson6_homework.models.Product;
import ru.gb.lesson6_homework.repositories.ProductDao;

@Service
public class ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

    public void increaseCostByOne(Product product) {
        product.setCost(product.getCost() + 1);
        productDao.addOrUpdateProduct(product);
    }

}
