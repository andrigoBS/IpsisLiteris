# Sintaxe da linguagem Ipsis Literis

A linguagem Ipsis Literis segue um padrão de sintaxe definido para as aulas de compiladores 
do curso de Ciência da Computação da Univali oferecido no semestre 2021-1.

### Seções:
1. ***[Forma geral de um programa](#forma-geral)***
   1. *[Campo de declarações](#declaracoes)*
2. Comandos
3. Nomenclatura de variáveis
4. Tipos e valores

---

## [Forma geral de um programa <a name="forma-geral" ></a>

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

- O Header é uma string com informações acerca do programa
- O identificador segue o [padrão de nomenclatura de variáveis e constantes]()
- A lista de comandos deve ter no mínimo um comando

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
- :{lista de identificadores}: se refere à um ou mais identificadores e deve seguir as [normas de nomenclatura]()

Perceba que cada declaração deve terminar com um ponto (.). 
O ponto é usado como delimitador da linguagem e é obrigatório no fim de toda sentença.

TODO: terminar




