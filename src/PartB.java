//Plamen Velkov

public class PartB {

    //Method to merge the given Matrix (A) in main with the vector (b) in order to alter both at the same time using the
    //method gaussElimination
    public static void mergeMV (double[][] matrix, double[] b){
        for(int i = 0; i < matrix.length; i++){
            matrix[i] = push(matrix[i], b[i]);
        }
    }

    //Helper method that is used to create a new array of a bigger size to be used whenever we need to merge the to
    //matrices (above)
    private static double[] push(double[] array, double push) {
        double[] longer = new double[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = push;
        return longer;
    }

    //Method for Gauss elimination algorithm for altering the matrix to create the upper triangular system.
    //This method uses pivoting points.
    public static void gaussElimination(double[][] matrix) {
        for (int k = 0; k < matrix[0].length; k++){
            int p = pivotPoint(matrix, k);

            double[] tmp;
            if (p != k){
                tmp = matrix[k];
                matrix[k] = matrix[p];
                matrix[p] = tmp;
            }

            for (int i = k + 1; i< matrix.length; i++){
                if(matrix[k][k] == 0) return;
                for (int j = k +1; j < matrix[0].length; j++){
                    matrix[i][j] -= matrix[k][j] * (matrix[i][k] / matrix[k][k]);
                }
                matrix[i][k] = 0;
                steps(matrix);
            }
        }
        System.out.println("============================");
    }

    //Method to determine pivot point in the combined matrix (Ab)
    public static int pivotPoint(double[][] matrix, int pos){
        int pivotPoint = pos;
        for (int i = pos + 1; i < matrix.length; i++) {
            if (Math.abs(matrix[i][pos]) > Math.abs(matrix[pivotPoint][pos])) {
                pivotPoint = i;
            }
        }
        return pivotPoint;
    }

    //Method to access array values and to print them out neatly and separated lhs and rhs (Ab)
    public static void steps(double[][] matrix){
        System.out.println("============================");
        for (double[] doubles : matrix) {
            System.out.print("| ");
            for (int j = 0; j < doubles.length - 1; j++) {
                System.out.print(doubles[j] + " ");
            }
            for (int i = doubles.length - 1; i < doubles.length; i++) {
                System.out.print("| " + doubles[i] + " |");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        //creating matrices A and b
        double[][] A={{1, 2, 1, -1},
                {3, 2, 4, 4},
                {4, 4, 3, 4},
                {2, 0, 1, 5}
        };

        double[] b = { 5, 16, 22, 15 };

        mergeMV(A, b);

        //INPUT PRINT
        System.out.println("============================");
        System.out.println("----------MATRIX(A)---------");
        for (double[] doubles : A) {
            System.out.print("| ");
            for (int j = 0; j < doubles.length - 1; j++) {
                System.out.print(doubles[j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("----------VECTOR(b)---------");
        for(int i = 0; i<b.length; i++){
            System.out.println("| " + b[i] + " |");
        }
        System.out.println("============================");

        System.out.println("---------ITERATIONS---------");
        gaussElimination(A);

        //transformation logic to apply substitution to the matrix
        for (int i = A.length - 1; i >= 0; i--) {
            double modifier = A[i][A.length] / A[i][i];
            for (int j = i - 1; j >= 0; j--) {
                A[j][A.length] -= A[j][i] * modifier;
                A[j][i] = 0;
            }

            A[i][A.length] = modifier;
            A[i][i] = 1;
        }

        double[] answer = new double[A[0].length];

        //get the last element of the combined matrix in order to print out the answers of xs (1-4)
        for (int i = 0; i < A.length; i++)
            answer[i] = A[i][A[0].length-1];

        System.out.println("-----------ANSWERS-----------");
        for (int i = 0; i<answer.length-1; i++)
            System.out.print(" x" + (i+1) +" = " + answer[i] + " ,");
    }
}
