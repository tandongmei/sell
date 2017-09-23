package com.immoc.service.impl;

import com.immoc.convertor.OrderMasterConverter;
import com.immoc.dao.IOrderDetailDao;
import com.immoc.dao.IOrderMasterDao;
import com.immoc.dao.IProductDao;
import com.immoc.dto.CartDTO;
import com.immoc.dto.OrderDTO;
import com.immoc.entity.OrderDetail;
import com.immoc.entity.OrderMaster;
import com.immoc.entity.Product;
import com.immoc.enums.ExceptionEnum;
import com.immoc.enums.OrderStatusEnum;
import com.immoc.enums.PayStatusEnum;
import com.immoc.exception.SellException;
import com.immoc.service.IOrderService;
import com.immoc.service.IProductService;
import com.immoc.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10 0010.
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private IOrderDetailDao orderDetailDao;

    @Autowired
    private IOrderMasterDao orderMasterDao;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductDao productDao;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        BigDecimal orderAmount = new BigDecimal(0);
        List<CartDTO> cartDTOList = new ArrayList<>();
        String orderId = UUIDUtils.getUUID(); // 创建订单号
        // 1.查找商品（数量，总价）
        for (OrderDetail orderDetail : orderDetailList){
            Product product = productDao.findOne(orderDetail.getProductId());
            if(product == null){
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            // 计算总价
            orderAmount = product.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);

            // 订单详情入库
            BeanUtils.copyProperties(product,orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(UUIDUtils.getUUID());
            orderDetailDao.save(orderDetail);

            // 封装购物车
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterDao.save(orderMaster);

        // 扣库存
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderDTO.getOrderId());
        if(orderMaster == null){
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        // 判断状态
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ExceptionEnum.ORDER_CANCEL_ERROR);
        }
        // 更新订单
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster1 = orderMasterDao.save(orderMaster);
        if(orderMaster1 == null){
            throw new SellException(ExceptionEnum.ORDER_UPDATE_ERROR);
        }
        // 增加库存
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderDTO.getOrderId());
        List<CartDTO> cartDTOList = new ArrayList<>();
        for(OrderDetail orderDetail : orderDetailList){
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        productService.increaseStock(cartDTOList);
        // 退款操作,如果已经支付，则需要退款
        if(orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }
        return null;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            throw new SellException(ExceptionEnum.ORDER_OWNER_ERROR);
        }
        return cancel(orderDTO);
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(orderDetailList == null){
            throw new  SellException(ExceptionEnum.ORDER_DETAIL_EMPTY);
        }
        OrderDTO orderDTO = OrderMasterConverter.orderMasterConverter(orderMaster);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOrderOne(String orderId, String openid) {
        OrderDTO orderDTO = findOne(orderId);
        if(orderDTO == null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            throw new SellException(ExceptionEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderMaster> orderMasterList = orderMasterPage.getContent();

        List<OrderDTO> orderDTOList = OrderMasterConverter.orderMasterConverter(orderMasterList);

        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }
}
