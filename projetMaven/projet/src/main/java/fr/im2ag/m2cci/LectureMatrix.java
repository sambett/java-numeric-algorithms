package fr.im2ag.m2cci;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;



import java.awt.*;

public class LectureMatrix {

    private JTextField[][] matriceFields;
    private JTextArea resultatArea;
    private final Font MAIN_FONT=new Font("Segoe print",Font.BOLD,18);
    private String type;
    private int size;
    int bandwidth; 

    public LectureMatrix(String type,int size,int bandWidth) {
        resultatArea = new JTextArea(10, 100);
        resultatArea.setFont(MAIN_FONT);
        resultatArea.setEditable(false);

        this.type=type;
        this.size=size;
        this.bandwidth=bandWidth;
        lireMatrice();
        Application.playSoundbut();
    }
    public void lireMatrice(){
        switch (type) {
            case "triangsup":
                lireMatriceTriangSup();
                break;
            case "trianginf":
                lireMatriceTriangInf();
                break;
            case "dense":
                lireMatriceDense();
                break;
                case "bande":
                lireMatriceBande();
                break;
            case "bandeSup":
                lireMatriceBandeSup();     
                break;
            case "bandeInf":
                lireMatriceBandeInf();
                break;
            case "symetrique":
                lireMatriceSymetrique();
                break;
            case "bande Symetrique":
                lireMatriceBandeSymetrique();
                break;
            case "bande symetrique definiePositive":
                lireMatriceBandeSymetrique();
                break;
            case "symetrique definiePositive":
                lireMatriceSymetrique();
                break;
            default:
            lireMatriceDense();
            break;
        }
    }
    private void lireMatriceBandeSymetrique() {
        // Initialize the matrix of text fields
        matriceFields = new JTextField[size][size];
    
        // Fixed size of text fields
        int fieldWidth = 5;
        int fieldHeight = 30;
    
        // Initialize the input panel
        JPanel saisiePanel = new JPanel(new GridLayout(size, size));
    
        // Input elements into the matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriceFields[i][j] = new JTextField();
                matriceFields[i][j].setFont(MAIN_FONT);
                matriceFields[i][j].setPreferredSize(new Dimension(fieldWidth, fieldHeight));
    
                // Condition to ensure symmetrical input only in the band
                if (j >= Math.max(0, i - bandwidth) && j <= Math.min(size - 1, i + bandwidth)) {
                    // If the element is in the upper triangular band
                    if (i < j) {
                       final int row = i;
                        final int col = j;
                        matriceFields[i][j].getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                updateLowerField(row, col);
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                updateLowerField(row, col);
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {
                                updateLowerField(row, col);
                            }
                        });
                    } else if (j < i) {
                        matriceFields[i][j].setEditable(false);
                    }
                } else {
                    // If the element is outside the band, set it to 0 and make it uneditable
                    matriceFields[i][j].setText("0");
                    matriceFields[i][j].setEditable(false);
                }
    
                saisiePanel.add(matriceFields[i][j]);
                saisiePanel.setPreferredSize(new Dimension(50 * size, 50 * size));
            }
        }
    
        // Display the input panel in a dialog box
        JOptionPane.showMessageDialog(null, saisiePanel, "Saisir Matrice", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void lireMatriceDense() {
        matriceFields = new JTextField[size][size];
        // Taille fixe des champs de texte
        int fieldWidth = 50;
        int fieldHeight = 30;

        JPanel saisiePanel = new JPanel(new GridLayout(size, size));

        // Saisie des éléments de la matrice
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriceFields[i][j] = new JTextField();
                matriceFields[i][j].setFont(MAIN_FONT);
                matriceFields[i][j].setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                saisiePanel.add(matriceFields[i][j]);
                saisiePanel.setPreferredSize(new Dimension(50*size,50*size));
            }
            
        }

        // Affichage du panneau de saisie dans une boîte de dialogue
        JOptionPane.showMessageDialog(null,saisiePanel, "Saisir Matrice", JOptionPane.PLAIN_MESSAGE);
    }
    private void lireMatriceTriangSup() {

        // Initialisation de la matrice de champs de texte
        matriceFields = new JTextField[size][size];

        // Taille fixe des champs de texte
        int fieldWidth = 5;
        int fieldHeight = 30;

        // Initialisation du panneau de saisie
        JPanel saisiePanel = new JPanel(new GridLayout(size, size));

        // Saisie des éléments de la matrice
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriceFields[i][j] = new JTextField();
                matriceFields[i][j].setFont(MAIN_FONT);
                matriceFields[i][j].setPreferredSize(new Dimension(fieldWidth, fieldHeight));

                // Si l'élément est dans la partie inférieure, le fixer à 0
                if (j < i) {
                    matriceFields[i][j].setText("0");
                    matriceFields[i][j].setEditable(false);
                }

                saisiePanel.add(matriceFields[i][j]);
                saisiePanel.setPreferredSize(new Dimension(50*size,50*size));
            }
        }

        // Affichage du panneau de saisie dans une boîte de dialogue
        JOptionPane.showMessageDialog(null,saisiePanel, "Saisir Matrice", JOptionPane.PLAIN_MESSAGE);
    }
    private void lireMatriceTriangInf() {

        // Initialisation de la matrice de champs de texte
        matriceFields = new JTextField[size][size];

        // Taille fixe des champs de texte
        int fieldWidth = 5;
        int fieldHeight = 30;

        // Initialisation du panneau de saisie
        JPanel saisiePanel = new JPanel(new GridLayout(size, size));

        // Saisie des éléments de la matrice
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <size; j++) {
                matriceFields[i][j] = new JTextField();
                matriceFields[i][j].setFont(MAIN_FONT);
                matriceFields[i][j].setPreferredSize(new Dimension(fieldWidth, fieldHeight));

                // Si l'élément est dans la partie inférieure, le fixer à 0
                if (j > i) {
                    matriceFields[i][j].setText("0");
                    matriceFields[i][j].setEditable(false);
                }

                saisiePanel.add(matriceFields[i][j]);
                saisiePanel.setPreferredSize(new Dimension(50*size,50*size));
            }
        }

        // Affichage du panneau de saisie dans une boîte de dialogue
        JOptionPane.showMessageDialog(null,saisiePanel, "Saisir Matrice", JOptionPane.PLAIN_MESSAGE);
    } 
    //Sam's contribution 
    private void lireMatriceBandeSup() {

        // Initialisation de la matrice de champs de texte
        matriceFields = new JTextField[size][size];

        // Taille fixe des champs de texte
        int fieldWidth = 5;
        int fieldHeight = 30;

        // Initialisation du panneau de saisie
        JPanel saisiePanel = new JPanel(new GridLayout(size, size));

        // Saisie des éléments de la matrice
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriceFields[i][j] = new JTextField();
                matriceFields[i][j].setFont(MAIN_FONT);
                matriceFields[i][j].setPreferredSize(new Dimension(fieldWidth, fieldHeight));

                // Si l'élément est hors bande supérieure, le fixer à 0
                
                if (!(j < Math.min(size, i + bandwidth + 1))|| i > j) {
                    matriceFields[i][j].setText("0");
                    matriceFields[i][j].setEditable(false);
                }

                saisiePanel.add(matriceFields[i][j]);
                saisiePanel.setPreferredSize(new Dimension(50*size,50*size));
            }
        }

        // Affichage du panneau de saisie dans une boîte de dialogue
        JOptionPane.showMessageDialog(null,saisiePanel, "Saisir Matrice", JOptionPane.PLAIN_MESSAGE);
    }
    private void lireMatriceBandeInf() {

        // Initialisation de la matrice de champs de texte
        matriceFields = new JTextField[size][size];

        // Taille fixe des champs de texte
        int fieldWidth = 5;
        int fieldHeight = 30;

        // Initialisation du panneau de saisie
        JPanel saisiePanel = new JPanel(new GridLayout(size, size));

        // Saisie des éléments de la matrice
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriceFields[i][j] = new JTextField();
                matriceFields[i][j].setFont(MAIN_FONT);
                matriceFields[i][j].setPreferredSize(new Dimension(fieldWidth, fieldHeight));

                // Si l'élément est hors bande supérieure, le fixer à 0
                if (!(j >= Math.max(0, i - bandwidth) && j <= i)) {
                    matriceFields[i][j].setText("0");
                    matriceFields[i][j].setEditable(false);
                }

                saisiePanel.add(matriceFields[i][j]);
                saisiePanel.setPreferredSize(new Dimension(50*size,50*size));
            }
        }

        // Affichage du panneau de saisie dans une boîte de dialogue
        JOptionPane.showMessageDialog(null,saisiePanel, "Saisir Matrice", JOptionPane.PLAIN_MESSAGE);
    }

    private void lireMatriceBande() {

        // Initialisation de la matrice de champs de texte
        matriceFields = new JTextField[size][size];

        // Taille fixe des champs de texte
        int fieldWidth = 5;
        int fieldHeight = 30;

        // Initialisation du panneau de saisie
        JPanel saisiePanel = new JPanel(new GridLayout(size, size));

        //  Saisie des éléments de la matrice
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriceFields[i][j] = new JTextField();
                matriceFields[i][j].setFont(MAIN_FONT);
                matriceFields[i][j].setPreferredSize(new Dimension(fieldWidth, fieldHeight));

                // Si l'élément est hors bande supérieure,
                if (!(j >= Math.max(0, i - bandwidth) && j <= Math.min(size - 1, i + bandwidth))){
                    matriceFields[i][j].setText("0");
                    matriceFields[i][j].setEditable(false);
                }

                saisiePanel.add(matriceFields[i][j]);
                saisiePanel.setPreferredSize(new Dimension(50*size,50*size));
            }
        }

        // Affichage du panneau de saisie dans une boîte de dialogue
        JOptionPane.showMessageDialog(null,saisiePanel, "Saisir Matrice", JOptionPane.PLAIN_MESSAGE);
    }
    
   private void lireMatriceSymetrique() {
    // Initialize the matrix of text fields
    matriceFields = new JTextField[size][size];

    // Fixed size of text fields
    int fieldWidth = 5;
    int fieldHeight = 30;

    // Initialize the input panel
    JPanel saisiePanel = new JPanel(new GridLayout(size, size));

    // Input elements into the matrix
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            matriceFields[i][j] = new JTextField();
            matriceFields[i][j].setFont(MAIN_FONT);
            matriceFields[i][j].setPreferredSize(new Dimension(fieldWidth, fieldHeight));

            // Add listener to upper triangular fields for live mirroring
            if (i < j) {
                final int row = i;
                final int col = j;
                matriceFields[i][j].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateLowerField(row, col);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateLowerField(row, col);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateLowerField(row, col);
                    }
                });
            }
            else if (j<i){matriceFields[i][j].setEditable(false);}

            saisiePanel.add(matriceFields[i][j]);
            saisiePanel.setPreferredSize(new Dimension(50*size,50*size));
        }
    }

    // Display the input panel in a dialog box
    JOptionPane.showMessageDialog(null, saisiePanel, "Saisir Matrice", JOptionPane.PLAIN_MESSAGE);
}

// Method to update the corresponding lower field based on changes in the upper field
private void updateLowerField(int row, int col) {
    String text = matriceFields[row][col].getText();
    matriceFields[col][row].setText(text);
}

    public int[][] convertirMatriceEntiers() {
        int[][] matriceEntiers = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriceEntiers[i][j] = Integer.parseInt(matriceFields[i][j].getText());
            }
        }
        return matriceEntiers;
    }
    
}
