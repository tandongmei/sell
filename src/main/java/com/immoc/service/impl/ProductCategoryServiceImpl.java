package com.immoc.service.impl;

import com.immoc.dao.IProductCategoryDao;
import com.immoc.entity.ProductCategory;
import com.immoc.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tandongmei on 2017/9/6.
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService{

    @Autowired
    private IProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryDao.findOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
