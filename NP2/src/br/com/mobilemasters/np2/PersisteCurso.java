package br.com.mobilemasters.np2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PersisteCurso {

    private List<Curso> cursos;

    public PersisteCurso() {
        cursos = new ArrayList<>();
    }

    public void adicionaCurso(Curso curso) throws ErroPersiste {
        if (!existeCurso(curso)) {
            this.cursos.add(curso);
            grava();    
        } else {
            throw new ErroPersiste("Curso já cadastrado");
        }
    }
    
    /*
    public void alteraCurso(Curso cursoAtual, Curso cursoNovo) throws ErroPersiste {
        Curso registroAtual = procuraCurso(cursoAtual);
        
        if (registroAtual == null) {
            throw new ErroPersiste("Curso atual não encontrado");
        }
        
        PersisteRendimento pr = new PersisteRendimento(Utils.geraNomeArquivo(registroAtual));
        pr.trocaNomeArquivo(Utils.geraNomeArquivo(cursoNovo));
                
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
    */
    
    public Boolean existeCurso(Curso curso) {
        Curso existente = procuraCurso(curso);
        
        if (existente == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public Curso procuraCurso(Curso curso) {
        for(Curso c : this.cursos) {
            if (c.equals(curso)) {
                return c;
            }
        }
        
        return null;
    }
    
    public void carrega() {
        try {
            Scanner scanner = new Scanner(new File("cursos.csv"));
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(",");

                Curso curso = new Curso();
                curso.setNome(campos[0]);
                curso.setNivel(campos[1]);
                curso.setAno(Integer.valueOf(campos[2]));

                cursos.add(curso);
            }

            scanner.close();
        } catch (NoSuchElementException e) {
            System.out.println("Nenhum curso salvo");
            
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    public void grava() {
        try {
            FileWriter writer = new FileWriter("cursos.csv");
            writer.write("Nome,Nível,Ano\n");

            for (Curso curso : cursos) {
                writer.write(curso.getNome() + "," + curso.getNivel() + "," + curso.getAno()+ "\n");
            }

            writer.close();
            System.out.println("Cursos salvos com sucesso para o arquivo cursos.csv");
        } catch (IOException e) {
            System.out.println("Erro ao salvar cursos: " + e.getMessage());
        }
    }

    public List<Curso> getCursos() {
        return cursos;
    }
    
}