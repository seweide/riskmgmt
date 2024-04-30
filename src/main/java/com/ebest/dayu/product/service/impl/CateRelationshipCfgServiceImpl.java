package com.ebest.dayu.product.service.impl;

import com.ebest.dayu.product.Dto.CateRelationshipCfgDto;
import com.ebest.dayu.product.Do.CateRelationshipCfgDo;
import com.ebest.dayu.product.service.ICateRelationshipCfgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类目关系表，该结构为森林，一个类目可以有0到多个父亲或0到多个儿子 服务实现类
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Service
public class CateRelationshipCfgServiceImpl extends ServiceImpl<CateRelationshipCfgDo, CateRelationshipCfgDto> implements ICateRelationshipCfgService {

}
