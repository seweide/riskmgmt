package com.haier.hairy.rmp.config;

import com.haier.hairy.credit.response.wanlian.dto.Count;
import com.haier.hairy.credit.response.wanlian.dto.ListDto;
import com.haier.hairy.credit.response.wanlian.dto.Riskdesc;
import com.haier.hairy.rmp.dto.excel.WlzsWarningDetailRow;
import com.haier.hairy.rmp.dto.excel.WlzsWarningListRow;
import com.haier.hairy.rmp.dto.excel.WlzsWarningSumRow;
import com.haier.hairy.rmp.entity.WlzsWarningDetailEntity;
import com.haier.hairy.rmp.entity.WlzsWarningListEntity;
import com.haier.hairy.rmp.entity.WlzsWarningSumEntity;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.Date;

/**
 * 对象属性复制框架Orika配置
 * @Author: Xie Hao
 * @Create: 2019-07-26 16:53
 **/
public class OrikaConfig {

    private final static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    static{
        mapperFactory.classMap(Count.class, WlzsWarningSumEntity.class)
                .field("hight", "hightRiskCnt")
                .field("middle", "middleRiskCnt")
                .field("low", "lowRiskCnt")
                .customize(new CustomMapper<Count, WlzsWarningSumEntity>() {
                    @Override
                    public void mapAtoB( Count count, WlzsWarningSumEntity wlzsWarningSumEntity, MappingContext context) {
                        super.mapBtoA(wlzsWarningSumEntity, count, context);
                        wlzsWarningSumEntity.setCorpName(context.getProperty("corpName").toString());
                        wlzsWarningSumEntity.setReportDate((Date)context.getProperty("reportDate"));
                    }
                })
                .byDefault()
                .register();

        mapperFactory.classMap(Riskdesc.class, WlzsWarningListEntity.class)
                .field("time", "alertedDate")
                .field("categoryDes", "categoryDesc")
                .customize(new CustomMapper<Riskdesc, WlzsWarningListEntity>() {
                    @Override
                    public void mapAtoB(Riskdesc riskdesc, WlzsWarningListEntity wlzsWarningListEntity, MappingContext context) {
                        super.mapAtoB(riskdesc, wlzsWarningListEntity, context);
                        wlzsWarningListEntity.setCorpName(context.getProperty("corpName").toString());
                        wlzsWarningListEntity.setReportDate((Date)context.getProperty("reportDate"));
                    }
                })
                .byDefault()
                .register();

        mapperFactory.classMap(ListDto.class, WlzsWarningDetailEntity.class)
                .field("catgCode", "categoryCode")
                .field("catgName", "categoryName")
                .customize(new CustomMapper<ListDto, WlzsWarningDetailEntity>() {
                    @Override
                    public void mapAtoB(ListDto listDto, WlzsWarningDetailEntity wlzsWarningDetailEntity, MappingContext context) {
                        super.mapAtoB(listDto, wlzsWarningDetailEntity, context);
                        wlzsWarningDetailEntity.setCorpName(context.getProperty("corpName").toString());
                        wlzsWarningDetailEntity.setReportDate((Date)context.getProperty("reportDate"));
                    }
                })
                .byDefault()
                .register();

        mapperFactory.classMap(WlzsWarningSumRow.class, WlzsWarningSumEntity.class).byDefault().register();
        mapperFactory.classMap(WlzsWarningListRow.class, WlzsWarningListEntity.class).byDefault().register();
        mapperFactory.classMap(WlzsWarningDetailRow.class, WlzsWarningDetailEntity.class).byDefault().register();
    }

    /**
     * 获取克隆Mapper
     * @return
     */
    public static MapperFacade getMapper(){
        return mapperFactory.getMapperFacade();
    }
}
