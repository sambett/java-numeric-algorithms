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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PageMethodeIterative {
    private JTextField textSize2,text;
    private JLabel matrixLabel2,vectLabel2,resultLabel2;
    private Vector vect2;
    private MatrixClass matrix2;
    private String type;
    private Dimension dim;
    private JLayeredPane layeredPane;
    private JPanel imagePanel,page11;
    private ImageIcon backgroundImage ;
    private JMenu TypeMenu;
    private JMenuItem MenuItem1;
    public PageMethodeIterative(Dimension dim){
        this.dim=dim;
    }
    public void intializePage(){
        JLabel labelTitre= new JLabel("                                 Page Resolution Méthodes iteratives :");
        labelTitre.setFont(Application.MAIN_FONT);
        labelTitre.setForeground(Color.WHITE);
        // saisir la taille
        JLabel labelType= new JLabel("Type de la matrice:");
        labelType.setFont(Application.MAIN_FONT);
        labelType.setForeground(Color.WHITE);
        JLabel labelSize2= new JLabel("Size:");
        labelSize2.setFont(Application.MAIN_FONT);
        labelSize2.setForeground(Color.WHITE);
        textSize2=new JTextField();
        textSize2.setFont(Application.MAIN_FONT);
        JLabel label= new JLabel("Epsilon:");
        label.setFont(Application.MAIN_FONT);
        label.setForeground(Color.WHITE);
        text=new JTextField();
        text.setFont(Application.MAIN_FONT);

        JMenuBar menuBar = new JMenuBar();

        TypeMenu = new JMenu("Type de la matrice");
        TypeMenu.setFont(Application.MAIN_FONT);
        MenuItem1 = new JMenuItem("Dense à Diagonale Dominante");
        MenuItem1.setFont(Application.MAIN_FONT);
        MenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                type="diagonaleDominat";
                TypeMenu.setText(MenuItem1.getText());
            }
        });
        TypeMenu.add(MenuItem1);
        menuBar.add(TypeMenu);
        menuBar.setOpaque(true);
        menuBar.setBackground(Color.DARK_GRAY);
        menuBar.setPreferredSize(new Dimension(100,50));
        
        JPanel entrePanel2= new JPanel();
        entrePanel2.setLayout(new GridLayout(5,1,5,5));
        entrePanel2.setOpaque(false);
        entrePanel2.add(labelType);
        entrePanel2.add(menuBar);
        entrePanel2.add(labelSize2);
        entrePanel2.add(textSize2);
        entrePanel2.add(label);
        entrePanel2.add(text);

        JPanel entreGlobPanel= new JPanel( new BorderLayout());
        entreGlobPanel.setOpaque(false);
        entreGlobPanel.add(labelTitre,BorderLayout.CENTER);
        entreGlobPanel.add(entrePanel2,BorderLayout.SOUTH);
        
        matrixLabel2 = new JLabel();
        matrixLabel2.setFont(Application.MAIN_FONT);
        matrixLabel2.setForeground(Color.WHITE);

        vectLabel2= new JLabel();
        vectLabel2.setFont(Application.MAIN_FONT);
        vectLabel2.setForeground(Color.WHITE);
        // vecteur resultat label
        resultLabel2=new JLabel();
        resultLabel2.setFont(Application.MAIN_FONT);
        resultLabel2.setForeground(Color.WHITE);


        JButton button2 = new JButton("créer vecteur");
        button2.setFont(Application.MAIN_FONT);
        button2.setBackground(new Color(0, 139, 139));
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                int size=Integer.parseInt(textSize2.getText());
                //creation d'un vecteur
                vect2=new Vector(size);
                String str=vect2.afficheVector();
                vectLabel2.setText("<html>" + str + "</html>");

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
                int size=Integer.parseInt(textSize2.getText());
                LectureVector lectureVector=new LectureVector(size);
                vect2=new Vector(size,lectureVector.getVector());
                String str=vect2.afficheVector();
                str = str.replaceAll("\n", "<br>");
                vectLabel2.setText("<html>" + str + "</html>");

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton buttonMatrix2 = new JButton("créer matrice ");
        buttonMatrix2.setFont(Application.MAIN_FONT);
        buttonMatrix2.setBackground(new Color(0, 139, 139));
        buttonMatrix2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                int size=Integer.parseInt(textSize2.getText());
                matrix2= new MatrixClass(type, size,0);
                String ch = matrix2.afficheMatrix();
                ch = ch.replaceAll("<td>", "&nbsp;&nbsp;&nbsp;&nbsp;<td>");  // Ajoutez de l'espace avant chaque cellule
                ch = ch.replaceAll("</td></tr>", "</td></tr><br>");  // Ajoutez un retour à la ligne après chaque ligne
                // Ajoute également les retours à la ligne avec des balises <br>
                ch = ch.replaceAll("\n", "<br>");

                matrixLabel2.setText("<html>" + ch + "</html>");
                // TODO Auto-generated method stub
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
                int size=Integer.parseInt(textSize2.getText());
                LectureMatrix lectMat= new LectureMatrix(type, size,0);
                matrix2= new MatrixClass(type, size,lectMat.convertirMatriceEntiers());
                String ch=matrix2.afficheMatrix();
                 ch = ch.replaceAll("<td>", "&nbsp;&nbsp;&nbsp;&nbsp;<td>");  // Ajoutez de l'espace avant chaque cellule
                ch = ch.replaceAll("</td></tr>", "</td></tr><br>");  // Ajoutez un retour à la ligne après chaque ligne
                // Ajoute également les retours à la ligne avec des balises <br>
                ch = ch.replaceAll("\n", "<br>");
                matrixLabel2.setText("<html>" + ch + "</html>");
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton buttonresSeidel = new JButton("Resultat G-seidel ");
        buttonresSeidel.setFont(Application.MAIN_FONT);
        buttonresSeidel.setBackground(new Color(0, 139, 139));
        buttonresSeidel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                double epsi=Double.parseDouble(text.getText());
                ResolutionGaussSeidel res=new ResolutionGaussSeidel(matrix2, vect2, epsi);
                String str=res.afficheSolution();
                resultLabel2.setText("<html>" + str + "</html>");

                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton buttonresJacobi = new JButton("Resultat Jacobi ");
        buttonresJacobi.setFont(Application.MAIN_FONT);
        buttonresJacobi.setBackground(new Color(0, 139, 139));
        buttonresJacobi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                double epsi=Double.parseDouble(text.getText());
                ResolutionJacobi res=new ResolutionJacobi(matrix2, vect2, epsi);
                String str=res.afficheSolution();
                resultLabel2.setText("<html>" + str + "</html>");

                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton previousPageButton = new JButton("<-- Page Principale");
        previousPageButton.setFont(Application.MAIN_FONT);
        previousPageButton.setBackground(new Color(0, 139, 139));

        // Ajoutez un ActionListener au bouton pour revenir à la première "page"
        previousPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.playSoundbut();
                Application.cardLayout.show( Application.cardPanel,"page1");
            }
        });
        JButton clearButton = new JButton("Effacer Contenu");
        clearButton.setFont(Application.MAIN_FONT);
        clearButton.setBackground(new Color(0, 139, 139));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Réinitialiser les composants
                Application.playSoundbut();
                TypeMenu.setText("Type de la matrice ");
                textSize2.setText("");
                text.setText("");
                vectLabel2.setText("");
                matrixLabel2.setText("");
                resultLabel2.setText("");

                // Remettre à zéro les objets vect2 et matrix2 si nécessaire
                vect2 = null;
                matrix2 = null;
            }
        });

            JPanel buttPanel = new JPanel();
            buttPanel.setLayout(new GridLayout(2, 3, 5, 5)); // Réduisez le nombre de colonnes à 3 pour la mise en page
            buttPanel.setOpaque(false);
            buttPanel.add(clearButton);
            buttPanel.add(buttonMatrix2);
            buttPanel.add(button2);
            buttPanel.add(buttonresSeidel);
            buttPanel.add(previousPageButton);
            buttPanel.add(buttonLect);
            buttPanel.add(lectvect);
            buttPanel.add(buttonresJacobi);
             


        JPanel matPanel= new JPanel();
        matPanel.setOpaque(false);
        matPanel.add(matrixLabel2);

        JPanel vectPanel = new JPanel();
        vectPanel.setOpaque(false);
        vectPanel.add(vectLabel2);

        JPanel resultPanel= new JPanel();
        resultPanel.setOpaque(false);
        resultPanel.add(resultLabel2);


        JPanel descripResIterative = new JPanel();
        descripResIterative.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 50));
        descripResIterative.setOpaque(false);
        descripResIterative.add(matPanel);
        descripResIterative.add(vectPanel);
        descripResIterative.add(resultPanel);

        JPanel viewportBackgroundPanel = new JPanel();
        viewportBackgroundPanel.setOpaque(false);
        viewportBackgroundPanel.setLayout(new BorderLayout());
        viewportBackgroundPanel.add(descripResIterative, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(viewportBackgroundPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        backgroundImage = new ImageIcon("./projet/src/main/java/fr/im2ag/m2cci/image2.gif");


        // Création du JLayeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(990, 760));

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
        page11 = new JPanel(new BorderLayout());
        page11.setOpaque(false);
        page11.setBounds(0, 0, 1000, 750);
        page11.add(entreGlobPanel, BorderLayout.NORTH);
        page11.add(scrollPane, BorderLayout.CENTER);
        page11.add(buttPanel, BorderLayout.SOUTH);



        // Ajout des panneaux au JLayeredPane
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(page11, JLayeredPane.PALETTE_LAYER);

        Application.cardPanel.add(layeredPane, "page11");
    }
    public void updateComponentsSize(Dimension newDim) {
        this.dim = newDim; // Mettez à jour la dimension

        // Mettez à jour la taille et la position des composants en fonction de la nouvelle dimension
        layeredPane.setPreferredSize(newDim);
       
        page11.setBounds(0, 0, (int)newDim.getWidth(), (int)newDim.getHeight()-50);
        imagePanel.setBounds(0, 0, (int) newDim.getWidth(), (int) newDim.getHeight());

        // Rafraîchissez les composants
        page11.revalidate();
        page11.repaint();
        layeredPane.revalidate();
        layeredPane.repaint();
    }
    
}
