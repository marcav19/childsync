CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  email varchar(40) NOT NULL,
  password varchar(40) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
);

CREATE TABLE appointments (
  id int NOT NULL AUTO_INCREMENT,
  datetime datetime NOT NULL,
  reason varchar(60) NOT NULL,
  result varchar(60) NOT NULL,
  userid int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (datetime),
  CONSTRAINT appointment_user_id FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE baths (
  id int NOT NULL AUTO_INCREMENT,
  datetime datetime NOT NULL,
  userid int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (datetime),
  CONSTRAINT bath_user_id FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE activities (
  id int NOT NULL AUTO_INCREMENT,
  datetime datetime NOT NULL,
  name varchar(40) NOT NULL,
  userid int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (datetime),
  CONSTRAINT activity_user_id FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE meals (
  id int NOT NULL AUTO_INCREMENT,
  datetime datetime NOT NULL,
  name varchar(40) NOT NULL,
  comment varchar(40) NOT NULL,
  userid int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (datetime),
  CONSTRAINT meal_user_id FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE medicines (
  id int NOT NULL AUTO_INCREMENT,
  datetime datetime NOT NULL,
  name varchar(45) NOT NULL,
  dosage varchar(20) NOT NULL,
  userid int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (datetime),
  CONSTRAINT medicine_user_id FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE potties (
  id int NOT NULL AUTO_INCREMENT,
  datetime datetime NOT NULL,
  description varchar(45) NOT NULL,
  userid int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (datetime),
  CONSTRAINT potty_user_id FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE sleeps (
  id int NOT NULL AUTO_INCREMENT,
  start datetime NOT NULL,
  end datetime NOT NULL,
  userid int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (start),
  UNIQUE (end),
  CONSTRAINT sleep_user_id FOREIGN KEY (userid) REFERENCES users(id)
);

INSERT INTO users (name, email, password)
VALUES ('test', 'test@email.com', '1234');

INSERT INTO users (name, email, password)
VALUES ('Marc', 'mvalle602@gmail.com', 'password');