RENAME TABLE chaves_pix TO pix_keys;
ALTER TABLE pix_keys
    RENAME COLUMN tipoChave TO keyType,
    RENAME COLUMN valorChave TO keyValue,
    RENAME COLUMN tipoConta TO accountType,
    RENAME COLUMN tipoPessoa TO personType,
    RENAME COLUMN numeroAgencia TO agencyNumber,
    RENAME COLUMN numeroConta TO accountNumber,
    RENAME COLUMN nomeCorrentista TO accountHolderName,
    RENAME COLUMN sobrenomeCorrentista TO accountHolderLastName;