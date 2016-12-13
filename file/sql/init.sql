-- 用户
insert into toe_user(pk_id,user_name,password,user_type,create_date,update_date) values(1,'superadmin','e10adc3949ba59abbe56e057f20f883e',0,unix_timestamp(now()),unix_timestamp(now()));

-- 角色
insert into toe_role(pk_id,alias_name,role_name,create_date,update_date) values(1,'超级管理员','ROLE_SUPER_ADMIN',unix_timestamp(now()),unix_timestamp(now()));-- 超级管理员

insert into toe_role(pk_id,alias_name,role_name,create_date,update_date) values(2,'省级管理员','ROLE_D_PROVINCE',unix_timestamp(now()),unix_timestamp(now()));-- 省级管理员(电信侧)
insert into toe_role(pk_id,alias_name,role_name,create_date,update_date) values(3,'市级管理员','ROLE_D_CITY',unix_timestamp(now()),unix_timestamp(now()));-- 市级管理员(电信侧)
insert into toe_role(pk_id,alias_name,role_name,create_date,update_date) values(4,'区县管理员','ROLE_D_AREA',unix_timestamp(now()),unix_timestamp(now()));-- 区县管理员(电信侧)

insert into toe_role(pk_id,alias_name,role_name,create_date,update_date) values(5,'客户管理员','ROLE_CUSTOMER_ADMIN',unix_timestamp(now()),unix_timestamp(now()));-- 区县管理员(客户侧)
insert into toe_role(pk_id,alias_name,role_name,create_date,update_date) values(6,'集团管理员','ROLE_GROUP_ADMIN',unix_timestamp(now()),unix_timestamp(now()));-- 集团管理员(电信侧)

-- 用户-角色
insert into toe_user_role(user_id,role_id) values(1,1);

