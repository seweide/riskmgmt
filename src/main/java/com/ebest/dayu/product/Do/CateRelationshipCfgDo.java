package com.ebest.dayu.product.Do;

import com.ebest.dayu.product.Dto.CateRelationshipCfgDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 类目关系表，该结构为森林，一个类目可以有0到多个父亲或0到多个儿子 Mapper 接口
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
public interface CateRelationshipCfgDo extends BaseMapper<CateRelationshipCfgDto> {

}
