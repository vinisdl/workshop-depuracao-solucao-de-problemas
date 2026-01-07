# Guia de Metodologia de Depuração

## Os 5 Passos da Depuração

### **Passo 1: Entender o Problema**

Antes de qualquer coisa, você precisa entender **exatamente** o que está acontecendo.

#### Perguntas a fazer:
- **O que era esperado?** — Qual era o comportamento correto?
- **O que aconteceu?** — Qual foi o comportamento real?
- **Em que cenário ocorre?** — Quando o problema aparece? (sempre, às vezes, com dados específicos?)

#### Exemplo:
```
Esperado: O programa deve calcular a média de 3 notas e exibir "Aprovado" se >= 7.0
Aconteceu: O programa lança uma exceção "NullPointerException"
Cenário: Acontece quando o usuário não preenche todas as notas
```

---

### **Passo 2: Formular Hipóteses**

Não adivinhe! Liste hipóteses baseadas no que você observou.

#### Regra: Máximo de 3 hipóteses
- Hipótese 1: [Descrição] — Por que é plausível?
- Hipótese 2: [Descrição] — Por que é plausível?
- Hipótese 3: [Descrição] — Por que é plausível?

#### Exemplo:
```
Hipótese 1: Uma variável está null quando não deveria estar
  → Plausível porque: A exceção é NullPointerException

Hipótese 2: Um array está vazio e tentamos acessar um índice
  → Plausível porque: Pode acontecer se não validarmos entrada

Hipótese 3: Um objeto não foi inicializado corretamente
  → Plausível porque: Pode ser um problema de construtor
```

---

### **Passo 3: Isolar a Causa**

Use ferramentas para **confirmar** qual hipótese está correta.

#### Ferramentas disponíveis:
- **System.out.println()** — Imprimir valores de variáveis
- **Logs** — Registrar o fluxo de execução
- **Breakpoints** — Pausar execução no debugger
- **Testes unitários** — Testar partes isoladas do código

#### Regra de ouro:
> **Nunca altere várias coisas ao mesmo tempo!**
> 
> Mude uma coisa, teste, depois mude outra.

#### Exemplo:
```java
// Antes de mudar, adicione logs para entender:
System.out.println("Valor da variável: " + variavel);
System.out.println("Tamanho do array: " + array.length);
```

---

### **Passo 4: Propor a Correção**

Corrija **apenas a causa raiz**, não os sintomas.

#### Checklist:
- A correção resolve a causa raiz?
- A correção é simples e clara?
- A correção não quebra outras partes do código?

#### Exemplo:
```java
// ERRADO: Corrigir o sintoma
if (variavel != null) {
    // código
}

// CERTO: Corrigir a causa raiz
// Garantir que a variável seja inicializada corretamente
String variavel = obterValor(); // ao invés de deixar null
```

---

### **Passo 5: Proteger com Teste**

Sua correção funciona? Prove com um teste!

#### O que testar:
- **Cenário normal** — Funciona como esperado?
- **Cenário de erro** — Trata erros corretamente?
- **Casos extremos** — Funciona com valores limites?

#### Exemplo:
```java
@Test
public void testarCalculoMedia() {
    // Cenário normal
    double media = calcularMedia(7.0, 8.0, 9.0);
    assertEquals(8.0, media, 0.01);
    
    // Caso extremo: valores zero
    double mediaZero = calcularMedia(0.0, 0.0, 0.0);
    assertEquals(0.0, mediaZero, 0.01);
}
```

---

## Princípios Importantes

### **Priorize:**
- Clareza sobre elegância
- Aprendizado sobre velocidade
- Código simples e legível

### **Evite:**
- Soluções "avançadas" desnecessárias
- Padrões complexos sem necessidade
- Reestruturações grandes do código
- Assumir conhecimento prévio

---

## Frases Úteis para Pensar

- "Vamos entender isso passo a passo"
- "Antes de mudar o código, pense nisso..."
- "Esse erro é comum, vamos analisar juntos"
- "O importante aqui é entender o motivo"

---

## Checklist de Depuração

Antes de considerar um bug resolvido, verifique:

- [ ] Entendi o problema completamente?
- [ ] Formulei hipóteses antes de corrigir?
- [ ] Confirmei a causa raiz com logs/testes?
- [ ] Minha correção é simples e direta?
- [ ] Escrevi testes para validar a correção?
- [ ] A correção não quebrou outras funcionalidades?

---

## O que Aprendemos?

Sempre termine a depuração refletindo:

- **O que causou esse bug?**
- **Como posso evitar isso no futuro?**
- **O que aprendi sobre depuração hoje?**

---

> **Lembre-se**: A depuração é uma habilidade que se desenvolve com prática e paciência. Não tenha pressa!
