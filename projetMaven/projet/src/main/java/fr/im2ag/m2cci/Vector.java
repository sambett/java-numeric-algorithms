package fr.im2ag.m2cci;
import java.util.Random;

public class Vector {
    private int size;
    int[] vector;
    public Vector(int size){
        this.size=size;
        this.vector=new int[size];
        createVector();
    }
    public Vector(int size,int[] vector){
        this.size=size;
        this.vector=vector;
    }
    public void createVector(){
         Random random = new Random();
        // Remplissage de la matrice triangulaire supérieure avec des entiers aléatoires
        for (int i = 0; i < size; i++) {
                vector[i] = random.nextInt(10); // Génère un entier aléatoire entre 0 et 99
        }
    }
    public String afficheVector(){
        StringBuilder vectorString = new StringBuilder("<html><table>");
        for (int i = 0; i < size; i++) {
            vectorString.append("<tr>").append(vector[i]).append("</tr>");
           
        }
        vectorString.append("</table></html>");
        return vectorString.toString(); 
    }

}
