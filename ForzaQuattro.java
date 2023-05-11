package pacchetto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ForzaQuattro extends JFrame {
    private static final int righe = 6;
    private static final int colonne = 7;
    private static final int TILE_SIZE = 80;
    private int[][] tabella;
    private boolean turnogiocatore1;
    private JLabel turnLabel;
    private JPanel boardPanel;

    public ForzaQuattro() {
        super("Forza Quattro");
        tabella = new int[righe][colonne];
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < righe; i++) {
                    for (int j = 0; j < colonne; j++) {
                        int x = j * TILE_SIZE;
                        int y = (i + 1) * TILE_SIZE;
                        if (tabella[i][j] == 0) {
                            g.setColor(Color.WHITE);
                        } else if (tabella[i][j] == 1) {
                            g.setColor(Color.RED);
                        } else {
                            g.setColor(Color.YELLOW);
                        }
                        g.fillOval(x, y, TILE_SIZE, TILE_SIZE);
                    }
                }
            }
        };
        boardPanel.setPreferredSize(new Dimension(colonne * TILE_SIZE, (righe + 1) * TILE_SIZE));
        boardPanel.setBackground(Color.BLUE);
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / TILE_SIZE;
                if (drop(col)) {
                    int row = getLowestEmptyRow(col)+2;
                    Graphics g = boardPanel.getGraphics();
                    g.setColor(turnogiocatore1 ? Color.RED : Color.YELLOW);
                    g.fillOval(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);  // Colora la pallina vincente
                    g.dispose();
                    if (checkWin(turnogiocatore1 ? 1 : 2)) {
                        String winner = turnogiocatore1 ? "Giocatore 1" : "Giocatore 2";
                        JOptionPane.showMessageDialog(ForzaQuattro.this, winner + " vittoria!");

                        reset();
                    } else if (isBoardFull()) {
                        JOptionPane.showMessageDialog(ForzaQuattro.this, "Pareggio!");
                        reset();
                    } else {
                        turnogiocatore1 = !turnogiocatore1;
                        turnLabel.setText(turnogiocatore1 ? "Turno Giocatore 1" : "Turno Giocatore 2");
                        boardPanel.repaint();
                    }
                }
            }
        });
        add(boardPanel, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        controlPanel.add(resetButton);
        turnLabel = new JLabel("Turno Giocatore 1");
        controlPanel.add(turnLabel);
        add(controlPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        reset();
    }

    private void reset() {
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                tabella[i][j] = 0;
            }
        }
        turnogiocatore1 = true;
        boardPanel.repaint();
        turnLabel.setText("Turno Giocatore 1");
    }

    private boolean drop(int col) {
        for (int i = righe - 1; i >= 0; i--) {
            if (tabella[i][col] == 0) {
                tabella[i][col] = turnogiocatore1 ? 1 : 2;
                return true;
            }
        }
        return false;
    }

    private boolean checkWin(int giocatore) {
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                if (j + 3 < colonne &&
                        tabella[i][j] == giocatore &&
                        tabella[i][j + 1] == giocatore &&
                        tabella[i][j + 2] == giocatore &&
                        tabella[i][j + 3] == giocatore) {
                    return true;
                }
                if (i + 3 < righe) {
                    if (tabella[i][j]== giocatore &&
                            tabella[i + 1][j] == giocatore &&
                            tabella[i + 2][j] == giocatore &&
                            tabella[i + 3][j] == giocatore) {
                        return true;
                    }
                    if (j + 3 < colonne &&
                            tabella[i][j] == giocatore &&
                            tabella[i + 1][j + 1] == giocatore &&
                            tabella[i + 2][j + 2] == giocatore &&
                            tabella[i + 3][j + 3] == giocatore) {
                        return true;
                    }
                    if (j - 3 >= 0 &&
                            tabella[i][j] == giocatore &&
                            tabella[i + 1][j - 1] == giocatore &&
                            tabella[i + 2][j - 2] == giocatore &&
                            tabella[i + 3][j - 3] == giocatore) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                if (tabella[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getLowestEmptyRow(int col) {
        for (int i = righe - 1; i >= 0; i--) {
            if (tabella[i][col] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new ForzaQuattro();
    }
}