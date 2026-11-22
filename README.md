# Bibliotech
	Um programa feito para gerenciar e realizar pesquisas em acervos de biblioteca.

## Visão Geral
O **Bibliotech** é um programa feito em Java utilizando sua biblioteca nativa Swing e SQLite para gerenciar e realizar pesquisas em um acervo criado pelo usuário. O programa é capaz de adicionar, editar, excluir e pesquisar livros.

A biblioteca do SQLite já vem no repositório, na pasta ``lib``.

## Como funciona
O programa solicita os dados do livro e um ID para ser guardado no banco de dados, que fica no arquivo ``biblioteca.db``, na pasta raiz do programa. Após adicionar um livro, é possível editá-lo clicando nele ainda na tela de gerenciamento e editando seus dados. Também é possível pesquisar dentro do acervo na tela de pesquisa.

## Como rodar

### Rodando pelo executável (.jar)
É possível rodar o programa por um arquivo ``.jar``, que está disponibilizado na aba Releases deste repositório. É necessário lembrar, porém, que o arquivo ``biblioteca.db`` será criado dentro do diretório em que o programa foi rodado.

---

### Rodando pelo código fonte
Para rodar pelo código fonte, é necessário ter instalado o Apache Ant.
Após isso, deve simplesmente clonar ou fazer download do repositório e rodar o seguinte comando em seu diretório principal:
```bash
ant run
```
