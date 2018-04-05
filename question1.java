package OnlineAssesment20180329;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class question1 {
	
	
	static List<String> counterWord (String input, List<String> exclude){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		List<String> result = new ArrayList<String>();
		
		
		String[] splitted = input.split(" ");
		
		for (String w : splitted){
			if (!exclude.contains(w)){
				if (map.get(w) == null){
					map.put(w, 1);
				}
				map.put(w, map.get(w)+1);
			}
		}
		
		int maxVal = (Collections.max(map.values()));
		for (Entry<String, Integer> entry : map.entrySet()){
			if (entry.getValue()==maxVal) {
                result.add(entry.getKey());
            }
		}
		
		return result;
	}
	
	
	public static void main(String[]  args){
		String input = "rose is a flower rose is pond a flower rose flower in garden garden garden pond pond rose is a rose is a rose is a rose is a";
		List<String> exclude = new ArrayList<String>();
		exclude.add("rose");
		exclude.add("is");
		exclude.add("a");
		
		List<String> res = counterWord(input, exclude);
		for (String w : res){
			System.out.println(w);
		}
	}

}
