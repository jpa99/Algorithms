import java.util.Arrays;

/**
 * Computes mean-variance optimal portfolios using the classical Markowitz formulation.
 *
 * <p>The optimizer solves for portfolio weights that minimize variance subject to achieving a target
 * expected return and the weights summing to one. The solution follows the closed-form KKT system
 * for the quadratic program:</p>
 *
 * <pre>
 *     minimize   (1/2) w^T Σ w
 *     subject to μ^T w = r_target
 *                1^T w = 1
 * </pre>
 */
public class MarkowitzPortfolioOptimization {

    /**
     * Solves for the optimal weights.
     *
     * @param expectedReturns asset expected returns μ
     * @param covariance covariance matrix Σ
     * @param targetReturn desired portfolio return
     * @return weights that satisfy the optimality conditions
     */
    public static double[] optimizePortfolio(
            double[] expectedReturns, double[][] covariance, double targetReturn) {
        int n = expectedReturns.length;
        if (covariance.length != n || covariance[0].length != n) {
            throw new IllegalArgumentException("Covariance matrix must be square");
        }

        double[][] system = new double[n + 2][n + 2];
        double[] rhs = new double[n + 2];

        // Stationarity conditions: Σ w - λ μ - γ 1 = 0
        for (int i = 0; i < n; i++) {
            System.arraycopy(covariance[i], 0, system[i], 0, n);
            system[i][n] = -expectedReturns[i];
            system[i][n + 1] = -1.0;
        }

        // μ^T w = targetReturn
        for (int j = 0; j < n; j++) {
            system[n][j] = expectedReturns[j];
        }
        rhs[n] = targetReturn;

        // 1^T w = 1
        Arrays.fill(system[n + 1], 0);
        for (int j = 0; j < n; j++) {
            system[n + 1][j] = 1.0;
        }
        rhs[n + 1] = 1.0;

        double[] solution = solveLinearSystem(system, rhs);
        return Arrays.copyOf(solution, n);
    }

    private static double[] solveLinearSystem(double[][] A, double[] b) {
        int n = b.length;
        double[][] augmented = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, augmented[i], 0, n);
            augmented[i][n] = b[i];
        }

        for (int col = 0; col < n; col++) {
            int pivot = col;
            for (int row = col + 1; row < n; row++) {
                if (Math.abs(augmented[row][col]) > Math.abs(augmented[pivot][col])) {
                    pivot = row;
                }
            }
            if (Math.abs(augmented[pivot][col]) < 1e-9) {
                throw new IllegalArgumentException("System is singular");
            }
            double[] tmp = augmented[pivot];
            augmented[pivot] = augmented[col];
            augmented[col] = tmp;

            double pivotValue = augmented[col][col];
            for (int j = col; j <= n; j++) {
                augmented[col][j] /= pivotValue;
            }
            for (int row = 0; row < n; row++) {
                if (row == col) {
                    continue;
                }
                double factor = augmented[row][col];
                for (int j = col; j <= n; j++) {
                    augmented[row][j] -= factor * augmented[col][j];
                }
            }
        }

        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = augmented[i][n];
        }
        return x;
    }

    public static void main(String[] args) {
        double[] expectedReturns = {0.12, 0.08, 0.05};
        double[][] covariance = {
            {0.0100, 0.0018, 0.0011},
            {0.0018, 0.0109, 0.0026},
            {0.0011, 0.0026, 0.0199}
        };
        double targetReturn = 0.08;

        double[] weights = optimizePortfolio(expectedReturns, covariance, targetReturn);
        double realizedReturn = 0.0;
        double variance = 0.0;
        for (int i = 0; i < weights.length; i++) {
            realizedReturn += weights[i] * expectedReturns[i];
            for (int j = 0; j < weights.length; j++) {
                variance += weights[i] * weights[j] * covariance[i][j];
            }
        }

        System.out.println("Weights: " + Arrays.toString(weights));
        System.out.println("Expected return: " + realizedReturn);
        System.out.println("Variance: " + variance);
    }
}
