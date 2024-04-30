package com.ebest.dayu.product.service.impl;

import com.ebest.dayu.product.Dto.ProductTagDto;
import com.ebest.dayu.product.Do.ProductTagDo;
import com.ebest.dayu.product.service.IProductTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品与标签关系表 服务实现类
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Service
public class ProductTagServiceImpl extends ServiceImpl<ProductTagDo, ProductTagDto> implements IProductTagService {

}
