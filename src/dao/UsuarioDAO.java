package dao;

import model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String ARQUIVO = "src/resources/Usuarios.txt";

    public boolean adicionarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(usuario.getId() + ";" + usuario.getNome() + ";" + usuario.getEmail() + ";" + usuario.getSenha());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
        return false;
    }

    public void removerUsuario(int id) {
        List<Usuario> usuarios = carregarUsuarios();
        usuarios.removeIf(usuario -> usuario.getId() == id);
        salvarUsuarios(usuarios);
    }

    public Usuario buscarUsuario(int id) {
        return carregarUsuarios().stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return carregarUsuarios();
    }

    private List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String email = "";
                String senha = "";
                usuarios.add(new Usuario(id,nome,email,senha));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    private void salvarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getId() + ";" + usuario.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
}