-- 权限
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(1,'platform','平台管理',0,1000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(2,'platform_list','平台列表',1,1100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(3,'platform_list_search','查询',2,1101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(4,'platform_list_add','添加',2,1102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(5,'platform_list_edit','编辑',2,1103,unix_timestamp(now()),unix_timestamp(now()));

insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(6,'project','项目管理',0,2000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(7,'project_list','项目列表',6,2100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(8,'project_list_search','查询',7,2101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(9,'project_list_add','添加',7,2102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(10,'project_list_edit','编辑',7,2103,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(11,'project_list_delete','删除',7,2104,unix_timestamp(now()),unix_timestamp(now()));

insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(12,'customer_manager','客户管理',0,3000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(13,'customer_manager_list','客户列表',12,3100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(14,'customer_manager_list_search','查询',13,3101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(15,'customer_manager_list_add','添加客户',13,3102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(16,'customer_manager_list_edit','编辑',13,3103,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(17,'customer_manager_list_show','详情',13,3104,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(18,'customer_manager_list_devicebind','设备绑定',13,3105,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(19,'customer_manager_list_addchild','添加下级单位',13,3106,unix_timestamp(now()),unix_timestamp(now()));

insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(20,'account_manager','账号管理',0,4000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(21,'account_manager_customer','客户侧账号',20,4100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(22,'account_manager_telecom','电信侧账号',20,4200,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(23,'account_manager_customer_search','查询',21,4101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(24,'account_manager_customer_pwdreset','重置密码',21,4102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(25,'account_manager_customer_permissionassign','分配权限',21,04103,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(26,'account_manager_telecom_search','查询',22,4201,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(27,'account_manager_telecom_add','创建账号',22,4202,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(28,'account_manager_telecom_edit','编辑',22,4203,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(29,'account_manager_telecom_delete','删除',22,4204,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(30,'account_manager_telecom_pwdreset','重置密码',22,4205,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(31,'account_manager_telecom_permissionassign','分配权限',22,4206,unix_timestamp(now()),unix_timestamp(now()));

insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(32,'device_manager','设备管理',0,5000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(33,'device_manager_list','设备列表',32,5100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(34,'device_manager_list_search','查询',33,5101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(35,'device_manager_list_transfer','设备分配',33,5102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(36,'device_manager_list_infor','详情',33,5103,unix_timestamp(now()),unix_timestamp(now()));

insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(37,'portal_manager','Portal管理',0,6000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(38,'site_manager_list','站点列表',37,6100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(39,'site_manager_default','默认站点',37,6200,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(40,'site_manager_list_search','查询',38,6101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(41,'site_manager_list_add','添加站点',38,6102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(42,'site_manager_list_edit','编辑',38,6103,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(43,'site_manager_list_delete','删除',38,6104,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(44,'site_manager_default_search','查询',39,6201,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(45,'site_manager_default_add','添加默认站点',39,6202,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(46,'site_manager_default_edit','编辑',39,6203,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(47,'site_manager_default_delete','删除',39,6204,unix_timestamp(now()),unix_timestamp(now()));

-- insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(48,'templet_manager','模板管理',0,7000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(49,'templet_manager_list','模板列表',37,7100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(50,'templet_manager_list_search','查询',49,7101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(51,'templet_manager_list_add','添加模板',49,7102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(52,'templet_manager_list_edit','编辑',49,7103,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(53,'templet_manager_list_delete','删除',49,7104,unix_timestamp(now()),unix_timestamp(now()));

-- insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(54,'strategy_manager','推送策略',0,8000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(55,'strategy_manager_list','策略列表',37,8100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(56,'strategy_manager_search','查询',55,8101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(57,'strategy_manager_add','添加推送策略',55,8102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(58,'strategy_manager_edit','编辑',55,8103,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(59,'strategy_manager_delete','删除',55,8104,unix_timestamp(now()),unix_timestamp(now()));

insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(60,'user_manager','用户管理',0,9000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(61,'user_manager_list','用户列表',60,9100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(62,'user_manager_whitelist','白名单管理',60,9200,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(63,'user_manager_blacklist','黑名单管理',60,9300,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(64,'user_manager_list_search','查询',61,9101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(65,'user_manager_list_add','新增用户',61,9102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(66,'user_manager_list_batchimport','批量导入',61,9103,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(67,'user_manager_list_downloadtemplate','下载模板',61,9104,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(68,'user_manager_list_edit','编辑',61,9105,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(69,'user_manager_list_delete','删除',61,9106,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(70,'user_manager_whitelist_search','查询',62,9201,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(71,'user_manager_whitelist_add','添加白名单',62,9202,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(72,'user_manager_whitelist_remove','移除白名单',62,9203,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(73,'user_manager_blacklist_search','查询',63,9301,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(74,'user_manager_blacklist_add','添加黑名单',63,9302,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(75,'user_manager_blacklist_remove','移除黑名单',63,9303,unix_timestamp(now()),unix_timestamp(now()));
--日志权限（第8迭代修改 日志管理改为系统配置）
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(76,'log_manager','系统配置',0,10000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(77,'log_manager_exceptionlog','异常日志',76,10100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(78,'log_manager_sysconfiger','参数配置',76,10200,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(79,'log_manager_exceptionlog_info','详情',77,10101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(80,'log_manager_exceptionlog_search','查询',77,10102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(81,'log_manager_sysconfiger_search','查询',78,10201,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(82,'log_manager_sysconfiger_add','新增',78,10202,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(83,'log_manager_sysconfiger_info','详情',78,10203,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(84,'log_manager_sysconfiger_edit','编辑',78,10204,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(85,'log_manager_sysconfiger_delete','删除',78,10205,unix_timestamp(now()),unix_timestamp(now()));
--统计权限  portal统计
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(86,'statistical_analysis','统计分析',0,11000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(87,'statistical_analysis_portal','portal统计',86,11100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(88,'statistical_analysis_portal_search','查询',87,11101,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(89,'statistical_analysis_portal_import','导出',87,11102,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(90,'statistical_analysis_portal_infor','查看',87,11103,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(91,'statistical_analysis_portal_pvdate','PV日查询',87,11104,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(92,'statistical_analysis_portal_pvmonth','PV月查询',87,11105,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(93,'statistical_analysis_portal_uvdate','UV日查询',87,11106,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(94,'statistical_analysis_portal_uvmonth','UV月查询',87,11107,unix_timestamp(now()),unix_timestamp(now()));
--客户新增权限
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(95,'customer_manager_list_importchild','批量添加',13,3107,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(96,'customer_manager_list_importbatch','批量导入',13,3108,unix_timestamp(now()),unix_timestamp(now()));
--统计权限 用户统计新增
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(97,'statistical_analysis_user','用户统计',86,11200,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(98,'statistical_analysis_user_search','查询',97,11201,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(99,'statistical_analysis_user_infor','查看',97,11202,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(100,'statistical_analysis_user_day','用户数按日查询',97,11203,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(101,'statistical_analysis_user_month','用户数按月查询',97,11204,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(102,'statistical_analysis_user_dayinfor','用户数按日详细数据',97,11205,unix_timestamp(now()),unix_timestamp(now()));
--客流分析
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(103,'app_manager','应用管理',0,12000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(104,'app_manager_passenger_analysis','客流分析',103,12100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(105,'app_manager_microshop','微旺铺',103,12200,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(106,'app_manager_content','内容发布',103,12300,unix_timestamp(now()),unix_timestamp(now()));
--统计权限 设备统计新增
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(107,'statistical_analysis_device','设备统计',86,11300,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(108,'statistical_analysis_device_search','查询',107,11301,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(109,'statistical_analysis_device_export','导出',107,11302,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(110,'statistical_analysis_device_next','关键指标',107,11303,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(111,'statistical_analysis_device_trend','趋势',107,11304,unix_timestamp(now()),unix_timestamp(now()));
--设备管理 增加设备监控
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(112,'device_manager_monitor','设备监控',32,5200,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(113,'device_manager_monitor_search','查询',112,5201,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(114,'device_manager_monitor_export','导出',112,5202,unix_timestamp(now()),unix_timestamp(now()));
--用户认证记录
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(115,'auth_log','用户认证记录',60,9400,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(116,'auth_log_list_search','查询',115,9401,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(117,'auth_log_list_export','导出',115,9402,unix_timestamp(now()),unix_timestamp(now()));
--统计分析 wifi地图
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(118,'statistical_analysis_wifi','WiFi地图',86,11400,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(119,'statistical_analysis_wifi_search','查询',118,11401,unix_timestamp(now()),unix_timestamp(now()));
--第8迭代新增
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(120,'log_manager_noperception','无感知配置',76,10300,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(121,'log_manager_theme','换肤配置',76,10400,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(122,'log_manager_theme_list','皮肤库',121,10401,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(123,'log_manager_requestlog','请求日志',76,10500,unix_timestamp(now()),unix_timestamp(now()));

insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(124,'heatmap','热点地图',0,13000,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(125,'heatmap_add','新增设备',124,13100,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(126,'heatmap_config','热点配置',124,13200,unix_timestamp(now()),unix_timestamp(now()));

insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(127,'templet_manager_list_sys','同步',49,7105,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(128,'app_manager_giantkey','集智集客',103,12400,unix_timestamp(now()),unix_timestamp(now()));
--第9迭代新增---awifi覆盖
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(129,'statistical_analysis_awificover','爱WiFi覆盖统计',86,11500,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(130,'statistical_analysis_awifidetail','爱WiFi覆盖明细',86,11600,unix_timestamp(now()),unix_timestamp(now()));
--第11迭代新增
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(131,'account_manager_admin','管理员账号',20,4300,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(132,'device_manager_hot','热点管理',32,5300,unix_timestamp(now()),unix_timestamp(now()));
--第十二迭代新增
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(133,'log_manager_operation','操作日志',76,10300,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(134,'templet_manager_list_copy','复制',49,7105,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(135,'site_manager_list_copy','复制',38,6105,unix_timestamp(now()),unix_timestamp(now()));
--修改ssid
insert into toe_permission(pk_id,code,alias_name,parent_id,order_no,create_date,update_date) values(136,'device_manager_monitor_editssid','修改SSID',112,5203,unix_timestamp(now()),unix_timestamp(now()));


--权限-角色
insert into toe_user_permission(permission_id,user_id) values(1,1);
insert into toe_user_permission(permission_id,user_id) values(2,1);
insert into toe_user_permission(permission_id,user_id) values(3,1);
insert into toe_user_permission(permission_id,user_id) values(4,1);
insert into toe_user_permission(permission_id,user_id) values(5,1);
insert into toe_user_permission(permission_id,user_id) values(6,1);
insert into toe_user_permission(permission_id,user_id) values(7,1);
insert into toe_user_permission(permission_id,user_id) values(8,1);
insert into toe_user_permission(permission_id,user_id) values(9,1);
insert into toe_user_permission(permission_id,user_id) values(10,1);
insert into toe_user_permission(permission_id,user_id) values(11,1);
insert into toe_user_permission(permission_id,user_id) values(12,1);
insert into toe_user_permission(permission_id,user_id) values(13,1);
insert into toe_user_permission(permission_id,user_id) values(14,1);
insert into toe_user_permission(permission_id,user_id) values(15,1);
insert into toe_user_permission(permission_id,user_id) values(16,1);
insert into toe_user_permission(permission_id,user_id) values(17,1);
insert into toe_user_permission(permission_id,user_id) values(18,1);
insert into toe_user_permission(permission_id,user_id) values(19,1);
insert into toe_user_permission(permission_id,user_id) values(20,1);
insert into toe_user_permission(permission_id,user_id) values(21,1);
insert into toe_user_permission(permission_id,user_id) values(22,1);
insert into toe_user_permission(permission_id,user_id) values(23,1);
insert into toe_user_permission(permission_id,user_id) values(24,1);
insert into toe_user_permission(permission_id,user_id) values(25,1);
insert into toe_user_permission(permission_id,user_id) values(26,1);
insert into toe_user_permission(permission_id,user_id) values(27,1);
insert into toe_user_permission(permission_id,user_id) values(28,1);
insert into toe_user_permission(permission_id,user_id) values(29,1);
insert into toe_user_permission(permission_id,user_id) values(30,1);
insert into toe_user_permission(permission_id,user_id) values(31,1);
insert into toe_user_permission(permission_id,user_id) values(32,1);
insert into toe_user_permission(permission_id,user_id) values(33,1);
insert into toe_user_permission(permission_id,user_id) values(34,1);
insert into toe_user_permission(permission_id,user_id) values(35,1);
insert into toe_user_permission(permission_id,user_id) values(36,1);
insert into toe_user_permission(permission_id,user_id) values(37,1);
insert into toe_user_permission(permission_id,user_id) values(38,1);
insert into toe_user_permission(permission_id,user_id) values(39,1);
insert into toe_user_permission(permission_id,user_id) values(40,1);
insert into toe_user_permission(permission_id,user_id) values(41,1);
insert into toe_user_permission(permission_id,user_id) values(42,1);
insert into toe_user_permission(permission_id,user_id) values(43,1);
insert into toe_user_permission(permission_id,user_id) values(44,1);
insert into toe_user_permission(permission_id,user_id) values(45,1);
insert into toe_user_permission(permission_id,user_id) values(46,1);
insert into toe_user_permission(permission_id,user_id) values(47,1);
insert into toe_user_permission(permission_id,user_id) values(48,1);
insert into toe_user_permission(permission_id,user_id) values(49,1);
insert into toe_user_permission(permission_id,user_id) values(50,1);
insert into toe_user_permission(permission_id,user_id) values(51,1);
insert into toe_user_permission(permission_id,user_id) values(52,1);
insert into toe_user_permission(permission_id,user_id) values(53,1);
insert into toe_user_permission(permission_id,user_id) values(54,1);
insert into toe_user_permission(permission_id,user_id) values(55,1);
insert into toe_user_permission(permission_id,user_id) values(56,1);
insert into toe_user_permission(permission_id,user_id) values(57,1);
insert into toe_user_permission(permission_id,user_id) values(58,1);
insert into toe_user_permission(permission_id,user_id) values(59,1);
insert into toe_user_permission(permission_id,user_id) values(60,1);
insert into toe_user_permission(permission_id,user_id) values(61,1);
insert into toe_user_permission(permission_id,user_id) values(62,1);
insert into toe_user_permission(permission_id,user_id) values(63,1);
insert into toe_user_permission(permission_id,user_id) values(64,1);
insert into toe_user_permission(permission_id,user_id) values(65,1);
insert into toe_user_permission(permission_id,user_id) values(66,1);
insert into toe_user_permission(permission_id,user_id) values(67,1);
insert into toe_user_permission(permission_id,user_id) values(68,1);
insert into toe_user_permission(permission_id,user_id) values(69,1);
insert into toe_user_permission(permission_id,user_id) values(70,1);
insert into toe_user_permission(permission_id,user_id) values(71,1);
insert into toe_user_permission(permission_id,user_id) values(72,1);
insert into toe_user_permission(permission_id,user_id) values(73,1);
insert into toe_user_permission(permission_id,user_id) values(74,1);
insert into toe_user_permission(permission_id,user_id) values(75,1);
insert into toe_user_permission(permission_id,user_id) values(76,1);
insert into toe_user_permission(permission_id,user_id) values(77,1);
insert into toe_user_permission(permission_id,user_id) values(78,1);
insert into toe_user_permission(permission_id,user_id) values(79,1);
insert into toe_user_permission(permission_id,user_id) values(80,1);
insert into toe_user_permission(permission_id,user_id) values(81,1);
insert into toe_user_permission(permission_id,user_id) values(82,1);
insert into toe_user_permission(permission_id,user_id) values(83,1);
insert into toe_user_permission(permission_id,user_id) values(84,1);
insert into toe_user_permission(permission_id,user_id) values(85,1);
insert into toe_user_permission(permission_id,user_id) values(86,1);
insert into toe_user_permission(permission_id,user_id) values(87,1);
insert into toe_user_permission(permission_id,user_id) values(88,1);
insert into toe_user_permission(permission_id,user_id) values(89,1);
insert into toe_user_permission(permission_id,user_id) values(90,1);
insert into toe_user_permission(permission_id,user_id) values(91,1);
insert into toe_user_permission(permission_id,user_id) values(92,1);
insert into toe_user_permission(permission_id,user_id) values(93,1);
insert into toe_user_permission(permission_id,user_id) values(94,1);
insert into toe_user_permission(permission_id,user_id) values(95,1);
insert into toe_user_permission(permission_id,user_id) values(96,1);
insert into toe_user_permission(permission_id,user_id) values(97,1);
insert into toe_user_permission(permission_id,user_id) values(98,1);
insert into toe_user_permission(permission_id,user_id) values(99,1);
insert into toe_user_permission(permission_id,user_id) values(100,1);
insert into toe_user_permission(permission_id,user_id) values(101,1);
insert into toe_user_permission(permission_id,user_id) values(102,1);
insert into toe_user_permission(permission_id,user_id) values(103,1);
insert into toe_user_permission(permission_id,user_id) values(104,1);
insert into toe_user_permission(permission_id,user_id) values(105,1);
insert into toe_user_permission(permission_id,user_id) values(106,1);
insert into toe_user_permission(permission_id,user_id) values(107,1);
insert into toe_user_permission(permission_id,user_id) values(108,1);
insert into toe_user_permission(permission_id,user_id) values(109,1);
insert into toe_user_permission(permission_id,user_id) values(110,1);
insert into toe_user_permission(permission_id,user_id) values(111,1);
insert into toe_user_permission(permission_id,user_id) values(112,1);
insert into toe_user_permission(permission_id,user_id) values(113,1);
insert into toe_user_permission(permission_id,user_id) values(114,1);
insert into toe_user_permission(permission_id,user_id) values(115,1);
insert into toe_user_permission(permission_id,user_id) values(116,1);
insert into toe_user_permission(permission_id,user_id) values(117,1);
insert into toe_user_permission(permission_id,user_id) values(118,1);
insert into toe_user_permission(permission_id,user_id) values(119,1);
--第8迭代
insert into toe_user_permission(permission_id,user_id) values(120,1);
insert into toe_user_permission(permission_id,user_id) values(121,1);
insert into toe_user_permission(permission_id,user_id) values(122,1);
insert into toe_user_permission(permission_id,user_id) values(123,1);
insert into toe_user_permission(permission_id,user_id) values(124,1);
insert into toe_user_permission(permission_id,user_id) values(125,1);
insert into toe_user_permission(permission_id,user_id) values(126,1);
insert into toe_user_permission(permission_id,user_id) values(127,1);
insert into toe_user_permission(permission_id,user_id) values(128,1);
--awifi覆盖
insert into toe_user_permission(permission_id,user_id) values(129,1);
insert into toe_user_permission(permission_id,user_id) values(130,1);
--管理员账号列表
insert into toe_user_permission(permission_id,user_id) values(131,1);
insert into toe_user_permission(permission_id,user_id) values(132,1);
--十二迭代新增
insert into toe_user_permission(permission_id,user_id) values(133,1);
insert into toe_user_permission(permission_id,user_id) values(134,1);
insert into toe_user_permission(permission_id,user_id) values(135,1);
insert into toe_user_permission(permission_id,user_id) values(136,1);

-- 应用
insert into toe_application(app_id,app_key,app_name,create_date,update_date) values('af93510d','2e76277aae45','营业厅',unix_timestamp(now()),unix_timestamp(now()));
insert into toe_application(app_id,app_key,app_name,create_date,update_date) values('107a66a5','60657ba3f778','会议应用',unix_timestamp(now()),unix_timestamp(now()));
insert into toe_application(app_id,app_key,app_name,login_url,create_date,update_date) values('ecd6b7ed','28e57302167d','微旺铺','http://w.zj189.cn/WWP-Mgr/apiForAwifi?type=LOGIN',unix_timestamp(now()),unix_timestamp(now()));
insert into toe_application(app_id,app_key,app_name,login_url,create_date,update_date) values('3790289e','a230f75616a9','客流分析','',unix_timestamp(now()),unix_timestamp(now()));
insert into toe_application(app_id,app_key,app_name,login_url,create_date,update_date) values('31075304','38ec2c5aeb26','爱考勤','',unix_timestamp(now()),unix_timestamp(now()));
-- 第8迭代新增
insert into toe_application(app_id,app_key,app_name,login_url,create_date,update_date) values('b9e94a41','02dd2572654c','集智集客','',unix_timestamp(now()),unix_timestamp(now()));
-- 第9迭代新增
insert into toe_application(app_id,app_key,app_name,login_url,create_date,update_date) values('6de49fd4','411259a8ec16','电访','',unix_timestamp(now()),unix_timestamp(now()));
insert into toe_application(app_id,app_key,app_name,login_url,params,create_date,update_date) values('70d3fe84','12e7f1720461','集团电渠','','{device_address}',unix_timestamp(now()),unix_timestamp(now()));
-- 第14迭代新增
insert into toe_application(app_id,app_key,app_name,login_url,params,create_date,update_date) values('6123f2d0','80f0bfb9fb24','广告','','',unix_timestamp(now()),unix_timestamp(now()));
insert into toe_application(app_id,app_key,app_name,login_url,params,create_date,update_date) values('efcf57bc','9c7b5ef22621','爱WiFi旅游行业','','',unix_timestamp(now()),unix_timestamp(now()));
-- 系统配置
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('台账每页记录数','page.pageSize','15',1,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('默认密码','system.default.password','123456',2,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('api接口秘钥','toe.admin.api.secret.key','B8221988ECAB410389C70C5B7D6B215A',3,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('授权正式员工数量','accredit.official.employees','2000',4,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('Portal平台域名','toe.portal','http://127.0.0.1:82',5,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('api调portal接口秘钥','toe.portal.api.secret.key','B8221988ECAB410389C70C5B7D6B215A',6,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('接人手机号认证接口','auth.phoneAuth.url','http://192.168.10.105:8088/auth/phoneAuth.htm',7,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('第三方对接秘钥','app.secret.key','7820c23a02c6',8,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('短信验证码内容','sms.authcode.content','您的验证码：$authcode$',9,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('短信网关地址','sms.gateway.url','http://pre-sms.51iwifi.com/awifi-sms/sms/send',10,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('短信验证码有效时间','sms.effective.time','30',12,unix_timestamp(now()),unix_timestamp(now()));
-- 系统配置 第6迭代新增
insert into toe_system_config(alias_name,param_key,param_value,order_no,create_date,update_date) values('接人获取验证码接口','auth.sendSmsCode.url','http://192.168.10.105:8088/auth/sendSmsCode.htm',7,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('接入临时放行接口','auth.provisionalRelease.url','http://192.168.36.11:8000/auth/phoneAuth.htm',7,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('接入踢下线接口','auth.offline.url','http://192.168.10.61:8087/userManage/kickUserOffline.htm',7,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('sftp请求地址url','sftp.req.host','192.168.10.63',13,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('sftp请求地址端口','sftp.req.post','22',14,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('sftp请求地址账号','sftp.req.username','root',15,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('sftp请求地址密码','sftp.req.password','admin@2015',16,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('上传文件保存地址(包含手机号)','sftp.push.csvfile.dscpath.containphone','/aaa',17,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('上传文件保存地址(不包含手机号)','ftp.push.csvfile.dscpath.notcontainphone','/aaa',18,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('sftp上传文件开始时间(时间戳)','sftp.upload.file.startdate','43200000',19,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('sftp上传文件结束时间(时间戳)','sftp.upload.file.enddate','42900000',20,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('sftp上传文件时间间隔','sftp.upload.file.time.interval','5',21,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('微旺铺爱wifi对应appId','iwifi.appid','ecd6b7ed',22,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('微旺铺auth地址','microShop.wechatwifiauth.url','http://wtest.zj189.cn/WWP-Mgr/wechatwifiauth',23,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('全局默认客户id','customer.default.id','180735',24,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('导出列表每页数量','sheet.show.size','10000',25,unix_timestamp(now()),unix_timestamp(now()));
insert into toe_system_config (alias_name,param_key,param_value,order_no,create_date,update_date)values('IVR语音认证号码','ivr.phone','057187939222',26,unix_timestamp(now()),unix_timestamp(now()));
