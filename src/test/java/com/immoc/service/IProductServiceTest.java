package com.immoc.service;

import com.immoc.dao.IProductDao;
import com.immoc.entity.Product;
import com.immoc.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tandongmei on 2017/9/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductServiceTest {

    @Autowired
    private IProductDao productDao;

    @Test
    public void findOne() throws Exception {
        Product product = productDao.findOne("001");
        Assert.assertNotNull(product);
    }

    @Test
    public void findUpAll() throws Exception {
        List<Product> productList = productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotNull(productList);
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<Product> productPage = productDao.findAll(pageRequest);
        Assert.assertNotNull(productPage);
    }

    @Test
    public void save() throws Exception {
        Product product = new Product();
        product.setProductId("002");
        product.setProductName("燕麦粥");
        product.setProductPrice(new BigDecimal(2.2));
        product.setCategoryType(2);
        product.setProductStock(200);
        Product product1 = productDao.save(product);
        Assert.assertNotNull(product1);
    }

}