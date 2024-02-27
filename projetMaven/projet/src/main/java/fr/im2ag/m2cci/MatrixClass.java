package fr.im2ag.m2cci;
import java.text.DecimalFormat;
import java.util.Random;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.NonSquareMatrixException;
public class MatrixClass {
    private String type;
    private int size;
    int[][] matrix;
    double[][] matrixInverse,matriceTranspose;
    int bandwidth;
    public MatrixClass(){
        type = null;
        size = 0;
        matrixInverse = new double[size][size]; // Initialisation de matrixInverse
        matriceTranspose=new double[size][size]; 
    }
    
    public MatrixClass(String type,int size,int bandWidth){
        this.type=type;
        this.size=size;
        this.bandwidth=bandWidth;
        this.matrix = new int[size][size]; 
        createMatrix();
    }
    public MatrixClass(String type,int size,int[][] matrix){
        this.type=type;
        this.size=size;  
        this.matrix=matrix;
    }
    
    
    public void createMatrixTriangSup(){
        Random random = new Random();

        // Remplissage de la matrice triangulaire supérieure avec des entiers aléatoires
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                matrix[i][j] = random.nextInt(10); // Génère un entier aléatoire entre 0 et 99
            }
        }
        
    }
    public void createMatrixTriangInf(){
        Random random = new Random();

        // Remplissage de la matrice triangulaire supérieure avec des entiers aléatoires
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                matrix[i][j] = random.nextInt(10); // Génère un entier aléatoire entre 0 et 99
            }
        }
    }
    public void createMatrixDense(){
        Random random = new Random();

        // Remplissage de la matrice triangulaire supérieure avec des entiers aléatoires
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(10); // Génère un entier aléatoire entre 0 et 99
            }
        }
    }
   
    public void createSupMatrixBande() {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = i; j < Math.min(size, i + bandwidth + 1); j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
    }
    public void createInfMatrixBande(){
        Random random = new Random();
 
        for (int i = 0; i < size; i++) {
            for (int j =Math.max(0,i-bandwidth) ; j <=i ;j++ ) {
                matrix[i][j] = random.nextInt(10); 
            }
        }
    }
    public void createMatrixBande() {
        Random random = new Random();

        // Fill the matrix with random values in the band structure
        for (int i = 0; i < size; i++) {
            for (int j = Math.max(0, i - bandwidth); j <= Math.min(size - 1, i + bandwidth); j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
    }
    public void createMatrixSymetrique(){
        Random random = new Random();
        
        // Remplissage de la matrice triangulaire supérieure avec des entiers aléatoires
        for (int i = 0; i < size; i++) {
            for (int j =0; j<=i ; j++ ) {
                if(i == j){
                    matrix[i][i] = random.nextInt(10);
                }
                matrix[i][j] = random.nextInt(10); 
                matrix[j][i] = matrix[i][j];

            }
        }
    }
    // Create a positive definite matrix
     // Function to generate a positive definite matrix A = MM^T
    public void createSymmetricPositiveDefiniteMatrix() {
        Random random = new Random();
    
         // Generate a positive definite matrix 'M'
    
         for (int i = 0; i < size; i++) {
             for (int j = 0; j <= i; j++) {
                 matrix[i][j] = random.nextInt(9) + 1; // Fill lower triangular part
             }
         }
    
         // Transpose the lower triangular matrix to create symmetric matrix 'M'
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < i; j++) {
                 matrix[j][i] = matrix[i][j]; // Fill upper triangular part by transposing
             }
         }
         
         // Calculate A = M * M^T (A = matrix * transpose(matrix))
         int[][] transpose = new int[size][size];
         int[][] result = new int[size][size];
         
         // Calculating transpose of 'M'
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 transpose[i][j] = matrix[j][i];
             }
         }
         
         // Calculating A = M * M^T
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 int sum = 0;
                 for (int k = 0; k < size; k++) {
                     sum += matrix[i][k] * transpose[k][j];
                 }
                 result[i][j] = sum;
             }
         }
          // Copy the result back to the original matrix
            for (int i = 0; i < size; i++) {
                System.arraycopy(result[i], 0, matrix[i], 0, size);
            }
    }
    // Function to generate a positive definite matrix A = LL^T

  


