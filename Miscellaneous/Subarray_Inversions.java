import java.util.*;

public class Subarray_Inversions {

    /* Subarray Inversions

        Problem: Given an array A of n integers, find the sum of the number of inversions in all subarrays of length k. To clarify, one must determine the number of inversions in each of the n – k + 1 subarrays of length k and add them together.
        
        Explanation: Many times, algorithms that perform the same or similar operation(s) repeatedly can be improved by the use of a more robust data structure. In our case, we’re looking for a dynamic data structure that can efficiently answer queries about an element’s relative position when sorted. We can use a self-balancing binary tree to actually maintain a sorted list, but insertion/removal takes logarithmic time. We can do this in constant time using a Binary Indexed Tree, or Fenwick Tree.
        A binary indexed tree is a tree represented in the form of an array. It uses clever bit manipulation to compute the cumulative sum of elements very efficiently. We can call the function update(index, val) function to add val to BIT[index] and all of the ancestors of index. The function read(index) returns the sum of the values stored at BIT[index] and all of the ancestors of index in the tree. Thus, calling read(index) returns the cumulative sum of all elements in BIT less than or equal to index. Instead of storing values, if we simply store 1, we can use read(index + 1) to determine the number of elements less than index. Now, we can construct a binary indexed tree by inserting the elements (updating) of the first window. For subsequent windows, we can remove the trailing element by calling update(tail_element, -1) and add the new element with update(head_element, 1). Since this is a tree, the longest possible root-node path is O(logk), Thus, we achieve an optimal runtime of O(nlogk + klogk) = O(nlogk)!
        Or do we…? Remember, binary indexed trees allocate memory for every possible value in the range [0, max_element], so this requires O(max_element) time and space. For very sparse arrays, this can be quite costly. Instead, we can define a hash function to . We can do this because we’re only concerned about inversions — as long as we keep the relative magnitude the same (i.e. A[i] <= A ==> A[hash(i)] <= A[hash(j)]), our solution will still be correct. Thus, we can map all the elements in A to the set {0, 1, 2, …, n}, yielding a guaranteed runtime of O(nlogk).

        Complexity:
            * Time - O(nlogk)
            * Space - O(n)

        Functions Defined:
            * inversionCountBIT1() - First pass, instantiates binary indexed tree 
            * inversionCountBIT2() - Subsequent passes, remove trailing element, add leading element, computes changes
            * inversionCount() - main algorithm that maps array to [1...n] and slides window
            * class BIT() - Instantiates binary indexed tree
                + update() - updates ancestors and index with specified value, runs in O(log n)
                + read() - computes and determines sum of index pointer and ancestors, runs in O(log n)


    */
    
    //Declare binary indexed tree with global scope
    static BIT bit;
    
    //first window, counts first k elements and creates BIT
    static long inversionCountBIT1(int[] arr, int start, int end) {
        bit = new BIT(arr.length);
        long count = 0;
        for(int index = start; index >=end; index--){
            count+=bit.read(arr[index]);
            bit.update(arr[index], 1);
        }
        return count;
    }
    
    //subsequent windows, removes previous element, adds new element, and increments change in inversions
    static long inversionCountBIT2(int[] arr, int start, int end, long val) {
        bit.update(arr[start+1], -1); //remove trailing element
        //find number of elements in range [start, end-1] greater than first
        int numGreaterThanFirst = start-end - bit.read(arr[start+1]+1); 
        long count = val + bit.read(arr[end]) - numGreaterThanFirst; 
        bit.update(arr[end], 1); //adds leading element
        
        return count;
    }
    
    //Main method to count inversions in size k subarrays
    public static long inversionCount(int n, int k, int[] arr){
        bit = new BIT(n);
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        int[] asort = arr.clone();

        //Map elements from [A[0]...A[n-1]] to [1...n]
        Arrays.sort(asort);
        int index = 0;
        int current = 1;
        for(int i=0;i<n;i++){
            if(!freq.containsKey(asort[i])){
                freq.put(asort[i], current);
                current++;
            }
        }
        for(int i=0;i<n;i++){
            arr[i] = freq.get(arr[i]);
        }
        
        long count = 0;
        long val = 0;

        //[start - end] ==> start - end = k+1
        for(int start = n-1; start >=k-1; start--){
            int end = start - k+1;
            if(start == n-1){ //First pass
                val = inversionCountBIT1(arr, n-1, n-k);
            }
            else{ //subsequent passes
                val = inversionCountBIT2(arr, start, end, val);
            }
            count+=val;
        }
        return count;
    }


    public static void main(String[] args)throws Exception {
        int n = 10;
        int[] arr = {15, 51, 44, 44, 76, 50, 29, 88, 48, 50};
        int k = 5;

        long result = inversionCount(n, k, arr);
        System.out.println(result);
    }

    //Implementation of Binary Indexed Tree
    static class BIT
    {
          int[] tree;
          int maxVal;
          public BIT(int N){
                  tree = new int[N+1];
                  maxVal = N;
          }

          //Updates BIT, starting with element at index and all ancestors
          void update(int index, int val){
               while (index <= maxVal){
                     tree[index] += val;
                     index += (index & -index);
               }
          }

          //Returns the cumulative frequency of index starting with element at index and all ancestors
          int read(int index)
          {
            --index;
            int cumulative_sum=0;
            while (index >0){
                  cumulative_sum += tree[index];
                  index -= (index & -index);
            }
            return cumulative_sum;
          }
    };
}
