create table tblAddress(
    seq number primary key ,
    name varchar2(30) not null ,
    age number(3) not null ,
    address varchar2(300) not null ,
    gender char(1) not null
);


create sequence seqAddress;

insert into tblAddress (seq, name, age, address, gender)
    values (seqAddress.nextval, '홍길동',20,'서울시 강남구 대치동','m');

insert into tblAddress (seq, name, age, address, gender)
    values (seqAddress.nextval, '강아지',3,'서울시 강남구 역삼동','m');

insert into tblAddress (seq, name, age, address, gender)
    values (seqAddress.nextval, '고양이',2,'서울시 강남구 천호동','f');

insert into tblAddress (seq, name, age, address, gender)
    values (seqAddress.nextval, '병아리',1,'서울시 강남구 둔촌동','m');

insert into tblAddress (seq, name, age, address, gender)
    values (seqAddress.nextval, '거북이',100,'서울시 강남구 길동','m');

select * from tblAddress;

commit ;

select * from tblAddress where seq =1;