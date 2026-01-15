CREATE TABLE users (
  user_id int NOT NULL AUTO_INCREMENT,
  user_name varchar(15) NOT NULL,
  user_email varchar(45) NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE KEY (user_email)
);

CREATE TABLE appointments (
  appointment_id int NOT NULL AUTO_INCREMENT,
  appointment_reason varchar(45) NOT NULL,
  appointment_datetime datetime NOT NULL,
  appointment_result varchar(45) NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (appointment_id),
  UNIQUE (appointment_datetime),
  CONSTRAINT appointment_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE baths (
  bath_id int NOT NULL AUTO_INCREMENT,
  bath_datetime datetime NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (bath_id),
  UNIQUE (bath_datetime),
  CONSTRAINT bath_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE events (
  event_id int NOT NULL AUTO_INCREMENT,
  event_name varchar(45) NOT NULL,
  event_datetime datetime NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (event_id),
  UNIQUE (event_datetime),
  CONSTRAINT event_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE meals (
  meal_id int NOT NULL AUTO_INCREMENT,
  meal_name varchar(40) NOT NULL,
  meal_datetime datetime NOT NULL,
  meal_comment varchar(25) DEFAULT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (meal_id),
  UNIQUE (meal_datetime),
  CONSTRAINT meal_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE medicine (
  medicine_id int NOT NULL AUTO_INCREMENT,
  medicine_name varchar(45) NOT NULL,
  medicine_datetime datetime NOT NULL,
  medicine_dosage varchar(20) NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (medicine_id),
  UNIQUE (medicine_datetime),
  CONSTRAINT medicine_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE potty (
  potty_id int NOT NULL AUTO_INCREMENT,
  potty_description varchar(45) NOT NULL,
  potty_datetime datetime NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (potty_id),
  UNIQUE (potty_datetime),
  CONSTRAINT potty_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE sleep (
  sleep_id int NOT NULL AUTO_INCREMENT,
  sleep_start datetime NOT NULL,
  sleep_end datetime NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (sleep_id),
  UNIQUE (sleep_start),
  UNIQUE (sleep_end),
  CONSTRAINT sleep_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);