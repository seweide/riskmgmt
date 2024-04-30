
-- 建库并指定默认编码
-- drop database if exists riskmgmt;
-- create database riskmgmt default charset utf8mb4 collate utf8mb4_general_ci;

-- 建表并指定存储引擎
drop table if exists riskmgmt.task_record;
create table riskmgmt.task_record(
  id bigint auto_increment primary key,
  report_date date not null comment '任务日期',
  report_type tinyint not null comment '任务类型 1=万链指数预警,2=DTS预警',
  start_time datetime comment '任务开始时间',
  end_time datetime comment '任务结束时间',
  task_status char(1) default 'I' comment '任务状态 I=初始化, P=处理中,S=成功,F=失败',
  notice_time datetime comment '通知时间',
  notice_status char(1) comment '通知结果 S=成功,F=失败',
  jpa_version int default 0 not null comment '乐观锁版本',
  deleted char(1) default '0' not null comment '是否删除 0=否,1=是',
  remark varchar(200) comment '备注',
  created_time datetime default current_timestamp comment '创建时间',
  updated_time datetime default current_timestamp on update current_timestamp comment '更新时间'
  ) engine=InnoDB comment '任务记录表';

drop table if exists riskmgmt.wlzs_warning_sum;
create table riskmgmt.wlzs_warning_sum(
  id bigint auto_increment primary key,
  report_date date not null comment '报告日期',
  corp_name varchar(50) not null comment '企业名称,唯一',
  hight_risk_cnt int default 0 comment '高级风险数量',
  middle_risk_cnt int default 0 comment '中级风险数量',
  low_risk_cnt int default 0 comment '低级风险数量',
  jpa_version int default 0 not null comment '乐观锁版本',
  deleted char(1) default '0' not null comment '是否删除 0=否,1=是',
  remark varchar(200) comment '备注',
  created_time datetime default current_timestamp comment '创建时间',
  updated_time datetime default current_timestamp on update current_timestamp comment '更新时间'
  ) engine=InnoDB comment '万链指数风险预警汇总表';

drop table if exists riskmgmt.dts_warning_sum;
create table riskmgmt.dts_warning_sum(
  id bigint auto_increment primary key,
  report_date date not null comment '报告日期',
  corp_name varchar(50) not null comment '企业名称,唯一',
  hit_rules_1 varchar(50) not null comment '命中规则1',
  hit_rules_2 varchar(50) not null comment '命中规则2',
  hit_rules_3 varchar(50) not null comment '命中规则3',
  jpa_version int default 0 not null comment '乐观锁版本',
  deleted char(1) default '0' not null comment '是否删除 0=否,1=是',
  remark varchar(200) comment '备注',
  created_time datetime default current_timestamp comment '创建时间',
  updated_time datetime default current_timestamp on update current_timestamp comment '更新时间'
) engine=InnoDB comment 'dts指数风险预警汇总表';

drop table if exists riskmgmt.wlzs_warning_list;
create table riskmgmt.wlzs_warning_list(
  id bigint auto_increment primary key,
  report_date date not null comment '报告日期',
  corp_name varchar(50) not null comment '企业名称,唯一',
  level varchar(50)  comment '风险等级编码',
  level_name varchar(50) comment '风险等级名称',
  alerted_date varchar(20) comment '预警日期',
  category varchar(200) comment '预警内容',
  category_desc varchar(200) comment '预警描述',
  jpa_version int default 0 not null comment '乐观锁版本',
  deleted char(1) default '0' not null comment '是否删除 0=否,1=是',
  remark varchar(200) comment '备注',
  created_time datetime default current_timestamp comment '创建时间',
  updated_time datetime default current_timestamp on update current_timestamp comment '更新时间'
  ) engine=InnoDB comment '万链指数风险预警列表';

drop table if exists riskmgmt.wlzs_warning_detail;
create table riskmgmt.wlzs_warning_detail(
  id bigint auto_increment primary key,
  report_date date not null comment '报告日期',
  corp_name varchar(50) not null comment '企业名称,唯一',
  category_code varchar(200) comment '预警大类编码',
  category_name varchar(200) comment '预警大类名称',
  type_code varchar(200) comment '预警大类编码',
  type_name varchar(200) comment '预警大类名称',
  alerted_level varchar(50)  comment '预警风险等级编码',
  alerted_date varchar(20) comment '预警日期',
  alerted_content varchar(4000) comment '预警内容',
  person_executed_id varchar(50) comment '执行人ID',
  update_flag varchar(10) comment '预警是否可更新',
  jpa_version int default 0 not null comment '乐观锁版本',
  deleted char(1) default '0' not null comment '是否删除 0=否,1=是',
  remark varchar(200) comment '备注',
  created_time datetime default current_timestamp comment '创建时间',
  updated_time datetime default current_timestamp on update current_timestamp comment '更新时间'
  ) engine=InnoDB comment '万链指数风险预警详情';

drop table if exists riskmgmt.dts_month_sum;
create table riskmgmt.dts_month_sum(
  id bigint auto_increment primary key,
  khbm varchar(20) not null comment '客户编号,企业编号',
  khmc varchar(700) not null comment '客户名称,企业名称',
  qy varchar(150) comment '区域',
  qd varchar(150) comment '渠道',
  rq varchar(6) comment '日期月份,如201906',
  hzsc decimal(15,4) comment '合作时长',
  jhsj datetime comment '建户时间',
  mdsl decimal(10,0) comment '门店数量',
  thje decimal(15,4) comment '提货金额',
  thsl decimal(10,0) comment '提货数量',
  thcs decimal(10,0) comment '提货次数',
  lyl decimal(15,4) comment '履约率',
  hfyt decimal(15,4) comment '后返预提',
  syzc decimal(15,4) comment '商业政策',
  smzq varchar(200) comment '生命周期',
  lsjz varchar(200) comment '历史价值',
  aqjz varchar(200) comment '安全价值',
  qljz varchar(200) comment '潜力价值',
  wljz varchar(200) comment '未来价值',
  xhb decimal(15,4) comment '现汇比',
  jpa_version int default 0 not null comment '乐观锁版本',
  deleted char(1) default '0' not null comment '是否删除 0=否,1=是',
  remark varchar(200) comment '备注',
  created_time datetime default current_timestamp comment '创建时间',
  updated_time datetime default current_timestamp on update current_timestamp comment '更新时间'
  ) engine=InnoDB comment '经销商月提货统计';


alter table riskmgmt.task_record add index(report_type, report_date);
alter table riskmgmt.wlzs_warning_sum add index(report_date, corp_name);
alter table riskmgmt.wlzs_warning_list add index(report_date, corp_name);
alter table riskmgmt.wlzs_warning_detail add index(report_date, corp_name);
alter table riskmgmt.dts_month_sum add index(rq);
-- alter table riskmgmt.dts_month_sum Add index(rq, khmc);
