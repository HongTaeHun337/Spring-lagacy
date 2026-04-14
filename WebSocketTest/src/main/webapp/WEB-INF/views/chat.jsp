<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK" />
    <style>
        html, body {
            padding: 0 !important;
            margin: 0 !important;
            background-color: #FFF !important; 
            display: block;
            overflow: hidden;
        }
        
        body > div {
            margin: 0; 
            padding: 0; 
        }
    
        #main {
            width: 400px;
            height: 510px;
            margin: 3px;
            display: grid;
            grid-template-rows: repeat(12, 1fr);
        }
        
        #header {
        
        }
        
        #header > h2 {      
            margin: 0px;
            margin-bottom: 10px;
            padding: 5px;
        }
    
        #list {
            border: 1px solid var(--border-color);
            box-sizing: content-box;
            padding: .5rem;
            grid-row-start: 2;
            grid-row-end: 12;
            font-size: 14px;
            overflow: auto;
        }
        
        #msg {
            margin-top: 3px;
        }
        
        #list .item {
            font-size: 14px;
            margin: 15px 0;
        }
        
        #list .item > div:first-child {
            display: flex;
        }
        
        #list .item.me > div:first-child {
            justify-content: flex-end;
        }
        
        #list .item.other > div:first-child {
            justify-content: flex-end;
            flex-direction: row-reverse;
        }
        
        #list .item > div:first-child > div:first-child {
            font-size: 10px;
            color: #777;
            margin: 3px 5px;
        }
        
        #list .item > div:first-child > div:nth-child(2) {
            border: 1px solid var(--border-color);
            display: inline-block;
            min-width: 100px;
            max-width: 250px;
            text-align: left;
            padding: 3px 7px;
        }
        
        #list .state.item > div:first-child > div:nth-child(2) {
            background-color: #EEE;
        }
        
        #list .item > div:last-child {
            font-size: 10px;
            color: #777;
            margin-top: 5px;
        }
        
        #list .me {
            text-align: right;
        }
        
        #list .other {
            text-align: left;
        }
        
        #list .msg.me.item > div:first-child > div:nth-child(2) {
            background-color: rgba(255, 99, 71, .2);
        }
        
        #list .msg.other.item > div:first-child > div:nth-child(2) {
            background-color: rgba(100, 149, 237, .2);
        }
        
        #list .secret.me.item > div:first-child > div:nth-child(2) {
            background-color: gold;
        }
        
        #list .secret.other.item > div:first-child > div:nth-child(2) {
            background-color: gold;
        }
        
        #list .msg img {
            width: 150px;
        }
    </style>
