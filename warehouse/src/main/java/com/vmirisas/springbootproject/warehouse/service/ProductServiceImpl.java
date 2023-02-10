package com.vmirisas.springbootproject.warehouse.service;

import com.vmirisas.springbootproject.warehouse.dto.ProductDTO;
import com.vmirisas.springbootproject.warehouse.entity.Product;
import com.vmirisas.springbootproject.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long theId) {
        return this.productRepository.findById(theId).orElseThrow(() -> new RuntimeException("Product with id '" + theId + "' not found"));
    }

    public Product findProductByBarcode(String theBarcode) {
        return this.productRepository.findByBarcode(theBarcode).orElseThrow(() -> new RuntimeException("Product with barcode '" + theBarcode + "' not found"));
    }

    @Override
    public Product save(ProductDTO theProduct) {
        return productRepository.save(new Product(theProduct));
    }

    @Override
    public void deleteById(Long theId) {
        Product product = this.productRepository.findById(theId).orElseThrow(() -> new RuntimeException("Product with id '" + theId + "' not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> toDtoList(List<Product> products) {
        List<ProductDTO> list = new ArrayList<>();
        for (Product p : products) {
            list.add(new ProductDTO(p));
        }
        return list;
    }
}
