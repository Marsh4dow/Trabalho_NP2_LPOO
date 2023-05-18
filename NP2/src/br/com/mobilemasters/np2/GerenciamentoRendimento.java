package br.com.mobilemasters.np2;

public class GerenciamentoRendimento extends GerenciamentoBase {

    private PersisteRendimento persisteRendimento;
    
    private PersisteCurso persisteCurso;
    
    private PersisteAluno persisteAluno;
    
    public GerenciamentoRendimento() {
        persisteCurso = new PersisteCurso();
        persisteCurso.carrega();
        
        persisteAluno = new PersisteAluno();
        persisteAluno.carrega();
    }

    public void incluiRendimento() {
        String nome = getInputString("Digite o nome do curso", true);
        String nivel = getInputString("Qual o nível do curso?\n\t\tUse G para Graduação\n\t\tou P para Pós Graduação\n", true);
        Integer ano = getInputInteger("Digite o ano do curso", true);

        Curso curso = new Curso(nome, nivel, ano);
        
        if (!persisteCurso.existeCurso(curso)) {
            mostraErro("O curso informado não existe");
            return;
        }
        
        persisteRendimento = new PersisteRendimento(Utils.geraNomeArquivo(curso));
        persisteRendimento.carrega();
        
        String idAluno = getInputString("Digite o RA do aluno", true);
        
        Aluno aluno = persisteAluno.procuraAlunoPorId(idAluno);
        if (aluno == null) {
            mostraErro("RA informado não existe");
            return;
        }
        
        Double np1 = getInputDouble("Digite a nota do NP1", true);
        Double np2 = getInputDouble("Digite a nota do NP2", true);
        Double reposicao = getInputDouble("Digite a nota da Reposição (sub)", false);
        Double exame = getInputDouble("Digite a nota do Exame", false);
        
        Rendimento r = new Rendimento();
        r.setAluno(aluno);
        r.setNp1(np1);
        r.setNp2(np2);
        r.setReposicao(reposicao);
        r.setExame(exame);
        
        try {
            persisteRendimento.adicionaRendimento(r);
        } catch (ErroPersiste ex) {
            mostraErro("Erro ao tentar gravar rendimento: " + ex.getMessage());
        }
    }
    
    public void listaRendimento() {
        String nome = getInputString("Digite o nome do curso", true);
        String nivel = getInputString("Qual o nível do curso?\n\t\tUse G para Graduação\n\t\tou P para Pós Graduação\n", true);
        Integer ano = getInputInteger("Digite o ano do curso", true);

        Curso curso = new Curso(nome, nivel, ano);
        
        if (!persisteCurso.existeCurso(curso)) {
            mostraErro("O curso informado não existe");
            return;
        }
        
        persisteRendimento = new PersisteRendimento(Utils.geraNomeArquivo(curso));
        persisteRendimento.carrega();

        for(Rendimento r : persisteRendimento.getRendimentos()) {
            System.out.println(
                r.getAluno().getNome() + "\t" + 
                r.getNp1() + "\t" + 
                r.getNp2() + "\t" + 
                (r.getExame() == null ?  " n/a " : r.getExame()) + "\t" + 
                (r.getReposicao() == null ?  " n/a " : r.getReposicao())
            );
        }
    }
    
    public void listaCurso() {
        System.out.println("                Listagem de Cursos                   ");
        System.out.println("-----------------------------------------------------");
        System.out.println("Nome, Nível, Ano");
        System.out.println("-----------------------------------------------------");
        
        for(Curso c : persisteCurso.getCursos()) {
            System.out.println(c.getNome() + "," + c.getNivel() + "," + c.getAno());
        }
        
        System.out.println("-----------------------------------------------------");
        System.out.println("Total de Cursos: " + persisteCurso.getCursos().size());
        System.out.println("-----------------------------------------------------");
        
        espera(5);
    }
    
}

/*
Alunos.csv  Cursos.csv                  Computacao_graduacao_2023.csv
A           Computacao/graduacao/2023   A   9.6 8.5
B
C
*/