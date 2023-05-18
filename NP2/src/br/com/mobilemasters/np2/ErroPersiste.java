package br.com.mobilemasters.np2;

public class ErroPersiste extends Exception {
 
    public ErroPersiste(String mensagemErro) {
        super(mensagemErro);
    }
    
    public ErroPersiste(String mensagemErro, Throwable error) {
        super(mensagemErro, error);
    }
    
}