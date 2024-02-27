package fr.im2ag.m2cci;
import java.text.DecimalFormat;

public class ResolutionJacobi {
    private MatrixClass matrix;
    private Vector vecteur;
    private double[] resultat;
    private int n;
    private double Epsilone;

    public ResolutionJacobi(MatrixClass mat, Vector vect, double tolerance) {
        this.matrix = mat;
        this.vecteur = vect;
        this.n = matrix.getSize();
        this.resultat = new double[n];
        this.Epsilone = tolerance;
        resoudre_Jacobi();
    }

    public void resoudre_Jacobi() {
        
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            resultat[i] = 0; // Initialisation des valeurs de x à 0
            y[i] = 0; // Initialisation des valeurs de y à 0
        }

        double maxDiff;
        do {
            maxDiff = 0.0;

            for (int i = 0; i < n; i++) {
                double s = vecteur.vector[i];

                for (int j = 0; j < i; j++) {
                        s -= matrix.matrix[i][j] * resultat[j];
                }
                for(int j = i+1 ; j<n ; j++ ) {
                	s -= matrix.matrix[i][j] * resultat[j];
                }

                y[i] = s / matrix.matrix[i][i];

                double diff = Math.abs(resultat[i] - y[i]);
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }

            // Mettre à jour les valeurs de x avec les valeurs de y pour la prochaine itération
            System.arraycopy(y, 0, resultat, 0, n);

        } while (maxDiff > Epsilone);

        afficheSolution();
    }

    // La méthode concatenerMatriceEtVecteur reste la même que celle dans la classe ResolutionGaussSeidelDense

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
