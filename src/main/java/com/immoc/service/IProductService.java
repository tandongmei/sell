package com.immoc.service;

import com.immoc.dto.CartDTO;
import com.immoc.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by tandongmei on 2017/9/6.
 */
public interface IProductService {

    Product findOne(String productId);

    /** 查询所有上架商品，即product_status = 0 */
    List<Product> findUpAll();

    Page<Product> findAll(Pageable pageable);

    Product save(Product product);

    // 加库存
    void decreaseStock(List<CartDTO> cartDTOList);

    // 减库存
    void increaseStock(List<CartDTO> cartDTOList);

    // 上架

    // 下架
}
