import java.util.Arrays;

public class Matrix_Exponentiation {
	
	/*Matrix Exponentiation Algorithm

	 Problem (informal): Given a matrix M, find the product: M^n = M x M x ... x M
	 
	 Algorithm: Brute force algorithm for repeated multiplication
	 
	 Complexity:
	 	* Time - O(n*i*j*k) where a has dimensions i x j and b has dimensions j x k to compute M^n
	 	* Space - O(i*j) where M has i rows and j columns
	 	
	 Functions Defined:
	 	* matrix_multiply() - returns matrix product of matrices a and b
	 	* matrix_exponentation() - returns M^n via repeated multiplication of M for square matrix M
	 	
	 */
	
	
	
	public static void main(String[] args){
		int[][] a = {{1, 2, 3, 2}, {2, 3, 4, 8}, {4, 5, 3, 6}}; //3 x 4
		int[][] b = {{6, 1, 4}, {9, 0, 6}, {6, 4, 5}, {3, 1, 1}}; // 4 x 3
		int[][] m = matrix_multiply(a, b);
		System.out.println(Arrays.deepToString(m));
		
		//testing staticness with multiplication with identity
		int[][] identity = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		int[][] m2 = matrix_multiply(m, identity);
		System.out.println(Arrays.deepToString(m2));
		
		//matrix exponentiation
		m2 = matrix_exponentiation(m2, 2);
		
		System.out.println(Arrays.deepToString(m2));
	}
	
	//assumes that matrix b has j rows and a has j columns
	//O(ijk) where a has dimensions i x j and b has dimensions j x k
	public static int[][] matrix_multiply(int[][] a, int[][] b){
		int[][] m = new int[a.length][b[0].length];
		for(int i=0;i<a.length;i++){
			for(int j = 0; j < b[0].length;j++){
				//m[i][j] is product of ith row of A and jth column of B
				int sum = 0;
				for(int k = 0; k < b.length; k++){
					sum+= a[i][k]*b[k][j];
				}
				m[i][j] = sum;
			}
		}
		return m;
	}
		
	//O(n*ijk) where a has dimensions i x j and b has dimensions j x k to compute M^n
	public static int[][] matrix_exponentiation(int[][] m, int n){
		while(n-- > 1){
			m = matrix_multiply(m, m);
		}
		return m;
	}
}
