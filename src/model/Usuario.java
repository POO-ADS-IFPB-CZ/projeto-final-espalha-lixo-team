package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario( int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId(){return  id;}

    public String getNome() {
        return nome;
    }

    public String getEmail() {return email;}

    public String getSenha() {return senha;}

    @Override
    public String toString() {
        return "ID:" + id + "Usuário: " + nome + "E-mail" + email ;
    }
}
