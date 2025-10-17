# 📚 Estante Digital

Trabalho Prático
Disciplina: Algoritmos e Estruturas de Dados III
PUC Minas – Engenharia de Computação
2025/2

---

## 👥 Componentes do Grupo

Kethellen da Silva Pereira

Priscila Silva Soares

Rafael dos Santos Generoso Pinheiro 

---

## 🧩 1. Descrição do Problema

O projeto tem como proposta o desenvolvimento de uma estante digital, um sistema que permita cadastrar, gerenciar e organizar livros associados a diferentes usuários.
Todos os dados devem ser armazenados em arquivos binários, contendo cabeçalho e controle de exclusão lógica por lápide, garantindo integridade, persistência e reaproveitamento de espaço.


---

## 🎯 2. Objetivo do Trabalho

O objetivo do trabalho é aplicar os conceitos de estruturas de dados e organização de arquivos, implementando um sistema completo com:

CRUD (Create, Read, Update, Delete) de todas as entidades:
Livro, Usuário, Autor e Editora.

Persistência em disco, utilizando arquivos binários com controle de exclusão lógica.

Relacionamentos entre entidades (1:N) por meio de B+ Tree e Hash Extensível.

Documentação técnica, incluindo:

Diagrama de Caso de Uso (DCU);

Diagrama Entidade-Relacionamento (DER);

Arquitetura Proposta do Sistema.


---

### 🧱 3. Estrutura do Projeto

 📂 src/
 
 ┣ 📂 controller/ Regras de negócio e controle de CRUD
 
 ┣ 📂 dao/ Classes de acesso a dados
 
 ┣ 📂 indices/ Estruturas de indices
 
 ┣ 📂 model/ Entidades principais
 
 ┣ 📂 view/ Classe principal e interface de interação com o usuário
 
  📂 dados/ Arquivos binários de data
  
 ┗ 📜 README.md


---

## 💾 4. Persistência dos Dados

Os registros são armazenados em arquivos binários.

Cada arquivo possui:

Cabeçalho (para controle do último ID e quantidade de registros válidos);

Registros com lápide, indicando exclusões lógicas.

Espaços de registros excluídos podem ser reutilizados conforme o tamanho do novo registro.

O relacionamento 1:N entre Livro e Editora é gerenciado por:

Hash Extensível – acesso direto otimizado.


---

## 🧰 5. Tecnologias Utilizadas

Linguagem: Java (JDK 17+)

Ambiente: Console

Paradigma: Programação Orientada a Objetos

Persistência: Arquivos binários (RandomAccessFile)

Controle de versão: Git e GitHub

---

## 🧪 6. Compilação e Execução

### 🔧 Compilação

No diretório raiz do projeto, execute:

javac -d bin src/*/.java

### ▶️ Execução

java -cp bin view.Main

> 💡 Caso esteja usando o Visual Studio Code ou Eclipse, basta executar a classe Main do pacote view.


---

## 🗂️ 7. Estrutura de Arquivos Persistentes

Arquivo	Conteúdo	Descrição

data/livros.dat	Registros de livros	Contém cabeçalho e lápides
data/usuarios.dat	Registros de usuários	CRUD com exclusão lógica
data/autores.dat	Registros de autores	Inclui data e nacionalidade
data/editoras.dat	Registros de editoras	Chave primária usada em relacionamentos
data/editora_livros_bpt.idx	Índice B+ Tree	Mapeia editora → livros
data/editora_livros_hash.idx	Índice Hash Extensível	Mapeia editora → livros


---

## 🧾 8. Documentação Técnica

O projeto será acompanhado pelos seguintes documentos:

Diagrama de Caso de Uso (DCU): mostra as interações do usuário com o sistema.

Diagrama Entidade-Relacionamento (DER): representa as entidades e seus relacionamentos.

Arquitetura Proposta: descreve a divisão do sistema em camadas e pacotes.

Manual de Execução: explica como rodar o sistema e realizar operações básicas.


---
