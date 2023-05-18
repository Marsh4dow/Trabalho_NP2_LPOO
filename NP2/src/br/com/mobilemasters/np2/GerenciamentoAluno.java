package br.com.mobilemasters.np2;

import java.util.List;

public class GerenciamentoAluno extends GerenciamentoBase {

    private PersisteAluno persisteAluno;
    
    public GerenciamentoAluno() {
        persisteAluno = new PersisteAluno();
        persisteAluno.carrega();
    }
            
    public void adicionaAluno() {
        String id = getInputString("Digite o RA do aluno", true);
        String nome = getInputString("Digite o nome do aluno", true);

        Aluno a = new Aluno(id, nome);
        
        try {
            persisteAluno.adicionaAluno(a);
            mostraMensagem("Aluno incluido com sucesso");
        } catch (ErroPersiste e) {
            mostraErro(e.getMessage());
        }
    }

    public void relatorioAluno() {
        String id = getInputString("Digite o RA do aluno", true);
        
        Aluno aluno = persisteAluno.procuraAlunoPorId(id);
        
        if (aluno == null) {
            mostraErro("RA não encontrado");
            return;
        }

        TodosRendimentos tr = new TodosRendimentos();
        tr.carregarTodosRendimentos();

        List<Rendimento> rendimentosAluno = tr.getRendimentosAluno(aluno);
        if (rendimentosAluno != null && rendimentosAluno.size() > 0) {

            Double somaMediaGeral = 0d;
            int registros = 0;

            System.out.println("\n\n-----------------------------------------------------------------------------------");
            System.out.println(Rendimento.getDadosCursoTitulo());
            System.out.println("-----------------------------------------------------------------------------------");
            
            for(Rendimento r : rendimentosAluno) {
                System.out.println(
                    r.getDadosCurso()
                );
                
                somaMediaGeral += r.getMedia();
                registros++;
            }
            
            System.out.println("\n\n");
            System.out.println("Média Geral: " + (somaMediaGeral / registros));
        } else {
            mostraMensagem("Aluno não cursou ainda nenhuma matéria");
        }
    }

    /*
    public void alteraAluno() {
        String id = getInputString("Digite o Id do aluno que você deseja alterar", true);
        
        if (!persisteAluno.existeAluno(id)) {
            mostraErro("Aluno não encontrado");
        } else {
            String nome = getInputString("Digite o novo nome do aluno", true);
            Aluno alunoAlterado = new Aluno(id, nome);
            
            try {
                persisteAluno.alteraAluno(alunoAlterado);
                mostraMensagem("Aluno alterado com sucesso");
            } catch (ErroPersiste ex) {
                mostraErro(ex.getMessage());
            }
        }
    }
    
    public void excluiAluno() {
        String id = getInputString("Digite o Id do aluno que você deseja excluir", true);
        
        try {
            persisteAluno.excluiAluno(id);
            mostraMensagem("Aluno removido com sucesso");
        } catch (ErroPersiste ex) {
            mostraErro(ex.getMessage());
        }
    }
    
    public void consultaAluno() {
        
    }
    */
    
    public void listaAluno() {
        System.out.println("                Listagem de Alunos                   ");
        System.out.println("-----------------------------------------------------");
        System.out.println("RA, Nome");
        System.out.println("-----------------------------------------------------");
        
        for(Aluno a : persisteAluno.getAlunos()) {
            System.out.println(a.getId() + "," + a.getNome());
        }
        
        System.out.println("-----------------------------------------------------");
        System.out.println("Total de Aluno: " + persisteAluno.getAlunos().size());
        System.out.println("-----------------------------------------------------");
        
        espera(5);
    }
    
}