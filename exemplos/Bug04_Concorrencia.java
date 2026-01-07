/**
 * BUG 04: Race Condition em Sistema de Estoque
 * 
 * PROBLEMA: O sistema de estoque está permitindo vendas mesmo quando não há produtos suficientes.
 * Múltiplas threads podem vender simultaneamente, causando estoque negativo.
 * 
 * TAREFA: Use a metodologia de 5 passos para depurar este código.
 * 
 * INSTRUÇÕES:
 * 1. Execute o código e observe o estoque negativo
 * 2. Analise o comportamento com múltiplas threads
 * 3. Siga os 5 passos da metodologia
 * 4. Identifique a race condition
 * 5. Proponha uma correção thread-safe
 * 
 * DICA: O problema está na verificação e atualização do estoque. 
 * Entre verificar e atualizar, outra thread pode modificar o valor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Bug04_Concorrencia {
    
    private static class Produto {
        private String nome;
        private int estoque;
        
        public Produto(String nome, int estoque) {
            this.nome = nome;
            this.estoque = estoque;
        }
        
        public String getNome() {
            return nome;
        }
        
        public int getEstoque() {
            return estoque;
        }
        
        public void setEstoque(int estoque) {
            this.estoque = estoque;
        }
    }
    
    private static Produto produto = new Produto("Notebook", 10);
    private static List<String> vendas = new ArrayList<>();
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Estoque inicial: " + produto.getEstoque());
        System.out.println("Iniciando 20 vendas simultâneas...\n");
        
        // Criando 20 threads que tentam vender 1 unidade cada
        int numThreads = 20;
        Thread[] threads = new Thread[numThreads];
        CountDownLatch latch = new CountDownLatch(numThreads);
        
        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                try {
                    latch.await(); // Aguarda todas as threads estarem prontas
                    vender(threadId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
            latch.countDown();
        }
        
        // Aguardando todas as threads terminarem
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println("Estoque final: " + produto.getEstoque());
        System.out.println("Total de vendas realizadas: " + vendas.size());
        System.out.println("Vendas esperadas: 10 (limite do estoque)");
        
        if (produto.getEstoque() < 0) {
            System.out.println("\nERRO: Estoque negativo detectado!");
        }
    }
    
    /**
     * Tenta vender uma unidade do produto
     * BUG: Race condition - a verificação e atualização não são atômicas
     * 
     * @param threadId ID da thread para log
     */
    public static void vender(int threadId) {
        // BUG: Entre verificar estoque > 0 e decrementar, outra thread pode modificar
        if (produto.getEstoque() > 0) {
            // Simulando processamento (tempo entre verificação e atualização)
            try {
                Thread.sleep(10); // Simula tempo de processamento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // BUG: Não verifica novamente se ainda há estoque
            int novoEstoque = produto.getEstoque() - 1;
            produto.setEstoque(novoEstoque);
            
            String venda = "Thread " + threadId + " vendeu 1 unidade. Estoque: " + novoEstoque;
            synchronized (vendas) {
                vendas.add(venda);
                System.out.println(venda);
            }
        } else {
            System.out.println("Thread " + threadId + ": Estoque insuficiente");
        }
    }
}
