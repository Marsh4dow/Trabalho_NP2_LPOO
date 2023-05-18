package br.com.mobilemasters.np2;

import java.util.Objects;

public class Curso {
    
    private String nome;
    
    private String nivel;
    
    private Integer ano;

    public Curso() {
        //
    }
    
    public Curso(String nome, String nivel, Integer ano) {
        this.nome = nome;
        this.nivel = nivel;
        this.ano = ano;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public boolean graduacao() {
        return this.nivel.trim().toUpperCase().startsWith("G");
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.nivel);
        hash = 29 * hash + Objects.hashCode(this.ano);
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
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.nivel, other.nivel)) {
            return false;
        }
        return Objects.equals(this.ano, other.ano);
    }
    
    @Override
    public String toString() {
        return "Curso{" + "nome=" + nome + ", nivel=" + nivel + ", ano=" + ano + '}';
    }
}