package br.com.mobilemasters.np2;

import java.util.Locale;
import java.util.Scanner;

public class Sistema {

    public void menuPrincipal() {
        Locale.setDefault(Locale.US);

        String opc;
        
        do {
            Scanner teclado = new Scanner(System.in);
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("                                      MENU");
            System.out.println("Escolha uma opção:");
            System.out.println("A - Alunos");
            System.out.println("C - Cursos");
            System.out.println("R - Rendimentos");
            System.out.println("S - Sair");

            teclado = new Scanner(System.in);
            opc = teclado.nextLine().trim().toUpperCase();

            switch (opc) {

                case "A":
                    menuAluno();
                    break;
                    
                case "C":
                    menuCurso();
                    break;
                    
                case "R":
                    menuRendimento();
                    break;
                    
                case "S":
                    break;
                    
                default:
                    System.out.println("Opção inválida");
            }

        } while (!opc.equals("S"));

    }

    private void menuAluno() {
        String opc;
        
        GerenciamentoAluno ga = new GerenciamentoAluno();
        
        do {
            Scanner teclado = new Scanner(System.in);
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("                                   ALUNOS");
            System.out.println("Escolha uma opção:");
            System.out.println("C - Cadastrar");
            System.out.println("L - Listar");
            System.out.println("R - Relatório");
            System.out.println("V - Voltar");

            teclado = new Scanner(System.in);
            opc = teclado.nextLine().toUpperCase().trim();

            switch (opc) {

                case "C":
                    ga.adicionaAluno();
                    break;
                    
                case "L":
                    ga.listaAluno();
                    break;
                    
                case "R":
                    ga.relatorioAluno();
                    break;
                    
                case "V":
                    break;
                    
                default:
                    System.out.println("Opção inválida");
            }

        } while (!opc.equals("V"));
    }

    private void menuCurso() {
        String opc;
        
        GerenciamentoCurso gc = new GerenciamentoCurso();
        
        do {
            Scanner teclado = new Scanner(System.in);
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("                                   CURSOS (MATÉRIAS)");
            System.out.println("Escolha uma opção:");
            System.out.println("C - Cadastrar");
            System.out.println("L - Listar");
            System.out.println("A - Listar Matérias de um ano");
            System.out.println("R - Relatório");
            System.out.println("V - Voltar");

            teclado = new Scanner(System.in);
            opc = teclado.nextLine().trim().toUpperCase();

            switch (opc) {

                case "C":
                    gc.adicionaCurso();
                    break;
                    
                case "L":
                    gc.listaCurso();
                    break;
                    
                case "A":
                    gc.listaCursoAno();
                    break;
                    
                case "R":
                    gc.relatorioCurso();
                    break;
                    
                case "V":
                    break;
                    
                default:
                    System.out.println("Opção inválida");
            }

        } while (!opc.equals("V"));
    }

    private void menuRendimento() {
        String opc;
        
        GerenciamentoRendimento gr = new GerenciamentoRendimento();
        
        do {
            Scanner teclado = new Scanner(System.in);
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("                                   RENDIMENTOS");
            System.out.println("Escolha uma opção:");
            System.out.println("C - Cadastrar");
            System.out.println("L - Listar");
            System.out.println("V - Voltar");

            teclado = new Scanner(System.in);
            opc = teclado.nextLine().trim().toUpperCase();

            switch (opc) {
                case "C":
                    gr.incluiRendimento();
                    break;
                    
                case "L":
                    gr.listaRendimento();
                    break;
                    
                case "V":
                    break;
                    
                default:
                    System.out.println("Opção inválida");
            }

        } while (!opc.equals("V"));
    }
    
}