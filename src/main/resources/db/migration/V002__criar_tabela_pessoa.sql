CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(300),
    numero VARCHAR(7),
    complemento VARCHAR(300),
    bairro VARCHAR(100),
    cep VARCHAR(10),
    cidade VARCHAR(255),
    estado VARCHAR(2)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo) values ('José Maria de Oliveira', true);
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado) values ('Jonatas Vieira Pontes', true,'Av das Violetas','30',
    'Das Violetas', 'Violeta', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado) values ('Paulo Medeiros Conceição', true, 'Arena Coringa', '123',
    'Coringão','Coringa', 'SP');
INSERT INTO pessoa (nome, ativo) values ('Matheus Marquezini', true);
INSERT INTO pessoa (nome, ativo) values ('Joana Tavares', true);