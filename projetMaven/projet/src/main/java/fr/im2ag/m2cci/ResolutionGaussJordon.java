package fr.im2ag.m2cci;
import java.text.DecimalFormat;

public class ResolutionGaussJordon {
    private double[] resultat;
    private MatrixClass matrix;
    private Vector vector;
    private int n;
    private String type;
    private int bandWidth;
    
    public ResolutionGaussJordon(MatrixClass mat,Vector vect){
        matrix= mat;
        vector=vect;
        n=matrix.getSize();
        type= matrix.getType();
        resultat= new double[n];
        bandWidth=mat.bandwidth;
        resolution();
    }
    public void resoudre_MatrixDense(){
        double[][] matrice=concatenerMatriceEtVecteur();
        diagonalisationDense(matrice);
    }
    public void resoudre_MatrixBande() {
        double[][] matrice = concatenerMatriceEtVecteur();
        diagonalisationBande(matrice); 
    }
    public void diagonalisationDense(double[][] matrice){
        for (int k = 0; k < n; k++) {
            for (int i = k+1; i < n+1; i++) {
                matrice[k][i] /= matrice[k][k];
            }
            for (int i =0; i < k; i++) {
                for (int j = k + 1; j < n+1; j++) {
                        matrice[i][j] -= matrice[i][k] * matrice[k][j];
                }
            }//diviser la boucle
            for (int i = k+1; i < n; i++) {
                for (int j = k + 1; j < n+1; j++) {
                        matrice[i][j] -= matrice[i][k] * matrice[k][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            resultat[i]=matrice[i][n] ;
        }
    }
    //diagonalisation bande
    public void diagonalisationBande(double[][] matrice){
      for (int k = 0; k < n; k++) {
            for (int i = k+1; i <=n; i++) {
                matrice[k][i] /= matrice[k][k];
            }
            for (int i =0; i < k; i++) {
                for (int j = k + 1; j <=Math.min(i+bandWidth, n-1); j++) {
                        matrice[i][j] -= matrice[i][k] * matrice[k][j];
                }
                matrice[i][n] -= matrice[i][k] * matrice[k][n];
            }//diviser la boucle
            for (int i = k+1; i < n; i++) {
                for (int j = k + 1; j <=Math.min(i+bandWidth, n-1); j++) {
                        matrice[i][j] -= matrice[i][k] * matrice[k][j];
                }
                matrice[i][n] -= matrice[i][k] * matrice[k][n];
            }
        }
        for (int i = 0; i < n; i++) {
            resultat[i]=matrice[i][n] ;
        }
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
            matriceEtVecteur[i][n] = vector.vector[i];
        }
        return matriceEtVecteur;
    }
    public void resolution(){
        switch (type) {
            case "bande symetrique definiePositive":
                resoudre_MatrixBande();
                break;
            case "symetrique definiePositive":
                resoudre_MatrixDense();
                break;
            default:
                resoudre_MatrixDense();
                break;
        }
    }
    public String afficheVector(){
        DecimalFormat format = new DecimalFormat("#.##");;
        StringBuilder vectorString = new StringBuilder("<html><table>");
        for (int i = 0; i < n; i++) {
            String chaine = format.format(resultat[i]);
            vectorString.append("<tr>").append(chaine).append("</tr>");
           
        }
        vectorString.append("</table></html>");
        return vectorString.toString(); 
    }
    
}
