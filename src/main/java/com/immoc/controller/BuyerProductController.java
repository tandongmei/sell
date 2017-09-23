package com.immoc.controller;

import com.immoc.common.RestfulResponse;
import com.immoc.entity.Product;
import com.immoc.entity.ProductCategory;
import com.immoc.service.IProductCategoryService;
import com.immoc.service.IProductService;
import com.immoc.vo.ProductInfoVO;
import com.immoc.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tandongmei on 2017/9/8.
 */
@Api(value = "product",description = "商品接口")
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private final Logger logger = LoggerFactory.getLogger(BuyerProductController.class);

    @Autowired
    private IProductCategoryService productCategoryService;

    @Autowired
    private IProductService productService;

    @ApiOperation(value = "商品列表")
    @GetMapping("/list")
    public RestfulResponse<List<ProductVO>> findAll(){
        RestfulResponse<List<ProductVO>> restfulResponse = new RestfulResponse<>();
        try {
            // 查询所有上架商品
            List<Product> productList = productService.findUpAll();
            // 拼装所有上架商品的类别号
            List<Integer> categoryTypeList = new ArrayList<>();
            for(Product product : productList){
                categoryTypeList.add(product.getCategoryType());
            }
            // 根据商品类别号集合查找类别
            List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

            List<ProductVO> productVOList = new ArrayList<>();
            for(ProductCategory productCategory : productCategoryList){
                ProductVO productVO = new ProductVO();
                productVO.setCategoryName(productCategory.getCategoryName());
                productVO.setCategoryType(productCategory.getCategoryType());
                List<ProductInfoVO> productInfoVOList = new ArrayList<>();
                for(Product product : productList){
                    if(product.getCategoryType().equals(productCategory.getCategoryType())){
                        ProductInfoVO productInfoVO = new ProductInfoVO();
                        BeanUtils.copyProperties(product,productInfoVO);
                        productInfoVOList.add(productInfoVO);
                    }
                }
                productVO.setProductInfoVOList(productInfoVOList);
                productVOList.add(productVO);
            }
            restfulResponse.setData(productVOList);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return  restfulResponse;
    }


}
