import java.util.*;
import java.util.Arrays;

public class BinaryTree {

	public static void main(String[] args) {
		
		//Initialize tree manually
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.leftChild = new BinaryTreeNode(3);
		root.rightChild = new BinaryTreeNode(8);
		root.rightChild.rightChild = new BinaryTreeNode(4);
		System.out.println(balance(root));
		
		//Initialize and balance tree from array
		int[] BinaryTreeArray = {1, 4, 3, 5, 6, 8, 2, 12, 4, 15, 6, 9};
		BinaryTreeNode tree = createBinaryTree(BinaryTreeArray);
		System.out.println(tree);
		System.out.println(balance(tree));
		
	}
	
	public static BinaryTreeNode createBinaryTree(int[] binaryTreeArray){
		if(binaryTreeArray.length == 0) return null;
		if(binaryTreeArray.length == 1) return new BinaryTreeNode((int) binaryTreeArray[0]);
		int mid = (binaryTreeArray.length)/2;
		BinaryTreeNode root = new BinaryTreeNode((int) binaryTreeArray[mid]);
		root.rightChild = createBinaryTree(Arrays.copyOfRange(binaryTreeArray, mid+1, binaryTreeArray.length));
		root.leftChild = createBinaryTree(Arrays.copyOfRange(binaryTreeArray, 0, mid));
		return root;
	}
	
	//
	public static BinaryTreeNode balance(BinaryTreeNode tree){	
		return createBinaryTree(tree.toArray());
	}
	
	
	//TreeNode data structure
	static class BinaryTreeNode{
		public int val = -1;
		public BinaryTreeNode leftChild;
		public BinaryTreeNode rightChild;
		
		public BinaryTreeNode(int val){
			this.val = val;
			leftChild = null;
			rightChild = null;
		}
		
		public String toString(){
			String left = leftChild == null ? "":leftChild.toString();
			String right = rightChild == null ? "":rightChild.toString();
			return val+" "+left+right;
		}
		
		public ArrayList<Integer> toArrayList(){
			ArrayList<Integer> left = leftChild == null ? new ArrayList<Integer>():leftChild.toArrayList();
			ArrayList<Integer> right = rightChild == null ? new ArrayList<Integer>():rightChild.toArrayList();
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(val);
			list.addAll(left);
			list.addAll(right);
			return list;
		}
		
		public int[] toArray(){
			return toArrayList().stream().mapToInt(i -> i).toArray();
		}
	}
	
	

}


