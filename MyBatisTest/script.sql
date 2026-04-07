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

select * from tblAddress where  gender = 'm';


create table tblPoint (
    seq number primary key ,
    point number default 100 not null ,
    aseq number not null references tblAddress(seq)
);

create sequence seqPoint;


--1. 회원 추가
insert into tblAddress (seq, name, age, address, gender)
    values (seqAddress.nextval, '닭',20,'서울시 강남구 대치동','m');


--*** 방금 추가한 회원 seq?
select max(seq) from tblAddress;

--2. 포인트 1000
insert into tblPoint (seq, point, aseq)
values (seqPoint.nextval, 1000, (select max(seq) from tblAddress));


insert into tblPoint (seq, point, aseq)
values (seqPoint.nextval, 1000, 3 );


SELECT
    a.seq, a.name, a.age, a.address, a.gender,
    p.seq as pseq, p.point, p.aseq
from tblAddress  a
         left outer join tblPoint p
            on a.seq = p.aseq;

commit ;



-- tblInsa : tblProject
create table tblProject (
    seq number primary key ,
    name varchar2(100) not null ,
    location varchar2(100) not null ,
    num number not null references TBLINSA(num)
);

select *
from TBLINSA where BUSEO = '홍보부';

insert into tblProject (seq, name, location, num) values (1, '해외 수출','서울',1012);
insert into tblProject (seq, name, location, num) values (2, 'TV 광고','부산',1037);
insert into tblProject (seq, name, location, num) values (3, '고객 홍보','인천',1012);
insert into tblProject (seq, name, location, num) values (4, '자재 매입','강릉',1008);
insert into tblProject (seq, name, location, num) values (5, '재고 확보','대구',1008);

commit ;

select
    a.num, a.name, a.buseo, a.jikwi, a.basicpay as salary,
    p.seq, p.name as pname, p.location, p.num as inum
from TBLINSA a
    left outer join tblProject p
        on a.NUM = p.num
                    where a.BUSEO = '홍보부'
                        order by a.NUM ;

