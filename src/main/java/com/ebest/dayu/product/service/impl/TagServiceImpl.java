package com.ebest.dayu.product.service.impl;

import com.ebest.dayu.product.Dto.TagDto;
import com.ebest.dayu.product.Do.TagDo;
import com.ebest.dayu.product.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品标签定义表 服务实现类
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagDo, TagDto> implements ITagService {

}
