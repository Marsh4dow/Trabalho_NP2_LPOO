package br.com.mobilemasters.np2;

import java.util.Scanner;

public abstract class GerenciamentoBase {

    private Scanner teclado = new Scanner(System.in);
    
    public GerenciamentoBase() {
        //
    }
    
    protected String getInputString(String frase, boolean requerido) {
        String valorDigitado = null;
        
        while (true) {
            System.out.println(frase);
            valorDigitado = teclado.nextLine();

            if ((valorDigitado == null || valorDigitado.trim().isEmpty()) && requerido) {
                mostraErro("É necessário preencher um valor");
            } else {
                break;
            }
        }

        if (valorDigitado != null) {
            return valorDigitado.toUpperCase();
        }
        
        return valorDigitado;
    }

    protected Integer getInputInteger(String frase, boolean requerido) {
        Integer valorDigitado = null;
        
        while (true) {
            System.out.println(frase);
            String valorDigitadoStr = teclado.nextLine();
            
            try {
                valorDigitado = Integer.valueOf(valorDigitadoStr);
            } catch (NumberFormatException ex) {
                if (requerido) {
                    mostraErro("Valor informado não é um número inteiro válido");
                    continue;
                } else {
                    break;
                }
            }
            
            if (valorDigitado == 0 && requerido) {
                mostraErro("É necessário preencher um valor");
            } else {
                break;
            }
        }
        
        return valorDigitado;
    }

    protected Double getInputDouble(String frase, boolean requerido) {
        Double valorDigitado = null;
        
        while (true) {
            System.out.println(frase);
            String valorDigitadoStr = teclado.nextLine();
            
            try {
                valorDigitado = Double.valueOf(valorDigitadoStr);
            } catch (NumberFormatException ex) {
                if (requerido) {
                    mostraErro("Valor informado não é um número válido");
                    continue;
                } else {
                    break;
                }
            }
            
            if (valorDigitado == 0 && requerido) {
                mostraErro("É necessário preencher um valor");
            } else {
                break;
            }
        }
        
        return valorDigitado;
    }
    
    protected void espera(int segundos) {
        try {
            Thread.sleep(1000 * segundos);
        } catch (InterruptedException ex) {
            //
        }
    }
    
    protected void mostraMensagem(String mensagemMsg) {
        System.out.println("                Mensagem                        ");
        System.out.println("------------------------------------------------");
        System.out.println(mensagemMsg);
        System.out.println("------------------------------------------------");
        espera(3);
    }
    
    protected void mostraErro(String mensagemErro) {
        System.out.println("                *** ERRO ****                   ");
        System.out.println("------------------------------------------------");
        System.out.println(mensagemErro);
        System.out.println("------------------------------------------------");
        espera(3);
    }
    
}