</head>
<body>
    <!-- chat.jsp -->
    <div id="main">
        <div id="header"><h2>WebSocker <small id="name"></small></h2></div>
        <div id="list"></div>
        <input type="text" id="msg" placeholder="대화 내용을 입력하시오." autofocus>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script>
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>    
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        /*
        
            클라이언트 <- (전송) -> 서버
            
            메시지(Message) 형식 > 프로토콜 설계
            - code: 상태코드
                - 1: 새로운 유저가 들어옴
                - 2: 기존 유저가 나감
                - 3: (전역)메시지 전달
                - 4: (귓속말)메시지 전달
                - 5: (전역)이모티콘 전달
            - sender: 메시지를 보내는 유저명
            - receiver: 메시지를 받는 유저명(1:1 채팅, 귓속말)
            - content: 메시지(대화 내용)
            - regdate: 날짜/시간  
        
        */
    
        let ws; //소켓
        const url = 'ws://192.168.10.30:8080/socket/chatserver.do';
    
        function connect(name) {
            
            //대화명 출력하기
            $('#name').text(name);
            
            //연결하기(+소캣 생성)
            ws = new WebSocket(url);
            log('채팅 서버에 연결을 시도합니다.');
            
            //소켓 이벤트 추가
            ws.onopen = evt => {
                log('채팅 서버에 연결이 되었습니다.');
                
                //내가 누군지 > 서버에게 알리기
                //- 접속한 대화명을 서버에게 전달하기
                //ws.send("강아지");
                const message = {
                    code: '1',
                    sender: $('#name').text(),
                    receiver: '',
                    content: '',
                    regdate: dayjs().format('YYYY-MM-DD HH:mm:ss')
                };
                
                //message 객체 > (직렬화 + JSON) > 1줄의 문자열
                //alert(JSON.stringify(message));
                ws.send(JSON.stringify(message));           
                
            };
            
            ws.onmessage = evt => {
                log('채팅 서버로부터 메시지가 도착했습니다.');
                
                //console.log(evt.data);
                
                //자바스크립트 객체 > JSON 문자열
                //- JSON.stringify(객체)
                
                //JSON 문자열 > 자바스크립트 객체
                //- JSON.parse(문자열)
                            
                let message = JSON.parse(evt.data);
                
                if (message.code == '1') {
                    print('', `[\${message.sender}]님이 들어왔습니다.`, 'other', 'state', message.regdate);
                } else if (message.code == '2') {
                    print('', `[\${message.sender}]님이 나갔습니다.`, 'other', 'state', message.regdate);
                } else if (message.code == '3') {
                    print(message.sender, message.content, 'other', 'msg', message.regdate);
                } else if (message.code == '4') {
                    print(message.sender, message.content, 'other', 'secret', message.regdate);
                } else if (message.code == '5') {
                    printEmoticon(message.sender, message.content, 'other', 'msg', message.regdate);
                }
                
                scrollList();
                
            };
            
            ws.onclose = evt => {
                log('채팅 서버에 연결이 종료되었습니다.');
            };
            
            ws.onerror = evt => {
                log(evt);
            };
            
            
        }
        
        function print(name, msg, side, state, time) {
            
            let temp = `
            <div class="item \${state} \${side}">
                <div>
                    <div>\${name}</div>
                    <div>\${msg}</div>
                </div>
                <div>\${time}</div>
            </div>      
            `;
            
            $('#list').append(temp);
            
        }



        
        function log(msg) {
            console.log(dayjs().format('HH:mm:ss'), msg);
        }
        
        //창을 닫기 직전
        $(window).on('beforeunload', () => {
            
            //부모창 초기화
            $(opener.document).find('.in').prop('disabled', false);
            $(opener.document).find('#name').val('').prop('readOnly', false);
            
            disconnect();
            
        });
        
        function disconnect() {
            
            //현재 사용자가 채팅방을 나간다고 서버에게 메시지 보내기
            const message = {
                code: '2',
                sender: $('#name').text(),
                receiver: '',
                content: '',
                regdate: dayjs().format('YYYY-MM-DD HH:mm:ss')
            };
            
            ws.send(JSON.stringify(message));
            
            //웹소켓 종료하기
            ws.close();
            
        }
        
        $('#msg').keydown(evt => {
            
            //- 안녕하세요. > 전체 메시지
            //- /고양이 안녕하세요. > 귓속말
            //- #심쿵 > 이모티콘
            
            //- #심쿵 안녕하세요.
            //- /고양이 #심쿵 안녕하세요...
            
            const regex = /^\/\S{1,}/;
            const regex2 = /^#[가-힣]{1,}/;
            const regex3 = //;
            const regex4 = //;
            
            if (evt.keyCode == 13) {
                
                if (regex.test($(evt.target).val().trim())) {
                    
                    //귓속말 메시지 전송하기
                    const message = {
                        code: '4',
                        sender: $('#name').text(),
                        receiver: $(evt.target).val().split(' ')[0].substr(1),
                        content: $(evt.target).val().substr($(evt.target).val().indexOf(' ') + 1),
                        regdate: dayjs().format('YYYY-MM-DD HH:mm:ss')
                    };
                    
                    ws.send(JSON.stringify(message));
                    
                    print(message.sender, message.content, 'me', 'secret', message.regdate);
                    
                } else if (regex2.test($(evt.target).val().trim())) {
                    
                    //이모티콘 전송하기
                    const message = {
                        code: '5',
                        sender: $('#name').text(),
                        receiver: '',
                        content: $(evt.target).val(), //- /심쿵
                        regdate: dayjs().format('YYYY-MM-DD HH:mm:ss')
                    };
                    
                    ws.send(JSON.stringify(message));
                    
                    printEmoticon(message.sender, message.content, 'me', 'msg', message.regdate);
                    
                } else {
                
                    //일반 메시지 전송하기
                    const message = {
                        code: '3',
                        sender: $('#name').text(),
                        receiver: '',
                        content: $(evt.target).val(),
                        regdate: dayjs().format('YYYY-MM-DD HH:mm:ss')
                    };
                    
                    ws.send(JSON.stringify(message));
                    
                    print(message.sender, message.content, 'me', 'msg', message.regdate);
                    
                }
                                
                $(evt.target).val('').focus();
                
                scrollList();
                
            }
            
        });
        
        function scrollList() {
            $('#list').scrollTop($('#list')[0].scrollHeight + 300);
        }
        
        
        function printEmoticon(name, msg, side, state, time) {
            let temp = `
                <div class="item \${state} \${side}">
                    <div>
                        <div>\${name}</div>
                        <div style='background-color: #FFF;border: 0;'><img src='/socket/resources/emoticon/\${msg.substr(1)}.png'></div>
                    </div>
                    <div>\${time}</div>
                </div>      
                `;
                
            $('#list').append(temp);
            
            setTimeout(scrollList, 100);
        }

        
        
        
        
        
    
    </script>
</body>
</html>




