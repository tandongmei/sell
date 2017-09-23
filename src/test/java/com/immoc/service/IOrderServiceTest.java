package com.immoc.service;

import com.immoc.dto.OrderDTO;
import com.immoc.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/9/10 0010.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IOrderServiceTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("yuhang");
        orderDTO.setBuyerAddress("shanghai");
        orderDTO.setBuyerOpenid("007008");
        orderDTO.setBuyerPhone("13212345678");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("002");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("003");
        orderDetail2.setProductQuantity(1);
        orderDetailList.add(orderDetail2);
        orderDTO.setOrderDetailList(orderDetailList);

        orderService.create(orderDTO);
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerOpenid("001001");
        orderDTO.setOrderId("15050380963115919");
        orderService.cancel(orderDTO);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("12345679");
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        orderService.findList("001001",pageRequest);
    }


}