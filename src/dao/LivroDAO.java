package dao;

import model.Livro;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private static final String ARQUIVO = "src/resources/Livros.txt"; // Arquivo onde os livros serão salvos

    // Adiciona um livro ao arquivo
    public static void adicionarLivro() {
        JTextField idField = new JTextField();
        JTextField tituloField = new JTextField();
        JTextField autorField = new JTextField();

        JPanel panel = new JPanel(new java.awt.GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("ID do Livro:"));
        panel.add(idField);
        panel.add(new JLabel("Título do Livro:"));
        panel.add(tituloField);
        panel.add(new JLabel("Autor do Livro:"));
        panel.add(autorField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Livro",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String titulo = tituloField.getText().trim();
                String autor = autorField.getText().trim();

                if (!titulo.isEmpty() && !autor.isEmpty()) {
                    Livro livro = new Livro(id, titulo, autor);
                    salvarLivro(livro);
                    JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido! Insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Remove um livro do arquivo
    public static void removerLivro() {
        List<Livro> livros = carregarLivros();
        if (livros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum livro para remover.");
            return;
        }

        String[] opcoes = livros.stream()
                .map(Livro::toString)
                .toArray(String[]::new);

        String livroParaRemover = (String) JOptionPane.showInputDialog(null, "Selecione o livro para remover:",
                "Remover Livro", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (livroParaRemover != null) {
            livros.removeIf(livro -> livro.toString().equals(livroParaRemover));
            salvarLivros(livros);
            JOptionPane.showMessageDialog(null, "Livro removido com sucesso!");
        }
    }

    // Salva um livro no arquivo
    private static void salvarLivro(Livro livro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(livro.getId() + ";" + livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.isEmprestado());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o livro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carrega todos os livros do arquivo
    public static List<Livro> carregarLivros() {
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
            // Arquivo pode não existir na primeira execução, ignoramos esse erro
        }
        return livros;
    }

    // Salva a lista de livros no arquivo
    private static void salvarLivros(List<Livro> livros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Livro livro : livros) {
                writer.write(livro.getId() + ";" + livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.isEmprestado());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os livros!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}