import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class test {
	public static void main(String[] args) throws Exception {
		Vector v=new Vector();
		v.add(2);
		v.add(8);
		v.add(88);
		v.add(1);
		v.remove(1);
		System.out.println(v);

	}

		// Converting from ArrayList (list) to Array (arr):
		//int[] arr = list.stream().mapToInt(i->i).toArray());
	
		// Converting from Array (arr) to ArrayList(list):
		//ArrayList<Integer> list=(ArrayList<Integer>) Arrays.stream(arr).boxed().collect(Collectors.toList());
	

}
