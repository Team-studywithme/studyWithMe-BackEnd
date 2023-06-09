------------------------------ Avatar Data -----------------------------
INSERT INTO avatar(id, active, created_date, last_modified_date, nickname) values (null, 1, '2023-04-01T11:44:30', '2023-04-13T09:22:31', '프론트 주니어')
INSERT INTO avatar(id, active, created_date, last_modified_date, nickname) values (null, 1, '2023-04-02T12:42:21', '2023-04-13T15:45:21', '백린이')
INSERT INTO avatar(id, active, created_date, last_modified_date, nickname) values (null, 1, '2023-04-02T14:44:30', '2023-04-02T14:44:30', '프론트는 드림코딩')
INSERT INTO avatar(id, active, created_date, last_modified_date, nickname) values (null, 1, '2023-04-03T14:24:04', '2023-04-03T14:24:04', '옵티머스 프라임')
INSERT INTO avatar(id, active, created_date, last_modified_date, nickname) values (null, 1, '2023-04-03T14:14:22', '2023-04-03T14:14:22', '선풍기')
INSERT INTO avatar(id, active, created_date, last_modified_date, nickname) values (null, 1, '2023-04-03T14:00:11', '2023-04-03T14:00:11', '랄로')
INSERT INTO avatar(id, active, created_date, last_modified_date, nickname) values (null, 1, '2023-04-03T11:00:11', '2023-04-03T11:00:11', '손세정제')
INSERT INTO avatar(id, active, created_date, last_modified_date, nickname) values (null, 1, '2023-04-09T12:10:51', '2023-04-09T12:10:51', '맥중독자')

------------------------------ Account Data ------------------------------

INSERT INTO account(id, active, created_date, last_modified_date, email, avatar_id) values ('2222222222', 1, '2023-04-01T11:44:30', '2023-04-13T09:22:31', 'weihyuk@gmail.com', 1)
INSERT INTO account(id, active, created_date, last_modified_date, email, avatar_id) values ('1111111111', 1, '2023-04-02T12:42:21', '2023-04-13T15:45:21', 'hyoungseok@gmail.com', 2)
INSERT INTO account(id, active, created_date, last_modified_date, email, avatar_id) values ('3333333333', 1, '2023-04-02T14:44:30', '2023-04-02T14:44:30', 'dreamcode@gmail.com', 3)
INSERT INTO account(id, active, created_date, last_modified_date, email, avatar_id) values ('4444444444', 1, '2023-04-03T14:24:04', '2023-04-03T14:24:04', 'optimizer@gmail.com', 4)
INSERT INTO account(id, active, created_date, last_modified_date, email, avatar_id) values ('5555555555', 1, '2023-04-03T14:14:22', '2023-04-03T14:14:22', 'fan@gmail.com', 5)
INSERT INTO account(id, active, created_date, last_modified_date, email, avatar_id) values ('6666666666', 1, '2023-04-03T14:00:11', '2023-04-03T14:00:11', 'ralo@gmail.com', 6)
INSERT INTO account(id, active, created_date, last_modified_date, email, avatar_id) values ('7777777777', 1, '2023-04-03T11:00:11', '2023-04-03T11:00:11', 'handwash@gmail.com', 7)
INSERT INTO account(id, active, created_date, last_modified_date, email, avatar_id) values ('8888888888', 1, '2023-04-09T12:10:51', '2023-04-09T12:10:51', 'mac@gmail.com', 8)

------------------------------ Board Data ------------------------------
INSERT INTO board(id, active, created_date, last_modified_date, name) values (null, 1, '2023-03-29T07:22:39', '2023-03-29T07:22:39', 'matching')


