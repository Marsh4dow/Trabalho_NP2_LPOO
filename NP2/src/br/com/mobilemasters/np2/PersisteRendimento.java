package br.com.mobilemasters.np2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PersisteRendimento {

    public static final String NOME_PASTA = "Rendimentos/";
    
    private List<Rendimento> rendimentos;
    
    private String nomeArquivo;
    
    private PersisteAluno persisteAluno;

    public PersisteRendimento(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        
        persisteAluno = new PersisteAluno();
        persisteAluno.carrega();
        
        rendimentos = new ArrayList<>();
    }

    public void adicionaRendimento(Rendimento rendimento) throws ErroPersiste {
        if (!existeRendimento(rendimento)) {
            this.rendimentos.add(rendimento);
            grava();    
        } else {
            throw new ErroPersiste("Aluno já tem registro de rendimento pra esse curso");
        }
    }
    
    public Boolean existeRendimento(Rendimento rendimento) {
        Rendimento existente = procuraRendimento(rendimento);
        
        if (existente == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public Rendimento procuraRendimento(Rendimento rendimento) {
        for(Rendimento r : this.rendimentos) {
            if (r.getAluno().equals(rendimento.getAluno())) {
                return r;
            }
        }
        
        return null;
    }
    
    public void carrega() {
        try {
            Scanner scanner = new Scanner(new File(NOME_PASTA + nomeArquivo + ".csv"));
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(",");

                Rendimento r = new Rendimento();
                r.setCurso(Utils.getCursoDoNomeArquivo(nomeArquivo));
                
                String idAluno = campos[0];
                
                r.setAluno(persisteAluno.procuraAlunoPorId(idAluno));
                r.setNp1(Double.parseDouble(campos[1]));
                r.setNp2(Double.parseDouble(campos[2]));
                
                if (!campos[3].equals("null")) {
                    r.setReposicao(Double.parseDouble(campos[3]));
                }

                if (!campos[4].equals("null")) {
                    r.setExame(Double.parseDouble(campos[4]));
                }

                r.calculaMedia();
                
                rendimentos.add(r);
            }

            scanner.close();
        } catch (NoSuchElementException e) {
            System.out.println("Nenhum rendimento salvo");
            
        } catch (FileNotFoundException e) {
            //System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    public void grava() {
        try {
            verificaPasta();
            
            FileWriter writer = new FileWriter(NOME_PASTA + nomeArquivo + ".csv");
            writer.write("IdAluno,NP1,NP2,Reposicao,Exame\n");

            for (Rendimento rendimento : rendimentos) {
                writer.write(
                    rendimento.getAluno().getId() + "," + 
                    rendimento.getNp1() + "," + 
                    rendimento.getNp2() + "," + 
                    rendimento.getReposicao() + "," + 
                    rendimento.getExame() + "\n"
                );
            }

            writer.close();
            System.out.println("Rendimentos salvos com sucesso para o arquivo " + NOME_PASTA + nomeArquivo + ".csv");
        } catch (IOException e) {
            System.out.println("Erro ao salvar rendimentos: " + e.getMessage());
        }
    }

    private void verificaPasta() {
        File pasta = new File(NOME_PASTA);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }
            
    /*
    public void alteraCurso(Curso cursoAtual, Curso cursoNovo) throws ErroPersiste {
        Curso registroAtual = procuraCurso(cursoAtual);
        
        if (registroAtual == null) {
            throw new ErroPersiste("Curso atual não encontrado");
        }
        
        cursos.remove(registroAtual);
        this.adicionaCurso(cursoNovo);
    }
    
    public void alteraCurso2(Curso cursoAtual, Curso cursoNovo) throws ErroPersiste {
        Curso registroAtual = procuraCurso(cursoAtual);
        
        if (registroAtual == null) {
            throw new ErroPersiste("Curso não encontrado");
        }
        
        registroAtual.setNome(cursoNovo.getNome());
        registroAtual.setNivel(cursoNovo.getNivel());
        registroAtual.setAno(cursoNovo.getAno());
        
        grava();    
    }
    
    public void excluiCurso(Curso curso) throws ErroPersiste {
        Curso registroAtual = procuraCurso(curso);
        
        if (registroAtual == null) {
            throw new ErroPersiste("Curso não encontrado");
        }
        
        this.cursos.remove(registroAtual);
        grava();    
    }
    
    public void trocaNomeArquivo(String novoNome) throws ErroPersiste {
        File arqAtual = new File(nomeArquivo + ".csv");
        
        if (!arqAtual.exists()) {
            throw new ErroPersiste("Arquivo " + nomeArquivo + ".csv não encontrado");
        }
        
        File arqNovo = new File(novoNome + ".csv");
        if (arqNovo.exists()) {
            throw new ErroPersiste("Arquivo novo " + novoNome + ".csv já existe");
        }
        
        arqAtual.renameTo(arqNovo);
    }
    */
    
    public List<Rendimento> getRendimentos() {
        return rendimentos;
    }
    
}