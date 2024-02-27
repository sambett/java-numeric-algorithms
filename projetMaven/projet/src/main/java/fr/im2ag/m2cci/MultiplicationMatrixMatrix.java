package fr.im2ag.m2cci;
import java.text.DecimalFormat;

public class MultiplicationMatrixMatrix {

    private int[][] resultat;
    private MatrixClass matrix1;
    private int bandWidth1,bandwith2;
    private MatrixClass matrix2;
    private int n;
    private String type1;
    private String type2;
    double[][] matrice ,matriceResult;
    //constructeur pour la multiplication matrice * matrice
    public MultiplicationMatrixMatrix(MatrixClass mat1,MatrixClass mat2){
        matrix1= mat1;
        matrix2=mat2;
        n=matrix1.getSize();
        type1= matrix1.getType();
        type2= matrix2.getType();
        bandWidth1=mat1.bandwidth;
        bandwith2=mat2.bandwidth;
        resultat= new int[n][n];
        multiplication();
    }
    //constructeur pour la multiplication matrice * son inverse
    public MultiplicationMatrixMatrix(MatrixClass mat1,double[][] mat){
        matrix1= mat1;
        n=matrix1.getSize();
        type1=mat1.getType();
        bandWidth1=mat1.bandwidth;
        matrice=mat;
        matriceResult=new double[n][n];
        multiplicationMatriceInverse();

    }
    public MultiplicationMatrixMatrix(MatrixClass mat1){
        matrix1= mat1;
        n=matrix1.getSize();
        type1=mat1.getType();
        bandWidth1=mat1.bandwidth;
        matriceResult=new double[n][n];
        multiplicationMatricetranspose();

    }

