package OnlineAssesment20180329;

public class StudyCompareTo {
	
	
	public static void main(String[] args){
		System.out.println("'a'.compareTo('b'): " + "a".compareTo("b"));
		System.out.println("'b'.compareTo('a'): " + "b".compareTo("a"));
		System.out.println("'a'.compareTo('aa'): " + "a".compareTo("aa"));
		System.out.println("'aa'.compareTo('a'): " + "aa".compareTo("a"));
		System.out.println("\n");
		System.out.println("'a'.compareTo('1'): " + "a".compareTo("1"));
		System.out.println("'1'.compareTo('a'): " + "1".compareTo("a"));
		System.out.println("'1'.compareTo('2'): " + "1".compareTo("2"));
		System.out.println("'2'.compareTo('1'): " + "2".compareTo("1"));
		
		// (-) if first is earlier
		// (+) if first is later
	}
	
	// This one might be used in Quick Sort version. Not necessary at the moment
	boolean isSmaller(String first, String second){
		// Compares always if first is smaller than second
		/*
		 * ("ae", "bd") --> True
		 * ("zd", "or") --> False
		 * ("ae", "ae") --> True **actually it doesn't matter.
		 * ("12", "23") --> True
		 * ("23", "12") --> False
		 * ("12", "ae") --> False **it is important to sort characters first, digits after
		 * ("ae", "12") --> True ** it is important to sort characters first, digits after 
		 */
		
		
		int j = 0;
		
		// Check for edges
		if (first.matches(".*\\d+.*")){
			if (!second.matches(".*\\d+.*")){
				return false;
			}
		} else{
			if (second.matches(".*\\d+.*")){
				return true;
			}
		}		
		
		//if other than edge cases work as is
		while (j < first.length() &&  j < second.length()){
			if (first.charAt(j) == second.charAt(j)){
				continue;
			} else if (first.charAt(j) < first.charAt(j)){
				return true;
			} else {
				return false;
			}
		}
		
		return true;
	}


}
