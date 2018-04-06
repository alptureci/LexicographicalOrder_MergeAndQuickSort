package OnlineAssesment20180329;

import java.util.*;

public class LexicographicalOrder_QuickSort {
	
	static class Node implements Comparable<Node>{
		String value;
		String key;
		
		public Node (String k, String v){
			this.key = k;
			this.value = v;
		}
		
		public int compareTo(Node node){
			//if (this.value.equalsIgnoreCase(node.value)) return 0;
			
			/* regular compareTo method for Strings returns:
			 * (-) if first is earlier
			 * (+) if first is later
			 * 
			 * but I want to modify this, ONLY for Character vs. Digit comparisons.
			 * Normally digits are earlier than characters.
			 * I want Character first, digit later
			 */
			
			if (this.value.matches(".*\\d+.*")){
				if (!node.value.matches(".*\\d+.*")){
					// if first is digit and second is character
					// reverse the sign
					return this.value.compareTo(node.value)*(-1);
				}
			} else if (!this.value.matches(".*\\d+.*")){
				if (node.value.matches(".*\\d+.*")){
					// first one is character and second is digit
					// reverse the sign
					return this.value.compareTo(node.value)*(-1);
				}
			} 
			
			// ELSE for all other Operate under Normal Conditions.
			return this.value.compareTo(node.value);
		}
	}
	
	public List<String> reorderLines(int logfilesize, List<String> logfile){
		
		List<String> result = new ArrayList<String>();
		
		Node[] logs = new Node[logfilesize];
		
		for (int i = 0; i < logfilesize; i++){
			String[] t = logfile.get(i).split(" ", 2);
			logs[i] = new Node(t[0],t[1]);
		}
		
		
		// Alternative 1: using Arrays.sort much more efficient in terms of coding. Less Code
		Arrays.sort(logs, new Comparator<Node>(){
			public int compare(Node n1, Node n2){
				return n1.compareTo(n2);
			}
		});
		
		// Alternative 2: using custom Quick Sort Method.
		// Relevant methods:
		// 	sort, sort, partition
		result = sort(logs);		
		return result;
		
	}
	
	public ArrayList<String> sort(Node[] log){
		int low = 0;
		int high = log.length - 1;
		
		sort(log, low, high);
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i <= high; i++){
			result.add(log[i].key + " " + log[i].value);
		}
		return result;
		
	}
	
	public void sort(Node[] log, int low, int high){
		if (low < high){
			
			/* pi = partitioning index, 
			 * log[pi] is now at right place */
			int pi = partition(log, low, high);
			
			//Recursively sort elements before and after partition
			sort(log, low, pi-1);
			sort(log, pi+1, high);			
		}
	}
	
	int partition(Node[] log, int low, int high){
		Node pivot = log[high];
		int i = (low-1);
		
		for (int j = low; j < high; j++){
			
			// If current element is smaller than or
            // equal to pivot
			if (log[j].compareTo(pivot) < 0){
				i++;
				
				// swap log[i] and log[j]
				Node temp = log[i];
				log[i] = log[j];
				log[j] = temp;
			}
		}
		
		// swap log[i+1] and log[high] (or pivot)
        Node temp = log[i+1];
        log[i+1] = log[high];
        log[high] = temp;
 
        return i+1;		
	}
	
	public static void main(String[] args){
		List<String> exclude = new ArrayList<String>();
		
		exclude.add("a1 9 2 3 1");
		exclude.add("g1 Act car");
		exclude.add("zo4 4 7");
		exclude.add("ab1 off KEY dog");
		exclude.add("a8 act zoo");
		
		LexicographicalOrder_QuickSort lx = new LexicographicalOrder_QuickSort();
		
		List<String> res = lx.reorderLines(5, exclude);
		for (String w : res){
			System.out.println(w);
		}
	}	
}
