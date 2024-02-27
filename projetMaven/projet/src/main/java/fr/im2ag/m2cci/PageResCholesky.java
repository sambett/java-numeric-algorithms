package fr.im2ag.m2cci;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
public class PageResCholesky {
    private JTextField textSize;
    private JLabel matrixLabel,vectLabel,resultLabel,resultLabel2,resultLabel3;
    private MatrixClass matrix;
    private Vector vect;
    private String type;
    private int large;
    private Dimension dim;
    private JLayeredPane layeredPane;
    private JPanel imagePanel,page7;
    private ImageIcon backgroundImage ;
    private JMenu TypeMenu;
    private JMenuItem MenuItem1,MenuItem2;
    private int[][] lireMatrice;
    public PageResCholesky(Dimension dim){
        this.dim=dim;
    }
    public void initializePage(){
        JLabel labelTitre= new JLabel("                             Page Resolution avec Cholesky:");
        labelTitre.setFont(Application.MAIN_FONT);
        labelTitre.setForeground(Color.WHITE);
        JLabel labelType= new JLabel("Type de la matrice:");
        labelType.setFont(Application.MAIN_FONT);
        labelType.setForeground(Color.WHITE);
        // saisir la taille
        JLabel labelSize= new JLabel("Size:");
        labelSize.setFont(Application.MAIN_FONT);
        labelSize.setForeground(Color.WHITE);
        textSize=new JTextField();
        textSize.setFont(Application.MAIN_FONT);
        //matrixLabel
        matrixLabel= new JLabel();
        matrixLabel.setFont(Application.MAIN_FONT);
        matrixLabel.setForeground(Color.WHITE);
        
        //vecteur label
        vectLabel= new JLabel();
        vectLabel.setFont(Application.MAIN_FONT);
        vectLabel.setForeground(Color.WHITE);
        // vecteur resultat label
        resultLabel=new JLabel();
        resultLabel.setFont(Application.MAIN_FONT);
        resultLabel.setForeground(Color.WHITE);

        resultLabel2=new JLabel();
        resultLabel2.setFont(Application.MAIN_FONT);
        resultLabel2.setForeground(Color.WHITE);

        resultLabel3=new JLabel();
        resultLabel3.setFont(Application.MAIN_FONT);
        resultLabel3.setForeground(Color.WHITE);
        JMenuBar menuBar = new JMenuBar();

        TypeMenu = new JMenu("Type de la matrice");
        TypeMenu.setFont(Application.MAIN_FONT);
        

        MenuItem1 = new JMenuItem("Symetrique Definie Positive ");
        MenuItem1.setFont(Application.MAIN_FONT);
        MenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                type="symetrique definiePositive";
                TypeMenu.setText(MenuItem1.getText());
            }
        });
        TypeMenu.add(MenuItem1);

        MenuItem2 = new JMenuItem("Bande Symetrique Definie Positive ");
        MenuItem2.setFont(Application.MAIN_FONT);
        MenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                type="bande symetrique definiePositive";
                TypeMenu.setText(MenuItem2.getText());
            }
        });
        TypeMenu.add(MenuItem2);
        menuBar.add(TypeMenu);
        menuBar.setOpaque(true);
        menuBar.setBackground(Color.DARK_GRAY);
        menuBar.setPreferredSize(new Dimension(100,50));


        //panel pour les entrées
        JPanel entrePanel2= new JPanel();
        entrePanel2.setLayout(new GridLayout(5,1,5,5));
        entrePanel2.setOpaque(false);
        entrePanel2.add(labelType);
        entrePanel2.add(menuBar);
        entrePanel2.add(labelSize);
        entrePanel2.add(textSize);

        JPanel entreGlobPanel= new JPanel( new BorderLayout());
        entreGlobPanel.setOpaque(false);
        entreGlobPanel.add(labelTitre,BorderLayout.CENTER);
        entreGlobPanel.add(entrePanel2,BorderLayout.SOUTH);

        JButton button = new JButton("créer vecteur");
        button.setFont(Application.MAIN_FONT);
        button.setBackground(new Color(0, 139, 139));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                int size=Integer.parseInt(textSize.getText());
                //creation d'un vecteur
                vect=new Vector(size);
                String str=vect.afficheVector();
                str = str.replaceAll("\n", "<br>");
                vectLabel.setText("<html>" + str + "</html>");

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        
        JButton buttonMatrix = new JButton("créer matrice ");
        buttonMatrix.setFont(Application.MAIN_FONT);
        buttonMatrix.setBackground(new Color(0, 139, 139));
        buttonMatrix.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                int size=Integer.parseInt(textSize.getText());
                if (type.equals("bande symetrique definiePositive")) {
                    int maxLarge = (size - 1) / 2;
                    while (true) {
                        String input = JOptionPane.showInputDialog(null, "Entrez la largeur :");
                        try {
                            int number = Integer.parseInt(input);
                            if (number <= maxLarge) {
                                large=number;
                                break; // Sort de la boucle while car la saisie est valide
                            } else {
                                Application.playSoundError();
                                JOptionPane.showMessageDialog(null, "La largeur doit être inférieure ou égale à " + maxLarge);
                            }
                        } catch (NumberFormatException ex) {
                            Application.playSoundError();
                            JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                Application.playSoundbut();
                matrix= new MatrixClass(type, size,large);
                String ch = matrix.afficheMatrix();
                ch = ch.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
                ch = ch.replaceAll("<td>", "&nbsp;&nbsp;&nbsp;&nbsp;<td>");  // Ajoutez de l'espace avant chaque cellule
                ch = ch.replaceAll("</td></tr>", "</td></tr><br>");  // Ajoutez un retour à la ligne après chaque ligne
                // Ajoute également les retours à la ligne avec des balises <br>
                ch = ch.replaceAll("\n", "<br>");

                matrixLabel.setText("<html>" + ch + "</html>");
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton buttonLect = new JButton("lire matrice ");
        buttonLect.setFont(Application.MAIN_FONT);
        buttonLect.setBackground(new Color(0, 139, 139));
        buttonLect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                int size=Integer.parseInt(textSize.getText());
                if (type.equals("bande symetrique definiePositive")) {
                    int maxLarge = (size - 1) / 2;
                    while (true) {
                        String input = JOptionPane.showInputDialog(null, "Entrez la largeur :");
                        try {
                            int number = Integer.parseInt(input);
                            if (number <= maxLarge) {
                                large=number;
                                break; // Sort de la boucle while car la saisie est valide
                            } else {
                                Application.playSoundError();
                                JOptionPane.showMessageDialog(null, "La largeur doit être inférieure ou égale à " + maxLarge);
                            }
                        } catch (NumberFormatException ex) {
                            Application.playSoundError();
                            JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                while (true) {
                    LectureMatrix lectMat= new LectureMatrix(type, size,large);
                    lireMatrice=lectMat.convertirMatriceEntiers();
                    double[][] doubleMatrix = new double[lireMatrice.length][lireMatrice.length];
                    for (int i = 0; i < lireMatrice.length; i++) {
                        for (int j = 0; j < lireMatrice.length; j++) {
                            doubleMatrix[i][j] = (double) lireMatrice[i][j];
                        }
                    }
                    Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(doubleMatrix);
                    boolean isDPos =MatrixClass.isPositiveDefinite(matrix);
                    if(!isDPos){
                        Application.playSoundError();
                        JOptionPane.showMessageDialog(null, "Veuillez entrer une Matrice definie Positive", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }else{
                        break;
                    }

                }
                matrix= new MatrixClass(type, size,lireMatrice);
                String ch=matrix.afficheMatrix();
                ch = ch.replaceAll("<td>", "&nbsp;&nbsp;&nbsp;&nbsp;<td>");  // Ajoutez de l'espace avant chaque cellule
                ch = ch.replaceAll("</td></tr>", "</td></tr><br>");  // Ajoutez un retour à la ligne après chaque ligne
                // Ajoute également les retours à la ligne avec des balises <br>
                ch = ch.replaceAll("\n", "<br>");
                matrixLabel.setText("<html>" + ch + "</html>");
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton lectvect = new JButton("lire vecteur");
        lectvect.setFont(Application.MAIN_FONT);
        lectvect.setBackground(new Color(0, 139, 139));
        lectvect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                int size=Integer.parseInt(textSize.getText());
                LectureVector lectureVector=new LectureVector(size);
                vect=new Vector(size,lectureVector.getVector());
                String str=vect.afficheVector();
                str = str.replaceAll("\n", "<br>");
                vectLabel.setText("<html>" + str + "</html>");

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        
        JButton buttonres = new JButton("Res Cholesky ");
        buttonres.setFont(Application.MAIN_FONT);
        buttonres.setBackground(new Color(0, 139, 139));
        buttonres.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                Cholesky res= new Cholesky(matrix, vect);
                String str1=res.getL();
                str1 = str1.replaceAll("<td>", "&nbsp;&nbsp;&nbsp;&nbsp;<td>");  // Ajoutez de l'espace avant chaque cellule
                str1 = str1.replaceAll("</td></tr>", "</td></tr><br>");
                resultLabel2.setText("<html>" + str1 + "</html>");
                String str2=res.getLT();
                str2 = str2.replaceAll("<td>", "&nbsp;&nbsp;&nbsp;&nbsp;<td>");  // Ajoutez de l'espace avant chaque cellule
                str2 = str2.replaceAll("</td></tr>", "</td></tr><br>");
                resultLabel3.setText("<html>" + str2 + "</html>");
                String str=res.afficheVector();
                str = str.replaceAll("<td>", "&nbsp;&nbsp;&nbsp;&nbsp;<td>");  // Ajoutez de l'espace avant chaque cellule
                str = str.replaceAll("</td></tr>", "</td></tr><br>");
                resultLabel.setText("<html>" + str + "</html>");

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton previousPageButton2 = new JButton("<-- Page Principale");
        previousPageButton2.setFont(Application.MAIN_FONT);
        previousPageButton2.setBackground(new Color(0, 139, 139));

        // Ajoutez un ActionListener au bouton pour revenir à la première "page"
        previousPageButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                Application.cardLayout.show(Application.cardPanel, "page1");
            }
        });

        
        JButton clearButton = new JButton("Effacer Contenu");
        clearButton.setFont(Application.MAIN_FONT);
        clearButton.setBackground(new Color(0, 139, 139));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                // Réinitialiser les composants
                TypeMenu.setText("Type de la matrice ");
                textSize.setText("");
                vectLabel.setText("");
                matrixLabel.setText("");
                resultLabel.setText("");
                resultLabel2.setText("");
                resultLabel3.setText("");

                // Remettre à zéro les objets vect2 et matrix2 si nécessaire
                vect = null;
                matrix = null;
            }
        });

         


        JPanel buttResolPanel= new JPanel();
        buttResolPanel.setLayout(new GridLayout(2,2,5,5));
        buttResolPanel.setOpaque(false);
        buttResolPanel.add(clearButton);
        buttResolPanel.add(buttonMatrix);
        buttResolPanel.add(button);
        buttResolPanel.add(buttonres);
        buttResolPanel.add(previousPageButton2);
        buttResolPanel.add(buttonLect);
        buttResolPanel.add(lectvect);
        
        JPanel matPanel= new JPanel();
        matPanel.setOpaque(false);
        matPanel.add(matrixLabel);

        JPanel vectPanel = new JPanel();
        vectPanel.setOpaque(false);
        vectPanel.add(vectLabel);

        JPanel resultPanel= new JPanel();
        resultPanel.setOpaque(false);
        resultPanel.add(resultLabel);

        JPanel resultPanel2= new JPanel();
        resultPanel2.setOpaque(false);
        resultPanel2.add(resultLabel2);
        JPanel resultPanel3= new JPanel();
        resultPanel3.setOpaque(false);
        resultPanel3.add(resultLabel3);


        
        JPanel descriPanelResolution = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 50));
        descriPanelResolution.setOpaque(false);
        descriPanelResolution.add(matPanel);
        descriPanelResolution.add(vectPanel);
        descriPanelResolution.add(resultPanel2);
        descriPanelResolution.add(resultPanel3);
        descriPanelResolution.add(resultPanel);


        JPanel viewportBackgroundPanel = new JPanel();
        viewportBackgroundPanel.setOpaque(false);
        viewportBackgroundPanel.setLayout(new BorderLayout());
        viewportBackgroundPanel.add(descriPanelResolution, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(viewportBackgroundPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        backgroundImage = new ImageIcon("./projet/src/main/java/fr/im2ag/m2cci/image2.gif");

        // Création du JLayeredPane
        layeredPane = new JLayeredPane();

        // Panneau pour l'image en arrière-plan
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        imagePanel.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());

        // Panneau pour les boutons superposés
        page7 = new JPanel(new BorderLayout());
        page7.setOpaque(false);
        page7.setBounds(0, 0, 1000, 750);
        page7.add(entreGlobPanel, BorderLayout.NORTH);
        page7.add(scrollPane,BorderLayout.CENTER);
        page7.add(buttResolPanel,BorderLayout.SOUTH);



        // Ajout des panneaux au JLayeredPane
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(page7, JLayeredPane.PALETTE_LAYER);

        Application.cardPanel.add(layeredPane, "page13");
    }
    public void updateComponentsSize(Dimension newDim) {
        this.dim = newDim; // Mettez à jour la dimension

        // Mettez à jour la taille et la position des composants en fonction de la nouvelle dimension
        layeredPane.setPreferredSize(newDim);
       
        page7.setBounds(0, 0, (int)newDim.getWidth(), (int)newDim.getHeight()-50);
        imagePanel.setBounds(0, 0, (int) newDim.getWidth(), (int) newDim.getHeight());

        // Rafraîchissez les composants
        page7.revalidate();
        page7.repaint();
        layeredPane.revalidate();
        layeredPane.repaint();
    }

}
