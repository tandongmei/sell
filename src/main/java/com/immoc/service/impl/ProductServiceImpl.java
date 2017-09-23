package com.immoc.service.impl;

import com.immoc.dao.IProductDao;
import com.immoc.dto.CartDTO;
import com.immoc.entity.Product;
import com.immoc.enums.ExceptionEnum;
import com.immoc.enums.ProductStatusEnum;
import com.immoc.exception.SellException;
import com.immoc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tandongmei on 2017/9/6.
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductDao productDao;

    @Override
    public Product findOne(String productId) {
        return productDao.findOne(productId);
    }

    @Override
    public List<Product> findUpAll() {
        return productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            Product product = productDao.findOne(cartDTO.getProductId());
            if(product == null){
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            int amount = product.getProductStock() - cartDTO.getProductQuantity();
            if(amount < 0){
                throw new SellException(ExceptionEnum.PRODUCT_STOCK_EMPTY);
            }
            product.setProductStock(amount);
            productDao.save(product);
        }
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            Product product = productDao.findOne(cartDTO.getProductId());
            if(product == null){
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            int amount = product.getProductStock() + cartDTO.getProductQuantity();
            product.setProductStock(amount);
            productDao.save(product);
        }

    }
}
