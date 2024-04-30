package com.ebest.dayu.product.service.impl;

import com.ebest.dayu.product.Dto.ProductSkuDto;
import com.ebest.dayu.product.Do.ProductSkuDo;
import com.ebest.dayu.product.service.IProductSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品所属sku，如红色+XL，不含价格与库存 服务实现类
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuDo, ProductSkuDto> implements IProductSkuService {

}
