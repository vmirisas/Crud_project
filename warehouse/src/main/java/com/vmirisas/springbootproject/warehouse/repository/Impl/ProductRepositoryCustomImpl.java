package com.vmirisas.springbootproject.warehouse.repository.Impl;

import com.vmirisas.springbootproject.warehouse.entity.QProduct;
import com.vmirisas.springbootproject.warehouse.repository.ProductRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
    QProduct qProduct = QProduct.product;

//    @Override
//    public Product findByBarcode(String barcode) {
//        JPAQuery<QProduct> qProductJPAQuery = new JPAQuery<>(em);
//        Product product = qProductJPAQuery.select(qProduct).from(qProduct).where(qProduct.barcode.eq(barcode)).fetchFirst();
//        return product;
//    }
}
