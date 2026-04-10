<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK" />
</head>
<body>
    <!-- ex08.jsp -->
    <h1>REST Client</h1>
    
    <div>
        <button type="button" class="add" data-modal-button="add">추가하기</button>
        <button type="button" class="del" onclick="del();">삭제하기</button>
    </div>
    
    <table id="tbl1">
        <thead>
            <tr>
                <th></th>
                <th>번호</th>
                <th>이름</th>
                <th>나이</th>
                <th>성별</th>
                <th>주소</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="6">데이터가 없습니다.</td>
            </tr>
        </tbody>
    </table>
    
    <div id="loader" style="display: none;">
        <img src="/ajax/resources/images/loader.gif"
            style="width: 100px; display: block; margin: 0px auto; margin-top: -10px;">
    </div>
    
    <div>
        <!-- <button type="button" onclick="list();">주소록 가져오기</button> -->
        <button type="button" class="more" onclick="more();">더보기</button>        
    </div>
    
    
    
    <!-- 입력창 -->
    <div data-modal-window="add" data-modal-title="주소록 추가하기">
        
        <form id="form1">
        <table class="vertical">
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" id="name" class="short"></td>
            </tr>
            <tr>
                <th>나이</th>
                <td><input type="number" name="age" id="age" min="0" max="120"></td>
            </tr>
            <tr>
                <th>성별</th>
                <td>
                    <select name="gender" id="gender">
                        <option value="m">남자</option>
                        <option value="f">여자</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>주소</th>
                <td><input type="text" name="address" id="address" class="full"></td>
            </tr>
        </table>
        </form>
        
        <hr>
        
        <div>
            <button type="button" data-modal-ok="add" onclick="add();">추가하기</button>
            <button type="button" data-modal-cancel="add">닫기</button>
        </div>
        
    </div>
    
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>   
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        function list() {
        
            $.ajax({
                type: 'GET',
                url: '/ajax/address',
                dataType: 'json',
                success: function(list) {
                    //console.log(list);
                    //화면 출력 > 클라이언트 측 화면 구성 > Client-side Rendering > CSR
                    $('#tbl1 tbody').html('');
                    
                    list.forEach(item => {
                        $('#tbl1 tbody').append(`
            <tr>
                <td><input type="checkbox" name="seq" value="\${item.seq}"></td>
                <td>\${item.seq}</td>
                <td>\${item.name}</td>
                <td>\${item.age}</td>
                <td>\${item.gender}</td>
                <td>\${item.address}</td>
            </tr>
                        `); 
                    });
                    
                },
                error: function(a,b,c) {
                    console.log(a,b,c);
                }
            });
            
        }
        
        list();
        
        
        function add() {
                        
            /*
                jQuery Ajax > 입력 데이터 > 서버 전송
                
                1. QueryString
                - 'name=홍길동&age=20&gender=m&address=서울시'
                
                2. Object > (매핑) > QueryString
                data: {
                    name: $('#name').val(),
                    age: $('#age').val(),
                    gender: $('#gender').val(),
                    address: $('#address').val()
                }
                
                3. 직렬화
                - <form>의 모든 입력 양식을 직렬화(한줄의 문자열로 변화)
            
            */
            
            //name=홍길동&age=20&gender=m&address=서울시
            //console.log($('#form1').serialize());
            
             
            const data = {
                name: $('#name').val(),
                age: $('#age').val(),
                gender: $('#gender').val(),
                address: $('#address').val()
            }; 
            //자바스크립트 객체 > (변환) > JSON 형태의 문자열
            //- {"name":"홍길동","age":"20","address":"서울시"}
            //alert(JSON.stringify(data));
            
            $.ajax({
                
                //서버측 엔드 포인터
                type: 'POST',
                url: '/ajax/address',
                
                //서버에게 보내는 데이터
                data: JSON.stringify(data),
                contentType: 'application/json; charset=UTF-8',
                
                //수신하는 데이터
                dataType: 'json',
                success: function(result) {
                    
                    if (result == '1') {
                        list();
                    } else {
                        alert('실패했습니다.');
                    }
                },
                error: function(a,b,c) {
                    console.log(a,b,c);
                }
            });
            
        };
        
        let index = 6;
        
        function more() {
            
            $('#loader').show();
            $('.more').prop('disabled', true).css('cursor', 'not-allowed');
            
            setTimeout(() => {
                
                $.ajax({
                    type: 'GET',
                    url: '/ajax/address/more',
                    data: 'index=' + index,
                    dataType: 'json',
                    success: function(list) {
                        //추가 목록
                        //console.log(list);
                        
                        if (list.length == 0) {
                            alert('더 이상 가져올 항목이 없습니다.');
                            return;
                        }
                        
                        list.forEach(item => {
                            $('#tbl1 tbody').append(`
                                <tr>
                                    <td><input type="checkbox" name="seq" value="\${item.seq}"></td>
                                    <td>\${item.seq}</td>
                                    <td>\${item.name}</td>
                                    <td>\${item.age}</td>
                                    <td>\${item.gender}</td>
                                    <td>\${item.address}</td>
                                </tr>
                            `); 
                        });
                        
                        index += 5;
                        
                        $('#loader').hide();
                        $('.more').prop('disabled', false).css('cursor', 'default');
                        
                    },
                    error: function(a,b,c) {
                        console.log(a,b,c);
                    }
                }); 
                
            }, 500);
            
        }
        
        function del() {
            
            //console.log($('input[name=seq]:checked').val());
            
            /*
            const seqList = [];
            
            $('input[name=seq]:checked').each((index, item) => {
                seqList.push(item.value);
            });
            */
            
            const seqList = $('input[name=seq]:checked').map((index, item) => item.value).get();
                        
            //console.log(seqList);
            
            const data = {
                slist: seqList
            };
            
            $.ajax({
                type: 'DELETE',
                url: '/ajax/address',
                data: JSON.stringify(data),
                contentType: 'application/json; charset=UTF-8',
                dataType: 'json',
                success: function(result) {
                    if (result > 0) {
                        //alert('성공');
                        
                        //list();
                        
                        //[]
                        seqList.forEach(seq => {
                            //alert(seq);
                            $('#tbl1 tr td:nth-child(2)').each((index,item) => {
                                
                                if ($(item).text() == seq) {
                                    $(item).parent().remove();
                                }
                                
                            });
                            
                        });
                        
                    } else {
                        alert('삭제 실패');
                    }
                }
            });
            
        }
    
    </script>
</body>
</html>







