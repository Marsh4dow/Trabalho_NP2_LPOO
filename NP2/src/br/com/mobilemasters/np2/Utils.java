package br.com.mobilemasters.np2;

public class Utils {
    
    public static String geraNomeArquivo(Curso curso) {
        /*
        return 
            curso.getNome().trim().replace(" ", "")  + "_" + 
            curso.getNivel().trim().replace(" ", "") + "_" + 
            curso.getAno();
        */
        return 
            curso.getNome().trim() + "_" + 
            curso.getNivel().trim() + "_" + 
            curso.getAno();
    }

    public static Curso getCursoDoNomeArquivo(String nomeArquivo) {
        Curso c = new Curso();
        
        String[] aux = nomeArquivo.split("_");
        c.setNome(aux[0]);
        c.setNivel(aux[1]);
        c.setAno(Integer.valueOf(aux[2]));
        
        return c;
    }
    
}