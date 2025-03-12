package view;

import javax.swing.*;
import java.awt.event.*;

public class TelaInicial extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public TelaInicial() {
        setContentPane(contentPane);
        ImageIcon icon = new ImageIcon("src/img/logoBiblioteca.png");
        setIconImage(icon.getImage());
        setTitle("Biblioteca Comunitária");
        setSize(1200, 1200);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // Ao clicar no botão "Não", abre a TelaCadastro
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // Lógica para o botão OK
        dispose();
    }

    private void onCancel() {
        // Abre a tela de cadastro e fecha a tela inicial
        TelaCadastro cadastro = new TelaCadastro();
        cadastro.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        TelaInicial dialog = new TelaInicial();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
