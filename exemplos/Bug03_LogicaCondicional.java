/**
 * BUG 03: Lógica de Negócio Complexa com Múltiplas Condições
 * 
 * PROBLEMA: O sistema de cálculo de desconto não está funcionando corretamente.
 * Alguns clientes recebem desconto quando não deveriam, outros não recebem quando deveriam.
 * 
 * TAREFA: Use a metodologia de 5 passos para depurar este código.
 * 
 * INSTRUÇÕES:
 * 1. Execute o código e observe os resultados incorretos
 * 2. Analise as regras de negócio e compare com os resultados
 * 3. Siga os 5 passos da metodologia
 * 4. Identifique onde a lógica está incorreta
 * 5. Proponha uma correção
 * 
 * REGRAS DE NEGÓCIO:
 * - Cliente VIP com compra >= 1000: 20% de desconto
 * - Cliente VIP com compra >= 500: 15% de desconto
 * - Cliente VIP com compra < 500: 10% de desconto
 * - Cliente regular com compra >= 1000: 10% de desconto
 * - Cliente regular com compra >= 500: 5% de desconto
 * - Cliente regular com compra < 500: sem desconto
 */

public class Bug03_LogicaCondicional {
    
    public static void main(String[] args) {
        // Teste 1: Cliente VIP, compra 1200 - deveria ter 20% desconto
        calcularDesconto(true, 1200.0);
        
        // Teste 2: Cliente VIP, compra 600 - deveria ter 15% desconto
        calcularDesconto(true, 600.0);
        
        // Teste 3: Cliente VIP, compra 300 - deveria ter 10% desconto
        calcularDesconto(true, 300.0);
        
        // Teste 4: Cliente regular, compra 1200 - deveria ter 10% desconto
        calcularDesconto(false, 1200.0);
        
        // Teste 5: Cliente regular, compra 600 - deveria ter 5% desconto
        calcularDesconto(false, 600.0);
        
        // Teste 6: Cliente regular, compra 300 - sem desconto
        calcularDesconto(false, 300.0);
        
        // Teste 7: Cliente VIP, compra exatamente 1000 - deveria ter 20% desconto
        calcularDesconto(true, 1000.0);
        
        // Teste 8: Cliente regular, compra exatamente 500 - deveria ter 5% desconto
        calcularDesconto(false, 500.0);
    }
    
    /**
     * Calcula o desconto baseado no tipo de cliente e valor da compra
     * BUG: A lógica das condições está incorreta, causando descontos errados
     * 
     * @param isVip true se o cliente é VIP, false caso contrário
     * @param valorCompra valor da compra
     */
    public static void calcularDesconto(boolean isVip, double valorCompra) {
        double desconto = 0.0;
        double valorFinal = valorCompra;
        
        if (isVip) {
            // BUG: A ordem das condições está errada
            // Condições com >= devem vir antes de condições com >
            if (valorCompra > 500) {
                desconto = 0.15; // 15%
            } else if (valorCompra > 1000) {
                desconto = 0.20; // 20% - NUNCA será alcançado!
            } else {
                desconto = 0.10; // 10%
            }
        } else {
            // Cliente regular
            if (valorCompra >= 1000) {
                desconto = 0.10; // 10%
            } else if (valorCompra >= 500) {
                desconto = 0.05; // 5%
            }
            // BUG: Não há else explícito, mas desconto já é 0.0 por padrão
        }
        
        valorFinal = valorCompra * (1 - desconto);
        
        System.out.printf("Cliente %s | Compra: R$ %.2f | Desconto: %.0f%% | Final: R$ %.2f%n",
                isVip ? "VIP" : "Regular", valorCompra, desconto * 100, valorFinal);
    }
}