------------------------------ Post Data ------------------------------
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-11T13:37:49', '2023-04-11T13:37:49', '자바 집중 스터디', 4, '스터디 주제 : 자바 집중 스터디 강의<br><br>스터디 목표 : 자바 문법 및 주요점들 집중 강의<br><br>예상 스터디 일정(횟수) : 1달 (주당 1회)<br><br><br><br> 1주차: 배열, 객체지향 프로그래밍<br>2주차: 예외처리, 유용한 클래스, 컬렉션 프레임웍<br>3주차: Generics, 열거형, annotation, 쓰레드<br>4주차: 람다와 스트림, 입출력(I/O), 네트워킹<br><br>weihyuk@gmail.com로 연락주세요~', 1, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-12T11:07:41', '2023-04-12T11:07:41', '[스프링부트] 직장인 퇴근 후 캠 스터디', 5, '스터디 주제 : 스프링부트 공부 하는 직장인의 퇴근 후 캠스터디<br>스터디 목표 : 구루미 캠 켜놓고 스프링부트 각자 공부<br>예상 스터디 일정(횟수) : 평일 저녁6시부터, 주말 자율 시작 / 최소3일 , 최소 2시간 이상 각자 계획별로 / 한 달 기준 스터디 지속or종료 선택<br>예상 커리큘럼 간략히 : 각자 공부<br>예상 모집인원 : 최대 6명<br><br>hyoungseok@gmail.com 로 연락주세요', 2, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-13T11:17:45', '2023-04-13T11:17:45', '이펙티브 자바 완벽 공략 스터디', 11, '[개발 스터디 모집 내용 예시]<br><br><br>스터디 주제 :이펙티브 자바 완벽 공략 스터디<br>스터디 목표 : 이펙티브 자바 완벽 공략 강의를 전부 보고 익힌다. 1부 2부 다.<br>예상 스터디 일정(횟수) : 총 8회 (8주)<br>예상 커리큘럼 간략히 :강의 계획 세우고 서로 진도 체크하며 독려하기<br>예상 모집인원 :5명<br><br>ralo@gmail.com로 연락주세요.', 6, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-14T11:27:26', '2023-04-14T11:27:26', '여행 커뮤니티 서비스 디자이너 한 분 모집합니다!', 13, '프로젝트 주제 : 여행 커뮤니티 서비스입니다!<br>프로젝트 목표 : 기획부터 배포까지 하는 것이 목표입니다.<br>프로젝트 소개와 개설 이유 : 국비 부트캠프 마지막 프로젝트에서 저의 역량을 많이 보여주지 못 해 아쉬운 마음에 프로젝트를 새로 하기로 결정했습니다. 각 팀원이 퍼포먼스를 잘 표현 해낼수 있는 프로젝트가 되었으면 좋겠습니다.<br><br>dreamcode@gmail.com로 연락주세요!!!', 3, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-15T11:25:19', '2023-04-15T11:25:19', '[서울] App 개발 스터디 - 디자이너 모집', 22, '스터디 주제: 유튜브 Shorts / 인스타 Reels 형태의 사진 등록 어플<br>장소 및 일정: 은평구 불광역 / 주 1회 ( 토요일 오후 2시30분 격 주 오프라인 진행 )<br>모집 인원: 디자이너 1명<br><br> - https://open.kakao.com/o/qqqqqqq', 1, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-16T11:43:18', '2023-04-16T11:43:18', '프론트엔드 면접스터디 하실분', 33, '스터디 주제 : 프론트엔드 면접대비 스터디<br>스터디 목표 : 면접에서 조리있고 자신감있게 말하기<br>예상 스터디 일정(횟수) : 화,금 오전 10시~13시<br>예상 커리큘럼 간략히 : 기술 + 인성 + 각자 프로젝트 기반 질문 진행 (각각 인당 15분씩 진행하고 돌아가면서 진행 및 피드백)<br><br> https://open.kakao.com/o/wwwwwwww', 1, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-17T11:24:24', '2023-04-17T11:24:24', 'CS 면접 스터디 같이 하실 분 구합니다!', 31, '[개발 스터디 모집 내용 예시]<br><br>스터디 주제 : CS 주제 면접 스터디<br>스터디 목표 : CS 면접 성공<br>예상 스터디 일정(횟수) : 주 1회<br>예상 모집인원 : 5명', 3, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-18T11:57:45', '2023-04-18T11:57:45', '정기 오프 스터디 모임(신림) - 평일,주말 총 주2회 이상 오실 분', 21, '신림역 주변 카페에서 같이 모여서<br>개발 관련 공부하실 분들 모집합니다!!<br><br>오셔서 공부, 이직준비, 일 등 하시면 되고<br>특정 책,주제로 잡고 단기간 하고 끝나는 스터디가 아닌<br>꾸준히 계속 같이 공부해가는 장기적인 스터디입니다!!<br><br><br> https://open.kakao.com/o/eeeeeeeeee', 1, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-19T11:07:16', '2023-04-19T11:07:16', '[사이드 프로젝트] 백엔드 개발자 구합니다.', 16, '[개발 프로젝트 모집 내용 예시]<br><br>프로젝트 주제 : 온라인 서점<br>예상 모집인원 : 1명<br>프로젝트 관련 주의사항 : 커뮤니케이션 많이 하면서 코드리뷰도 적극적으로 할 예정입니다. 이러한 환경에서 같이 협업하실 분 구해요 코드 컨벤션을 정하고 진행할거라 시간 많이 할애하실 수 있는 분만 지원 부탁드립니다.<br>https://open.kakao.com/o/aaaaaaaaaa', 5, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-20T11:24:37', '2023-04-20T11:24:37', '퇴근후 계획기반 자율형 스터디 모집합니다!', 7, '계획 기반 자율형 스터디 모집합니다<br><br>인원 : 4인~<br>시간 : 오후 8시 30분~ 11시 30분 (평일)<br>기간 : ~9/15(한달마다 입,퇴부 자유)<br>장소/방식 : 매주 일요일 다음주 평일의 계획과 목표 세우기(휴무자유)', 1, 1)
INSERT INTO post(id, active, created_date, last_modified_date, title, hits, content, avatar_id, board_id) values (null, 1, '2023-04-20T11:52:31', '2023-04-20T11:52:31', '리액트 공식문서 읽고 발표하는 스터디', 2, '안녕하세요! 저는 프론트엔드 취업준비생이고 공부한지는 1년 정도 되었습니다!<br><br>일요일 오전 시간을 침대에서 밍기적거리는게 너무 아까워서 오전 시간을 활용하기 위한 강제적인 장치를 구상하던 중에 스터디를 모집하게 됐습니다<br>또한, React.dev (리액트 공식문서) 가 리뉴얼되고 나서, 블로그에 꾸준히 번역, 정리하며 공부하려 했으나,, 혼자서는 동기도 떨어지고, 제 자신과 자꾸 타협하게 되더라구요..<br>그래서, 비슷한 생각을 가지신 분들이 있지 않을까 하여 스터디를 모집하게 됐습니다!', 2, 1)


