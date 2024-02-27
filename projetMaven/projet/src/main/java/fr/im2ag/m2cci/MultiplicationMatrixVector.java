package fr.im2ag.m2cci;
public class MultiplicationMatrixVector {
    private int[] resultat;
    private MatrixClass matrix;
    private Vector vector;
    private int n;
    private String type;
    private int bandwidth;
    public MultiplicationMatrixVector(MatrixClass mat,Vector vect){
        matrix= mat;
        vector=vect;
        n=matrix.getSize();
        bandwidth=mat.bandwidth;
        type= matrix.getType();
        resultat= new int[n];
        multiplication();
    }
    public void multipMatrixDense(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultat[i] +=matrix.matrix[i][j]*vector.vector[j];
            }
        }
    }
    public void multipMatrixTriangSup(){
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                resultat[i] +=matrix.matrix[i][j]*vector.vector[j];
            }
        }
    }
    public void multipMatrixTriangInf(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                resultat[i] +=matrix.matrix[i][j]*vector.vector[j];
            }
        }
    }
    public void multipMatrixBande(){
        for (int i = 0; i < n; i++) {
            for (int j =Math.max(0, i - bandwidth); j <=Math.min(n-1, i + bandwidth); j++) {
                resultat[i] +=matrix.matrix[i][j]*vector.vector[j];
            }
        }
    }
    
     public void multipMatrixbandeInf(){
        for (int i = 0; i < n; i++) {
            for (int j = Math.max(0, i - bandwidth); j <= i; j++) {
                resultat[i] += matrix.matrix[i][j] * vector.vector[j];
            }
        }
    }
    public void multipMatrixbandeSup() {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < Math.min(n, i + bandwidth); j++) {
                resultat[i] += matrix.matrix[i][j] * vector.vector[j];
            }
        }
    }
    public void multipMatrixSymetrique() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i - 1; j++) {
                resultat[i] += matrix.matrix[i][j] * vector.vector[j];
            }
            // diagonale aside
            resultat[i] += matrix.matrix[i][i] * vector.vector[i];
    
            for (int j = i + 1; j < n; j++) {
                resultat[i] += matrix.matrix[i][j] * vector.vector[j];
            }
        }
    }

    public void multiplication(){
        switch (type) {
            case "triangsup":
                multipMatrixTriangSup();
                break;
            case "trianginf":
                multipMatrixTriangInf();
                break;
            case "dense":
                multipMatrixDense();
                break;
                case "bande":
                multipMatrixBande();
                break;
            case "bandeSup":
                multipMatrixbandeSup();     
                break;
            case "bandeInf":
                multipMatrixbandeInf();
                break;
            case "symetrique":
                multipMatrixSymetrique();
                break;
            case "symetrique definiePositive":
                multipMatrixSymetrique();
                break;
            default:
                multipMatrixDense();
                break;
        }
    }
    public String afficheResult(){
        StringBuilder vectorString = new StringBuilder("<html><table>");
        for (int i = 0; i < n; i++) {
            vectorString.append("<tr>").append(resultat[i]).append("</tr>");
           
        }
        vectorString.append("</table></html>");
        return vectorString.toString(); 
    }

}
