DROP TABLE IF EXISTS "User" CASCADE;

CREATE TABLE "User" (
    "username" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "status" VARCHAR(1) CHECK ("status" IN ('0', '1')),
    PRIMARY KEY ("username")
);

DROP TABLE IF EXISTS "Friend" CASCADE;
CREATE TABLE "Friend"(
	"username" VARCHAR(255) NOT NULL,
	"friend_name" VARCHAR(255) NOT NULL,
	FOREIGN KEY ("username") REFERENCES "User" ("username"),
	FOREIGN KEY ("friend_name") REFERENCES "User" ("username")
);

DROP TABLE IF EXISTS "MessageLog" CASCADE;
CREATE TABLE "MessageLog" (
    messageId SERIAL PRIMARY KEY,
    sender VARCHAR(255),
    receiver VARCHAR(255),
    "content" TEXT,
    sentTime VARCHAR(255),
	FOREIGN KEY ("sender") REFERENCES "User" ("username"),
	FOREIGN KEY ("sender") REFERENCES "Group" ("groupName"),
	FOREIGN KEY ("receiver") REFERENCES "User" ("username")
);

DROP TABLE IF EXISTS "Group" CASCADE;
CREATE TABLE "Group"(
	"groupName" VARCHAR(255) NOT NULL,
	"username" VARCHAR(255) NOT NULL,
	PRIMARY KEY ("groupName")
);

INSERT INTO "User" ("username", "password")
VALUES
    ('user1', 'password1'),
    ('user2', 'password2'),
    ('user3', 'password3'),
    ('user4', 'password4'),
    ('user5', 'password5'),
    ('user6', 'password6'),
    ('user7', 'password7'),
    ('user8', 'password8'),
    ('user9', 'password9'),
    ('user10', 'password10');
	

INSERT INTO "Friend" ("username", "friend_name") VALUES ('user1', 'user2');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user2', 'user1');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user1', 'user4');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user4', 'user1');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user3', 'user6');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user6', 'user3');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user4', 'user8');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user8', 'user4');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user1', 'user10');
INSERT INTO "Friend" ("username", "friend_name") VALUES ('user10', 'user1');