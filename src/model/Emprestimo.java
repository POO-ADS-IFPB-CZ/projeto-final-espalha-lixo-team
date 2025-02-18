import java.util.Date;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private Date dataEmprestimo;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = new Date();
        livro.emprestar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void devolverLivro() {
        livro.devolver();
    }

    @Override
    public String toString() {
        return "Empréstimo - " + livro.getTitulo() + " para " + usuario.getNome() +
                " em " + dataEmprestimo;
    }
}
