# 🎮 NORDESTE NERD GAME - DOCUMENTAÇÃO COMPLETA

**Versão:** 1.0  
**Data de Criação:** 18 de Fevereiro de 2026  
**Data do Evento:** 26 de Abril de 2026  
**Prazo de Desenvolvimento:** ~10 semanas  

---

## 📋 ÍNDICE

1. [Visão Geral do Projeto](#1-visão-geral-do-projeto)
2. [Regras do Jogo](#2-regras-do-jogo)
3. [Lista dos 12 Desafios](#3-lista-dos-12-desafios)
4. [Lista dos 12 Cards](#4-lista-dos-12-cards)
5. [Modelo de Dados](#5-modelo-de-dados)
6. [Diagrama do Banco de Dados](#6-diagrama-do-banco-de-dados)
7. [Endpoints da API](#7-endpoints-da-api)
8. [Sistema de QR Code](#8-sistema-de-qr-code)
9. [Sistema de Ranking](#9-sistema-de-ranking)
10. [Fluxo de Telas](#10-fluxo-de-telas)
11. [Tecnologias Utilizadas](#11-tecnologias-utilizadas)
12. [Convenções de Nomenclatura](#12-convenções-de-nomenclatura)
13. [Estrutura de Pastas](#13-estrutura-de-pastas)
14. [Roadmap de Desenvolvimento](#14-roadmap-de-desenvolvimento)

---

## 1. VISÃO GERAL DO PROJETO

### 1.1 Contexto
Jogo mobile para o evento **Nordeste Nerd** (Nerdest), organizado por amigos do desenvolvedor. O evento é geek/nerd com cosplays, competições de dança Kpop, feiras e atividades diversas.

### 1.2 Objetivo do Jogo
Sistema de desafios gamificado onde participantes do evento coletam **12 cards virtuais** ao completar **12 desafios** espalhados pelo evento.

### 1.3 Tipo de Plataforma
- **Web Application** (Progressive Web App)
- Acesso via navegador mobile (Chrome, Safari, etc)
- Não requer instalação
- Jogador acessa via link: `nerdest.com/jogo` (ou domínio definido)

### 1.4 Validação dos Desafios
**Sistema de QR Code Automático:**
- Cada desafio possui QR Code único impresso e fixado no local
- Jogador escaneia QR Code após completar desafio
- Sistema valida automaticamente e libera card
- Zero intervenção de funcionários

---

## 2. REGRAS DO JOGO

### 2.1 Mecânica Principal
1. Jogador se registra no sistema com username e senha
2. Recebe acesso aos **12 desafios** (todos visíveis desde o início)
3. Pode fazer os desafios em **qualquer ordem**
4. Ao completar desafio, escaneia **QR Code** no local
5. Sistema valida e libera **card correspondente**
6. Objetivo: coletar os 12 cards

### 2.2 Sistema de Validação
- Cada QR Code só pode ser escaneado **1 vez por jogador**
- QR Code contém ID do desafio + token de segurança
- Sistema impede re-escaneamento do mesmo desafio
- Validação é instantânea e automática

### 2.3 Ranking e Competição
- Sistema registra **tempo total** para completar os 12 desafios
- **Ranking público** mostra Top 10 jogadores
- Prêmios para **Top 3** (definir posteriormente)
- Jogadores que não competem podem jogar no próprio ritmo

### 2.4 Cards e Raridades
- **5 Cards Básicos** (desafios simples/rápidos)
- **5 Cards Raros** (desafios medianos)
- **2 Cards Lendários** (desafios difíceis/complexos)

---

## 3. LISTA DOS 12 DESAFIOS

| ID | Nome | Descrição | Raridade do Card |
|----|------|-----------|------------------|
| 1 | Arena Gamer | Jogar na Arena Helio Filho | RARO |
| 2 | Meus Produtos | Comprar no mínimo 3 itens de uma loja | RARO |
| 3 | Hora da Boquinha | Comprar alimento na vendinha | BÁSICO |
| 4 | Just Dance | Jogar Just Dance | RARO |
| 5 | Solte a Voz | Cantar no karaokê | LENDÁRIO |
| 6 | Eu Amo Artes | Comprar alguma arte | BÁSICO |
| 7 | Domínio dos Infláveis | Brincar nos infláveis | RARO |
| 8 | Bate Papo Geek | Assistir o bate papo | BÁSICO |
| 9 | Multiverso | Participar das brincadeiras | RARO |
| 10 | Sou Cosplay | Ir vestido de cosplay | LENDÁRIO |
| 11 | Eu Amo Kpop | Assistir competição Kpop | BÁSICO |
| 12 | Eu Amo Cosplay | Assistir competição Cosplay | BÁSICO |

### 3.1 Distribuição de Raridades
- **BÁSICO:** 5 desafios (41.7%)
- **RARO:** 5 desafios (41.7%)
- **LENDÁRIO:** 2 desafios (16.6%)

---

## 4. LISTA DOS 12 CARDS

| Card ID | Nome do Card | Desafio Relacionado | Raridade | Imagem |
|---------|--------------|---------------------|----------|--------|
| 1 | Arena Gamer | Desafio 1 | RARO | [A definir] |
| 2 | Meus Produtos | Desafio 2 | RARO | [A definir] |
| 3 | Hora da Boquinha | Desafio 3 | BÁSICO | [A definir] |
| 4 | Just Dance | Desafio 4 | RARO | [A definir] |
| 5 | Solte a Voz | Desafio 5 | LENDÁRIO | [A definir] |
| 6 | Eu Amo Artes | Desafio 6 | BÁSICO | [A definir] |
| 7 | Domínio dos Infláveis | Desafio 7 | RARO | [A definir] |
| 8 | Bate Papo Geek | Desafio 8 | BÁSICO | [A definir] |
| 9 | Multiverso | Desafio 9 | RARO | [A definir] |
| 10 | Sou Cosplay | Desafio 10 | LENDÁRIO | [A definir] |
| 11 | Eu Amo Kpop | Desafio 11 | BÁSICO | [A definir] |
| 12 | Eu Amo Cosplay | Desafio 12 | BÁSICO | [A definir] |

**Nota:** Imagens dos cards serão definidas posteriormente pelo organizador do evento.

---

## 5. MODELO DE DADOS

### 5.1 Entidade: User
Representa tanto jogadores quanto administradores do sistema.

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String senha; // Hash BCrypt
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role; // JOGADOR ou ADMIN
    
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    
    private LocalDateTime dataInicioJogo; // Quando começou primeiro desafio
    private LocalDateTime dataConclusaoJogo; // Quando completou todos os 12
    
    @Column
    private Long tempoTotalSegundos; // Tempo total para completar (em segundos)
}

enum UserRole {
    JOGADOR,
    ADMIN
}
```

### 5.2 Entidade: Desafio
Representa cada um dos 12 desafios do jogo.

```java
@Entity
@Table(name = "desafios")
public class Desafio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    @Column(nullable = false, length = 500)
    private String descricao;
    
    @Column(nullable = false, unique = true)
    private String qrCodeToken; // Token único para validação
    
    @Column(nullable = false)
    private Boolean ativo; // Para ativar/desativar desafios
}
```

### 5.3 Entidade: Card
Representa os cards colecionáveis.

```java
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Raridade raridade;
    
    @Column
    private String imagemUrl; // URL da imagem do card
    
    @OneToOne
    @JoinColumn(name = "desafio_id", nullable = false)
    private Desafio desafio; // Cada card está ligado a 1 desafio
}

enum Raridade {
    BASICO,
    RARO,
    LENDARIO
}
```

### 5.4 Entidade: JogadorDesafio
Tabela de relacionamento que rastreia progresso do jogador nos desafios.

```java
@Entity
@Table(name = "jogador_desafios")
public class JogadorDesafio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private User jogador;
    
    @ManyToOne
    @JoinColumn(name = "desafio_id", nullable = false)
    private Desafio desafio;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDesafio status;
    
    @Column
    private LocalDateTime dataInicio;
    
    @Column
    private LocalDateTime dataConclusao;
    
    // Constraint: Cada jogador só pode ter 1 registro por desafio
    // Unique constraint: (jogador_id, desafio_id)
}

enum StatusDesafio {
    PENDENTE,      // Não começou ainda
    EM_ANDAMENTO,  // Jogador clicou para começar (opcional)
    CONCLUIDO      // QR Code validado
}
```

### 5.5 Entidade: JogadorCard
Coleção de cards do jogador.

```java
@Entity
@Table(name = "jogador_cards")
public class JogadorCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private User jogador;
    
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
    
    @Column(nullable = false)
    private LocalDateTime dataObtencao;
    
    // Constraint: Cada jogador só pode ter 1 exemplar de cada card
    // Unique constraint: (jogador_id, card_id)
}
```

---

## 6. DIAGRAMA DO BANCO DE DADOS

```
┌─────────────────┐
│     users       │
├─────────────────┤
│ id (PK)         │
│ username        │
│ senha           │
│ role            │
│ dataCriacao     │
│ dataInicioJogo  │
│ dataConclusao   │
│ tempoTotal      │
└─────────────────┘
         │
         │ 1:N
         ▼
┌──────────────────────┐        ┌─────────────────┐
│  jogador_desafios    │   N:1  │    desafios     │
├──────────────────────┤◄───────├─────────────────┤
│ id (PK)              │        │ id (PK)         │
│ jogador_id (FK)      │        │ nome            │
│ desafio_id (FK)      │        │ descricao       │
│ status               │        │ qrCodeToken     │
│ dataInicio           │        │ ativo           │
│ dataConclusao        │        └─────────────────┘
└──────────────────────┘                 │
         │                               │ 1:1
         │                               ▼
         │                        ┌─────────────────┐
         │                        │     cards       │
         │                        ├─────────────────┤
         │                        │ id (PK)         │
         │                        │ nome            │
         │                        │ raridade        │
         │                        │ imagemUrl       │
         │                        │ desafio_id (FK) │
         │                        └─────────────────┘
         │                               │
         │ 1:N                           │ 1:N
         ▼                               ▼
┌──────────────────────┐        ┌─────────────────┐
│   jogador_cards      │   N:1  │     cards       │
├──────────────────────┤◄───────┤  (referência)   │
│ id (PK)              │        └─────────────────┘
│ jogador_id (FK)      │
│ card_id (FK)         │
│ dataObtencao         │
└──────────────────────┘
```

**Constraints Importantes:**
- `jogador_desafios`: UNIQUE(jogador_id, desafio_id)
- `jogador_cards`: UNIQUE(jogador_id, card_id)
- `users.username`: UNIQUE
- `desafios.nome`: UNIQUE
- `desafios.qrCodeToken`: UNIQUE

---

## 7. ENDPOINTS DA API

### 7.1 Autenticação

#### POST `/api/auth/registro`
Registra novo jogador.

**Request Body:**
```json
{
  "username": "jogador123",
  "senha": "senha123"
}
```

**Response (201):**
```json
{
  "id": 1,
  "username": "jogador123",
  "role": "JOGADOR",
  "dataCriacao": "2026-04-26T10:00:00"
}
```

#### POST `/api/auth/login`
Login do jogador ou admin.

**Request Body:**
```json
{
  "username": "jogador123",
  "senha": "senha123"
}
```

**Response (200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "jogador123",
    "role": "JOGADOR"
  }
}
```

---

### 7.2 Desafios

#### GET `/api/desafios`
Lista todos os desafios ativos.

**Response (200):**
```json
[
  {
    "id": 1,
    "nome": "Arena Gamer",
    "descricao": "Jogar na Arena Helio Filho",
    "ativo": true
  },
  ...
]
```

#### GET `/api/desafios/{id}`
Detalhes de um desafio específico.

**Response (200):**
```json
{
  "id": 1,
  "nome": "Arena Gamer",
  "descricao": "Jogar na Arena Helio Filho",
  "card": {
    "id": 1,
    "nome": "Arena Gamer",
    "raridade": "RARO",
    "imagemUrl": "https://..."
  }
}
```

---

### 7.3 Progresso do Jogador

#### GET `/api/jogador/progresso`
Retorna progresso completo do jogador autenticado.

**Headers:** `Authorization: Bearer {token}`

**Response (200):**
```json
{
  "jogador": {
    "id": 1,
    "username": "jogador123"
  },
  "desafios": [
    {
      "desafioId": 1,
      "nome": "Arena Gamer",
      "status": "CONCLUIDO",
      "dataConclusao": "2026-04-26T11:30:00"
    },
    {
      "desafioId": 2,
      "nome": "Meus Produtos",
      "status": "PENDENTE",
      "dataConclusao": null
    },
    ...
  ],
  "totalConcluidos": 1,
  "totalDesafios": 12,
  "percentualConclusao": 8.33
}
```

---

### 7.4 Validação de QR Code

#### POST `/api/validar-qr`
Valida QR Code escaneado pelo jogador.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "desafioId": 1,
  "qrCodeToken": "a3f8k2j9s"
}
```

**Response (200) - Sucesso:**
```json
{
  "sucesso": true,
  "mensagem": "Desafio concluído com sucesso!",
  "card": {
    "id": 1,
    "nome": "Arena Gamer",
    "raridade": "RARO",
    "imagemUrl": "https://..."
  },
  "totalConcluidos": 1,
  "jogoCompleto": false
}
```

**Response (200) - Jogo Completo:**
```json
{
  "sucesso": true,
  "mensagem": "Parabéns! Você completou todos os desafios!",
  "card": {...},
  "totalConcluidos": 12,
  "jogoCompleto": true,
  "tempoTotal": "2h 35min",
  "posicaoRanking": 7
}
```

**Response (400) - Erro:**
```json
{
  "sucesso": false,
  "mensagem": "QR Code inválido ou desafio já concluído"
}
```

---

### 7.5 Cards

#### GET `/api/jogador/cards`
Lista cards coletados pelo jogador.

**Headers:** `Authorization: Bearer {token}`

**Response (200):**
```json
{
  "cards": [
    {
      "id": 1,
      "nome": "Arena Gamer",
      "raridade": "RARO",
      "imagemUrl": "https://...",
      "dataObtencao": "2026-04-26T11:30:00"
    },
    ...
  ],
  "estatisticas": {
    "totalCards": 1,
    "basicos": 0,
    "raros": 1,
    "lendarios": 0
  }
}
```

---

### 7.6 Ranking

#### GET `/api/ranking`
Retorna ranking global dos jogadores.

**Query Params:** 
- `limite` (opcional, padrão: 10)

**Response (200):**
```json
{
  "ranking": [
    {
      "posicao": 1,
      "jogador": "jogador123",
      "tempoTotal": "1h 45min",
      "dataConclusao": "2026-04-26T13:15:00"
    },
    {
      "posicao": 2,
      "jogador": "nerd_master",
      "tempoTotal": "2h 10min",
      "dataConclusao": "2026-04-26T14:20:00"
    },
    ...
  ],
  "minhaPosicao": 7,
  "totalJogadoresCompletos": 45
}
```

---

### 7.7 Admin (futuro)

#### POST `/api/admin/desafios`
Criar novo desafio (apenas admin).

#### PUT `/api/admin/desafios/{id}`
Editar desafio (apenas admin).

#### DELETE `/api/admin/desafios/{id}`
Desativar desafio (apenas admin).

---

## 8. SISTEMA DE QR CODE

### 8.1 Estrutura do QR Code

Cada QR Code contém uma URL com formato:
```
https://nerdest.com/jogo/validar?d={desafioId}&t={token}
```

**Exemplo:**
```
https://nerdest.com/jogo/validar?d=1&t=a3f8k2j9s
```

**Parâmetros:**
- `d` = ID do desafio (1 a 12)
- `t` = Token único de segurança (gerado randomicamente)

### 8.2 Geração dos QR Codes

**Ferramentas:**
- Backend: Biblioteca `qrcode` do Java
- Ou: Gerador online + download manual

**Processo:**
1. Para cada desafio, gerar token único (UUID)
2. Salvar token no banco (`desafios.qrCodeToken`)
3. Gerar QR Code com URL contendo ID + token
4. Imprimir QR Codes (12 no total)
5. Plastificar e colar nos locais do evento

### 8.3 Segurança

**Proteções implementadas:**
- ✅ Token único por desafio (não pode ser adivinhado)
- ✅ Validação única por jogador (constraint no banco)
- ✅ QR Code só valida se jogador autenticado
- ✅ Verificação de status (não permite re-validação)

### 8.4 Scanner no Frontend

**Tecnologia:** HTML5 Camera API
- Biblioteca sugerida: `html5-qrcode`
- Funciona nativamente no navegador mobile
- Não requer app nativo

**Fluxo:**
1. Jogador clica "Escanear QR Code"
2. Navegador pede permissão da câmera
3. Câmera abre e detecta QR Code
4. JavaScript extrai URL do QR Code
5. Frontend faz POST para `/api/validar-qr`
6. Backend valida e retorna resultado

---

## 9. SISTEMA DE RANKING

### 9.1 Cálculo do Tempo

**Início:** Quando jogador escaneia o PRIMEIRO QR Code
- Campo `users.dataInicioJogo` é preenchido

**Fim:** Quando jogador escaneia o 12º QR Code
- Campo `users.dataConclusaoJogo` é preenchido
- Campo `users.tempoTotalSegundos` = (dataFim - dataInicio) em segundos

### 9.2 Ordenação do Ranking

**Critério:** Menor tempo total vence

**Query SQL:**
```sql
SELECT 
  username,
  tempoTotalSegundos,
  dataConclusaoJogo
FROM users
WHERE dataConclusaoJogo IS NOT NULL
  AND role = 'JOGADOR'
ORDER BY tempoTotalSegundos ASC
LIMIT 10;
```

### 9.3 Exibição

**Top 10 Público:**
- Nome do jogador
- Tempo total (formatado: "2h 35min")
- Data/hora de conclusão

**Destaque Top 3:**
- Medals: 🥇 🥈 🥉
- Destaque visual diferenciado

---

## 10. FLUXO DE TELAS

### 10.1 Tela de Login/Registro
```
┌─────────────────────────┐
│   NORDESTE NERD GAME    │
│                         │
│  [Logo do Evento]       │
│                         │
│  Username: [_______]    │
│  Senha:    [_______]    │
│                         │
│  [  ENTRAR  ]           │
│  [REGISTRAR]            │
└─────────────────────────┘
```

### 10.2 Tela Principal (Home)
```
┌─────────────────────────┐
│ Olá, jogador123!    [⚙] │
├─────────────────────────┤
│ Progresso: 3/12 (25%)   │
│ [████████░░░░░░░░░░░]   │
│                         │
│ ┌─────────────────────┐ │
│ │ [ESCANEAR QR CODE]  │ │
│ └─────────────────────┘ │
│                         │
│ Menu:                   │
│ • Meus Desafios         │
│ • Minha Coleção         │
│ • Ranking               │
│ • Sair                  │
└─────────────────────────┘
```

### 10.3 Tela de Desafios
```
┌─────────────────────────┐
│ ← MEUS DESAFIOS         │
├─────────────────────────┤
│ ✅ Arena Gamer (RARO)   │
│    Concluído às 11:30   │
├─────────────────────────┤
│ ⏳ Meus Produtos (RARO) │
│    Pendente             │
├─────────────────────────┤
│ 🔒 Hora da Boquinha     │
│    (BÁSICO)             │
├─────────────────────────┤
│ ...                     │
└─────────────────────────┘
```

### 10.4 Tela de Scanner QR Code
```
┌─────────────────────────┐
│ ← ESCANEAR QR CODE      │
├─────────────────────────┤
│                         │
│   ┌─────────────────┐   │
│   │                 │   │
│   │   [CÂMERA]      │   │
│   │     ATIVA       │   │
│   │                 │   │
│   └─────────────────┘   │
│                         │
│ Aponte a câmera para    │
│ o QR Code do desafio    │
└─────────────────────────┘
```

### 10.5 Tela de Card Liberado
```
┌─────────────────────────┐
│   DESAFIO CONCLUÍDO!    │
│                         │
│  ┌─────────────────┐    │
│  │                 │    │
│  │  [IMAGEM CARD]  │    │
│  │                 │    │
│  └─────────────────┘    │
│                         │
│   Arena Gamer           │
│   ⭐ RARO               │
│                         │
│  Você tem 3/12 cards!   │
│                         │
│  [  CONTINUAR  ]        │
└─────────────────────────┘
```

### 10.6 Tela de Coleção
```
┌─────────────────────────┐
│ ← MINHA COLEÇÃO         │
├─────────────────────────┤
│ Cards: 3/12             │
│ • Básicos: 0/5          │
│ • Raros: 3/5            │
│ • Lendários: 0/2        │
├─────────────────────────┤
│ ┌───┐ ┌───┐ ┌───┐      │
│ │ 1 │ │ 2 │ │ 4 │      │
│ └───┘ └───┘ └───┘      │
│ ┌───┐ ┌───┐            │
│ │ ? │ │ ? │ ...        │
│ └───┘ └───┘            │
└─────────────────────────┘
```

### 10.7 Tela de Ranking
```
┌─────────────────────────┐
│ ← RANKING TOP 10        │
├─────────────────────────┤
│ 🥇 1. nerd_master       │
│    1h 45min             │
├─────────────────────────┤
│ 🥈 2. cosplay_queen     │
│    2h 10min             │
├─────────────────────────┤
│ 🥉 3. gamer_pro         │
│    2h 22min             │
├─────────────────────────┤
│ 4. jogador123 (VOCÊ)    │
│    2h 35min             │
├─────────────────────────┤
│ ...                     │
└─────────────────────────┘
```

---

## 11. TECNOLOGIAS UTILIZADAS

### 11.1 Backend
- **Java 17+**
- **Spring Boot 3.x**
  - Spring Web (REST API)
  - Spring Data JPA (ORM)
  - Spring Security (Autenticação JWT)
  - Spring Validation
- **MySQL 8.0** (Banco de dados)
- **Maven** (Gerenciador de dependências)
- **Lombok** (Redução de boilerplate)
- **JWT** (JSON Web Tokens para auth)

### 11.2 Frontend
- **HTML5**
- **CSS3** (ou Tailwind CSS para agilizar)
- **JavaScript (Vanilla ou React)**
- **html5-qrcode** (Biblioteca para scanner QR)
- **Fetch API** (Requisições HTTP)

### 11.3 Configuração
- **Porta Backend:** 8082
- **Porta Frontend:** 8080 (ou servido pelo próprio Spring)

---

## 12. CONVENÇÕES DE NOMENCLATURA

### 12.1 Java (Backend)

**Classes:**
- PascalCase
- Exemplos: `User`, `Desafio`, `JogadorDesafio`

**Métodos:**
- camelCase
- Verbos no infinitivo
- Exemplos: `validarQrCode()`, `obterProgresso()`, `salvarDesafio()`

**Variáveis:**
- camelCase
- Substantivos descritivos
- Exemplos: `jogadorId`, `qrCodeToken`, `tempoTotalSegundos`

**Constantes:**
- UPPER_SNAKE_CASE
- Exemplos: `MAX_DESAFIOS`, `TOKEN_LENGTH`

**Packages:**
- lowercase
- Estrutura: `com.nerdest.game.{camada}`
- Exemplos: 
  - `com.nerdest.game.entity`
  - `com.nerdest.game.repository`
  - `com.nerdest.game.service`
  - `com.nerdest.game.controller`

### 12.2 Banco de Dados

**Tabelas:**
- snake_case (plural)
- Exemplos: `users`, `desafios`, `jogador_desafios`

**Colunas:**
- snake_case
- Exemplos: `id`, `username`, `data_criacao`, `qr_code_token`

**Foreign Keys:**
- Padrão: `{tabela}_id`
- Exemplos: `jogador_id`, `desafio_id`, `card_id`

### 12.3 API Endpoints

**Padrão REST:**
- `/api/{recurso}`
- Verbos HTTP: GET, POST, PUT, DELETE
- Exemplos:
  - `GET /api/desafios`
  - `POST /api/validar-qr`
  - `GET /api/jogador/progresso`

### 12.4 Frontend

**Arquivos:**
- kebab-case
- Exemplos: `login.html`, `main-style.css`, `qr-scanner.js`

**Funções JavaScript:**
- camelCase
- Exemplos: `escanearQrCode()`, `exibirProgresso()`, `atualizarRanking()`

**IDs e Classes CSS:**
- kebab-case
- Exemplos: `#btn-escanear`, `.card-lendario`, `.ranking-item`

---

## 13. ESTRUTURA DE PASTAS

### 13.1 Backend (Spring Boot)

```
nerdest-game/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── nerdest/
│   │   │           └── game/
│   │   │               ├── NerdestGameApplication.java
│   │   │               ├── config/
│   │   │               │   ├── SecurityConfig.java
│   │   │               │   └── CorsConfig.java
│   │   │               ├── entity/
│   │   │               │   ├── User.java
│   │   │               │   ├── Desafio.java
│   │   │               │   ├── Card.java
│   │   │               │   ├── JogadorDesafio.java
│   │   │               │   └── JogadorCard.java
│   │   │               ├── repository/
│   │   │               │   ├── UserRepository.java
│   │   │               │   ├── DesafioRepository.java
│   │   │               │   ├── CardRepository.java
│   │   │               │   ├── JogadorDesafioRepository.java
│   │   │               │   └── JogadorCardRepository.java
│   │   │               ├── service/
│   │   │               │   ├── AuthService.java
│   │   │               │   ├── DesafioService.java
│   │   │               │   ├── JogadorService.java
│   │   │               │   ├── QrCodeService.java
│   │   │               │   └── RankingService.java
│   │   │               ├── controller/
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── DesafioController.java
│   │   │               │   ├── JogadorController.java
│   │   │               │   └── RankingController.java
│   │   │               ├── dto/
│   │   │               │   ├── LoginRequest.java
│   │   │               │   ├── RegistroRequest.java
│   │   │               │   ├── ValidarQrRequest.java
│   │   │               │   ├── ProgressoResponse.java
│   │   │               │   └── RankingResponse.java
│   │   │               ├── security/
│   │   │               │   ├── JwtUtil.java
│   │   │               │   └── JwtAuthFilter.java
│   │   │               └── exception/
│   │   │                   ├── GlobalExceptionHandler.java
│   │   │                   └── CustomExceptions.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql (dados iniciais)
│   └── test/
│       └── java/
│           └── com/
│               └── nerdest/
│                   └── game/
│                       └── (testes unitários)
├── pom.xml
└── README.md
```

### 13.2 Frontend

```
frontend/
├── index.html (landing page)
├── login.html
├── home.html
├── desafios.html
├── colecao.html
├── ranking.html
├── css/
│   ├── style.css
│   └── components.css
├── js/
│   ├── main.js
│   ├── auth.js
│   ├── qr-scanner.js
│   ├── api.js
│   └── utils.js
└── assets/
    ├── images/
    │   └── (logos, cards, etc)
    └── icons/
        └── (ícones do app)
```

---

## 14. ROADMAP DE DESENVOLVIMENTO

### SPRINT 1 - Setup e Fundação (Semana 1-2)
- ✅ Criar documento de arquitetura (CONCLUÍDO)
- [ ] Criar projeto Spring Boot
- [ ] Configurar MySQL e criar banco
- [ ] Criar entidades (User, Desafio, Card, etc)
- [ ] Criar repositories
- [ ] Popular banco com os 12 desafios iniciais
- [ ] Testar conexão banco ↔ backend

### SPRINT 2 - Autenticação (Semana 2-3)
- [ ] Implementar registro de usuário
- [ ] Implementar login com JWT
- [ ] Configurar Spring Security
- [ ] Criar endpoints de autenticação
- [ ] Testar autenticação com Postman

### SPRINT 3 - Lógica de Desafios (Semana 3-4)
- [ ] Criar serviço de desafios
- [ ] Endpoint: listar desafios
- [ ] Endpoint: detalhes do desafio
- [ ] Criar serviço de progresso do jogador
- [ ] Endpoint: obter progresso
- [ ] Inicializar progresso ao criar jogador (12 registros PENDENTE)

### SPRINT 4 - Sistema de QR Code (Semana 4-5)
- [ ] Gerar tokens únicos para os 12 desafios
- [ ] Implementar serviço de validação de QR Code
- [ ] Endpoint: validar QR Code
- [ ] Lógica: liberar card ao validar
- [ ] Lógica: calcular tempo total ao completar 12º desafio
- [ ] Gerar QR Codes físicos (imprimir)

### SPRINT 5 - Cards e Coleção (Semana 5-6)
- [ ] Criar serviço de cards
- [ ] Endpoint: listar cards do jogador
- [ ] Popular tabela cards com os 12 cards
- [ ] Adicionar URLs de imagens dos cards (design pendente)

### SPRINT 6 - Ranking (Semana 6)
- [ ] Criar serviço de ranking
- [ ] Endpoint: top 10 jogadores
- [ ] Endpoint: posição do jogador autenticado
- [ ] Lógica de ordenação por tempo

### SPRINT 7 - Frontend Básico (Semana 7-8)
- [ ] Criar telas HTML (login, home, desafios, coleção, ranking)
- [ ] Estilizar com CSS
- [ ] Implementar JavaScript para comunicação com API
- [ ] Integrar biblioteca html5-qrcode
- [ ] Testar scanner de QR Code

### SPRINT 8 - Integração e Testes (Semana 9)
- [ ] Integração completa frontend ↔ backend
- [ ] Testes de fluxo completo (registro → login → escanear → coleção)
- [ ] Corrigir bugs encontrados
- [ ] Validar todos os endpoints

### SPRINT 9 - Refinamentos e Deploy (Semana 10)
- [ ] Melhorias de UX
- [ ] Otimização de performance
- [ ] Deploy do backend (Heroku, AWS, etc)
- [ ] Deploy do frontend (Netlify, Vercel, etc)
- [ ] Configurar domínio (se disponível)
- [ ] Testar em dispositivos reais

### SPRINT 10 - Preparação Evento (Semana 11 - antes do evento)
- [ ] Imprimir e plastificar 12 QR Codes
- [ ] Colar QR Codes nos locais do evento
- [ ] Criar usuário admin
- [ ] Popular imagens dos cards finais
- [ ] Teste final com equipe do evento
- [ ] Briefing para organizadores

### DIA DO EVENTO - 26 de Abril de 2026
- [ ] Monitorar sistema
- [ ] Suporte a jogadores
- [ ] Resolver problemas em tempo real
- [ ] Divulgar ranking ao vivo

---

## 15. NOTAS ADICIONAIS

### 15.1 Dados Iniciais (data.sql)

Ao criar o projeto, já popular o banco com:
- 12 desafios (nome, descrição, tokens gerados)
- 12 cards vinculados aos desafios
- 1 usuário admin (para testes)

### 15.2 Melhorias Futuras (pós-evento)

**Possíveis features para versão 2.0:**
- Sistema de conquistas/badges extras
- Chat entre jogadores
- Troca de cards entre jogadores
- Desafios diários/semanais
- Integração com redes sociais (compartilhar conquistas)
- Dashboard admin para visualizar estatísticas do evento
- Sistema de hints para desafios difíceis
- Mapa interativo do evento

### 15.3 Considerações de Segurança

- Senhas sempre armazenadas com BCrypt (hash)
- JWT com expiração (24h recomendado)
- HTTPS obrigatório em produção
- CORS configurado para domínio específico
- Rate limiting para evitar spam de requisições
- Validação de entrada em todos os endpoints

### 15.4 Backup e Contingência

**Antes do evento:**
- Backup do banco de dados
- QR Codes impressos em duplicata
- Servidor de backup preparado

**Durante o evento:**
- Monitorar logs em tempo real
- Ter plano B se sistema cair (ex: validação manual temporária)

---

## 🎯 FIM DO DOCUMENTO

**Este documento é a referência mestra do projeto.**  
Consulte-o sempre antes de implementar novas features ou fazer mudanças na arquitetura.

**Última atualização:** 18 de Fevereiro de 2026  
**Próxima revisão:** Após Sprint 1

---

**Desenvolvido com ❤️ para o Nordeste Nerd 2026**
