CREATE TABLE chaves_pix (
    id binary(16) DEFAULT (UUID_TO_BIN(UUID(), 1)) PRIMARY KEY,
    tipoChave varchar(9),
    valorChave varchar(77),
    tipoConta varchar(10),
    tipoPessoa varchar(8),
    numeroAgencia varchar(4),
    numeroConta varchar(8),
    nomeCorrentista varchar(30),
    sobrenomeCorrentista varchar(45)
);