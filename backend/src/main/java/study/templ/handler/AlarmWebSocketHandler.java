package study.templ.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import study.templ.domain.*;
import study.templ.service.UserService;

import javax.servlet.http.HttpSession;
import javax.websocket.OnError;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AlarmWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>(); //접속한 아이디 모두 가져옴.
    private final Map<String, WebSocketSession> userSessions = new HashMap<>(); //필요 아이디 정보만 가져옴.
    private Object StringUtils;
    private static final Logger logger = LoggerFactory.getLogger(AlarmWebSocketHandler.class);
    UserService userService;


    //clinet가 서버 접속에 성공했을 때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
        String senderId = getId(session);
        userSessions.put(senderId, session);
    }
//실시간으로 정보 확인해서 클라이언트에 알림 보내기
    //메시지를 보낼 때
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String senderId = getId(session); //메시지 보내는 사람(댓글 단 사람)의 아이디

        //protocol: cmd,댓글 작성자,게시글 작성자,게시글 이름 (reply,user2,user1,"백엔드 개발자 3명 구합니다." )
        //1. 댓글 달렸을 때 팀 주인에게 알림 가는 것
        String msg = message.getPayload();
        if (StringUtils != null) { //stringutils: 스트링 값이 널이더라도 오류반환 안함.
            String[] stringOne = msg.split(",");
            if (stringOne != null && stringOne.length == 4) {
                String cmd = stringOne[0]; //comment
                String commentWriter = stringOne[1];//댓글 단 사람
                String target_user = stringOne[2]; //팀 페이지 주인, owner가 좋지 않을까?
                String target_team = stringOne[3]; //팀 페이지 제목 title이 좋지 않을까?

                WebSocketSession ownerSession = userSessions.get(target_user);
                if ("comment".equals(cmd) && ownerSession != null) {
                    TextMessage txtMsg = new TextMessage(commentWriter + "님이" + target_team + " 게시글에 댓글을 남습니다.");
                    ownerSession.sendMessage(txtMsg); //팀 주인한테 댓글 달렸다는 메시지가 날라감.

                }
            }
        }
        //2.가입 신청 했을 때 가입자 신청 버튼 -> 팀 주인한테 메시지가 가는 방식이어야함.

        UserService userApplication = new UserService();
        Application application = new Application();
        Alarm alarm = new Alarm();
        int team_id = alarm.getTarget_team();
        int user_id = alarm.getTarget_user();
        String content = application.getContents();
        userApplication.createApplication(team_id, user_id, content);
        if (userApplication != null) {
            WebSocketSession ownerSession = userSessions.get(team_id);
            if (ownerSession != null) {
                TextMessage txtMsg = new TextMessage(user_id + "님이 가입신청을 했습니다. ");
                ownerSession.sendMessage(txtMsg);

            }

        }


        //3. 가입 수락이 되었을 때 target_user한테 연락이 가게 됨.

        Alarm alarm1 = new Alarm();
        Team team = new Team();
        int acceptTeam = alarm1.getTarget_team();
        int acceptUser = alarm1.getTarget_user();
        UserService acceptApplication = new UserService();
        if (acceptApplication.getUserById(acceptUser)!= null) { //팀에 지원한 유저(유저아이디)가 있다면
            WebSocketSession userSession = userSessions.get(user_id);
            if (userSession != null) {
                TextMessage txtMsg = new TextMessage(acceptTeam + "팀에 가입되었습니다.");
                userSession.sendMessage(txtMsg);

            }

        }
    }

        private String getId (WebSocketSession session){
            Map<String, Object> httpSession = session.getAttributes(); //로그인한 유저아이디 가져오기
            String user_id =(String)httpSession.get(userSessions);
            return user_id;
        }

        //연결이 끊어졌을 때
        @Override
        public void afterConnectionClosed (WebSocketSession session, CloseStatus status) throws Exception {
            HttpSession httpSession = (HttpSession) userSessions.get(session);
            logger.debug("WebSocket Session destroyed:" + session.getId());
            logger.debug("HTTP Session was:" + httpSession.getId());
            userSessions.remove(session);
        }

        @OnError
        public void handleError (Throwable e, Session userSession){
            e.printStackTrace();
        }
    }



