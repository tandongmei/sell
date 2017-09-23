package com.immoc.service;

import com.immoc.entity.ProductCategory;

import java.util.List;

/**
 * Created by tandongmei on 2017/9/6.
 */
public interface IProductCategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
