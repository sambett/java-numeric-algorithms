package fr.im2ag.m2cci;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
public class Application extends JFrame{
    final static Font MAIN_FONT=new Font("Segoe print",Font.BOLD,18);
    static CardLayout cardLayout;
    static JPanel cardPanel;
    private Page1 p1;
    private Page2Boutton1 p2;
    private Page3boutton1_Boutton1 p4;
    private PageResolutionButton p7;
    private Page2Boutton2 p3;
    private Page3Boutton2_Boutton1 p5;
    private Page3Boutton1_Boutton2 p6;
    private PcontentResolutionJordon p8;
    private PageMethodeIterative p9;
    private PageDecompLU p10;
    private PageResCholesky p11;
    private AboutUsContent p12;
    
    public void initialize(){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(1000, 800));
        this.setLocationRelativeTo(null);
        this.setName("Application des matrices");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Ajout du son à l'initialisation de l'application
        playSound("./projet/src/main/java/fr/im2ag/m2cci/sonApp.wav");
        p1=new Page1(this.getSize());
        p1.intializePage1();
        p2=new Page2Boutton1(this.getSize());
        p2.intializePage1P2();
        p7=new PageResolutionButton(this.getSize());
        p7.intializePage();
        p3= new Page2Boutton2(this.getSize());
        p3.intializePage2P2();
        p4=new Page3boutton1_Boutton1(this.getSize());
        p4.intializePage11P3();
        p5= new Page3Boutton2_Boutton1(this.getSize());
        p5.intializePage12P3();
        p6=new Page3Boutton1_Boutton2(this.getSize());
        p6.initializePage21P3();
        p8=new PcontentResolutionJordon(this.getSize());
        p8.initializePage();
        p9= new PageMethodeIterative(this.getSize());
        p9.intializePage();
        p10=new PageDecompLU(this.getSize());
        p10.initializePage();
        p11=new PageResCholesky(this.getSize());
        p11.initializePage();
        p12 = new AboutUsContent(this.getSize());
        p12.informations();
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Dimension newSize = e.getComponent().getSize();
                if (p1 != null) {
                    p1.updateComponentsSize(newSize);
                    p2.updateComponentsSize(newSize);
                    p3.updateComponentsSize(newSize);
                    p4.updateComponentsSize(newSize);
                    p5.updateComponentsSize(newSize);
                    p9.updateComponentsSize(newSize);
                    p6.updateComponentsSize(newSize);
                    p7.updateComponentsSize(newSize);
                    p8.updateComponentsSize(newSize);
                    p10.updateComponentsSize(newSize);
                    p11.updateComponentsSize(newSize);
                    p12.updateComponentsSize(newSize);

                }
            }
        });
        //this.setResizable(false);
        this.getContentPane().add(cardPanel);
        this.setVisible(true);
    }
    public Dimension getApplicationSize() {
        return this.getSize();
    }
   private void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public static void playSoundbut() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("./projet/src/main/java/fr/im2ag/m2cci/clicSon.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public static void playSoundError() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("./projet/src/main/java/fr/im2ag/m2cci/error.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Récupération du contrôle du volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Augmentation du volume (en dB, par exemple, 6.0f)
            float volume = 6.0f; // Vous pouvez ajuster cette valeur selon votre besoin
            gainControl.setValue(volume);
            
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
        e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Application window= new Application();
        window.initialize();
    }
}
