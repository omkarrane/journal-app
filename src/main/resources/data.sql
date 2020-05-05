INSERT INTO entry(title,summary,created) VALUES('Get to know Spring Boot','Today I will learn Spring Boot','2016-01-02 00:00:00.00');
INSERT INTO entry(title,summary,created) VALUES('Simple Spring Boot Project','I will do my first Spring Boot project','2016-01-03 00:00:00.00');
INSERT INTO entry(title,summary,created) VALUES('Spring Boot Reading','Read more about Spring Boot','2016-02-02 00:00:00.00');
INSERT INTO entry(title,summary,created) VALUES('Spring Boot in the Cloud','Learn Spring Boot using Cloud Foundry','2016-02-05 00:00:00.00');


INSERT INTO authority(authority) VALUES('journal:read');
INSERT INTO authority(authority) VALUES('journal:write');
INSERT INTO user(active, email, password, username) VALUES(1, 'admin@gmail.com', '$2a$10$AqDScBSlSA3YnVkYMN5QWemW81GMorwBetbLip.ivk0fm6zwu8/xa', 'admin');
INSERT INTO user(active, email, password, username) VALUES(1, 'trainee@gmail.com', '$2a$10$AqDScBSlSA3YnVkYMN5QWemW81GMorwBetbLip.ivk0fm6zwu8/xa', 'trainee');

INSERT INTO user_authority(user_id, authority_id) VALUES(1, 1);
INSERT INTO user_authority(user_id, authority_id) VALUES(1, 2);

INSERT INTO user_authority(user_id, authority_id) VALUES(2, 1);