package com.ebest.dayu.product.service.impl;

import com.ebest.dayu.product.Dto.CatePropertyCfgDto;
import com.ebest.dayu.product.Do.CatePropertyCfgDo;
import com.ebest.dayu.product.service.ICatePropertyCfgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类目属性表，用于定义一个类目下所拥有的属性：
如生产批次、品牌、材质、包装 服务实现类
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Service
public class CatePropertyCfgServiceImpl extends ServiceImpl<CatePropertyCfgDo, CatePropertyCfgDto> implements ICatePropertyCfgService {

}
