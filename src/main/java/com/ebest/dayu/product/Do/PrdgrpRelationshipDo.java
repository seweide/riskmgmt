package com.ebest.dayu.product.Do;

import com.ebest.dayu.product.Dto.PrdgrpRelationshipDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 产品与分组关系，一个产品可以在多个分组中，一个分组可以包含多个产品 Mapper 接口
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
public interface PrdgrpRelationshipDo extends BaseMapper<PrdgrpRelationshipDto> {

}
