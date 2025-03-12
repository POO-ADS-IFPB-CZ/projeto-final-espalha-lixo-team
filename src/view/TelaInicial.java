package view;

import javax.swing.*;

public class TelaInicial extends JFrame {
    private JPanel contentPane;
    private JButton cadastrarLivroButton;
    private JButton emprestarLivroButton;
    private JButton cadastrarUsuario;

    public TelaInicial() {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/img/logoBiblioteca.png");
        setIconImage(icon.getImage());
        setTitle("Biblioteca Comunitária");
        setSize(470, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(cadastrarLivroButton);

        cadastrarLivroButton.addActionListener(e -> {
            TelaCadastrarLivro cadastroLivro = new TelaCadastrarLivro();
            dispose();
            cadastroLivro.pack();
            cadastroLivro.setLocationRelativeTo(null);
            cadastroLivro.setVisible(true);
            setVisible(true);
        });

        emprestarLivroButton.addActionListener(e -> {
            TelaEmprestarLivro emprestimoLivro = new TelaEmprestarLivro();
            dispose();
            emprestimoLivro.pack();
            emprestimoLivro.setLocationRelativeTo(null);
            emprestimoLivro.setVisible(true);
            setVisible(true);
        });

        cadastrarUsuario.addActionListener(e->{
            TelaCadastroUsuario cadastroUsuario = new TelaCadastroUsuario();
            dispose();
            cadastroUsuario.pack();
            cadastroUsuario.setLocationRelativeTo(null);
            cadastroUsuario.setVisible(true);
            setVisible(true);
        });
    }
    public static void main(String[] args) {
        TelaInicial dialog = new TelaInicial();
        dialog.setVisible(true);
    }
}
