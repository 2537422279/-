# Press-release-management-system
## 新闻发布管理系统
### SSM框架整合联系项目

### 数据库设计
```mysql
create table t_role(
    roleId int primary key comment '角色ID',
    roleName varchar(20) comment '角色姓名'
) comment '角色表';

create table t_user(
    userId int primary key comment '用户ID',
    userName varchar(20) comment '用户姓名',
    loginName varchar(20) comment '登录账号',
    password varchar(20) comment '登陆了密码',
    roleId int comment '角色ID',
    tel varchar(20) comment '联系电话',
    registerTime DATETIME comment '注册时间',
    status char(1) comment '注册状态 1:未启用 2:已启用 3:被禁用'
) comment '用户表';

create table t_category(
    categoryId int primary key comment '类别ID',
    categoryName varchar(20) comment '类别名称'
) comment '新闻类别表';

create table t_news(
    newId int primary key comment '类别ID',
    title varchar(60) comment '信息标题',
    contentTitle varchar(120) comment '信息内容标题',
    titlePicUrl varchar(120) comment '标题图（路径）',
    content text comment '信息内容',
    contentAbstract varchar(300) comment '内容摘要',
    keywords varchar(100) comment '关键词',
    categoryId int comment '信息类别ID',
    userId int comment '发布用户ID',
    author varchar(30) comment '作者（来源）',
    publishTime datetime comment '发布时间',
    clicks int comment '浏览次数',
    publishStatus char(1) comment '发布状态 1:发布 2:撤稿',
    constraint foreign key (categoryId) references t_category(categoryId) on update cascade on delete cascade ,
    constraint foreign key (userId) references t_user(userId) on update cascade on delete cascade
) comment '新闻表';


insert into t_role values(1,'管理员');
insert into t_role values (2,'信息员');

insert into t_user (userId,userName,loginName,password,status,roleId)
                values (1,'Pickle','admin','123456','2',1);

insert into t_category values (1,'今日头条');
insert into t_category values (2,'综合资讯');
insert into t_category values (3,'国内新闻');
insert into t_category values (4,'国际新闻');
```