package com.example.productList.service;

import com.example.productList.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);

    Product getProductById(long id);
    void deleteProductById(long id);

    void save(MultipartFile file);

//    Page<Product> findPaginated(int pageNo, int pageSize);

    Page<Product> findPaginated(int pageNo, int pageSize);
}
