package br.com.tomvieira.relatoriotemplate;

import java.io.Serializable;

/**
 *
 * @author tom
 */
public class Developer {
    
    private String nome;
    private String telefone;
    private String linguagem; 

    public Developer(String nome, String telefone, String linguagem) {
        this.nome = nome;
        this.telefone = telefone;
        this.linguagem = linguagem;
    }       

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }
        
    
}
