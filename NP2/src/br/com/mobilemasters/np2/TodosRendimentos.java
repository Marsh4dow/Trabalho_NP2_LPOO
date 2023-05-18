package br.com.mobilemasters.np2;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class TodosRendimentos {

    private List<Rendimento> todosRendimentos;

    public TodosRendimentos() {
        todosRendimentos = new ArrayList<>();
    }

    public List<Rendimento> getTodosRendimentos() {
        return todosRendimentos;
    }

    public List<Rendimento> getRendimentosAluno(Aluno aluno) {
        List result = new ArrayList<>();
        
        for(Rendimento r : this.todosRendimentos) {
            if (r.getAluno().getId().equals(aluno.getId())) {
                result.add(r);
            }
        }

        return result;
    }
    
    public void carregarTodosRendimentos() {
        File pasta = new File(PersisteRendimento.NOME_PASTA);
        if (pasta.exists()) {

            FilenameFilter filtro = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String nomeArquivo) {
                    return nomeArquivo.toLowerCase().endsWith(".csv");
                }
            };

            File[] arquivos = pasta.listFiles(filtro);
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    String nomeBase = arquivo.getName().split("\\.")[0];
                            
                    PersisteRendimento pr = new PersisteRendimento(nomeBase);
                    pr.carrega();
                    
                    todosRendimentos.addAll(pr.getRendimentos());
                }
            }
        }
    }

}