public void createSparseMatrix(){
    Random rand = new Random();
    
    double sparsity =0.3; // Set the desired sparsity level

    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (rand.nextDouble() < sparsity) {
                matrix[i][j] = rand.nextInt(10); // Set a random non-zero value
            } else {
                matrix[i][j] = 0; // Set zero for the majority of elements
            }
        }
    }
}
public void createDiagonaleDominatMatrix() {
    Random rand = new Random();
    for (int i = 0; i < size; i++) {
        int sum = 0;
        for (int j = 0; j < size; j++) {
            matrix[i][j] = rand.nextInt(10);
            sum += matrix[i][j];
        }
        
        // Ensure the diagonal element is greater than the sum of other elements
        while (matrix[i][i] <= (sum - matrix[i][i])) {
            matrix[i][i] = rand.nextInt(10) + sum; // Increase the diagonal element
        }
    }
}

public void createMatrix(){
    switch (type) {
        case "triangsup":
            createMatrixTriangSup();
            break;
        case "trianginf":
            createMatrixTriangInf();
            break;
        case "dense":
            createMatrixDense();
            break;
        case "bande":
            createMatrixBande();
            break;
        // sam's addition
        case "bandeSup":
            createSupMatrixBande();
            break;
        case "bandeInf":
            createInfMatrixBande();
            break;
        case "symetrique":
            createMatrixSymetrique();
            break;
        case "creuse":
            createSparseMatrix();
            break;
        case "symetrique definiePositive":
            createSymmetricPositiveDefiniteMatrix();
            break;
        case "diagonaleDominat":// methode iteratives
            createDiagonaleDominatMatrix();
            break;
        case "bande definiePositive":
            createPositiveDefiniteBandedMatrix();
            break;
        case "bande Symetrique":
            createBandedSymetricMatrix();
            break;
        case "bande symetrique definiePositive":
            createBandedSymetricdiagdominantMatrix();
            break;
        default:
            break;
    }
}
public void createBandedSymetricMatrix() {
    Random random = new Random();

    // Fill the matrix with band structure and ensure symmetry
    for (int i = 0; i < size; i++) {
        matrix[i][i] = random.nextInt(10)+1;
        for (int j = i+1; j <= Math.min(size - 1, i + bandwidth); j++) {
            matrix[i][j] = random.nextInt(10)+1;
            matrix[j][i] = matrix[i][j]; // Ensure symmetry
            
        }
        
    }
}
public void createBandedSymetricdiagdominantMatrix() {
    Random random = new Random();

    // Fill the matrix with band structure and ensure symmetry
    for (int i = 0; i < size; i++) {
        for (int j = i+1; j <= Math.min(size - 1, i + bandwidth); j++) {
            matrix[i][j] = random.nextInt(10);
            matrix[j][i] = matrix[i][j]; // Ensure symmetry
        }
        for (int j =Math.max(0, i - bandwidth) ; j <= Math.min(size - 1, i + bandwidth); j++) {
            matrix[i][i] += matrix[i][j]+1;
        }
    }
}
public void createPositiveDefiniteBandedMatrix() {
    Random random = new Random();

    // Fill the class-level matrix with random values in the band structure
    for (int i = 0; i < size; i++) {
        for (int j = Math.max(0, i - bandwidth); j <= Math.min(size - 1, i + bandwidth); j++) {
            this.matrix[i][j] = random.nextInt(10);
        }
    }

    // Generate a lower triangular matrix (L)
    int[][] lowerTriangular = new int[size][size];
    for (int i = 0; i < size; i++) {
        for (int j = 0; j <= i; j++) {
            lowerTriangular[i][j] = this.matrix[i][j];
        }
    }

    // Transpose the lower triangular matrix (transpose of L)
    int[][] transpose = new int[size][size];
    for (int i = 0; i < size; i++) {
        for (int j = 0; j <= i; j++) {
            transpose[i][j] = lowerTriangular[j][i];
        }
    }

    // Multiply the lower triangular matrix with its transpose (L * L^T)
    int[][] result = new int[size][size];
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            int sum = 0;
            for (int k = 0; k <= Math.min(i, j); k++) {
                sum += lowerTriangular[i][k] * transpose[k][j];
            }
            result[i][j] = sum;
        }
    }

    // Copy the result back to the original class-level matrix
    for (int i = 0; i < size; i++) {
        System.arraycopy(result[i], 0, this.matrix[i], 0, size);
    }
}
    

    public String getType(){
        return type;
    }
    public void setType(String typ){
        this.type=typ;
    }
    public void setSize(int s){
        this.size=s;
    }
    public int getSize(){
        return size;
    }
    public void setBandWith(int a){
        bandwidth=a;
    }
    public int[][] getMatrix(){
        return matrix;
    }
    public String afficheMatrix() {
        StringBuilder matrixString = new StringBuilder("<html><table>");
        for (int i = 0; i < size; i++) {
            matrixString.append("<tr>");
            for (int j = 0; j < size; j++) {
                matrixString.append("<td>").append(matrix[i][j]).append("</td>");
            }
            matrixString.append("</tr>");
        }
        matrixString.append("</table></html>");
        return matrixString.toString();
    }
    public int[][] generateIdentiteMatrix(){
        int[][] matrixIdentite=new int[size][size];
        for(int i=0; i<size;i++){
            matrixIdentite[i][i]=1;
        }
        return matrixIdentite;
    }
    public double[][] concatMatrixWithIdentite(){
        double[][] concatMatrix=new double[size][2*size];
        int[][] matrixIdentite=generateIdentiteMatrix();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                concatMatrix[i][j]=matrix[i][j];
            }
            for (int j = size; j < (2*size); j++) {
                concatMatrix[i][j]=matrixIdentite[i][j-size];
            }
        }
        return concatMatrix;
    }
    //Gauss Jordon avec l'identité pour trouver l'inverse
    public double[][] inverse() {
            double[][] augmentedMatrix = concatMatrixWithIdentite();
            int augmentedSize = 2 * size;

            // Implémentation de Gauss-Jordan avec l'identité pour trouver l'inverse
            for (int k = 0; k < size; k++) {
                for (int j = k + 1; j < augmentedSize; j++) {
                    augmentedMatrix[k][j] /= augmentedMatrix[k][k];
                }
                for (int i = 0; i < k; i++) {
                    for (int j = k + 1; j < augmentedSize; j++) {
                            augmentedMatrix[i][j] -= augmentedMatrix[i][k] * augmentedMatrix[k][j];
                    }
                }//diviser la boucle
                for (int i = k+1; i < size; i++) {
                    for (int j = k + 1; j < augmentedSize; j++) {
                            augmentedMatrix[i][j] -= augmentedMatrix[i][k] * augmentedMatrix[k][j];
                    }
                }
            }
             matrixInverse = new double[size][size]; 
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrixInverse[i][j] = augmentedMatrix[i][size + j];
                }
            }
            return matrixInverse;

    }
    public double[][] transpose(){
        matriceTranspose= new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriceTranspose[i][j] = matrix[j][i];
            }
        }
        return matriceTranspose;
    }
    
    public String afficheTranspose() {
        StringBuilder matrixString = new StringBuilder("<html><table>");
        DecimalFormat format = new DecimalFormat("#.##");
        for (int i = 0; i < size; i++) {
            matrixString.append("<tr>");
            for (int j = 0; j < size; j++) {
                    matrixString.append("<td>").append(format.format(matriceTranspose[i][j])).append("</td>");
            }
            matrixString.append("</tr>");
        }
        matrixString.append("</table></html>");
        return matrixString.toString();
    }
    public String afficheInverse() {
        StringBuilder matrixString = new StringBuilder("<html><table>");
        DecimalFormat format = new DecimalFormat("#.##");
        for (int i = 0; i < size; i++) {
            matrixString.append("<tr>");
            for (int j = 0; j < size; j++) {
                    matrixString.append("<td>").append(format.format(matrixInverse[i][j])).append("</td>");
            }
            matrixString.append("</tr>");
        }
        matrixString.append("</table></html>");
        return matrixString.toString();
    }
    public static void main(String[] args) {
        // Exemple d'une matrice 3x3
        double[][] matrixData = {
            {4, 1, -2},
            {1, 10, 3},
            {-2, 3, 6}
        };

        // Création de la matrice
        Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(matrixData);

        // Vérification de la symétrie
        boolean isSymmetric = isSymmetric(matrix);

        // Vérification de la définie positivité
        boolean isDefinitePositive = isPositiveDefinite(matrix);

        System.out.println("La matrice est symétrique : " + isSymmetric);
        System.out.println("La matrice est définie positive : " + isDefinitePositive);
    }

    // Vérifier si la matrice est symétrique
    public static boolean isSymmetric(Array2DRowRealMatrix matrix) {
        return matrix.transpose().equals(matrix);
    }

    // Vérifier si la matrice est définie positive
    public static boolean isPositiveDefinite(Array2DRowRealMatrix matrix) {
        try {
            LUDecomposition decomposition = new LUDecomposition(matrix);
            return decomposition.getSolver().isNonSingular() && isSymmetric(matrix);
        } catch (NonSquareMatrixException e) {
            return false;
        }
    }
}


