package OnlineAssesment20180329;

import java.util.ArrayList;
import java.util.List;

public class question2 {
	
	
	public static List<String> reorderLines(int logFileSize, List<String> logfile){
		
		List<String> result = new ArrayList<String>();
		
		String[] tempArr = new String[logFileSize];
		String[] tempArr2 = new String[logFileSize];
		
		List<String> ordered =  new ArrayList<String>();
		List<String> nums = new ArrayList<String>();
		
		for (int i = 0; i <logfile.size(); i++){
			String[] t = logfile.get(i).split(" ", 2);
			tempArr[i] = t[0];
			String z[] = t[1].split(" ");
			char[] c = z[0].toCharArray();
			if (Character.isDigit(c[0])){
				nums.add(t[1]);
			} else{				
				ordered.add(t[1]);
			}
		}
		
		 for (int i = 0; i < ordered.size(); i++) {
			    int tIndex = i;
			    for (int j = i; j < ordered.size(); j++) {
			        if(ordered.get(j).compareTo(ordered.get(tIndex))<0){
			            tIndex = j;
			        }

			    }
			    String tString = ordered.get(i);
			    ordered.set(i, ordered.get(tIndex));
			    ordered.set(tIndex, tString);
			    
			    for (String w : nums){
			    	ordered.add(w);
			    }

			}
				
		return ordered;	
		
	}
	
	
	
	
	public static void main(String[]  args){
		
		List<String> exclude = new ArrayList<String>();
		exclude.add("g1 Act car");
		exclude.add("a1 9 2 3 1");
		exclude.add("zo4 4 7");
		exclude.add("ab1 off KEY dog");
		exclude.add("a8 act zoo");
		
		List<String> res = reorderLines(5, exclude);
		for (String w : res){
			System.out.println(w);
		}
	}

}
