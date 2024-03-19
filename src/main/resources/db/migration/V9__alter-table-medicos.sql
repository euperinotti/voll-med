ALTER TABLE medicos
RENAME COLUMN nome to name,
RENAME COLUMN especialidade to specialty,
RENAME COLUMN logradouro to street,
RENAME COLUMN bairro to neighborhood,
RENAME COLUMN cidade to city,
RENAME COLUMN cep to zipCode,
RENAME COLUMN complemento to complement,
RENAME COLUMN numero to number,
RENAME COLUMN uf to state,
RENAME COLUMN ativo to isActive;