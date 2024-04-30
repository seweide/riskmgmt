package com.ebest.dayu.product.service.impl;

import com.ebest.dayu.product.Dto.PrdgrpRelationshipDto;
import com.ebest.dayu.product.Do.PrdgrpRelationshipDo;
import com.ebest.dayu.product.service.IPrdgrpRelationshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品与分组关系，一个产品可以在多个分组中，一个分组可以包含多个产品 服务实现类
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Service
public class PrdgrpRelationshipServiceImpl extends ServiceImpl<PrdgrpRelationshipDo, PrdgrpRelationshipDto> implements IPrdgrpRelationshipService {

}
