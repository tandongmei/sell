package com.immoc.dao;


import com.immoc.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tandongmei on 2017/9/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductCategoryDaoTest {

    @Autowired
    private IProductCategoryDao productCategoryDao;

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("最热商品");
        productCategory.setCategoryType(5);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryDao.findOne(1);
        System.out.println(productCategory.getCategoryName());
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = productCategoryDao.findOne(2);
        productCategory.setCategoryName("冷门商品");
        productCategoryDao.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<ProductCategory> productCategoryList = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotNull(productCategoryList);
    }

}