package pacchetto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SchermataNomi extends JFrame {
    private JTextField nomeGiocatore1Field;
    private JTextField nomeGiocatore2Field;
    private JButton confermaButton;

    public SchermataNomi() {
        setTitle("Inserimento Nomi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel nomeGiocatore1Label = new JLabel("Nome Giocatore 1:");
        nomeGiocatore1Field = new JTextField(20);

        JLabel nomeGiocatore2Label = new JLabel("Nome Giocatore 2:");
        nomeGiocatore2Field = new JTextField(20);

        confermaButton = new JButton("Conferma");
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeGiocatore1 = nomeGiocatore1Field.getText();
                String nomeGiocatore2 = nomeGiocatore2Field.getText();

                ForzaQuattro gioco = new ForzaQuattro(nomeGiocatore1, nomeGiocatore2);
                gioco.setVisible(true);

                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(nomeGiocatore1Label);
        panel.add(nomeGiocatore1Field);
        panel.add(nomeGiocatore2Label);
        panel.add(nomeGiocatore2Field);
        panel.add(confermaButton);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
