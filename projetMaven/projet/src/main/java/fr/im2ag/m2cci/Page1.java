package fr.im2ag.m2cci;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Page1 {
    private Dimension dim;
    private static final float BUTTON_CLICK_VOLUME = 1.5f;
    private JLayeredPane layeredPane;
    private JPanel imagePanel,buttonPanel;
    private ImageIcon backgroundImage ;
    public Page1(Dimension dim){
        this.dim=dim;
    }
    public void intializePage1() {
        Dimension tailleBouton = new Dimension(300, 150);

        JButton nextPageButton1 = new JButton("Multiplication");
        nextPageButton1.setFont(Application.MAIN_FONT);
        nextPageButton1.setBackground(new Color(0, 139, 139));
        nextPageButton1.setPreferredSize(tailleBouton);
        nextPageButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page2");
            }
        });
        JButton nextPageButton2 = new JButton("Resolution");
        nextPageButton2.setFont(Application.MAIN_FONT);
        nextPageButton2.setBackground(new Color(0, 139, 139));
        nextPageButton2.setPreferredSize(tailleBouton);
        nextPageButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page8");
            }
        });
        JButton nextPageButton3 = new JButton("à propos nous");
        nextPageButton3.setFont(Application.MAIN_FONT);
        nextPageButton3.setBackground(new Color(0, 139, 139));
        nextPageButton3.setPreferredSize(new Dimension(100,50));
        nextPageButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();
                Application.cardLayout.show(Application.cardPanel, "page33");
            }
        });

            JPanel page1buttPanel = new JPanel(new BorderLayout());
            page1buttPanel.setOpaque(false);

            // Ajouter nextPageButton1 et nextPageButton2 au centre
            JPanel centerPanel = new JPanel(new GridLayout(1,1,5,5));
            centerPanel.setOpaque(false);
            centerPanel.add(nextPageButton1);
            centerPanel.add(nextPageButton2);
            page1buttPanel.add(centerPanel, BorderLayout.CENTER);

            // Ajouter nextPageButton3 au centre en dessous
            page1buttPanel.add(nextPageButton3, BorderLayout.SOUTH);

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

        JLabel descLabel = new JLabel("Choisir SVP si tu veux faire une multiplication ou une resolution");
        descLabel.setFont(Application.MAIN_FONT);
        descLabel.setForeground(Color.WHITE);

        buttonPanel.add(descLabel);
        buttonPanel.add(page1buttPanel);

        // Ajout des panneaux au JLayeredPane
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        Application.cardPanel.add(layeredPane, "page1");
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
