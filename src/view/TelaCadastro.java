package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCadastro extends JDialog {
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField passwordSenha;
    private JButton buttonCadastrar;
    private JButton buttonCancelar;

    public TelaCadastro() {
        contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(20);
        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);
        JLabel lblSenha = new JLabel("Senha:");
        passwordSenha = new JPasswordField(20);
        buttonCadastrar = new JButton("Cadastrar");
        buttonCancelar = new JButton("Cancelar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(lblNome, gbc);
        gbc.gridx = 1;
        contentPane.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lblEmail, gbc);
        gbc.gridx = 1;
        contentPane.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(lblSenha, gbc);
        gbc.gridx = 1;
        contentPane.add(passwordSenha, gbc);

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(buttonCadastrar);
        panelBotoes.add(buttonCancelar);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPane.add(panelBotoes, gbc);

        setContentPane(contentPane);
        setTitle("Tela de Cadastro");
        setSize(600, 400);
        setModal(true);
        setLocationRelativeTo(null);

        buttonCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCadastrar();
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelar();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelar();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCadastrar() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = new String(passwordSenha.getPassword()).trim();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        dispose();
    }

    private void onCancelar() {
        dispose();
    }

    public static void main(String[] args) {
        TelaCadastro cadastro = new TelaCadastro();
        cadastro.setVisible(true);
    }
}
