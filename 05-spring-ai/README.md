# 🚀 API de Orçamento Inteligente com Spring AI

Projeto desenvolvido no módulo de Spring Boot e Inteligência Artificial da DIO, com evoluções focadas em **Tool Calling (Execução de Funções)**, validação de dados e novos endpoints de consulta.

## 📌 O que o projeto faz
A aplicação é uma API de gerenciamento financeiro pessoal que utiliza o **Spring AI** para processar áudio e texto. A IA analisa os comandos do usuário, aciona funções reais do sistema (*Tool Calling*) para criar ou consultar transações financeiras e devolve respostas contextualizadas (inclusive em áudio MP3).

---

## ✨ Melhorias Implementadas

1. **Novas Tools para a IA (`FinancialSummaryUseCase`):**
   - `get-total-balance`: Permite que a IA consulte o saldo total e histórico do usuário.
   - `get-category-spending-summary`: Permite consultar gastos filtrados por categoria (ex: `GROCERIES`, `PHARMA`, `AUTO`).
   
2. **Endpoint de Teste por Texto:**
   - Adicionado o endpoint `POST /transactions/ai/text` para testar perguntas e interações de texto sem depender do envio de arquivos de áudio.

3. **Validação e Tratamento de Erros (`GlobalExceptionHandler`):**
   - Tratamento amigável para envio de arquivos de áudio vazios ou formatos inválidos, retornando status `400 Bad Request` em formato JSON.

4. **Ajuste na Regra de Moeda:**
   - Ajuste no DTO de resposta para converter corretamente valores inteiros em centavos para a representação em reais (ex: `2590` -> `25.90`).

---

## 🛠️ Tecnologias Utilizadas
- Java 21+ / Spring Boot 3+
- **Spring AI** (Integração com modelos de linguagem e transcrição)
- **Spring Data JPA** & Banco de dados H2 / MySQL
- **Gradle**

---

## 🧪 Como Testar a Aplicação

### 1. Criar uma Transação (Valor em Centavos)
```bash
curl -X POST http://localhost:8080/transactions \
  -H "Content-Type: application/json" \
  -d "{\"description\":\"Mercado\",\"category\":\"GROCERIES\",\"amount\":2590}"