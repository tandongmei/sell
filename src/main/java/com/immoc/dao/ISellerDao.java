package com.immoc.dao;

import com.immoc.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tandongmei on 2017/9/7.
 */
public interface ISellerDao extends JpaRepository<Seller,String>{
}
