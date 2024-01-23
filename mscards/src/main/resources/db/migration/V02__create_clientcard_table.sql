CREATE TABLE tb_clientcard(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
cpf VARCHAR(255),
limit_aprove DECIMAL(10,2),

 id_card BIGINT,
    FOREIGN KEY (id_card) REFERENCES tb_cards(id)
)