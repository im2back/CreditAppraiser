CREATE TABLE tb_cards(
id BIGINT AUTO_INCREMENT PRIMARY KEY,

card_name VARCHAR(255) NOT NULL,
card_flag varchar(255) NOT NULL,
income DECIMAL(10, 2) NOT NULL,
basic_limit DECIMAL(10, 2) NOT NULL

)