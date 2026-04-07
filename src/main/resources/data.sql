-- Limpa dados anteriores
DELETE FROM jogador_desafio;
DELETE FROM jogador_card;
DELETE FROM card;
DELETE FROM desafio;

-- Insere os 12 Desafios
INSERT INTO desafio (nome, descricao, qr_code_token, ativo) VALUES
('Arena Gamer', 'O Desafio é: Jogar na Arena Helio Filho', 'TOKEN_DESAFIO_1', true),
('Meus Produtos', 'O Desafio é: Comprar no Mínimo 3 Itens de uma Loja', 'TOKEN_DESAFIO_2', true),
('Hora da Boquinha', 'Comprar alimento na vendinha', 'TOKEN_DESAFIO_3', true),
('Just Dance', 'Jogar Just Dance', 'TOKEN_DESAFIO_4', true),
('Solte a Voz', 'Cantar no karaokê', 'TOKEN_DESAFIO_5', true),
('Eu Amo Artes', 'Comprar alguma arte', 'TOKEN_DESAFIO_6', true),
('Dominio dos Inflaveis', 'Brincar nos infláveis', 'TOKEN_DESAFIO_7', true),
('Bate Papo Geek', 'Assistir o bate papo', 'TOKEN_DESAFIO_8', true),
('Multiverso', 'Participe das brincadeiras', 'TOKEN_DESAFIO_9', true),
('Sou Cosplay', 'Ir vestido de cosplay', 'TOKEN_DESAFIO_10', true),
('Eu Amo Kpop', 'Assistir competição Kpop', 'TOKEN_DESAFIO_11', true),
('Eu Amo Cosplay', 'Assistir competição Cosplay', 'TOKEN_DESAFIO_12', true);


-- Insere os 12 Cards com caminhos corretos
INSERT INTO card (nome, raridade, imagem_url, desafio_id) VALUES
('Arena Gamer', 'RARO', '/assets/cards/card-1.png', 1),
('Meus Produtos', 'RARO', '/assets/cards/card-2.png', 2),
('Hora da Boquinha', 'BASICO', '/assets/cards/card-3.png', 3),
('Just Dance', 'RARO', '/assets/cards/card-4.png', 4),
('Solte a Voz', 'LENDARIO', '/assets/cards/card-5.png', 5),
('Eu Amo Artes', 'BASICO', '/assets/cards/card-6.png', 6),
('Dominio dos Inflaveis', 'RARO', '/assets/cards/card-7.png', 7),
('Bate Papo Geek', 'BASICO', '/assets/cards/card-8.png', 8),
('Multiverso', 'RARO', '/assets/cards/card-9.png', 9),
('Sou Cosplay', 'LENDARIO', '/assets/cards/card-10.png', 10),
('Eu Amo Kpop', 'BASICO', '/assets/cards/card-11.png', 11),
('Eu Amo Cosplay', 'BASICO', '/assets/cards/card-12.png', 12);