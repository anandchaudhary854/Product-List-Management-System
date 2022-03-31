package com.example.productList.service;

import com.example.productList.helper.Helper;
import com.example.productList.model.Product;
import com.example.productList.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public void saveProduct(Product product){
        this.productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());
            this.productRepository.saveAll(products);
//            for(int i = 0; i<products.size(); i++){
//                this.productRepository.save(products.get(i));
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.productRepository.findAll(pageable);
    }

}
