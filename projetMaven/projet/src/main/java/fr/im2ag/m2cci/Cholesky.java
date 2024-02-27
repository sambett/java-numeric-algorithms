package fr.im2ag.m2cci;
import java.text.DecimalFormat;

public class Cholesky {
    private double[] resultat,Y;
    private MatrixClass matrix;
    private Vector vector;
    private int n;
    private String type;
    private String matL,matLT;
    private int bandWidth;
    private double[][] matriceL,matriceLT;
    public Cholesky(MatrixClass mat,Vector vect){
        matrix= mat;
        vector=vect;
        n=matrix.getSize();
        type= matrix.getType();
        resultat= new double[n];
        Y=new double[n];
        matriceL=new double[n][n];
        matriceLT=new double[n][n];
        bandWidth=mat.bandwidth;
        resoudre();
    }
    public void factoriser(){
        for(int j=0;j<n;j++){
            matriceL[j][j]=matrix.matrix[j][j];
            for(int k=0;k<=j-1;k++){
                matriceL[j][j]-=matriceL[j][k]*matriceL[j][k];
            }
            matriceL[j][j]=Math.sqrt(matriceL[j][j]);
            for(int i=j+1;i<n;i++){
                matriceL[i][j]=matrix.matrix[i][j];
                for(int k=0;k<=j-1;k++){
                    matriceL[i][j]-=matriceL[i][k]*matriceL[j][k];
                }
                matriceL[i][j]/=matriceL[j][j];
            }
            
        }
    }
    public void factoriserBande(){
        for(int j=0;j<n;j++){
            matriceL[j][j]=matrix.matrix[j][j];
            for(int k=Math.max(0, j-bandWidth);k<=j-1;k++){
                matriceL[j][j]-=matriceL[j][k]*matriceL[j][k];
            }
            matriceL[j][j]=Math.sqrt(matriceL[j][j]);
            for(int i=j+1;i<=Math.min(n-1,j+bandWidth);i++){
                matriceL[i][j]=matrix.matrix[i][j];
                int m1=Math.max(0, i-bandWidth);
                int m2=Math.max(0,j-bandWidth);
                for(int k=Math.max(m1, m2);k<=j-1;k++){
                    matriceL[i][j]-=matriceL[i][k]*matriceL[j][k];
                }
                matriceL[i][j]/=matriceL[j][j];
            }
            
        }
    }
    public void transpose(){
        matriceLT= new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j <n; j++) {
                matriceLT[i][j] = matriceL[j][i];
            }
        }
    }
    public void transposeBande(){
        matriceLT= new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j <=Math.min(n-1, i+bandWidth); j++) {
                matriceLT[j][i] = matriceL[i][j];
            }
        }
    }
    public void resoudre_matriceBande(){
        factoriserBande();
        matL=afficheMat(matriceL);
        transposeBande();
        matLT=afficheMat(matriceLT);
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = Math.max(i-bandWidth,0); j < i; j++) {
                sum += matriceL[i][j] * Y[j];
            }
            Y[i] = (vector.vector[i] - sum) / matriceL[i][i];
        }
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j <=Math.min(n-1,i+bandWidth); j++) {
                sum += matriceLT[i][j] * resultat[j];
            }
            resultat[i] = (Y[i] - sum) / matriceLT[i][i];
        }
    }
    public void resoudre_matriceDense(){
        factoriser();
        matL=afficheMat(matriceL);
        transpose();
        matLT=afficheMat(matriceLT);
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < i; j++) {
                sum += matriceL[i][j] * Y[j];
            }
            Y[i] = (vector.vector[i] - sum) / matriceL[i][i];
        }
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += matriceLT[i][j] * resultat[j];
            }
            resultat[i] = (Y[i] - sum) / matriceLT[i][i];
        }
    }
    public String getL(){
        return matL;
    }
    public String getLT(){
        return matLT;
    }
    public void resoudre(){
        switch (type) {
            case "dense":
                resoudre_matriceDense();
                break;
            case "bande":
                resoudre_matriceBande();
                break;
            default:
            resoudre_matriceDense();
            break;
        }
                
    }
    public String afficheMat(double[][] matrice) {
        StringBuilder matrixString = new StringBuilder("<html><table>");
        DecimalFormat format = new DecimalFormat("#.##");
        for (int i = 0; i < n; i++) {
            matrixString.append("<tr>");
            for (int j = 0; j < n; j++) {
                matrixString.append("<td>").append(format.format(matrice[i][j])).append("</td>");
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
