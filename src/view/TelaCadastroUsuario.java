package view;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;

public class TelaCadastroUsuario extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JFormattedTextField campoNome;
    private JFormattedTextField campoEmail;
    private JPasswordField campoPassword;

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public TelaCadastroUsuario() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()){
                    String nome = campoNome.getText();
                    String email = campoEmail.getText();
                    String senha = Arrays.toString(campoPassword.getPassword());

                    Usuario usuario = new Usuario(nome, email);
                }
                try {
                    if(usuarioDAO.adicionarUsuario(usuario)){
                        JOptionPane.showMessageDialog(null,"Salvo com Sucesso!");
                    }
                } catch (IOException ex) {

                } catch (ClassNotFoundException ex) {

                }
            }
        });

    }
}
