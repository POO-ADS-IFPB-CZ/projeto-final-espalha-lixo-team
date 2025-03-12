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
    private JFormattedTextField campoId;

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public TelaCadastroUsuario() {
        setContentPane(contentPane);
        setModal(true);
        ImageIcon icon = new ImageIcon("src/img/logoBiblioteca.png");
        setIconImage(icon.getImage());
        setTitle("Biblioteca Comunitária");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()){
                    String idCodigo = campoId.getText();
                    int id = Integer.parseInt(idCodigo);
                    String nome = campoNome.getText();
                    String email = campoEmail.getText();
                    String senha = Arrays.toString(campoPassword.getPassword());
                    Usuario usuario = new Usuario(id, nome, email, senha);

                    if(usuarioDAO.adicionarUsuario(usuario)){
                        JOptionPane.showMessageDialog(null,"Salvo com Sucesso!");
                    }else {
                        JOptionPane.showMessageDialog(null,
                                "Código já existente");
                        }
                }

            }
        });

    }

    private boolean validarCampos() {
        //TODO: Validar os outros campos
        if(campoId.getText().isEmpty() ||
                campoNome.getText().isEmpty() ||
                campoEmail.getText().isEmpty() ||
                campoPassword.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Preencha todos os campos");
            return false;
        }
        return true;
    }
}
