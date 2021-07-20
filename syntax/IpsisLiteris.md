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
4. ***[Nomenclatura de variáveis e constantes]()***
5. ***[Tipos e valores]()***
6. ***[Operadores e expressões]()***

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
- O identificador segue o [padrão de nomenclatura de variáveis e constantes]();
- A lista de comandos deve ter no mínimo um [comando](#comandos).

### Campo de declarações <a name="declaracoes" ></a>

O campo de declarações, possui as declarações de constantes e/ou variáveis segue a seguinte forma: 

```
declare {

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
- :{tipo}: é o tipo de dado da variável e pode ser **real**, **natural**, **char** ou **boolean**. Você pode ver mais na seção de [tipos]();
- :{lista de identificadores}: se refere à um ou mais identificadores e deve seguir as [normas de nomenclatura]();
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

- :{expressão ou valor}: se refere a qualquer expressão lógica, relacional ou aritimética envolvendo constantes ou identificadores.
Você pode ver mais detalhes na [seção de tipos e valores]();
- o resultado da expressão deve possuir o mesmo tipo das variáveis passadas na lista de identificadores.
- :{lista de identificadores se refere à uma lista com um ou mais nomes de variáveis separados com vírgula, onde todas devem ter o mesmo tipo.

### Comandos de Entrada e Saída <a name="io" ></a>

```
:_ ler valor do usuário
get { :{lista de identificadores}: }.

:_ mostrar valor para o usuário
put { :{lista de identificadores e/ou constantes}: }. 
```

### Comando de seleção <a name="if" ></a>

```
verify :{expressão}:
   is true { 
      :{lista de comandos}: 
   } is false { 
      :{lista de comandos}: 
   }.
```

### Loops <a name="loops" ></a>

```
verify :{expressão}:
   is true { 
      :{lista de comandos}: 
   } is false { 
      :{lista de comandos}: 
   }.
```

---





