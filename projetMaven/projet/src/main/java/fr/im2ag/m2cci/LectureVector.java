package fr.im2ag.m2cci;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LectureVector {
    private JTextField[] vectorFields;
    private JTextArea resultatArea;
    private final Font MAIN_FONT=new Font("Segoe print",Font.BOLD,18);
    private int size;

    public LectureVector(int size) {
        resultatArea = new JTextArea(10, 20);
        resultatArea.setFont(MAIN_FONT);
        resultatArea.setEditable(false);
        this.size=size;
        lireVecteur();
        Application.playSoundbut();
    }
    private void lireVecteur() {
        vectorFields = new JTextField[size];
        // Taille fixe des champs de texte
        int fieldWidth = 50;
        int fieldHeight = 30;

        JPanel saisiePanel = new JPanel(new GridLayout(size, 1));

        // Saisie des éléments de la matrice
        for (int i = 0; i < size; i++) {
                vectorFields[i] = new JTextField();
                vectorFields[i].setFont(MAIN_FONT);
                vectorFields[i].setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                saisiePanel.add(vectorFields[i]);
        }

        // Affichage du panneau de saisie dans une boîte de dialogue
        JOptionPane.showMessageDialog(null,saisiePanel, "Saisir vecteur", JOptionPane.PLAIN_MESSAGE);
    }
    public int[] getVector() {
        int[] vecteurEntiers = new int[size];
        for (int i = 0; i < size; i++) {
                vecteurEntiers[i] = Integer.parseInt(vectorFields[i].getText());
            
        }
        return vecteurEntiers;
    }
}
