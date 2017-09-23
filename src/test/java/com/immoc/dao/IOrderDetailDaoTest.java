package com.immoc.dao;

import com.immoc.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by tandongmei on 2017/9/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IOrderDetailDaoTest {

    @Autowired
    private IOrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("00135");
        orderDetail.setOrderId("12345678");
        orderDetail.setProductId("002");
        orderDetail.setProductName("燕麦粥");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(10);
        orderDetailDao.save(orderDetail);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId("12345678");
        Assert.assertNotNull(orderDetailList);
    }

}