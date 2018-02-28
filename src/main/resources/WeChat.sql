
-- 用户表uid openID phone  email status 
create table user(
	uid int primary key AUTO_INCREMENT,
	openID varchar(30) not null,        #微信openID
    uname varchar(20) ,         #真实姓名
    phone varchar(20) unique,  
    email varchar(20) unique,
    upic  varchar(200),                 #用户图片在服务器地址
    password varchar(20)                #用户密码 
    status int not null                 #0:被封号  1:正常 
);

-- 美发店表
create table rheinStore(
	rid int primary key AUTO_INCREMENT,
	rpic  varchar(200),                 #美发店头像
	rname varchar(50) not null,
	raddress varchar(200) not null,     #地址
	latitude double not null,           #纬度
	longitude double not null,          #经度
	rTCount int ,                       #最近三十天消费人数
	rtAmount double,					#最近三十天推荐票
	rEvaluation int,                    #好评数
	level int not null,                 #等级
	status int not null,                #状态 1：关闭  2：打开
	noteDate date                       #注册日期
);

--美发店负责联系人信息表
create table owner(
	owid int primary key AUTO_INCREMENT,
	owphone varchar(20),                #联系电话
	owemail varchar(20),                #联系邮箱
	owlicense varchar(60)              #营业执照照片地址
	rid int not null,                  #美发店编号
);

--美发店图片
create table rpicture(
	rpid int primary key AUTO_INCREMENT,
	rid int not null,                   #美发店编号
	rpurl varchar(200) not null         #图片地址
);
-- 套餐表
create table dinner(
	did int primary key AUTO_INCREMENT,
	dname varchar(100) not null,        #套餐名字	
	dPrice double not null ,            #套餐价格
	oprice double ,                     #原价
	dContent varchar(200),              #套餐内容	
	rid int,                            #美发店id
	lid                                 #标签编号
);


-- 套餐详情表
create table dinnerDetail(
	ddid int primary key AUTO_INCREMENT,
	did int,                           #套餐编号
	wid int ,                          #服务人员
);

-- 员工表
create table workers(
	wid int primary key AUTO_INCREMENT,
	wname varchar(20) not null,          #昵称
	wpic varchar(200),                   #员工头像
	pid int not null,                    #职位表编号
	raddress varchar(200) not null,     #地址 （如果所在美发店没有注册才需要）
	latitude double not null,           #纬度 （如果所在美发店没有注册才需要）
	longitude double not null,          #经度 （如果所在美发店没有注册才需要）
	phone varchar(20) not null unique,  
	sprice double,                       #服务费用
	rTCount int ,                        #服务人数
	rtAmount double,                     #推荐票数
	level int ,                          #荣誉等级=好评+服务人数+服务金额*计算公式
	noteDate date,                       #注册日期
	status int not null,                 #状态 
	rid int                              #美发店编号
);

-- 职位表
create table position(
	pid int primary key AUTO_INCREMENT,
	pname varchar(20) not null          #职位名称	
);
-- 评价表
create table evaluation(
	eid int primary key AUTO_INCREMENT,
	uid int not null,                    #评价人编号
	rid int ,                            #被评价的美发店编号
	wid int ,                            #被评价的服务人员编号
	attitude int,                        #服务态度星数  最低没有，最高五星   没有不计算平均
	technology int,                      #服务技术星数  最低没有，最高五星   没有不计算平均
	surroundings int  ,                  #环境星数     最低没有，最高五星   没有不计算平均
	message varchar(200),                #评价留言
	edate date                           #评价日期
);

--套餐标签表   标签表和美发店表是多对多      标签表和套餐表是多对多
create table lable(
	lid int primary key AUTO_INCREMENT,
	lname varchar(20),                  #标签名字
	rid int not null,					#美发店编号
	ltype int                           #1:套餐类  2：洗头师 3：理发师
);

-- 选套餐表     标签表和套餐表关联表
create table choose(
	lid int not null ,                  #标签编号
	did int not null                    #套餐标号
);

-- 订单表
create table orders (
	oid int primary key AUTO_INCREMENT,
	uid int not null ,                  #用户id
	rid int not null ,                  #美发店id
	price double not null ,             #订单金额
	realPrice double ,                  #实付金额
	status int not null  ,              #订单状态 1：未支付 2：用户取消 3：店家取消 4：店家确认支付 5：用户确认支付
	setTime  timestamp not null ,       #订单生成时间       
);

-- 订单详情表
create table orderDetail(
	odid int primary key AUTO_INCREMENT,
	wid int ,                           #服务人员编号
	wprice double ,                     #服务费用
	oid int not null,                   #订单编号
	odcontent varchar(50)               #服务内容
);

-- 优惠券
create table coupon(
	cid int primary key AUTO_INCREMENT,
	cdescription varchar(50)  ,         #优惠说明
	rid int  ,                          #美发店编号
	uid int  ,                          #用户编号
	ctype int not null ,                #优惠类型  1：系统优惠  2：店家优惠  
	cprice double not null,             #优惠券价值
	cdate timestamp not null,           #优惠券有效期	
	Instructions int not null ,         #使用方法  1：直接使用 2：连续消费多少次使用 3：满多少钱使用
	incount int ,                       #设置消费次数可使用
	inprice double                      #设置消费金额可使用
);

-- 发型表
create table hairstyle(
	hid int primary key AUTO_INCREMENT,
	hname varchar(50) not null,			#发型名字
	hcount int , 						#发型推荐票数
	hconsumption int ,                  #这个发型被消费次数
	hdesc varchar(200),                 #发型介绍
	hsex int not null,                  #发型适合性别：1.男生 2女生
	hpic varchar(200)                   #
);

--发型轮播图表
create table hpicture(
	hpid int primary key AUTO_INCREMENT,
	hid int,    						#发型编码
	hpurl varchar(200) 					#图片地址					
);