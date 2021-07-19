# IpsisLiteris (Compilador JavaCC)

Projeto de compilador desenvolvido com javaCC seguindo orientações da aula de compiladores - Univali - 2021/01.

### Dependencias:
- Java 14.1
- JavaFX 16

## Instalando o compilador IpsisLiteris:

- Para baixar o projeto basta cloná-lo na pasta que desejar.

  ``` bash
  git clone https://github.com/andrigoBS/IpsisLiteris.git
  ```

- Então, na sua IDE, apontar a classe [Launcher] como classe principal (main).
- Caso queira gerar o jar, basta dar o comando ```mvn clean package``` e o jar será gerado na pasta [jar] (nós já disponibilizamos o jar na mesma pasta)

## Executando

Ao executar o [jar], ou a função main da classe [Launcher], será aberta uma janela com a IDE para escrita do código fonte na linguagem [Ipsis Literis].

O código compilado, irá gerar um código intermediário na linguagem [Id est], que será executado pela nossa máquina virtual de mesmo nome.

## Autores
O presente código foi implementado por:
* [Andrigo B. Santos](https://github.com/andrigoBS)
* [Hilson A. W. Junior](https://github.com/Hilson-Alex)
* [Mathias]


## Licença

Esse projeto está sob a licença [MIT](/License.md)


## Links:
- como instalar javafx no intellij IDEA 2020.3:
  https://www.jetbrains.com/help/idea/javafx.html?keymap=primary_windows#vm-options
    

- JavaFX: https://gluonhq.com/products/javafx/

[Launcher]: /src/main/java/scanner/controller/Launcher.java
[Ipsis Literis]: /sintaxe/IpsisLiteris.md
[Id est]: /sintaxe/IdEst.md
[jar]: /src/jar
