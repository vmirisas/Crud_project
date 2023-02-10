package com.vmirisas.springbootproject.warehouse.rest;

import com.vmirisas.springbootproject.warehouse.dto.ProductDTO;
import com.vmirisas.springbootproject.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    // expose "/product" and return list of products
    @GetMapping("")
    public List<ProductDTO> findAll() {
        return this.productService.toDtoList(this.productService.findAll());
    }

    //  add mapping for GET /product/{productId}
    @GetMapping("/{productId}")
    public ProductDTO getProduct(@PathVariable Long productId) {
        return new ProductDTO(this.productService.findById(productId));
    }

    // add mapping for POST /product/add = add new product
    @PostMapping("/add")
    public ProductDTO addProduct(@RequestBody ProductDTO theProduct) {
        theProduct.setProductId(null);
        return new ProductDTO(this.productService.save(theProduct));
    }

    // add mapping for PUT /product/update = update existing product
    @PutMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO theProduct) {
        return new ProductDTO(this.productService.save(theProduct));
    }

    // add mapping for DELETE /product/remove/{productId} = delete existing product
    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return "Deleted product with id '" + productId + "'";
    }
}
