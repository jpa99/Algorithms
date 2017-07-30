
public class Number_letter_counts {

	public static void main(String[] args) {
		int digits=36;
		int teens=70;
		int tens=46;
		int ninety_nine=digits*9+teens+tens*10;
		int hundreds=(digits+(7*9))*100;
		int ands=99*9*3;
		int thousand=11;
		System.out.println(ninety_nine*10+hundreds+ands+thousand);
		
	}

}


/*


one two three four five six seven eight nine ten eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen

twenty thirty forty fifty sixty seventy eighty ninety

hundred

*/