        //methode de multiplication matrice * inverse
            //multiplication matrice bande matrice inverse
            private void multiplicationMatriceBandInverse() {
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        for(int k=Math.max(0, i-bandWidth1);k<=Math.min(n-1, i+bandWidth1);k++){
                            matriceResult[i][j]+=matrix1.matrix[i][k]*matrice[k][j];
                        }
                    }
                }
            }
            //multiplication matrice dense matrice inverse
            private void multiplicationMatriceDenseInverse() {
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        for(int k=0;k<n;k++){
                            matriceResult[i][j]+=matrix1.matrix[i][k]*matrice[k][j];
                        }
                    }
                }
            }
            public void multiplicationMatriceInverse(){
                switch (type1) {
                    case "bande":
                        multiplicationMatriceBandInverse();
                        break;
                    default:
                        multiplicationMatriceDenseInverse();
                        break;
                }
            }
        //méthode du multiplication matrice * transposé
            public void multiplicationMatricetranspose(){
                switch (type1) {
                    case "bande":
                        multiplicationMatriceBandtranspose();
                        break;
                    default:
                        multiplicationMatriceDenseTranspose();
                        break;
                }
            }
            private void multiplicationMatriceDenseTranspose() {
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        for(int k=0;k<n;k++){
                            matriceResult[i][j]+=matrix1.matrix[i][k]*matrix1.matrix[j][k];
                        }
                    }
                }
            }
            public void multiplicationMatriceBandtranspose(){
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int s1=Math.max(0, i-bandWidth1);
                        int s2=Math.max(0, j-bandWidth1);
                        int e1=Math.min(i + bandWidth1,n-1);
                        int e2=Math.min(j + bandWidth1,n-1);
                        for(int k=Math.max(s1,s2);k<= Math.min(e1,e2);k++){
                            matriceResult[i][j] +=matrix1.matrix[i][k]*matrix1.matrix[j][k];
                        }
                    }
                }
            }

    public void multipMatrixTriangSup_MatrixTriangSup(){
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for(int k=i;k<=j;k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixTriangInf_MatrixTrianginf(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                for(int k=j;k<=i;k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixTriangSup_MatrixDense(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for(int k=0;k<n;k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixTrianginf_MatrixDense(){
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for(int k=0;k<=i;k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixDense_MatrixDense(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for(int k=0;k<n;k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixTriangSup_MatrixTrianginf(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for(int k=(i > j) ? i : j; k <n; k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixTriangInf_MatrixTriangSup(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for(int k=0; k <=((i > j) ? j : i); k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixDense_MatrixTriangSup(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for(int k=0;k<=j;k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixDense_MatrixTriangInf(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for(int k=j;k<n;k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixbande_MatrixBandeInf(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int s=Math.max(0, i-bandWidth1);
                int e=Math.min(i + bandWidth1,n-1);
                for(int k=Math.max(s,j);k<= Math.min(e,j+bandwith2);k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    public void multipMatrixbandeSup_MatrixBandeInf(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int end1=Math.min(i + bandWidth1,n-1);
                int end2=Math.min(j + bandwith2,n-1);
                for(int k=Math.max(i,j);k<= Math.min(end1,end2);k++){
                    resultat[i][j] +=matrix1.matrix[i][k]*matrix2.matrix[k][j];
                }
            }
        }
    }
    

    public void multiplication(){
        switch (type1) {
            case "triangsup":
                switch (type2) {
                    case "triangsup":
                        multipMatrixTriangSup_MatrixTriangSup();
                        break;
                    case "trianginf":
                        multipMatrixTriangSup_MatrixTrianginf();
                        break;
                    case "dense":
                        multipMatrixTriangSup_MatrixDense();
                        break;
                
                    default:
                        multipMatrixDense_MatrixDense();
                        break;
                }
                break;
            case "trianginf":
                switch (type2) {
                    case "triangsup":
                        multipMatrixTriangInf_MatrixTriangSup();
                        break;
                    case "trianginf":
                        multipMatrixTriangInf_MatrixTrianginf();
                        break;
                    case "dense":
                        multipMatrixTrianginf_MatrixDense();
                        break;
                    default:
                        multipMatrixDense_MatrixDense();
                        break;
                }
                break;
            case "dense":
                switch (type2) {
                    case "triangsup":
                        multipMatrixDense_MatrixTriangSup();
                        break;
                    case "trianginf":
                        multipMatrixDense_MatrixTriangInf();
                        break;
                    case "dense":
                        multipMatrixDense_MatrixDense();
                        break;
                    default:
                        multipMatrixDense_MatrixDense();
                        break;
                }
                break;
            case "bande":
                switch (type2) {
                    case "bandeInf":
                        multipMatrixbande_MatrixBandeInf();
                        break;
                    default:
                        multipMatrixDense_MatrixDense();
                        break;
                }
                break;
            case "bandeSup":
                switch (type2) {
                    case "bandeInf":
                        multipMatrixbandeSup_MatrixBandeInf();
                        break;
                    default:
                        multipMatrixDense_MatrixDense();
                        break;
                }
                break;
            default:
                multipMatrixDense_MatrixDense();
                break;
        }
    }
    public String afficheMatrix() {
        StringBuilder matrixString = new StringBuilder("<html><table>");
        for (int i = 0; i < n; i++) {
            matrixString.append("<tr>");
            for (int j = 0; j < n; j++) {
                matrixString.append("<td>").append(resultat[i][j]).append("</td>");
            }
            matrixString.append("</tr>");
        }
        matrixString.append("</table></html>");
        return matrixString.toString();
    }
     public String affichemultip() {
        StringBuilder matrixString = new StringBuilder("<html><table>");
        DecimalFormat format = new DecimalFormat("#.##");
        for (int i = 0; i < n; i++) {
            matrixString.append("<tr>");
            for (int j = 0; j < n; j++) {
                matrixString.append("<td>").append(format.format(matriceResult[i][j])).append("</td>");
            }
            matrixString.append("</tr>");
        }
        matrixString.append("</table></html>");
        return matrixString.toString();
    }

}