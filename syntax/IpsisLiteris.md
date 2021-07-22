# Sintaxe da linguagem Ipsis Literis

A linguagem Ipsis Literis segue um padrão de sintaxe definido para as aulas de compiladores 
do curso de Ciência da Computação da Univali oferecido no semestre 2021-1.

### Seções:

1. ***[Forma geral de um programa](#forma-geral)***
   1. *[Campo de declarações](#declaracoes)*
   2. *[Comentários](#comentarios)*
2. ***[Comandos](#comandos)***
   1. *[Comando de atribuição](#atribuicao)*
   2. *[Comandos de Entrada e Saída](#io)*
   3. *[Comando de seleção](#if)*
   4. *[Loops](#loops)*
4. ***[Nomenclatura de variáveis e constantes](#nomes)***
5. ***[Tipos e valores](#tipos)***
   1. *[Valores Numéricos](#numericos)*
   2. *[Literais](#char)*
   3. *[Lógicos](#bool)*
7. ***[Operadores e expressões](#operadores)***
   1. *[Operadores Lógicos](#logico)*
   2. *[Operadores Relacionais](#relacional)*
   3. *[Operadores Aritméticos](#aritmetico)*

---

## Forma geral de um programa <a name="forma-geral" ></a>

Para que um programa seja compilado pelo compilador IpsisLiteris, deve seguir o seguinte padrão:

```
:- "Header do programa (opcional)"
program {

  :_ campo de declarações (opcional)
  
  execute {
    :_ lista de comandos>
  }
}
identificador (opcional)
```

- O Header é uma string com informações acerca do programa;
- O identificador segue o [padrão de nomenclatura de variáveis e constantes](#nomes);
- A lista de comandos deve ter no mínimo um [comando](#comandos).

### Campo de declarações <a name="declaracoes" ></a>

O campo de declarações, possui as declarações de constantes e/ou variáveis segue a seguinte forma: 

```
define {

  :_ seção de constantes
  not variable
    :{tipo}: is :{lista de identificadores}: :{valor}: .
    :{tipo}: is :{lista de identificadores}: :{valor}: .
    
  :_ seção de variáveis
  variable
    :{tipo}: is :{lista de identificadores}: .

}
```

- Só pode haver uma seção de cada tipo (uma de constantes e uma de variáveis), 
porém, dentro das seções pode-se declarar quantas constantes ou variáveis quiser, contanto que sejam separadas por ponto (.);
- As seções podem vir em qualquer ordem, e não é obrigatório ter ambas, pode-se ter apenas a seção de constantes ou a de variáveis;
- :{tipo}: é o tipo de dado da variável e pode ser [**real**](#real), [**natural**](#natural), [**char**](#char) ou [**boolean**](#bool).
Você pode ver mais na seção de [tipos](#tipos);
- :{lista de identificadores}: se refere à um ou mais identificadores e deve seguir as [normas de nomenclatura](#nomes);
- Caso haja mais de 1 identificador, os identificadores devem ser separados por vírgula (,);
- :{valor}: deve ser compatível com o tipo de dado declarado.

Perceba que cada declaração deve terminar com um ponto (.). 
O ponto é usado como delimitador da linguagem e é obrigatório no fim de toda sentença.

### Comentários <a name="comentarios" ></a>

Como a maioria das linguagens, há duas formas de comentar um programa Ipsis Literis:

```
:_ Isso é um comentário de linha

:{
Isso
é um 
comentário
de bloco
}:
```

---

## Comandos <a name="comandos" ></a>

O compilador considera erro sintático um programa que não possua no mínimo um comando, isso porque uma lista de comandos espera no mínimo um comando.
É importante ressaltar que todos os comandos devem terminar com o delimitador (.).

### Comando de atribuição <a name="atribuicao" ></a>

O comando de atribuicao segue da seguinte forma: 

```
set :{expressão ou valor}: to :{lista de iidentificadores}: .
```

- :{expressão ou valor}: se refere a qualquer expressão lógica, relacional ou aritmética envolvendo constantes ou identificadores.
Você pode ver mais detalhes na seção [de tipos e valores](#tipos) ou de [expressões e operadores](#operadores);
- o resultado da expressão deve possuir o mesmo tipo das variáveis passadas na lista de identificadores.
- :{lista de identificadores se refere à uma lista com um ou mais [nomes de variáveis](#nomes) separados com vírgula, onde todas devem ter o mesmo tipo.

### Comandos de Entrada e Saída <a name="io" ></a>

```
:_ ler valor do usuário
get { :{lista de identificadores}: }.

:_ mostrar valor para o usuário
put { :{lista de identificadores e/ou constantes}: }. 
```
- Para o comando de entrada de dados (`get`), é esperada uma lista com um ou mais identificadores separados por vírgula;
- Os identificadores passados ao comando de entrada de dados (`get`) devem estar vinculados apenas à variáveis;
- O comando `get` lê apenas um único valor e o passa para todas as variáveis listadas;
- Para o comando de saída de dados (`put`), é esperada uma lista com um ou mais valores separados por vírgula,
sendo que tais valores podem ser *[numéricos](#numericos)*, *[literais](#char)*, [*identificadores de variáveis* ou *identificadores de constantes*](#nomes),

### Comando de seleção <a name="if" ></a>

```
verify :{expressão}:
   is true { 
      :{lista de comandos}: 
   } is false { 
      :{lista de comandos}: 
   }.
```

- :{expressão}: se refere a qualquer expressão [lógica](#logico) ou [relacional](#relacional) envolvendo constantes ou identificadores.
Você pode ver mais detalhes na seção [de tipos e valores](#tipos) ou de [expressões e operadores](#operadores);
- O resultado da expressão deve, obrigatóriamente, ser um valor [booleano](#bool);
- As cláusulas `is true` e `is false` podem acontecer apenas uma vez cada, em qualquer ordem, e é necessário que apenas uma delas 
esteja presente.


### Loops <a name="loops" ></a>

```
:_ Loop com no mínimo uma execução (do-while)
loop { 
   :{lista de comandos}: 
} while :{expressão}: is true.

:_ Loop com no mínimo nenhuma execução (while)
while :{expressão}: is true do { 
   :{lista de comandos}: 
}. 
```
- :{expressão}: se refere a qualquer expressão [lógica](#logico) ou [relacional](#relacional) envolvendo constantes ou identificadores.
- O resultado da expressão deve, obrigatóriamente, ser um valor [booleano](#bool);
- Os comandos da estrutura de repetição serão repetidos sempre que o resultado da avaliação da expressão for true. 

---

## Nomenclatura de variáveis e constantes <a name="nomes" ></a>

De acordo com as especificações passadas para as aulas de compiladores, os identificadores seguem uma limitação de nome
restritiva, que segue as seguintes regras:

1. O nome dos identificadores deve começar com *letras* ou com o símbolo *" \_"*;
2. Após o caractere inicial, pode, ou não, haver letras, dígitos ou o símbolo "\_";
3. O identificador não pode começar nem terminar com dígitos;
4. O identificador não pode ter dígitos consecutivos;
5. O identificador não pode ter o símbolos "\_" consecutivos.

Caso um identificador seja declarado como uma ***variável*** (e não uma constante), poderá ser acompanhado de um 
índice para que se comporte como um vetor, como por exemplo `variavel[5]`.

Quando usado em uma declaração, o índice indica a quantidade de espaços alocados para o vetor, 
já quando usado no código, o índice passado é usado como endereço de acesso, tendo valor mínimo 1 e valor máximo o passado em sua declaração. 
Exemplo:

```
:- "Exemplo de vetor"
program {

  define {
     variable
       :_ definindo um vetor de 4 posições
       real is vetor[4].
   }
  
  execute {
    :_ preenchendo o vetor com um valor passado pelo usuário
    get { vetor[1], vetor[2], vetor[3], vetor[4] }.
  }
}

exemplo_vetor
```

---

## Tipos e valores <a name="tipos" ></a>

A linguagem IpsisLiteris não oferece suporte a funções ou tipos abstratos de dados, apenas aos tipos primitivos
[real](#real), [natural](#natural), [boolean](#bool), ou [char](#char) (Apesar do nome, contra intuitivo, char é usada para strings).

### Valores Numéricos <a name="numericos" ></a>

Na linguagem ipsis literis, valores numéricos são sempre positivos, mas, isso não significa que a linguagem não
tenha suporte a valores negativos, basta iniciar com 0 e subtrair o valor desejado. 

Para operações [aritméticas](#aritmetico) ou [relacionais](#relacional), valores numéricos podem ser usados idependentemente de seus tipos,
mas, caso se misture natural e real, o resultado sempre será real.

> Vale ressaltar que, valores numéricos ***não*** são concatenáveis, logo não se deve misturá-los com strings.

Os tipos de dados numéricos se resumem à:

- ***Naturais:*** <a name="natural"></a>
Naturais são números inteiros, que, em atribuições, são aceitos números de 0 a 999, e, para operações aritméticas,
pode assumir valores de -2147483648 até 2147483647.

- ***Reais:*** <a name="reais"></a>
Reais são números que podem ser decimais. Em atribuições, são aceitos números de 0 a 99999.99, sendo que números com
ou sem casas decimais, tendo no máximmo duas casas decimais. Em operações aritméticas é possível assumir valores desde
1.7976931348623157 x 10^308 até 4.9406564584124654 x 10^-324 (valores positivos e negativos).

### Literais <a name="char"></a>

Literais são valores textuais que, na linguagem IpsisLiteris, são do tipo `char`. Valores literais não possuem limitações,
ou seja, qualquer valor entre aspas duplas ou simples pode ser uma literal.

Aspas também são aceitas caso escapadas, mas aspas duplas só podem ser escapadas entre aspas duplas e aspas simples só podem
ser escapadas entre aspas simples. Aspas duplas não necessitam ser escapadas entre aspas simples e vice-versa.

Exemplos de valores literais válidos:

- "Abobora";
- 'Andorinha';
- "123534";
- "Então ele disse: \\"Olá, tudo bem?\\"";

### Lógicos <a name="bool"></a>

Lógicos se limitam a valores booleanos que assumem os valores `true` ou `false` e possuem o tipo `boolean`.

---

## Operadores e expressões <a name="operadores"></a>

### Operadores Lógicos <a name="logico"></a>

Operadores lógicos operam sobre valores booleanos e retornam resultados booleanos. 

Os operadores lógicos da linguagem são:

| **Operador** |    **Função**   |    **Uso**    |
|:------------:|:---------------:|:-------------:|
|      _&_     |    "E" lógico   |  true & true  |
|     _\|_     |   "Ou" lógico   | true \| false |
|      _!_     | Inversor lógico |    !(false)   |

### Operadores Relacionais <a name="relacional"></a>

Operadores relacionais recebem valores numéricos e devolvem valores booleanos, exceto pelo operador de igualdade, que funciona
para qualquer tipo de dado.

Os operadores relacionais da linguagem são:

| **Operador** |   **Função**   |    **Uso**    |
|:------------:|:--------------:|:-------------:|
|     _==_     |   Igualdade    |     1 == 1    |
|     _!=_     |   Diferença    | true != false |
|      _<_     |   Menor que    |     1 < 2     |
|      _>_     |   Maior que    |     2 > 1     |
|     _<=_     | Menor ou igual |    1 <= 2     |
|     _>=_     | Maior ou igual |    2 >= 1     |

### Operadores Aritméticos <a name="aritmetico"></a>

| **Operador** |   **Função**    |    **Uso**    |
|:------------:|:---------------:|:-------------:|
|      _+_     |      Soma       |     1 + 1     |
|      _-_     |    Subtração    |     1 - 1     |
|      _*_     |  Multiplicação  |     1 * 2     |
|      _/_     |     Divisão     |     2 / 1     |
|     _**_     |   Potenciação   |    2 ** 4     |
|      _%_     | Divisão Inteira |    10 % 3     |
|     _%%_     |      Resto      |    10 %% 3    |

