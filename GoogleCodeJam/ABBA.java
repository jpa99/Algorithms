
public class ABBA {
	public static void main(String[] args) throws Exception{
		System.out.println(canObtain("B","ABBA"));
		System.out.println(canObtain("AB","ABB"));
		System.out.println(canObtain("BBAB","ABABABABB"));
		System.out.println(canObtain("BBBBABABBBBBBA","BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));
		System.out.println(canObtain("A","BB"));
	}
	
	public static String canObtain(String initial, String target){
	    boolean fwd=true;
	    while(target.length()>initial.length()){
	        String ch="";
	        int len=target.length();
	        if(fwd){
	       		ch=target.substring(len-1);
	        	target=target.substring(0, len-1);
	        }
	        else{
	            ch=target.substring(0, 1);
	            target=target.substring(1);
	        }
	        if(ch.equals("B")){
	           fwd=!fwd;
	        }
		}
	    
	    if(fwd && initial.equals(target))
	        return "possible";
	    else if(!fwd){
	        int l=target.length();
	        for(int i=0;i<l;i++){
	            if(!target.substring(l-i-1, l-i).equals(initial.substring(i, i+1))){
	                return "impossible";
	            }
	        }
	        return "possible";
	    }
	    else
	        return "impossible";
	            
	        
	}
}
