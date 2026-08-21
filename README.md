# 🚀 Desafio de Projeto: API de Orçamento Inteligente com Spring AI

Projeto desenvolvido como desafio prático do módulo de Spring Boot e Inteligência Artificial da **DIO (Digital Innovation One)**, contido na pasta [`/05-spring-ai`](./05-spring-ai).

---

## 📌 Visão Geral do Projeto

A aplicação é uma **API Inteligente de Gestão Financeira** que processa comandos do usuário enviando entradas via **áudio ou texto**. O sistema utiliza o **Spring AI** para:
1. Converter arquivos de áudio para texto (Transcrição).
2. Compreender a intenção do usuário utilizando modelos de linguagem (LLM).
3. Executar funções reais do sistema (*Tool Calling / Function Calling*).
4. Gravar ou consultar transações e gerar respostas contextualizadas (com geração opcional de voz em áudio MP3).

---

## ✨ Melhorias e Evoluções Implementadas

Com base no projeto fornecido em aula, foram implementadas as seguintes evoluções:

### 1. Novas Tools de IA (`FinancialSummaryUseCase`)
A IA foi capacitada com duas novas ferramentas para responder dúvidas financeiras diretamente:
* **`get-total-balance`**: Consulta o total gasto e o saldo consolidado do usuário.
* **`get-category-spending-summary`**: Permite consultar os gastos filtrados por categorias específicas (ex: `GROCERIES`, `PHARMA`, `AUTO`).

### 2. Endpoint de Interação via Texto
* Criado o endpoint **`POST /transactions/ai/text`**, permitindo enviar mensagens diretas e testar a inteligência da API sem dependência obrigatória de envio de arquivos de áudio.

### 3. Validação de Dados e Respostas Amigáveis (`GlobalExceptionHandler`)
* Adicionado tratamento com `@RestControllerAdvice` para capturar envios de arquivos de áudio vazios ou corrompidos, retornando uma resposta limpa em JSON com status **`400 Bad Request`**.

### 4. Correção na Formatação de Moeda
* Ajustada a conversão de centavos para reais na saída das transações (`TransactionOutput`), garantindo que entradas como `2590` retornem formatadas como `25.90`.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21+)
* **Framework:** Spring Boot
* **Módulo AI:** Spring AI (`ChatClient`, Tool Calling)
* **Persistência:** Spring Data JPA
* **Banco de Dados:** H2 (Testes/Runtime local) / MySQL
* **Automação de Build:** Gradle

---

## 📂 Estrutura do Repositório

```text
.
├── 05-spring-ai/                    <-- PASTA PRINCIPAL DO DESAFIO
│   ├── src/                         <-- Código-fonte Java e Testes
│   ├── build.gradle                 <-- Configuração de dependências
│   └── README.md                    <-- Documentação técnica do módulo
└── README.md                        <-- Guia geral do repositório
```

🧪 Como Executar e Testar
Navegue até a pasta do projeto:
```text
Bash
cd 05-spring-ai
```
1. Executar a Aplicação
```text
Bash
# Definir a chave da OpenAI (se for utilizar a API real)
export OPENAI_API_KEY="sua_chave_aqui"   # Linux/Mac
$env:OPENAI_API_KEY="sua_chave_aqui"    # PowerShell Windows
# Subir a aplicação
./gradlew bootRun
```
2. Exemplos de Requisições (cURL)
Criar Transação (valor em centavos):
```text
Bash
curl -X POST http://localhost:8080/transactions \
  -H "Content-Type: application/json" \
  -d "{\"description\":\"Mercado do bairro\",\"category\":\"GROCERIES\",\"amount\":2590}"
```
Consultar Saldo Total:
```text
Bash
curl http://localhost:8080/transactions/balance
```

Consultar Gastos por Categoria (GROCERIES):
```text
Bash
curl http://localhost:8080/transactions/summary/GROCERIES
```
Perguntar para a IA por Texto:
```text
Bash
curl -X POST http://localhost:8080/transactions/ai/text \
  -H "Content-Type: application/json" \
  -d "{\"message\":\"Quanto eu gastei com alimentacao?\"}"
  ```
🧠 Aprendizados
Durante este desafio, foi possível compreender:

A integração entre aplicações Spring Boot e LLMs utilizando o ecossistema Spring AI.

O padrão de Tool Calling, onde a IA atua como orquestradora chamando métodos do próprio código Java.

A separação de responsabilidades entre regras de negócio, persistência e exposição REST.
