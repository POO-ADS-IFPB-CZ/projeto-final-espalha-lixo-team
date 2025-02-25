import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    private static final String ARQUIVO = "src/resources/Emprestimos.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(emprestimo.getUsuario().getId() + ";" +
                    emprestimo.getLivro().getId() + ";" +
                    DATE_FORMAT.format(emprestimo.getDataEmprestimo()));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar empréstimo: " + e.getMessage());
        }
    }

    public void removerEmprestimo(int livroId) {
        List<Emprestimo> emprestimos = carregarEmprestimos();
        emprestimos.removeIf(emprestimo -> emprestimo.getLivro().getId() == livroId);
        salvarEmprestimos(emprestimos);
    }

    public Emprestimo buscarEmprestimo(int livroId) {
        return carregarEmprestimos().stream()
                .filter(emprestimo -> emprestimo.getLivro().getId() == livroId)
                .findFirst()
                .orElse(null);
    }

    public List<Emprestimo> listarEmprestimos() {
        return carregarEmprestimos();
    }

    private List<Emprestimo> carregarEmprestimos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int usuarioId = Integer.parseInt(dados[0]);
                int livroId = Integer.parseInt(dados[1]);
                Usuario usuario = new UsuarioDAO().buscarUsuario(usuarioId);
                Livro livro = new BibliotecaDAO().buscarLivro(livroId);
                emprestimos.add(new Emprestimo(usuario, livro));
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar empréstimos: " + e.getMessage());
        }
        return emprestimos;
    }

    private void salvarEmprestimos(List<Emprestimo> emprestimos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Emprestimo emprestimo : emprestimos) {
                writer.write(emprestimo.getUsuario().getId() + ";" +
                        emprestimo.getLivro().getId() + ";" +
                        DATE_FORMAT.format(emprestimo.getDataEmprestimo()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar empréstimos: " + e.getMessage());
        }
    }
}
