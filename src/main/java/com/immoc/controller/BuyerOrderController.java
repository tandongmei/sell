package com.immoc.controller;

import com.immoc.common.RestfulResponse;
import com.immoc.convertor.OrderFormConverter;
import com.immoc.dto.OrderDTO;
import com.immoc.enums.ExceptionEnum;
import com.immoc.exception.SellException;
import com.immoc.form.OrderForm;
import com.immoc.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/13 0013.
 */
@Api(value = "order", description = "订单接口")
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    private final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "创建订单")
    @PostMapping("/create")
    public RestfulResponse<Map<String, String>> create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                restfulResponse.setCode(ExceptionEnum.PARAM_ERROR.getCode());
                restfulResponse.setMsg(bindingResult.getFieldError().getDefaultMessage());
                return restfulResponse;
            }
            OrderDTO orderDTO = OrderFormConverter.orderFormConverter(orderForm);
            OrderDTO orderDTO2 = orderService.create(orderDTO);
            Map<String, String> map = new HashMap<>();
            map.put("orderId", orderDTO2.getOrderId());
            restfulResponse.setData(map);
        } catch (SellException se) {
            logger.error(se.getMessage());
            restfulResponse.setCode(se.getCode());
            restfulResponse.setMsg(se.getMsg());
        } catch (Exception e) {
            logger.error(e.getMessage());
            restfulResponse.setCode(ExceptionEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ExceptionEnum.SERVER_ERROR.getMsg());
        }
        return restfulResponse;
    }

    @ApiOperation(value = "订单列表")
    @GetMapping(value = "/list")
    public RestfulResponse<List<OrderDTO>> list(@RequestParam(value = "openid") String openid,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            if (StringUtils.isEmpty(openid)) {
                restfulResponse.setCode(ExceptionEnum.PARAM_ERROR.getCode());
                restfulResponse.setMsg(ExceptionEnum.PARAM_ERROR.getMsg());
                return restfulResponse;
            }
            PageRequest pageRequest = new PageRequest(page, size);
            Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
            restfulResponse.setData(orderDTOPage.getContent());
        } catch (Exception e) {
            logger.error(e.getMessage());
            restfulResponse.setCode(ExceptionEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ExceptionEnum.SERVER_ERROR.getMsg());
        }
        return restfulResponse;
    }

    @ApiOperation(value = "订单详情")
    @GetMapping(value = "/detail")
    public RestfulResponse<OrderDTO> detail(@RequestParam(value = "orderId") String orderId,
                                            @RequestParam(value = "openid") String openid) {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            OrderDTO orderDTO = orderService.findOrderOne(orderId, openid);
            restfulResponse.setData(orderDTO);
        } catch (SellException se) {
            logger.error(se.getMessage());
            restfulResponse.setCode(se.getCode());
            restfulResponse.setMsg(se.getMsg());
        } catch (Exception e) {
            logger.error(e.getMessage());
            restfulResponse.setCode(ExceptionEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ExceptionEnum.SERVER_ERROR.getMsg());
        }
        return restfulResponse;
    }

    @ApiOperation(value = "取消订单")
    @PostMapping(value = "/cancel")
    public RestfulResponse cancel(@RequestParam(value = "openid") String openid,
                                  @RequestParam(value = "orderId") String orderId) {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            orderService.cancelOrder(openid, orderId);
        } catch (SellException se) {
            logger.error(se.getMessage());
            restfulResponse.setCode(se.getCode());
            restfulResponse.setMsg(se.getMsg());
        } catch (Exception e) {
            logger.error(e.getMessage());
            restfulResponse.setCode(ExceptionEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ExceptionEnum.SERVER_ERROR.getMsg());
        }
        return restfulResponse;
    }
}
