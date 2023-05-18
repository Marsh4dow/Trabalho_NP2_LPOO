package br.com.mobilemasters.np2;

import java.util.Arrays;
import java.util.Objects;

public class Rendimento {

    private Aluno aluno;
    
    private Curso curso;
            
    private Double np1;
    
    private Double np2;
    
    private Double reposicao;
    
    private Double exame;
    
    private Boolean calculado;
    
    private Boolean aprovado;
    
    private Double media;
    
    private Double mediaInicial;
    
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public Double getNp1() {
        return np1;
    }

    public void setNp1(Double np1) {
        this.np1 = np1;
    }

    public Double getNp2() {
        return np2;
    }

    public void setNp2(Double np2) {
        this.np2 = np2;
    }

    public Double getReposicao() {
        return reposicao;
    }

    public void setReposicao(Double reposicao) {
        this.reposicao = reposicao;
    }

    public Double getExame() {
        return exame;
    }

    public void setExame(Double exame) {
        this.exame = exame;
    }

    public void calculaMedia() {
        this.media = 0d;
        this.mediaInicial = getMediaInicial();
        this.aprovado = false;
        
        if (this.curso.graduacao()) {
            if (this.mediaInicial >= 7) {
                this.aprovado = true;
                this.media = this.mediaInicial;
                
            } else {
                if (this.exame == null) {
                    throw new RuntimeException("Nota do exame não foi informada");
                }
                
                this.media = (this.exame + this.mediaInicial) / 2;
                if (this.media >= 5) {
                    this.aprovado = true;
                }
            }
            
        // Pós
        } else {
            if (this.mediaInicial >= 5) {
                this.aprovado = true;
                this.media = this.mediaInicial;
            } else {
                if (this.exame == null) {
                    throw new RuntimeException("Nota do exame não foi informada");
                }
                
                this.media = (this.exame + this.mediaInicial) / 2;
                
                if (this.media >= 5) {
                    this.aprovado = true;
                    this.media = 5d;
                }
            }
        }
            
        calculado = true;
    }
    
    public String getStatus() {
        validaExecucaoCalculo();
        
        if (this.aprovado) {
            return "Aprovado";
        } else {
            return "Reprovado";
        }
    }
    
    public Double getMedia() {
        validaExecucaoCalculo();
        
        return this.media;
    }

    public Boolean getAprovado() {
        validaExecucaoCalculo();
        
        return this.aprovado;
    }
    
    private void validaExecucaoCalculo() {
        if (this.calculado == null || !this.calculado) {
            throw new RuntimeException("Calculo da média não executado");
        }
    }
    
    private Double getMediaInicial() {
        // Para definir as duas maiores notas
        Double[] notas = new Double[3];
        notas[0] = this.np1;
        notas[1] = this.np2;
        notas[2] = (this.reposicao != null ? this.reposicao : 0);
        
        // Ordena do menor para o maior
        Arrays.sort(notas);
        
        return (notas[1] + notas[2]) / 2;
    }

    public static String getDadosAlunoTitulo() {
        return
            "RA\t" + 
            "Nome\t\t" + 
            "NP1\t" +
            "NP2\t" +
            "Repos\t" +
            "Exame\t" +
            "Média\t" +
            "Status";
    }
    
    public String getDadosAluno() {
        return
            this.getAluno().getId() + "\t" + 
            this.getAluno().getNome() + "\t\t" + 
            this.getNp1() + "\t" +
            this.getNp2() + "\t" +
            (this.getReposicao() != null ? this.getReposicao() : "") + "\t" +
            (this.getExame() != null ? this.getExame() : "") + "\t" +
            this.getMedia() + "\t" +
            this.getStatus();
    }

    public static String getDadosCursoTitulo() {
        return
            "Curso\t\t" + 
            "Nível\t" + 
            "Ano\t" + 
            "NP1\t" +
            "NP2\t" +
            "Repos\t" +
            "Exame\t" +
            "Média\t" +
            "Status";
    }
    
    public String getDadosCurso() {
        return
            this.getCurso().getNome() + "\t\t" + 
            this.getCurso().getNivel() + "\t" + 
            this.getCurso().getAno() + "\t" +
            this.getNp1() + "\t" +
            this.getNp2() + "\t" +
            (this.getReposicao() != null ? this.getReposicao() : "") + "\t" +
            (this.getExame() != null ? this.getExame() : "") + "\t" +
            this.getMedia() + "\t" +
            this.getStatus();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.aluno);
        hash = 59 * hash + Objects.hashCode(this.curso);
        hash = 59 * hash + Objects.hashCode(this.np1);
        hash = 59 * hash + Objects.hashCode(this.np2);
        hash = 59 * hash + Objects.hashCode(this.reposicao);
        hash = 59 * hash + Objects.hashCode(this.exame);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rendimento other = (Rendimento) obj;
        if (!Objects.equals(this.aluno, other.aluno)) {
            return false;
        }
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        if (!Objects.equals(this.np1, other.np1)) {
            return false;
        }
        if (!Objects.equals(this.np2, other.np2)) {
            return false;
        }
        if (!Objects.equals(this.reposicao, other.reposicao)) {
            return false;
        }
        return Objects.equals(this.exame, other.exame);
    }

    @Override
    public String toString() {
        return "Rendimento{" + "aluno=" + aluno + ", curso=" + curso + ", np1=" + np1 + ", np2=" + np2 + ", reposicao=" + reposicao + ", exame=" + exame + '}';
    }

}