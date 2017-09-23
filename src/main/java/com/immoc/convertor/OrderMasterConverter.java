package com.immoc.convertor;

import com.immoc.dto.OrderDTO;
import com.immoc.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10 0010.
 */
public class OrderMasterConverter {

    public static OrderDTO orderMasterConverter(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static  List<OrderDTO> orderMasterConverter(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasterList){
            orderDTOList.add(orderMasterConverter(orderMaster));
        }
        return  orderDTOList;
    }
}
