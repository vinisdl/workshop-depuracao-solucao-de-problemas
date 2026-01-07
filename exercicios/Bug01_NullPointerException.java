/**
 * BUG 01: NullPointerException em Cadeia de Chamadas
 * 
 * PROBLEMA: O programa lança uma NullPointerException, mas não é óbvio onde exatamente.
 * O erro ocorre em uma cadeia de chamadas de métodos, tornando difícil identificar a causa.
 * 
 * TAREFA: Use a metodologia de 5 passos para depurar este código.
 * 
 * INSTRUÇÕES:
 * 1. Execute o código e observe o erro
 * 2. Analise a stack trace para entender a cadeia de chamadas
 * 3. Siga os 5 passos da metodologia
 * 4. Identifique qual objeto na cadeia é null
 * 5. Proponha uma correção que valide toda a cadeia
 */

import java.util.ArrayList;
import java.util.List;

public class Bug01_NullPointerException {
    
    public static void main(String[] args) {
        // Simulando um sistema de gestão de alunos
        Turma turma = criarTurma();
        
        // Tentando calcular a média da turma
        double mediaTurma = calcularMediaTurma(turma);
        
        System.out.println("Média da turma: " + mediaTurma);
    }
    
    /**
     * Cria uma turma com alguns alunos
     * ATENÇÃO: Este método pode retornar uma turma incompleta
     */
    public static Turma criarTurma() {
        Turma turma = new Turma();
        turma.setNome("Turma A");
        
        // Adicionando alguns alunos
        Aluno aluno1 = new Aluno("João");
        aluno1.adicionarNota(8.5);
        aluno1.adicionarNota(7.0);
        turma.adicionarAluno(aluno1);
        
        Aluno aluno2 = new Aluno("Maria");
        // BUG: aluno2 não tem notas adicionadas
        turma.adicionarAluno(aluno2);
        
        Aluno aluno3 = new Aluno("Pedro");
        aluno3.adicionarNota(9.0);
        aluno3.adicionarNota(8.0);
        turma.adicionarAluno(aluno3);
        
        return turma;
    }
    
    /**
     * Calcula a média de todas as notas de todos os alunos da turma
     */
    public static double calcularMediaTurma(Turma turma) {
        if (turma == null) {
            return 0.0;
        }
        
        List<Aluno> alunos = turma.getAlunos();
        double somaTotal = 0.0;
        int quantidadeNotas = 0;
        
        for (Aluno aluno : alunos) {
            // BUG: aluno pode ser null, ou aluno.getNotas() pode retornar null
            List<Double> notas = aluno.getNotas();
            for (Double nota : notas) {
                somaTotal += nota;
                quantidadeNotas++;
            }
        }
        
        return quantidadeNotas > 0 ? somaTotal / quantidadeNotas : 0.0;
    }
}

// Classes auxiliares
class Turma {
    private String nome;
    private List<Aluno> alunos;
    
    public Turma() {
        this.alunos = new ArrayList<>();
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }
}

class Aluno {
    private String nome;
    private List<Double> notas;
    
    public Aluno(String nome) {
        this.nome = nome;
        // BUG: notas não é inicializada - será null até adicionarNota() ser chamado
    }
    
    public String getNome() {
        return nome;
    }
    
    public void adicionarNota(double nota) {
        if (notas == null) {
            notas = new ArrayList<>();
        }
        notas.add(nota);
    }
    
    public List<Double> getNotas() {
        // BUG: Retorna null se nenhuma nota foi adicionada
        return notas;
    }
}



