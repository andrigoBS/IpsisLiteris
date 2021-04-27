# Compilador JAVA CC (Linguagem: IPSIS_LITERIS)

Projeto de compilador usando javaCC desenvolvido para aula de compiladores - Univali - 2020/01.
No presente momento apenas o analisador léxico está sendo implementado.

## Intalação:

- Primeiro deve ser feito o download do projeto.
  
- Após isso na sua IDE de programação java preferida de ser adicionado como biblioteca externa todos os aquivos do 
  diretório: javafx-sdk-16/lib.
  
- Nas configurações de execução, onde trata-se das opções da maquina virtual java deve-se adicionar os seguintes 
  parâmetros:
  -  --module-path **diretorio do projeto no SO**/scannerJavaCC/javafx-sdk-16/lib
  -  --add-modules javafx.controls,javafx.fxml
  -  --add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED

### Dependencias:
- Java 14.1
- JavaFX 16
    
## Execução:
- Deve ser feita via IDE ou via linha de comando informando os parametros da JVM
- Executavel JAR do projeto se encontra no diretório: /jar
    - Para rodar o executavel deve ser feito via linha de comando usando: 
        - java -jar scanner.jar --module-path **diretorio do projeto no SO**/scannerJavaCC/javafx-sdk-16/lib --add-modules javafx.controls,javafx.fxml --add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
    
## Links:
- como instalar javafx no intellij IDEA 2020.3:
  https://www.jetbrains.com/help/idea/javafx.html?keymap=primary_windows#vm-options
    

- JavaFX: https://gluonhq.com/products/javafx/


## Autores
O presente código foi implementado por:
* [Andrigo B. Santos](https://github.com/andrigoBS)
* [Hilson A. W. Junior](https://github.com/Hilson-Alex)

