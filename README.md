# Sistema de Biblioteca

Sistema de console em Java para controlar empréstimos de livros, com prazo de devolução, multa por atraso e relatórios. Os dados ficam em memória, sem utilização de banco de dados.

**Status:** em desenvolvimento

---

## Funcionalidades

- [ ] Estruturação da arquitetura e pacotes do projeto
- [ ] Cadastro de livros
- [ ] Cadastro de usuários
- [ ] Empréstimo de livros
- [ ] Devolução de livros
- [ ] Pagamento de multas
- [ ] Relatórios do sistema:
    - [ ] empréstimos atrasados
    - [ ] top 3 livros mais emprestados
    - [ ] empréstimos por mês
    - [ ] total de multas por usuário
    - [ ] usuário com mais empréstimos
    - [ ] autores distintos
    - [ ] livros nunca emprestados

---

## Regras de Negócio

| Regra | Valor / Condição |
| :--- | :--- |
| **Prazo de devolução** | 14 dias a partir do empréstimo |
| **Máximo de empréstimos ativos** | 3 |
| **Idade mínima do usuário** | 12 anos |
| **Multa por atraso** | R$ 2,00 por dia |
| **Usuário com multa pendente** | Não pode pegar livros emprestados |
| **Livro já emprestado** | Não pode ser emprestado novamente |
| **Devolução no último dia** | Sem multa |
| **Devolução duplicada** | Gera erro |

---

## Tecnologias

- Java 17 ou superior
- Maven
- Git e GitHub

---

## Estrutura do Projeto

```text
src/
├── main/java/biblioteca/
│   ├── modelo/        Livro, Usuario, Emprestimo
│   ├── excecao/       Exceções customizadas
│   ├── repositorio/   Repositórios e controle em memória
│   ├── servico/       Regras de negócio e relatórios
│   └── app/           Main com o menu de terminal
└── test/java/biblioteca/
    └── ...            Testes automatizados

```

## Como Executar o Projeto

### Pré-requisitos
- JDK 17 ou superior
- Apache Maven configurado
- Git

### Passo a Passo

1. Clonar o repositório:
   ```bash
   git clone https://github.com/ezequielmoreira-java/Biblioteca.git
   cd Biblioteca
   ```

2. Compile:
   ```bash
   mvn compile
   ```

3. Execute a classe `Main` pela sua IDE.
   *(As instruções para gerar o `.jar` serão adicionadas quando o projeto estiver completo.)*

---

## Como rodar os testes
   ```bash
    mvn test
   ```
Os testes vão cobrir:

empréstimo com sucesso
- cada regra de bloqueio (usuário inexistente, multa pendente, limite de 3 empréstimos, idade mínima, livro já emprestado)
- devolução no prazo, no último dia, com 1 dia e com 5 dias de atraso
- devolução duplicada
- relatórios
