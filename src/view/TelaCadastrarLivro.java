package view;

import javax.swing.*;
import java.awt.event.*;

public class TelaCadastrarLivro extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JFormattedTextField campoIdLivro;
    private JFormattedTextField campoTituloLivro;
    private JFormattedTextField campoAutorLivro;
    private JRadioButton naEstanteRadioButton;
    private JRadioButton emprestadoRadioButton;

    public TelaCadastrarLivro() {
        setContentPane(contentPane);
        setModal(true);
        ImageIcon icon = new ImageIcon("src/img/logoBiblioteca.png");
        setIconImage(icon.getImage());
        setTitle("Biblioteca Comunitária");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
