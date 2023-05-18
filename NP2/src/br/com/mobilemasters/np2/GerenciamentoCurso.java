package br.com.mobilemasters.np2;

import java.util.ArrayList;
import java.util.List;

public class GerenciamentoCurso extends GerenciamentoBase {

    private PersisteCurso persisteCurso;
    
    public GerenciamentoCurso() {
        persisteCurso = new PersisteCurso();
        persisteCurso.carrega();
    }

    private Curso getDadosCurso() {
        String nome = getInputString("Digite o nome do curso", true);
        String nivel = getInputString("Qual o nível do curso?\n\t\tUse G para Graduação\n\t\tou P para Pós Graduação\n", true);
        Integer ano = getInputInteger("Digite o ano do curso", true);

        return new Curso(nome, nivel, ano);
    }
    
    public void adicionaCurso() {
        Curso c = getDadosCurso();
        
        try {
            persisteCurso.adicionaCurso(c);
            mostraMensagem("Curso incluido com sucesso");
        } catch (ErroPersiste e) {
            mostraErro(e.getMessage());
        }
    }
    
    public void relatorioCurso() {
        Curso c = getDadosCurso();
        
        if (!persisteCurso.existeCurso(c)) {
            mostraErro("Curso não encontrado");
            return;
        }
        
        PersisteRendimento pr = new PersisteRendimento(Utils.geraNomeArquivo(c));
        pr.carrega();
        
        if (pr.getRendimentos() == null || pr.getRendimentos().size() < 1) {
            System.out.println("Nenhum rendimento definido para essa matéria");
            return;
        }

        System.out.println("\n\n------------------------------------------------------------------------------");
        System.out.println(Rendimento.getDadosAlunoTitulo());
        System.out.println("------------------------------------------------------------------------------");

        Double somaMediaGeral = 0d;
        int registros = 0;
        
        for(Rendimento r : pr.getRendimentos()) {
            System.out.println(
                r.getDadosAluno()
            );
            
            somaMediaGeral += r.getMedia();
            registros++;
        }
        
        System.out.println("\n\n");
        System.out.println("Média Geral: " + (somaMediaGeral / registros));
    }
    
    public void listaCurso() {
        imprimeCursos(persisteCurso.getCursos());
    }

    public void listaCursoAno() {
        Integer ano = getInputInteger("Digite um ano para listar os cursos", true);
        
        List<Curso> cursoAno = new ArrayList<>();
        
        for(Curso c : persisteCurso.getCursos()) {
            if (c.getAno().equals(ano)) {
                cursoAno.add(c);
            }
        }
        
        imprimeCursos(cursoAno);
    }
    
    private void imprimeCursos(List<Curso> cursos) {
        if (cursos == null || cursos.size() < 0) {
            System.out.println("Nenhum curso encontrado");
            return;
        }
        
        System.out.println("                Listagem de Cursos                   ");
        System.out.println("-----------------------------------------------------");
        System.out.println("Nome, Nível, Ano");
        System.out.println("-----------------------------------------------------");
        
        for(Curso c : cursos) {
            System.out.println(c.getNome() + "," + c.getNivel() + "," + c.getAno());
        }
        
        System.out.println("-----------------------------------------------------");
        System.out.println("Total de Cursos: " + cursos.size());
        System.out.println("-----------------------------------------------------");
        
        espera(5);
    }
    
    /*
    public void alteraCurso() {
        Curso cursoAtual = getDadosCurso();
        
        if (persisteCurso.existeCurso(cursoAtual)) {
            String nome = getInputString("Digite o novo nome do curso", true);
            String nivel = getInputString("Qual o novo nível do curso?\n\t\tUse G para Graduação\n\t\tou P para Pós Graduação\n", true);
            Integer ano = getInputInteger("Digite o novo ano do curso", true);

            Curso cursoNovo = new Curso(nome, nivel, ano);
            
            try {
                persisteCurso.alteraCurso(cursoAtual, cursoNovo);
            } catch (ErroPersiste ex) {
                mostraErro(ex.getMessage());
            }
            
        } else {
            mostraErro("O curso informado não existe");
        }
        
    }
    
    public void excluiCurso() {
        
    }
    
    public void consultaAluno() {
        
    }
    */
    
}