
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTOINCREMENT,
  `color` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK761vd6ppyavafgiym4urlcn9y` (`owner_id`),
  CONSTRAINT `FK761vd6ppyavafgiym4urlcn9y` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTOINCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tags_notes`;
CREATE TABLE `tags_notes` (
  `TAG_ID` int(11) NOT NULL,
  `NOTE_ID` int(11) NOT NULL,
  PRIMARY KEY (`NOTE_ID`,`TAG_ID`),
  KEY `FKo2if9d6jfqdlixwmudpql28kv` (`TAG_ID`),
  CONSTRAINT `FK7gqguqyxf6vkbwc5qy15vdy3v` FOREIGN KEY (`NOTE_ID`) REFERENCES `note` (`id`),
  CONSTRAINT `FKo2if9d6jfqdlixwmudpql28kv` FOREIGN KEY (`TAG_ID`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTOINCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_note`;
CREATE TABLE `user_note` (
  `User_id` int(11) NOT NULL,
  `notes_id` int(11) NOT NULL,
  UNIQUE KEY `UK_cfhgtosuhwtl9ddnquq3og8ca` (`notes_id`),
  KEY `FK4qmno6vhwgjwwkkkytn72qru1` (`User_id`),
  CONSTRAINT `FK2uf8561x2oomt5sya91b4dw31` FOREIGN KEY (`notes_id`) REFERENCES `note` (`id`),
  CONSTRAINT `FK4qmno6vhwgjwwkkkytn72qru1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


