import java.util.Arrays;

/**
 * Solves linear programs of the form maximize c^T x subject to Ax <= b and x >= 0 using the simplex
 * algorithm.
 *
 * <p>The implementation follows the textbook tableau method and automatically introduces slack
 * variables to obtain an initial basic feasible solution. It is intended for classroom-sized
 * problems, so the implementation favors clarity over advanced pivoting rules.</p>
 */
public class LinearProgrammingSimplex {

    private static final double EPS = 1e-9;

    private final int constraints;
    private final int variables;
    private final double[][] tableau;
    private final int[] basis;

    /**
     * Constructs a simplex solver.
     *
     * @param A constraint matrix where each row corresponds to an inequality of the form
     *     A[i] * x <= b[i]
     * @param b right-hand side vector
     * @param c objective function coefficients for maximize c^T x
     */
    public LinearProgrammingSimplex(double[][] A, double[] b, double[] c) {
        this.constraints = b.length;
        this.variables = c.length;
        int width = variables + constraints + 1;
        this.tableau = new double[constraints + 1][width];
        this.basis = new int[constraints];

        for (int i = 0; i < constraints; i++) {
            if (A[i].length != variables) {
                throw new IllegalArgumentException("Row " + i + " has incorrect length");
            }
            System.arraycopy(A[i], 0, tableau[i], 0, variables);
            tableau[i][variables + i] = 1.0; // slack variable
            if (b[i] < 0) {
                throw new IllegalArgumentException("Right-hand side must be non-negative");
            }
            tableau[i][width - 1] = b[i];
            basis[i] = variables + i;
        }

        for (int j = 0; j < variables; j++) {
            tableau[constraints][j] = -c[j];
        }

        solve();
    }

    private void solve() {
        while (true) {
            int entering = enteringColumn();
            if (entering == -1) {
                break; // optimal reached
            }
            int leaving = leavingRow(entering);
            if (leaving == -1) {
                throw new IllegalStateException("Linear program is unbounded");
            }
            pivot(leaving, entering);
            basis[leaving] = entering;
        }
    }

    private int enteringColumn() {
        int width = tableau[0].length;
        for (int j = 0; j < width - 1; j++) {
            if (tableau[constraints][j] > EPS) {
                return j;
            }
        }
        return -1;
    }

    private int leavingRow(int entering) {
        double bestRatio = Double.POSITIVE_INFINITY;
        int row = -1;
        for (int i = 0; i < constraints; i++) {
            if (tableau[i][entering] > EPS) {
                double ratio = tableau[i][tableau[i].length - 1] / tableau[i][entering];
                if (ratio < bestRatio - EPS) {
                    bestRatio = ratio;
                    row = i;
                }
            }
        }
        return row;
    }

    private void pivot(int pivotRow, int pivotColumn) {
        double pivot = tableau[pivotRow][pivotColumn];
        for (int j = 0; j < tableau[pivotRow].length; j++) {
            tableau[pivotRow][j] /= pivot;
        }
        for (int i = 0; i < tableau.length; i++) {
            if (i == pivotRow) {
                continue;
            }
            double factor = tableau[i][pivotColumn];
            if (Math.abs(factor) < EPS) {
                continue;
            }
            for (int j = 0; j < tableau[i].length; j++) {
                tableau[i][j] -= factor * tableau[pivotRow][j];
            }
        }
    }

    /**
     * Returns the optimal primal solution.
     */
    public double[] primal() {
        double[] solution = new double[variables];
        for (int i = 0; i < constraints; i++) {
            if (basis[i] < variables) {
                solution[basis[i]] = tableau[i][tableau[i].length - 1];
            }
        }
        return solution;
    }

    /**
     * Returns the optimal objective value.
     */
    public double value() {
        return tableau[constraints][tableau[constraints].length - 1];
    }

    public static void main(String[] args) {
        double[][] A = {
            {2, 1},
            {1, 2},
            {1, 0}
        };
        double[] b = {14, 14, 6};
        double[] c = {3, 2};

        LinearProgrammingSimplex simplex = new LinearProgrammingSimplex(A, b, c);
        System.out.println("Optimal value: " + simplex.value());
        System.out.println("Solution: " + Arrays.toString(simplex.primal()));
    }
}
