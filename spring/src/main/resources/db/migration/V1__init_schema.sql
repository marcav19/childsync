CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15) NOT NULL,
  email varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
);

CREATE TABLE appointments (
  appointment_id int NOT NULL AUTO_INCREMENT,
  appointment_datetime datetime NOT NULL,
  appointment_reason varchar(45) NOT NULL,
  appointment_result varchar(45) NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (appointment_id),
  UNIQUE (appointment_datetime),
  CONSTRAINT appointment_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE baths (
  bath_id int NOT NULL AUTO_INCREMENT,
  bath_datetime datetime NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (bath_id),
  UNIQUE (bath_datetime),
  CONSTRAINT bath_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE activities (
  id int NOT NULL AUTO_INCREMENT,
  datetime datetime NOT NULL,
  name varchar(45) NOT NULL,
  userid int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (datetime),
  CONSTRAINT activity_user_id FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE meals (
  meal_id int NOT NULL AUTO_INCREMENT,
  meal_datetime datetime NOT NULL,
  meal_name varchar(40) NOT NULL,
  meal_comment varchar(25) NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (meal_id),
  UNIQUE (meal_datetime),
  CONSTRAINT meal_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE medicines (
  medicine_id int NOT NULL AUTO_INCREMENT,
  medicine_datetime datetime NOT NULL,
  medicine_name varchar(45) NOT NULL,
  medicine_dosage varchar(20) NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (medicine_id),
  UNIQUE (medicine_datetime),
  CONSTRAINT medicine_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE potties (
  potty_id int NOT NULL AUTO_INCREMENT,
  potty_datetime datetime NOT NULL,
  potty_description varchar(45) NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (potty_id),
  UNIQUE (potty_datetime),
  CONSTRAINT potty_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE sleeps (
  sleep_id int NOT NULL AUTO_INCREMENT,
  sleep_start datetime NOT NULL,
  sleep_end datetime NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (sleep_id),
  UNIQUE (sleep_start),
  UNIQUE (sleep_end),
  CONSTRAINT sleep_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (name, email)
VALUES ('test', 'test@email.com');