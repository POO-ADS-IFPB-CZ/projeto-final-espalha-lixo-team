public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Criando alguns livros
        Livro livro1 = new Livro(1, "Java para Iniciantes", "Herbert Schildt");
        Livro livro2 = new Livro(2, "Clean Code", "Robert C. Martin");

        // Adicionando livros à biblioteca
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        // Criando usuários
        Usuario usuario1 = new Usuario(1, "Carlos Silva");

        // Listando os livros disponíveis
        System.out.println("Livros disponíveis:");
        biblioteca.listarLivros();

        // Emprestando um livro
        System.out.println("\nEmprestando um livro...");
        biblioteca.emprestarLivro(usuario1, 1);

        // Tentando emprestar um livro já emprestado
        System.out.println("\nTentando emprestar o mesmo livro novamente...");
        biblioteca.emprestarLivro(usuario1, 1);

        // Devolvendo o livro
        System.out.println("\nDevolvendo o livro...");
        biblioteca.devolverLivro(1);

        // Tentando devolver um livro que não está emprestado
        System.out.println("\nTentando devolver o livro novamente...");
        biblioteca.devolverLivro(1);
    }
}
