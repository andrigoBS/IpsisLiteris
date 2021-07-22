# Id Est

O compilador Ipsis Literis, após compilar um programa, o transforma um programa na linguagem Id Est,
que é interpretada pela máquina virtual de mesmo nome.

Id Est é uma linguagem baseada em duplas, onde se passa comandos separados por linhas e um parâmetro por comando. O comando modifica uma pilha
de dados de acordo com sua função e com o parâmetro recebido.

Os comandos da linguagem são os seguintes:

| Comando | Parâmetro | Execução |
|:-------:|:---------:|:--------:|
|   ADD   | QUALQUER  | Soma os dois últimos números da pilha |
|   DIV   | QUALQUER  | Divide os dois últimos números da pilha |
|   MUL   | QUALQUER  | Multiplica os dois últimos números da pilha |
|   SUB   | QUALQUER  | Subtrai os dois últimos números da pilha |
|   MOD   | QUALQUER  | Calcula o resto da divisão dos dois últimos números da pilha |
|   IDV   | QUALQUER  | Calcula a divisão inteira dos dois últimos números da pilha |
|   POW   | QUALQUER  | Eleva o subtopo número à potencia do topo da pilha |
