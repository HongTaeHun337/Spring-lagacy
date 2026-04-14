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
    <!-- socket.jsp -->
    <h1>WebSocket <small>사용법</small></h1>
    
    <div>
         <button type="button" id="btn-connect">연결하기</button>
         <button type="button" id="btn-disconnect" disabled>종료하기</button>
    </div>
    
    <hr>
    
    <div>
        <input type="text" class="long" id="msg" disabled>
        <button type="button" id="btn-echo" disabled>에코 테스트</button>
    </div>
    
    <hr>
    
    <div class="message full"></div>
    
    <script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script>
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>    
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        //서버측 종단점(주소)
        let ws = null;
        const url = "ws://localhost:8080/socket/server.do";
        
        //소켓 + 서버 연결
        $('#btn-connect').click(() => {
        	
        	//1. 소켓 생성 + 2. 서버 연결 요청
        	ws = new WebSocket(url); //전화 걸기
        	log('소켓 상태: ' + ws.readyState);
        	
        	//소켓 이벤트
        	//- ws.readyState
        	//- 0: 연결 전
        	//- 1: 연결 완료(***)
            //- 2: 연결 종료 중
            //- 3: 연결 종료 완료(***)
        	
        	//서버측에서 소켓 연결 요청을 수락 직후 > 서로 연결된 직 후 발생
        	ws.onopen = evt => {
        		log('서버와 연결되었습니다.');
        		log('소켓 상태: ' + ws.readyState);
        		
        		$('#btn-disconnect').prop('disabled', false);
        		$('#msg').prop('disabled', false);
        		$('#btn-echo').prop('disabled', false);
        		$('#btn-connect').prop('disabled', true);
        	};
        	
        	//서버가 클라이언트에게 메시지를 전달하는 순간 발생
        	ws.onmessage = evt => {
        		log('서버로부터 받은 데이터: ' + evt.data);
        	};
        	
        	//상대방과 연결이 끊기는 순간 발생
            ws.onclose = evt => {
            	log('서버와 연결이 종료되었습니다.');
                log('소켓 상태: ' + ws.readyState);
                
                $('#btn-disconnect').prop('disabled', true);
                $('#msg').prop('disabled', true);
                $('#btn-echo').prop('disabled', true);
                $('#btn-connect').prop('disabled', false);
            };
            
            //소켓 통신 에러
            ws.onerror = evt => {
            	$('#btn-disconnect').prop('disabled', true);
                $('#msg').prop('disabled', true);
                $('#btn-echo').prop('disabled', true);
                $('#btn-connect').prop('disabled', false);
            };
        	        	
        });
        
        $('#btn-disconnect').click(() => {
        	//소켓 닫기
        	ws.close();
        });
        
        $('#btn-echo').click(() => {
        	
        	if (ws == null || ws.readyState != 1) {
        		log('서버와 연결이 끊겼습니다.');
        		return;
        	}        	
        	
        	//현재 연결된 소켓을 사용해서 상대방에게 데이터를 전달하기
        	//ws.send('안녕하세요.');
        	ws.send($('#msg').val());
        	
        	log('메시지를 전송했습니다.');
        	
        });
        
        function log(msg) {
        	$('.message').prepend(`
        		<div>[\${dayjs().format('HH:mm:ss')}] \${msg}</div>		
        	`);
        }
    
    </script>
</body>
</html>






