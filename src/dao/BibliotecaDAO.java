package dao;

import model.Livro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaDAO {
    private static final String ARQUIVO = "src/resources/Livros.txt";

    public void adicionarLivro(Livro livro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(livro.getId() + ";" + livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.isEmprestado());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar livro: " + e.getMessage());
        }
    }

    public void removerLivro(int id) {
        List<Livro> livros = carregarLivros();
        livros.removeIf(livro -> livro.getId() == id);
        salvarLivros(livros);
    }

    public Livro buscarLivro(int id) {
        return carregarLivros().stream()
                .filter(livro -> livro.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Livro> listarLivros() {
        return carregarLivros();
    }

    private List<Livro> carregarLivros() {
        List<Livro> livros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String titulo = dados[1];
                String autor = dados[2];
                boolean emprestado = Boolean.parseBoolean(dados[3]);
                Livro livro = new Livro(id, titulo, autor);
                if (emprestado) {
                    livro.emprestar();
                }
                livros.add(livro);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar livros: " + e.getMessage());
        }
        return livros;
    }

    private void salvarLivros(List<Livro> livros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Livro livro : livros) {
                writer.write(livro.getId() + ";" + livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.isEmprestado());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar livros: " + e.getMessage());
        }
    }
}
