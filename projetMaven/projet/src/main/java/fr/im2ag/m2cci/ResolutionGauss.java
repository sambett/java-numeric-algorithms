package fr.im2ag.m2cci;
import java.text.DecimalFormat;

public class ResolutionGauss {
    private double[] resultat;
    private MatrixClass matrix;
    private Vector vector;
    private int n;
    private String type;
    private String mat;
    private int bandWidth;
    
    public ResolutionGauss(MatrixClass mat,Vector vect){
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
        triangularisation(matrice);
        mat = afficheMatTriang(matrice);
        resoudre_MatrixTriangSup1(matrice);
       
    }
    public void resoudre_MatrixTrianSup(){
        double[][] matrice=concatenerMatriceEtVecteur();
        resoudre_MatrixTriangSup1(matrice);
    }
    public void resoudre_MatrixTriangInf(){
        double[][] matrice=concatenerMatriceEtVecteur();
        resoudre_MatrixTriangInf1(matrice);
    }
    public void resoudre_MatrixBandeSup() {
        double[][] matrice = concatenerMatriceEtVecteur();
        resoudre_MatrixTriangBand(matrice); // Back-substitution for upper banded matrix
    }
    public void resoudre_MatrixBande() {
        double[][] matrice = concatenerMatriceEtVecteur();
        triangularisationBande(matrice); // Gaussian elimination for banded matrix
        mat = afficheMatTriang(matrice);
        resoudre_MatrixTriangBand(matrice); // Back-substitution for banded matrix
    }
    public void resoudre_MatrixBandeInf() {
        double[][] matrice = concatenerMatriceEtVecteur();
        resoudre_MatrixTriangBandInf(matrice); // Back-substitution for lower banded matrix
    }
    public void resoudre_MatrixSymetrique() {
        double[][] matrice=concatenerMatriceEtVecteur();
        triangularisationDenseSym(matrice);
        mat = afficheMatTriang(matrice);
        resoudre_MatrixTriangSup1(matrice);
        
    }
    public void resoudre_MatrixBandeSymetrique() {
        double[][] matrice=concatenerMatriceEtVecteur();
        triangularisationBandeSymetriq(matrice);
        mat = afficheMatTriang(matrice);
        resoudre_MatrixTriangSup1(matrice);
        
    }

    //determination du vecteur
        public void resoudre_MatrixTriangSup1(double[][] matrice) {
            for (int i = n - 1; i >= 0; i--) {
                double sum = 0.0;
                for (int j = i + 1; j < n; j++) {
                    sum += matrice[i][j] * resultat[j];
                }
                resultat[i] = (matrice[i][n] - sum) / matrice[i][i];
            }
        }
        
    public String getMatrix(){return mat;} 
    public void resoudre_MatrixTriangInf1(double[][] matrice) {
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < i; j++) {
                sum += matrice[i][j] * resultat[j];
            }
            resultat[i] = (matrice[i][n] - sum) / matrice[i][i];
        }
    }
  
    public void triangularisation(double[][] matrice) {
        for (int p = 0; p < n - 1; p++) {
            for (int i = p + 1;i<n; i++) {
                matrice[i][p] = matrice[i][p] / matrice[p][p];
                for (int j = p + 1; j <= n; j++) {
                    matrice[i][j] -= matrice[i][p] * matrice[p][j];
                }
            }
        }
    }
    
    
    public void triangularisationBande(double[][] matrice) { // You can set your specific bandwidth here
        for (int p = 0; p < n - 1; p++) {
            for (int i = p + 1;i<n; i++) {
                matrice[i][p] = matrice[i][p] / matrice[p][p];
                for (int j = p + 1; j <= Math.min(n-1, i+bandWidth); j++) {
                    matrice[i][j] -= matrice[i][p] * matrice[p][j];
                }
                 matrice[i][n] -= matrice[i][p] * matrice[p][n];
            }
        }
    }

    // Function to perform specialized solving for a banded matrix
    public void resoudre_MatrixTriangBand(double[][] matrice) {// You can set your specific bandwidth here
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j  <= Math.min(n-1, i+bandWidth); j++) {
                sum += matrice[i][j] * resultat[j];
            }
            resultat[i] = (matrice[i][n] - sum) / matrice[i][i];
        }
    }

    
    // Function to perform specialized solving for a lower banded matrix
    public void resoudre_MatrixTriangBandInf(double[][] matrice) {
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = Math.max(0, i-bandWidth); j < i; j++) {
                sum += matrice[i][j] * resultat[j];
            }
            resultat[i] = (matrice[i][n] - sum) / matrice[i][i];
        }
    }
     // Function to perform Gaussian elimination for a symmetric matrix
     
    public void triangularisationBandeSymetriq(double[][] matrice) { // You can set your specific bandwidth here
        for (int p = 0; p < n - 1; p++) {
            for (int i = p + 1;i<n; i++) {
                double a = matrice[p][i] / matrice[p][p];
                for (int j = i; j <= Math.min(n-1, i+bandWidth); j++) {
                    matrice[i][j] -= a * matrice[p][j];
                }
                 matrice[i][n] -= a * matrice[p][n];
            }
        }
    }

    public void triangularisationDenseSym(double[][] matrice) {
        for (int p = 0; p < n - 1; p++) {
            for (int i = p + 1;i<n; i++) {
                double a = matrice[p][i] / matrice[p][p];
                for (int j = i; j <= n; j++) {
                    matrice[i][j] -= a * matrice[p][j];
                }
            }
        }
    }

    public void resolution(){
        switch (type) {
            case "triangsup":
                resoudre_MatrixTrianSup();
                break;
            case "trianginf":
                resoudre_MatrixTriangInf();
                break;
            case "dense":
                resoudre_MatrixDense();
                break;
            case "bande":
                resoudre_MatrixBande();
                break;
            case "bandeInf":
                resoudre_MatrixBandeInf();
                break;
            case "bandeSup":
                resoudre_MatrixBandeSup();
                break;
            case "symetrique":
                resoudre_MatrixSymetrique();
                break;
            case "bande Symetrique":
                resoudre_MatrixBandeSymetrique();
                break;
            case "bande symetrique definiePositive":
                resoudre_MatrixBandeSymetrique();
                break;
            default:
            resoudre_MatrixDense();
                break;
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
