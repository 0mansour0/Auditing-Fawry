use auditing;

insert into USERS (NAME,EMAIL,TITLE,PHOTO) 
values("hany","hany@gmail.com","Team Lead Architecture","photo1");

insert into USERS (NAME,EMAIL,TITLE,PHOTO) 
values("Ahmed","ahmed@gmail.com","Software Engineer","photo2");

insert into BUSINESS_ENTITY (NAME) 
values("hany's BE");

insert into BUSINESS_ENTITY (NAME) 
values("ahmed's BE");

insert into APPLICATION (NAME) 
values("Order");

insert into ACTION_TYPE (CODE,NAME_AR,NAME_EN,MESSAGE_TEMPLATE_En,MESSAGE_TEMPLATE_Ar) 
values("Order_Refunded","استرجاع طلب","order refunded","User ${user.value} refunded order ${order.value} created by customer: ${customer.value}","المستخدم  ${user.value}  استرجع طلب  ${order.value}  تم انشاءه بواسطه العميل ${customer.value}");

insert into ACTION_TYPE (CODE,NAME_AR,NAME_EN,MESSAGE_TEMPLATE_AR,MESSAGE_TEMPLATE_EN) 
values("Order_Created","انشاء الطلب","order created","العميل ${customer.value} قام بإنشاء طلب ${order.value} لشراء منتج ${product.value}","customer ${customer.value} created order ${order.value} to buy product ${product.value}");

insert into PARAM_TYPE (CODE,NAME_AR,NAME_EN) 
values("customer","عميل","customer");

insert into PARAM_TYPE (CODE,NAME_AR,NAME_EN) 
values("product","منتج","product");

insert into PARAM_TYPE (CODE,NAME_AR,NAME_EN) 
values("order","طلب","order");