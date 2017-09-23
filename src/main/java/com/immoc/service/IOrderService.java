package com.immoc.service;

import com.immoc.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by Administrator on 2017/9/10 0010.
 */
public interface IOrderService {

    // 创建订单
    OrderDTO create(OrderDTO orderDTO);

    // 取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    OrderDTO cancelOrder(String openid,String orderId);

    // 查询订单
    OrderDTO findOne(String orderId);

    OrderDTO findOrderOne(String orderId,String openid);

    // 查询订单列表
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

}
