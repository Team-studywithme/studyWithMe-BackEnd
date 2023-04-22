------------------------------ Avatar Data -----------------------------
INSERT INTO avatar(id, active, createdDate, lastModifiedDate, nickname) values (null, 1, '2023-04-11T11:44:30', '2023-04-13T09:22:31', 'beforeNickname')
INSERT INTO avatar(id, active, createdDate, lastModifiedDate, nickname) values (null, 1, '2023-04-13T12:42:21', '2023-04-13T15:45:21', 'exampleNickname')

------------------------------ Account Data ------------------------------
INSERT INTO account(id, active, createdDate, lastModifiedDate, email, avatar_id) values ("2753601544", 1, '2023-04-11T11:44:30', '2023-04-13T09:22:31', 'weihyuk39@nate.com', 1)
INSERT INTO account(id, active, createdDate, lastModifiedDate, email, avatar_id) values ("1111111111", 1, '2023-04-13T12:42:21', '2023-04-13T15:45:21', 'email@nate.com', 2)

------------------------------ Board Data ------------------------------
INSERT INTO board(id, active, createdDate, lastModifiedDate, name) values (null, 1, '2023-04-13T07:22:39', '2023-04-13T07:22:39', 'matching')


------------------------------ Post Data ------------------------------
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-11T13:37:49', '2023-04-11T13:37:49', 'content_example_1', 'title_example_1', 1, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-12T11:07:41', '2023-04-12T11:07:41', 'content_example_2', 'title_example_2', 1, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-13T11:17:45', '2023-04-13T11:17:45', 'content_example_3', 'title_example_3', 2, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-14T11:27:26', '2023-04-14T11:27:26', 'content_example_4', 'title_example_4', 2, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-15T11:25:19', '2023-04-15T11:25:19', 'content_example_5', 'title_example_5', 1, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-16T11:43:18', '2023-04-16T11:43:18', 'content_example_6', 'title_example_6', 1, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-17T11:24:24', '2023-04-17T11:24:24', 'content_example_7', 'title_example_7', 2, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-18T11:57:45', '2023-04-18T11:57:45', 'content_example_8', 'title_example_8', 1, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-19T11:07:16', '2023-04-19T11:07:16', 'content_example_9', 'title_example_9', 2, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-20T11:24:37', '2023-04-20T11:24:37', 'content_example_10', 'title_example_10', 1, 1)
INSERT INTO post(id, active, createdDate, lastModifiedDate, content, title, avatar_id, board_id) values (null, 1, '2023-04-20T11:52:31', '2023-04-20T11:52:31', 'content_example_11', 'title_example_11', 2, 1)
