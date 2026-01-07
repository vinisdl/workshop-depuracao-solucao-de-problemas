/**
 * BUG 02: IndexOutOfBounds em Algoritmo de Ordenação
 * 
 * PROBLEMA: O programa lança uma IndexOutOfBoundsException ao tentar ordenar uma lista.
 * O erro não ocorre sempre - depende do tamanho e conteúdo da lista.
 * 
 * TAREFA: Use a metodologia de 5 passos para depurar este código.
 * 
 * INSTRUÇÕES:
 * 1. Execute o código e observe o erro
 * 2. Analise quando o erro ocorre (com quais dados?)
 * 3. Siga os 5 passos da metodologia
 * 4. Identifique a lógica incorreta no algoritmo
 * 5. Proponha uma correção
 * 
 * DICA: O bug está na lógica do algoritmo de ordenação. Preste atenção nos limites dos loops.
 */

import java.util.ArrayList;
import java.util.List;

public class Bug02_IndexOutOfBounds {
    
    public static void main(String[] args) {
        // Teste 1: Lista pequena - pode funcionar
        List<Integer> lista1 = new ArrayList<>();
        lista1.add(3);
        lista1.add(1);
        lista1.add(2);
        System.out.println("Lista original: " + lista1);
        ordenarBubbleSort(lista1);
        System.out.println("Lista ordenada: " + lista1);
        System.out.println();
        
        // Teste 2: Lista maior - pode falhar
        List<Integer> lista2 = new ArrayList<>();
        lista2.add(5);
        lista2.add(2);
        lista2.add(8);
        lista2.add(1);
        lista2.add(9);
        System.out.println("Lista original: " + lista2);
        ordenarBubbleSort(lista2);
        System.out.println("Lista ordenada: " + lista2);
        System.out.println();
        
        // Teste 3: Lista vazia
        List<Integer> lista3 = new ArrayList<>();
        System.out.println("Lista original: " + lista3);
        ordenarBubbleSort(lista3);
        System.out.println("Lista ordenada: " + lista3);
        System.out.println();
        
        // Teste 4: Lista com um elemento
        List<Integer> lista4 = new ArrayList<>();
        lista4.add(42);
        System.out.println("Lista original: " + lista4);
        ordenarBubbleSort(lista4);
        System.out.println("Lista ordenada: " + lista4);
    }
    
    /**
     * Ordena uma lista de inteiros usando o algoritmo Bubble Sort
     * BUG: Há um erro na lógica dos loops que causa IndexOutOfBoundsException
     * 
     * @param lista lista a ser ordenada (será modificada in-place)
     */
    public static void ordenarBubbleSort(List<Integer> lista) {
        if (lista == null || lista.isEmpty()) {
            return;
        }
        
        int n = lista.size();
        
        // BUG: O loop externo e interno têm limites incorretos
        // Isso causa acesso a índices inválidos
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // BUG: Quando j = n-1, j+1 = n, que está fora dos limites
                if (lista.get(j) > lista.get(j + 1)) {
                    // Trocar elementos
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }
}
