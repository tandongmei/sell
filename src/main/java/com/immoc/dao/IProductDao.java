package com.immoc.dao;

import com.immoc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by tandongmei on 2017/9/7.
 */
public interface IProductDao extends JpaRepository<Product,String>{

    List<Product> findByProductStatus(Integer productStatus);

}
