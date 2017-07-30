import java.util.*;

public class Reverse_message{
    public static void main(String[] args){
    	System.out.println(reverse_words("find you will pain only go you recordings security the into if"));
    }
    public static String reverse_words(String message){
       	String[] words=message.split(" ");
        String[] rev_words=new String[words.length];
        for(int i=0;i<words.length;i++){
            rev_words[i]=words[words.length-1-i];
        }
        StringBuilder builder=new StringBuilder();
        for(String string: rev_words){
        	if(builder.length()>0){
        		builder.append(" ");
        	}
        	builder.append(string);
        }
        String rev_message=builder.toString();
        return rev_message;
    }
    
    
}

