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
//page pour choisir la multiplication
public class Page2Boutton2 {
    private Dimension dim;
    private JLayeredPane layeredPane;
    private JPanel imagePanel,buttonPanel;
    private ImageIcon backgroundImage ;
    private static final float BUTTON_CLICK_VOLUME = 1.5f;
    public Page2Boutton2(Dimension dim){
        this.dim=dim;
    }
     public void intializePage2P2(){

        Dimension tailleBouton = new Dimension(300, 150);

        JButton nextPageButton3 = new JButton("Resolution Gauss/Gauss Pivotage");
        nextPageButton3.setFont(Application.MAIN_FONT);
        nextPageButton3.setBackground(new Color(0, 139, 139));
        nextPageButton3.setPreferredSize(tailleBouton);
        nextPageButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page4");
            }
        });
       
        JButton nextPageButton4 = new JButton("Resolution GaussJordon");
        nextPageButton4.setFont(Application.MAIN_FONT);
        nextPageButton4.setBackground(new Color(0, 139, 139));
        nextPageButton4.setPreferredSize(tailleBouton);
        nextPageButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page7");
            }
        });
        JButton nextPageButton = new JButton("DecompositionLU");
        nextPageButton.setFont(Application.MAIN_FONT);
        nextPageButton.setBackground(new Color(0, 139, 139));
        nextPageButton.setPreferredSize(tailleBouton);
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page12");
            }
        });
        JButton nextPageButton1 = new JButton("Cholesky");
        nextPageButton1.setFont(Application.MAIN_FONT);
        nextPageButton1.setBackground(new Color(0, 139, 139));
        nextPageButton1.setPreferredSize(tailleBouton);
        nextPageButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page13");
            }
        });
        JPanel page2buttPanel= new JPanel();
        page2buttPanel.setOpaque(false);
        page2buttPanel.add(nextPageButton3);
        page2buttPanel.add(nextPageButton4); 
        JPanel butPanel= new JPanel();
        butPanel.setOpaque(false);
        butPanel.add(nextPageButton);
        butPanel.add(nextPageButton1); 
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
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 500, 50));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 100, backgroundImage.getIconWidth()/2, backgroundImage.getIconHeight()/2);


        JPanel page2 =new JPanel();
        page2.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
        page2.setBackground(new Color(150,150,150));
        JLabel descLabel2=new JLabel("Choisir SVP quelle Resolution voulez-vous :");
        descLabel2.setFont(Application.MAIN_FONT);
        descLabel2.setForeground(Color.WHITE);

        buttonPanel.add(descLabel2);
        buttonPanel.add(page2buttPanel);
        buttonPanel.add(butPanel);

        // Ajout des panneaux au JLayeredPane
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        Application.cardPanel.add(layeredPane, "page6");



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
    public void updateComponentsSize(Dimension newDim) {
        this.dim = newDim; // Mettez à jour la dimension

        // Mettez à jour la taille et la position des composants en fonction de la nouvelle dimension
        layeredPane.setPreferredSize(newDim);
        int centerX = (int) (newDim.getWidth() - backgroundImage.getIconWidth() / 2) / 2;
        int centerY = 100;
        buttonPanel.setBounds(centerX, centerY, backgroundImage.getIconWidth() / 2, backgroundImage.getIconHeight() / 2);
        imagePanel.setBounds(0, 0, (int) newDim.getWidth(), (int) newDim.getHeight());

    }
    
}
