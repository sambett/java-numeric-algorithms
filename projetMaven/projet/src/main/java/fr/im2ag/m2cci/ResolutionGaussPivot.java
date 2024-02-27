package fr.im2ag.m2cci;
import java.text.DecimalFormat;

public class ResolutionGaussPivot {
    private double[] resultat;
    private MatrixClass matrix;
    private Vector vector;
    private int n;
    private String type;
    private String mat;
    
    public ResolutionGaussPivot(MatrixClass mat,Vector vect){
        matrix= mat;
        vector=vect;
        n=matrix.getSize();
        type= matrix.getType();
        resultat= new double[n];
        resolution();
    }
    public void resoudre_MatrixDense(){
        double[][] matrice=concatenerMatriceEtVecteur();
        triangularisation(matrice);
        mat = afficheMatTriang(matrice);
        resoudre_MatrixTriangSup(matrice);
       
    }
    public void resoudre_MatrixTriangSup(double[][] matrice) {
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += matrice[i][j] * resultat[j];
            }
            resultat[i] = (matrice[i][n] - sum) / matrice[i][i];
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
    public void triangularisation(double[][] matrice) {
    
        for (int p = 0; p < n - 1; p++) {
            int maxIndex = p;
            double maxValue = Math.abs(matrice[p][p]);
    
            // Recherche du pivot partiel
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(matrice[i][p]) > maxValue) {
                    maxValue = Math.abs(matrice[i][p]);
                    maxIndex = i;
                }
            }
    
            // Échange des lignes pour mettre le pivot en position
            if (maxIndex != p) {
                for (int j = p; j < n+1; j++) {
                    double temp = matrice[p][j];
                    matrice[p][j] = matrice[maxIndex][j];
                    matrice[maxIndex][j] = temp;
                }
            }
    
            for (int i = p + 1; i < n; i++) {
                matrice[i][p] = matrice[i][p] / matrice[p][p];
                for (int j = p+1; j < n+1; j++) {
                    matrice[i][j] -= matrice[i][p] * matrice[p][j];
                }
            }
        }
    }
    public String afficheMatTriang(double[][] matrice) {
        StringBuilder matrixString = new StringBuilder("<html><table>");
        DecimalFormat format = new DecimalFormat("#.##");
        for (int i = 0; i < n; i++) {
            matrixString.append("<tr>");
            for (int j = 0; j < n; j++) {
                if(j>=i){
                    matrixString.append("<td>").append(format.format(matrice[i][j])).append("</td>");
                }else{matrixString.append("<td>").append(0).append("</td>");}
            }
            matrixString.append("</tr>");
        }
        matrixString.append("</table></html>");
        return matrixString.toString();
    }
    public void resolution(){
        switch (type) {
            case "dense":
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
    public String getMatrix() {
        return mat;
    }
}
