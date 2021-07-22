# Id Est

O compilador Ipsis Literis, após compilar um programa, o transforma um programa na linguagem Id Est,
que é interpretada pela máquina virtual de mesmo nome. Apesar de ser o resultado do código compilado,
as limitações da linguagem Ipsis Literis não se aplicam à máquina virtual. A máquina virtual segue os tipos
e valores padrões do Java.

Id Est é uma linguagem baseada em duplas, onde se passa comandos separados por linhas e um parâmetro por comando. O comando modifica uma pilha
de dados de acordo com sua função e com o parâmetro recebido.

Os comandos da linguagem são os seguintes:

| Comando | Parâmetro | Execução |
|:-------:|:---------:|:---------|
|   ADD   | QUALQUER  | Soma os dois últimos números da pilha |
|   DIV   | QUALQUER  | Divide os dois últimos números da pilha |
|   MUL   | QUALQUER  | Multiplica os dois últimos números da pilha |
|   SUB   | QUALQUER  | Subtrai os dois últimos números da pilha |
|   MOD   | QUALQUER  | Calcula o resto da divisão dos dois últimos números da pilha |
|   IDV   | QUALQUER  | Calcula a divisão inteira dos dois últimos números da pilha |
|   POW   | QUALQUER  | Eleva o subtopo número à potencia do topo da pilha |
|   ALB   |  Integer  | Aloca espaço na pilha de dados para a quantidade de variáveis booleanas passada como parâmetro |
|   ALR   |  Integer  | Aloca espaço na pilha de dados para a quantidade de variáveis double passada como parâmetro |
|   ALI   |  Integer  | Aloca espaço na pilha de dados para a quantidade de variáveis int passada como parâmetro |
|   ALS   |  Integer  | Aloca espaço na pilha de dados para a quantidade de variáveis String passada como parâmetro |
|   LDB   |  Boolean  | Carrega o Boolean passado como parâmetro no topo da pilha de dados |
|   LDI   |  Integer  | Carrega o Integer passado como parâmetro no topo da pilha de dados |
|   LDR   |  Double   | Carrega o Double passado como parâmetro no topo da pilha de dados |
|   LDS   |  String   | Carrega a String passada como parâmetro no topo da pilha de dados |
|   LDV   |  Integer  | Carrega no topo da pilha de dados o valor contido no endereço passado como parâmetro |
|   STR   |  Integer  | Guarda o valor do topo da pilha de dados no endereço passado como parâmetro |
|   STC   |  Integer  | Guarda o valor do topo da pilha de dados no em todos os valores desde o topo - parâmetro até o topo -1 |
|   DPC   | QUALQUER  | Duplica o valor no topo da pilha |
|   AND   | QUALQUER  | Realiza a operação and sobre os dois últimos números da pilha |
|   OR    | QUALQUER  | Realiza a operação or sobre os dois últimos números da pilha |
|   NOT   | QUALQUER  | Realiza a operação not sobre o topo da pilha |
|   BGE   | QUALQUER  | Verifica se o subtopo é maior ou igual ao topo da pilha |
|   BGR   | QUALQUER  | Verifica se o subtopo é maior que topo da pilha |
|   SME   | QUALQUER  | Verifica se o subtopo é menor ou igual ao topo da pilha |
|   EQL   | QUALQUER  | Verifica se o subtopo é igual ao topo da pilha |
|   DIF   | QUALQUER  | Verifica se o subtopo é diferente do topo da pilha |
|   JMF   |  Integer  | Pula para o endereço passado caso o valor no topo seja false |
|   JMT   |  Integer  | Pula para o endereço passado caso o valor no topo seja true |
|   JMP   |  Integer  | Pula para o endereço passado |
|   REA   |  Integer  | Lê a entrada do usuário, fazendo um Typecast para o tipo selecionado (1 - Integer; 2 - Double; 3 - String; 4 -Boolean) |
|   WRT   | QUALQUER  | Escreve o valor no topo |
|   STP   | QUALQUER  | Para o programa |


> Todos os comandos que utilizam o topo ou topo e subtopo, desempilham os valores usados, e, caso retornem um valor, empilham o resultado, com exceção do DPR que apenas reempilha o topo sem desempilhá-lo.

