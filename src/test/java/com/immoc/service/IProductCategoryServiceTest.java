package com.immoc.service;

import com.immoc.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tandongmei on 2017/9/8.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IProductCategoryServiceTest {

    @Autowired
    private IProductCategoryService productCategoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = productCategoryService.findOne(1);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> list = productCategoryService.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List list = Arrays.asList(1,2,3);
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(list);
        Assert.assertNotNull(productCategoryList);
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("冷门商品");
        productCategory.setCategoryType(10);
        ProductCategory pc = productCategoryService.save(productCategory);
        Assert.assertNotNull(pc);
    }

}