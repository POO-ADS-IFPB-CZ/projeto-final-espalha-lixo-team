import javax.swing.*;
import java.util.List;

public class Main {
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
    private static EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    public static void main(String[] args) {
        while (true) {
            String opcao = JOptionPane.showInputDialog(
                    "Selecione uma opção:\n" +
                            "1 - Adicionar Usuário\n" +
                            "2 - Adicionar Livro\n" +
                            "3 - Realizar Empréstimo\n" +
                            "4 - Devolver Livro\n" +
                            "5 - Listar Usuários\n" +
                            "6 - Listar Livros\n" +
                            "7 - Listar Empréstimos\n" +
                            "0 - Sair");

            if (opcao == null || opcao.equals("0")) {
                break; // Sair do programa
            }

            switch (opcao) {
                case "1":
                    adicionarUsuario();
                    break;
                case "2":
                    adicionarLivro();
                    break;
                case "3":
                    realizarEmprestimo();
                    break;
                case "4":
                    devolverLivro();
                    break;
                case "5":
                    listarUsuarios();
                    break;
                case "6":
                    listarLivros();
                    break;
                case "7":
                    listarEmprestimos();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    private static void adicionarUsuario() {
        String nome = JOptionPane.showInputDialog("Digite o nome do usuário:");
        if (nome != null && !nome.trim().isEmpty()) {
            int id = usuarioDAO.listarUsuarios().size() + 1; // ID automático
            usuarioDAO.adicionarUsuario(new Usuario(id, nome));
            JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nome inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void adicionarLivro() {
        String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
        String autor = JOptionPane.showInputDialog("Digite o autor do livro:");
        if (titulo != null && !titulo.trim().isEmpty() && autor != null && !autor.trim().isEmpty()) {
            int id = bibliotecaDAO.listarLivros().size() + 1; // ID automático
            bibliotecaDAO.adicionarLivro(new Livro(id, titulo, autor));
            JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Dados inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void realizarEmprestimo() {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        List<Livro> livros = bibliotecaDAO.listarLivros();

        if (usuarios.isEmpty() || livros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastre usuários e livros primeiro!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Selecionar usuário
        Usuario usuario = (Usuario) JOptionPane.showInputDialog(
                null, "Selecione o usuário:", "Realizar Empréstimo",
                JOptionPane.QUESTION_MESSAGE, null, usuarios.toArray(), usuarios.get(0));

        // Selecionar livro
        Livro livro = (Livro) JOptionPane.showInputDialog(
                null, "Selecione o livro:", "Realizar Empréstimo",
                JOptionPane.QUESTION_MESSAGE, null, livros.toArray(), livros.get(0));

        if (usuario != null && livro != null) {
            if (livro.isEmprestado()) {
                JOptionPane.showMessageDialog(null, "Livro já emprestado!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                emprestimoDAO.adicionarEmprestimo(new Emprestimo(usuario, livro));
                JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");
            }
        }
    }

    private static void devolverLivro() {
        List<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimos();

        if (emprestimos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum empréstimo ativo!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Selecionar empréstimo
        Emprestimo emprestimo = (Emprestimo) JOptionPane.showInputDialog(
                null, "Selecione o empréstimo para devolução:", "Devolver Livro",
                JOptionPane.QUESTION_MESSAGE, null, emprestimos.toArray(), emprestimos.get(0));

        if (emprestimo != null) {
            emprestimoDAO.removerEmprestimo(emprestimo.getLivro().getId());
            JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso!");
        }
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        StringBuilder mensagem = new StringBuilder("Usuários:\n");
        for (Usuario usuario : usuarios) {
            mensagem.append(usuario).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    private static void listarLivros() {
        List<Livro> livros = bibliotecaDAO.listarLivros();
        StringBuilder mensagem = new StringBuilder("Livros:\n");
        for (Livro livro : livros) {
            mensagem.append(livro).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    private static void listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimos();
        StringBuilder mensagem = new StringBuilder("Empréstimos:\n");
        for (Emprestimo emprestimo : emprestimos) {
            mensagem.append(emprestimo).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }
}