create table reader_card(
    id    int primary key auto_increment,
    name  varchar(255) comment '姓名',
    sex   varchar(10) comment '性别',
    birth datetime comment '生日',
    address varchar(200) comment '地址',
    phone varchar(11) comment '联系电话'
)comment '读者信息表';
