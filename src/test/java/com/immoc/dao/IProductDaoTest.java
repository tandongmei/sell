package com.immoc.dao;

import com.immoc.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by tandongmei on 2017/9/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductDaoTest {

    @Autowired
    private IProductDao productDao;

    @Test
    public void saveTest() {
        Product product = new Product();
        product.setProductId("011");
        product.setProductName("香草冰");
        product.setProductPrice(new BigDecimal(5.5));
        product.setCategoryType(6);
        product.setProductStock(40);
        productDao.save(product);
    }

}