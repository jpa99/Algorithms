public class Binary_Indexed_Tree {

	public static void main(String[] args) throws IOException {
  		//testting BIT
		BIT bit = new BIT(12);
		bit.update(4, 1);
		bit.update(3, 1);
		bit.update(5, 1);
		bit.update(2, 1);
		bit.update(5, 1);
		bit.update(4, -1);
		int a = bit.read(9);
		System.out.println(a);
		
	}
	
	static class BIT{
	      int[] binary_indexed_tree;
	      int maxVal;
        
	      public BIT(int n){
	              binary_indexed_tree = new int[++n];
	              maxVal = n;
	      }
		
	      public void update(int index, int val){
		   while (index <= maxVal){
			 tree[index] += val;
			 index += (index & -index); //last bit removal
		   }
	      }
        
	      //Returns the cumulative frequency of index index
	      public int read(int index){
	        int sum=0;
	        while (index>0){
	              sum += tree[index];
	              index -= (index & -index);
	        }
	        return sum;
	      }
	}

}
