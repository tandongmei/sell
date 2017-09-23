package com.immoc.dao;

import com.immoc.entity.OrderMaster;
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
public class IOrderMasterDaoTest {

    @Autowired
    private IOrderMasterDao orderMasterDao;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12345679");
        orderMaster.setBuyerAddress("淮南");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerOpenid("901");
        orderMaster.setBuyerPhone("13244556677");
        orderMaster.setOrderAmount(new BigDecimal(20));
        orderMasterDao.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<OrderMaster> orderMasterList = orderMasterDao.findByBuyerOpenid("901",pageRequest);
        Assert.assertNotNull(orderMasterList);
    }

}