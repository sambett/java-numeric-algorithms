package fr.im2ag.m2cci;
import java.text.DecimalFormat;

public class ResolutionGaussSeidel {
    private MatrixClass matrix;
    private Vector vecteur ; 
    private double[] resultat;
    private int n;
    private double Epsilone;

    public ResolutionGaussSeidel(MatrixClass mat,Vector vect , double tolerance) {
    	 this.matrix= mat;
         this.vecteur = vect ; 
        this.n = matrix.getSize();
        this.resultat = new double[n];
        this.Epsilone= tolerance;
        resoudre_GaussSeidel();
    }
    
    public double[][] concatenerMatriceEtVecteur() {
        // Créer une nouvelle matrice avec une colonne supplémentaire pour le vecteur
        double[][] matriceEtVecteur = new double[n][n + 1];

        // Copier la matrice d'origine dans la nouvelle matrice
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriceEtVecteur[i][j] = matrix.matrix[i][j];
            }
        }

        // Copier le vecteur dans la dernière colonne de la nouvelle matrice
        for (int i = 0; i < n; i++) {
            matriceEtVecteur[i][n] = vecteur.vector[i];
        }
        return matriceEtVecteur;
    }
    public void resoudre_GaussSeidel() {
    	double[][] matriceEtVecteur = concatenerMatriceEtVecteur();
        double maxDiff;


        for (int i = 0; i < n; i++) {
        	resultat[i] = 0; //Initialisation des valeurs de x à 0
        }

        do {
            maxDiff = 0.0;

            for (int i = 0; i < n; i++) {
                double s = 0.0;

                for (int j = 0; j < i ; j++) {
                    s += matriceEtVecteur[i][j] * resultat[j];
                }
                for(int j  = i+1 ; j<n ; j++) {
                    s += matriceEtVecteur[i][j] * resultat[j];
                }

                double oldX = resultat[i];
                resultat[i] = (matriceEtVecteur[i][n] - s) / matriceEtVecteur[i][i];

                double diff = Math.abs(resultat[i] - oldX);
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }

        } while (maxDiff > Epsilone);
        afficheSolution() ; 

 }

    public String afficheSolution() {
        DecimalFormat format = new DecimalFormat("#.######");;
        StringBuilder vectorString = new StringBuilder("<html><table>");
        for (int i = 0; i < n; i++) {
            String chaine = format.format(resultat[i]);
            vectorString.append("<tr>").append(chaine).append("</tr>");
           
        }
        vectorString.append("</table></html>");
        return vectorString.toString(); 
    }


}