------------------------------ Comment Data ------------------------------
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-12T12:22:22', '2023-04-12T12:22:22', '혹시 몇년차 개발자이신가요?', 8, 1)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-13T23:33:43', '2023-04-13T23:33:43', '2년차 개발자입니다~ㅎㅎ', 1, 1)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-14T14:34:49', '2023-04-14T14:34:49', '멋지십니다!', 8, 1)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-14T13:37:39', '2023-04-14T13:37:39', '퇴근을 안해서..', 1, 2)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:27:49', '2023-04-15T11:27:49', '좋은 스터디네요!!', 1, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:28:22', '2023-04-15T11:28:22', '도움이 많이 될것 같습니다', 7, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:28:44', '2023-04-15T11:28:44', '혹시 진행시간은 어떻게될까요?ㅠㅠ', 7, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:28:59', '2023-04-15T11:28:59', '오후 7시정도로 생각하고있습니다.', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:29:03', '2023-04-15T11:29:03', '요일은 요??', 6, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:29:09', '2023-04-15T11:29:09', '상의해봐야겠지만 매주 월요일로 생각중입니다!!', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:29:16', '2023-04-15T11:29:16', '제가 퇴근을 10시에하는데 늦게 참여해도 될까요?', 7, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:29:19', '2023-04-15T11:29:19', '넵 가능합니다!!', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:29:26', '2023-04-15T11:29:26', '그럼 노션같은걸로 진행도 체크를 하나요?', 6, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:30:43', '2023-04-15T11:30:43', '넵! 노션으로 각자 주차마다 강의를 수강했는지 안했는지 수강중인지 체크합니다.', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:30:55', '2023-04-15T11:30:55', '1주에 1세션씩 들으면 되는거죠?', 6, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:31:11', '2023-04-15T11:31:11', '넵!!', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:31:14', '2023-04-15T11:31:14', '오픈 채팅방을 만들어 주실수있나요?', 7, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:31:58', '2023-04-15T11:31:58', '오픈 채팅방에서 자세한 내용 듣겠습니다!', 6, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:32:01', '2023-04-15T11:32:01', 'https://open.kakao.com/o/ooooooooo 으로 들어오시면 될 것 같아요.', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:32:55', '2023-04-15T11:32:55', '혹시 현직자이신가요?', 8, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:32:57', '2023-04-15T11:32:57', '넵! 현재 1년차 백엔드 개발자입니다.', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:32:58', '2023-04-15T11:32:58', '이펙티브 자바를 공부하려는 계기가 있나요?', 8, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:33:44', '2023-04-15T11:33:44', '따로 특별한 계기는 없는것 같네요 ㅋㅋㅋ', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:48:22', '2023-04-15T11:48:22', '스터디 관련 주의사항이 있을까요?', 8, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:48:25', '2023-04-15T11:48:25', '분란만 조장하지 않는다면 딱히 없습니다!', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:55:11', '2023-04-15T11:55:11', '넵!! 알겠습니다!!', 8, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:56:16', '2023-04-15T11:56:16', '자리 다 찼나요?', 4, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T11:59:11', '2023-04-15T11:59:11', '마지막 한분 남았습니다!!', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T12:11:11', '2023-04-15T12:11:11', '빠르게 지원하겠습니다', 5, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T12:15:55', '2023-04-15T12:15:55', '한명 더 안될까요..?', 2, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T12:22:44', '2023-04-15T12:22:44', '팀원분들한테 여쭤볼게요', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T12:44:31', '2023-04-15T12:44:31', '네 감사합니다ㅠㅠ', 2, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-15T13:44:11', '2023-04-15T13:44:11', '오픈 채팅방 들어오시면 될것같아요!!', 3, 3)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-16T03:07:49', '2023-04-16T03:07:49', '디자인 자신있습니다!', 7, 4)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-17T01:37:59', '2023-04-17T01:37:59', '구현하기 빡세보이네요..', 7, 5)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-18T11:37:48', '2023-04-18T11:37:48', '이직 면접도 포함인가요?', 1, 6)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-19T15:04:39', '2023-04-19T15:04:39', 'https://open.kakao.com/o/nnnnnnnnnn', 7, 7)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-23T02:27:01', '2023-04-23T02:27:01', 'https://open.kakao.com/o/xxxxxxxxxx', 10, 10)
INSERT INTO comment(id, active, created_date, last_modified_date, content, avatar_id, post_id) values (null, 1, '2023-04-25T03:17:40', '2023-04-25T03:17:40', '맞아요. 저도 같은 생각이에요.. 지원은 어떻게 하면 될까요.', 1, 11)

