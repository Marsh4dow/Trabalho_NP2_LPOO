package br.com.mobilemasters.np2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PersisteAluno {

    private List<Aluno> alunos;

    public PersisteAluno() {
        alunos = new ArrayList<>();
    }

    public void adicionaAluno(Aluno aluno) throws ErroPersiste {
        if (!existeAluno(aluno.getId())) {
            this.alunos.add(aluno);
            grava();    
        } else {
            throw new ErroPersiste("Aluno já cadastrado");
        }
    }
    
    public void alteraAluno(Aluno aluno) throws ErroPersiste {
        Aluno registroAtual = procuraAlunoPorId(aluno.getId());
        
        if (registroAtual == null) {
            throw new ErroPersiste("Aluno não encontrado - Id: " + aluno.getId());
        }
        
        alunos.remove(registroAtual);
        this.adicionaAluno(aluno);
    }
    
    public void alteraAluno2(Aluno aluno) throws ErroPersiste {
        Aluno registroAtual = procuraAlunoPorId(aluno.getId());
        
        if (registroAtual == null) {
            throw new ErroPersiste("Aluno não encontrado - Id: " + aluno.getId());
        }
        
        registroAtual.setNome(aluno.getNome());
        grava();    
    }
    
    public void excluiAluno(String id) throws ErroPersiste {
        Aluno registroAtual = procuraAlunoPorId(id);
        
        if (registroAtual == null) {
            throw new ErroPersiste("Aluno não encontrado - Id: " + id);
        }
        
        this.alunos.remove(registroAtual);
        grava();    
    }
    
    public Boolean existeAluno(String id) {
        Aluno existente = procuraAlunoPorId(id);
        
        if (existente == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public Aluno procuraAlunoPorId(String id) {
        for(Aluno a : this.alunos) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        
        return null;
    }
    
    public void carrega() {
        try {
            Scanner scanner = new Scanner(new File("alunos.csv"));
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(",");

                Aluno aluno = new Aluno();
                aluno.setId(campos[0]);
                aluno.setNome(campos[1]);

                alunos.add(aluno);
            }

            scanner.close();
        } catch (NoSuchElementException e) {
            System.out.println("Nenhum aluno salvo");
            
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    public void grava() {
        try {
            FileWriter writer = new FileWriter("alunos.csv");
            writer.write("Id,Nome\n");

            for (Aluno aluno : alunos) {
                writer.write(aluno.getId() + "," + aluno.getNome() + "\n");
            }

            writer.close();
            System.out.println("Alunos salvos com sucesso para o arquivo alunos.csv");
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
    
}