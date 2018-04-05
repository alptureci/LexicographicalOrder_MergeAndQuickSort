package OnlineAssesment20180329;

import java.util.*;

public class LexigraphicalOrder {
	
	static class Node{
		String key;
		String value;
		Node next;
		Node prev;
		
		public Node(String k, String v){
			this.key = k;
			this.value = v;
		}
	}
	
	static class CustomQueue{
		private int size = 0;
		private Node first;
		private Node last;
		
		public int getSize(){
			return size;
		}
		
		public void add(Node node){
			if (last != null){
				last.next = node;
			}
			last = node;
			if (first == null){
				first = last;
			}
			size++;
		}
		
		public Node remove(){
			if (first == null) return null;
			
			Node n = new Node(first.key, first.value);
			n.next = null;
			first = first.next;
			if (first == null){
				last = null;
			}
			size--;
			return n;
		}
		
		public Node peek(){
			if (first == null) return null;
			return first;
		}
		
	}
	
	
	
	public List<String> reorderLines(int logfilesize, List<String> logfile){
		
		List<String> result = new ArrayList<String>();
		
		CustomQueue q = new CustomQueue();
		//HashMap<String, String> split = new HashMap<String, String>();
		
		
		for (int i = 0; i < logfilesize; i++){
			String[] t = logfile.get(i).split(" ", 2);
			Node n = new Node(t[0], t[1]);
			q.add(n);
		}
		
		CustomQueue rq = mergesort(q);
		
		while (rq.getSize() != 0){
			
			Node n = rq.remove();
			String st = n.key + " " + n.value;
			result.add(st);
		}
		return result;
		
	}
	
	public CustomQueue mergesort(CustomQueue q){
		
		int length = q.getSize();
		int mid = q.getSize() / 2;
		
		CustomQueue left = new CustomQueue();
		CustomQueue right = new CustomQueue();
		
		for (int i = 0; i < mid; i++){
			left.add(q.remove());
		}
		
		for (int i = mid; i < length; i++){
			right.add(q.remove());
		}
		
		CustomQueue l;
		CustomQueue r;
		
		if (left.getSize() > 1){
			l = mergesort(left);
		} else {
			l = left;
		}
		if (right.getSize() > 1){
			r = mergesort(right);
		} else {
			r = right;
		}	
		
		return merge(l, r);
				
	}
	
	public CustomQueue merge(CustomQueue first, CustomQueue second){
		CustomQueue result = new CustomQueue();
		
		while(first.getSize() > 0 || second.getSize() > 0){
			Node n1 = first.peek();
			Node n2 = second.peek();
			
			if (n1 != null && n1.value == oneByoneCompare(n1, n2).value){
				result.add(first.remove());
			} else {
				result.add(second.remove());
			}
		}		
		return result;
	}
	
	
	
	public Node oneByoneCompare(Node n1, Node n2){
		
		if (n1 == null) return n2;
		if (n2 == null) return n1;
		
		Node shorterString = n1.value.length() < n2.value.length() ? n1 : n2;
		Node longerString = n1.value.length() >= n2.value.length() ? n1 : n2;
		
		
		if (shorterString.value.matches(".*\\d+.*")){
			return longerString;
		}
		if (longerString.value.matches(".*\\d+.*")){
			return shorterString;
		}
		
		for (int i = 0; i < shorterString.value.length(); i++){
			if (shorterString.value.charAt(i) == longerString.value.charAt(i)){
				continue;
			}
			
			else if (shorterString.value.charAt(i) < longerString.value.charAt(i)){
				return shorterString;
			} else {
				return longerString;
			}
		}
		return shorterString;
	}
	
	
	public static void main(String[] args){
		List<String> exclude = new ArrayList<String>();
		exclude.add("g1 Act car");
		exclude.add("a1 9 2 3 1");
		exclude.add("zo4 4 7");
		exclude.add("ab1 off KEY dog");
		exclude.add("a8 act zoo");
		
		LexigraphicalOrder lx = new LexigraphicalOrder();
		
		List<String> res = lx.reorderLines(5, exclude);
		for (String w : res){
			System.out.println(w);
		}
	}

}
