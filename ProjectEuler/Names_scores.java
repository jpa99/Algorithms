import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Names_scores {

	public static void main(String[] args) {
		//Initialize array containing names from text file
		String fileName="names.txt";
	    String[] names = null;
	       try{
	          FileReader inputFile = new FileReader(fileName);
	          BufferedReader bufferReader = new BufferedReader(inputFile);
	          String line=bufferReader.readLine();
	          names=line.split(",");
	          bufferReader.close();
	       }
	       catch(Exception e){                     
	       }
	       
	       //Sort array of names alphabetically
	       Arrays.sort(names);
	       
	       //Iterate through array and calculate score, adding to total sum
	       int total_score=0;
	       for(int i=0;i<names.length;i++){
	    	    int score=0;
				for(char letter:names[i].toCharArray()){
					score+="ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(letter)+1;
				}
				total_score+=(score*(i+1));
	       }
	       System.out.println(total_score);
	      
	}

}
