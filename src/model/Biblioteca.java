import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public Livro buscarLivro(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    public void emprestarLivro(Usuario usuario, int livroId) {
        Livro livro = buscarLivro(livroId);
        if (livro != null && !livro.isEmprestado()) {
            Emprestimo emprestimo = new Emprestimo(usuario, livro);
            emprestimos.add(emprestimo);
            System.out.println("Livro emprestado: " + livro.getTitulo());
        } else {
            System.out.println("Livro indisponível.");
        }
    }

    public void devolverLivro(int livroId) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getId() == livroId) {
                emprestimo.devolverLivro();
                emprestimos.remove(emprestimo);
                System.out.println("Livro devolvido: " + emprestimo.getLivro().getTitulo());
                return;
            }
        }
        System.out.println("Livro não encontrado nos empréstimos.");
    }
}
