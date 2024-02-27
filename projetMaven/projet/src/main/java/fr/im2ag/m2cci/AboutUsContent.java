package fr.im2ag.m2cci;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class AboutUsContent {
    private Dimension dim;
    private JLayeredPane layeredPane;
    private JPanel imagePanel,descPanel;
    private ImageIcon backgroundImage ;
    public AboutUsContent(Dimension dim){
        this.dim=dim;
    }
    public void informations(){
        // Initialisation de backgroundImage
        backgroundImage = new ImageIcon("./projet/src/main/java/fr/im2ag/m2cci/image3.gif");
        JLabel lab0 = new JLabel("Développeurs : Siwar Ben Rhouma & Selma Bettaieb & Firas Barkia ");
        lab0.setFont(Application.MAIN_FONT);
        lab0.setForeground(Color.WHITE);
        JLabel lab1 = new JLabel("Superviseur: Dr. Sirine MARRAKCHI ");
        lab1.setFont(Application.MAIN_FONT);
        lab1.setForeground(Color.WHITE);
        JLabel lab2 = new JLabel("Matière: Algorithmes Numériques ");
        lab2.setFont(Application.MAIN_FONT);
        lab2.setForeground(Color.WHITE);
        JLabel lab3 = new JLabel("Filière : Cycle d'ingénieur ");
        lab3.setFont(Application.MAIN_FONT);
        lab3.setForeground(Color.WHITE);
        JLabel lab4 = new JLabel("Etablissement : Faculté des Sciences de Sfax ");
        lab4.setFont(Application.MAIN_FONT);
        lab4.setForeground(Color.WHITE);
        JLabel lab5 = new JLabel("Département: Informatique et Communications ");
        lab5.setFont(Application.MAIN_FONT);
        lab5.setForeground(Color.WHITE);
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
        // Panneau pour les boutons superposés
        descPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 40));
        descPanel.setOpaque(false);
        descPanel.setBounds(0, 200, backgroundImage.getIconWidth()/2, backgroundImage.getIconHeight()/2);
        descPanel.add(lab0);
        descPanel.add(lab1);
        descPanel.add(lab2);
        descPanel.add(lab3);
        descPanel.add(lab4);
        descPanel.add(lab5);
        descPanel.add(previousPageButton);
    
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
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(descPanel, JLayeredPane.PALETTE_LAYER);
        Application.cardPanel.add(layeredPane, "page33");
    }
    
    public void updateComponentsSize(Dimension newDim) {
        this.dim = newDim; // Mettez à jour la dimension

        // Mettez à jour la taille et la position des composants en fonction de la nouvelle dimension
        layeredPane.setPreferredSize(newDim);
        int centerX = (int) (newDim.getWidth() - backgroundImage.getIconWidth() / 2) / 2;
        int centerY = 100;
        descPanel.setBounds(centerX, centerY, backgroundImage.getIconWidth() / 2, backgroundImage.getIconHeight() / 2);
        imagePanel.setBounds(0, 0, (int) newDim.getWidth(), (int) newDim.getHeight());

    }
}
