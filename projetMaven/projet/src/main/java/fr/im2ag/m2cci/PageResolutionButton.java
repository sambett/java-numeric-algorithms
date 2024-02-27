package fr.im2ag.m2cci;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PageResolutionButton {
    private Dimension dim;
    private JLayeredPane layeredPane;
    private JPanel imagePanel,buttonPanel;
    private ImageIcon backgroundImage ;
    private static final float BUTTON_CLICK_VOLUME = 1.5f;
    public PageResolutionButton(Dimension dim){
        this.dim=dim;
    }
    public void intializePage(){
        Dimension tailleBouton = new Dimension(300, 150);
       

        JButton nextPageButton1 = new JButton("Méthodes directes");
        nextPageButton1.setFont(Application.MAIN_FONT);
        nextPageButton1.setBackground(new Color(0, 139, 139));
        nextPageButton1.setPreferredSize(tailleBouton);
        nextPageButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page6");
            }
        });
        JButton nextPageButton2 = new JButton("Méthodes Itératives");
        nextPageButton2.setFont(Application.MAIN_FONT);
        nextPageButton2.setBackground(new Color(0, 139, 139));
        nextPageButton2.setPreferredSize(tailleBouton);
        nextPageButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page11");
            }
        });

         JPanel page8buttPanel= new JPanel();
        page8buttPanel.setOpaque(false);
        page8buttPanel.add(nextPageButton1);
        page8buttPanel.add(nextPageButton2);
        backgroundImage = new ImageIcon("./projet/src/main/java/fr/im2ag/m2cci/image3.gif");

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
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 70));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 100, backgroundImage.getIconWidth()/2, backgroundImage.getIconHeight()/2);

        JLabel descLabel = new JLabel("Avec quelle méthodes voulez_vous faire la résolution");
        descLabel.setFont(Application.MAIN_FONT);
        descLabel.setForeground(Color.WHITE);

        buttonPanel.add(descLabel);
        buttonPanel.add(page8buttPanel);

        // Ajout des panneaux au JLayeredPane
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        Application.cardPanel.add(layeredPane, "page8");

    }
    public void updateComponentsSize(Dimension newDim) {
        this.dim = newDim; // Mettez à jour la dimension

        // Mettez à jour la taille et la position des composants en fonction de la nouvelle dimension
        layeredPane.setPreferredSize(newDim);
        int centerX = (int) (newDim.getWidth() - backgroundImage.getIconWidth() / 2) / 2;
        int centerY = 100;
        buttonPanel.setBounds(centerX, centerY, backgroundImage.getIconWidth() / 2, backgroundImage.getIconHeight() / 2);
        imagePanel.setBounds(0, 0, (int) newDim.getWidth(), (int) newDim.getHeight());

    }
    private void playButtonClickSound() {
        try {
            // Chargement du fichier audio du clic de bouton
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./projet/src/main/java/fr/im2ag/m2cci/CilcBouton.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(BUTTON_CLICK_VOLUME));
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
