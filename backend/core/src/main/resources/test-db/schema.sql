CREATE TABLE `role` (
  `id`        INT(11)      NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`authority`)
);

CREATE TABLE `user` (
  `id`       BIGINT      NOT NULL AUTO_INCREMENT,
  `login`    VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email`    VARCHAR(45) NOT NULL,
  `enabled`  BOOLEAN     NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`login`),
  UNIQUE (`email`)
);

CREATE TABLE `user_role` (
  `id`      BIGINT      NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT      NOT NULL,
  `role_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
);