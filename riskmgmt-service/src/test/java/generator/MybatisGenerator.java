package generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.haier.hairy.rmp.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过mybatis生成mapper等代码
 *
 * @Author: Xie Hao
 * @Create: 2019-07-19 19:01
 **/
public class MybatisGenerator {

//    private final static String JDBC_URL = "jdbc:mysql://10.100.13.9:13306/riskmgmt?useUnicode=true&useSSL=false&characterEncoding=utf8";
private final static String JDBC_URL = "jdbc:mysql://localhost:3306/product?useUnicode=true&characterEncoding=utf8&useSSL=false&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";

    private final static String[] SUPER_ENTITY_COLUMN = {"jpa_version", "deleted", "remark", "created_time", "updated_time"};

    private final static String BASE_CODE_PATH = System.getProperty("user.dir") + "/src/main/java";

    public static void main(String[] args) {

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setOutputDir(BASE_CODE_PATH)
                .setFileOverride(true)
                .setAuthor("heweiwen")
                .setOpen(false)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setEntityName("%sDto")
                .setMapperName("%sDo")
                .setControllerName(null);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(JDBC_URL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("mysql");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityBuilderModel(true)
                .setEntityLombokModel(true)
                .setLogicDeleteFieldName("deleted")
                .setVersionFieldName("jpa_version")
                .setSuperEntityClass(BaseEntity.class);
                //.setSuperEntityColumns(SUPER_ENTITY_COLUMN);

        PackageConfig packageConfig = new PackageConfig();
        //Map<String, String> pathInfos = new HashMap<>();
        //pathInfos.put(ConstVal.XML_PATH, System.getProperty("user.dir") + "/src/main/resources/mapper");
        //pathInfos.put(ConstVal.ENTITY_PATH, BASE_CODE_PATH);
        packageConfig.setParent("com.ebest.dayu.product")
                //.setPathInfo(pathInfos)
                .setEntity("Dto")
                .setMapper("Do");

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .execute();
    }
}
