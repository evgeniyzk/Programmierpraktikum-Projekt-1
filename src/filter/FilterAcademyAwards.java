package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collectors;

import io.InvalidTokenException;
import io.TextParser;
import model.Actor;
import model.Nominee;

public class FilterAcademyAwards {
	private TreeSet<Actor> actors = new TreeSet<> ();
	private TreeSet<Nominee> nominees = new TreeSet<> ();
	
	public FilterAcademyAwards() throws InvalidTokenException{
		this.init();
	}
	
	/*  read and parse text from data files and 
	 *   create instances of the classes Actor and
	 *   Nominee that are stored in the respective
	 *   TreeSet instances 
	 *   */
	public void init() throws InvalidTokenException{
		TextParser.parseData(TextParser.ACTORS, actors);
		TextParser.parseData(TextParser.AWARDS, nominees);
	}
	
	/* ********************************************* */
	
	/* ***  Aufgabenteil (2a)  *** */
	
	public Vector<String> getActorNames(){
		// TODO
		Vector<String> v = new Vector<>();
		Iterator<Actor> it = actors.iterator();
		
		while(it.hasNext()) {
			v.add(it.next().getName());
		}
		
		
		return v;  // Dummy return
	}
	

	private Actor getActor(String aName){
		
		for(Actor a : actors) {
			if(a.getName().equals(aName)){
				return a;
			}
		}

		return null; // Dummy return
	}
	
	
	/* *** Aufgabenteil (2b) *** */


	public String getAwardsActor(String aName){
		// TODO
		Vector<Nominee> awards = new Vector<>();
		Vector<Nominee> nominations = new Vector<>();
		
		StringBuffer result = new StringBuffer("");
		
		 for(Nominee n : nominees) {
			 if(n.getActor().getName().equals(aName)) {
				 if(n.isWon())
					 awards.add(n);
				 else
					 nominations.add(n);
			 }
		 }
		 
		 awards.sort(Comparator.comparingInt(Nominee :: getYear));
		 nominations.sort(Comparator.comparingInt(Nominee :: getYear));
		 
		 Actor a = getActor(aName);
		 
		 //Start
		 result.append(aName + "(" + a.getBirth() + ", Age:" + (2026-a.getBirth()) + ")\n");
		 result.append("Awards:\n");
		 if(awards.isEmpty()) {
			 result.append("---\n");
		 }else {
			 for(Nominee n : awards) {
				 result.append(n.getYear()).append(": ").append(n.getMovie()).append(", ").append(n.getCategory());
				 result.append("\n");
			 }
		 }
		 result.append("\n");
		 
		 //Nominations
		 result.append("Nominations:\n");
		 if(nominations.isEmpty()) {
			 result.append("---\n");
		 }else {
			 for(Nominee n : nominations) {
				 result.append(n.getYear()).append(": ").append(n.getMovie()).append(", ").append(n.getCategory());
				 result.append("\n");
			 }
		 }
		 
		 
		return result.toString();    // Dummy return
	}
	
	
	/* ***  Aufgabenteil 2c *** */
	// 2P
	
	public String actorsBornInYear(int y){
		// TODO
		StringBuffer result = new StringBuffer();
		List<String> list = actors.stream()
				.filter(a -> a.getBirth() == y)
				.map(t -> {
					try {
						return t.getNameLastNameFirst();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						return t.getName();
					}
				})
				.sorted()
				.collect(Collectors.toList());
		
		
		for(String s : list) {
			result.append(s).append("\n");
		}
		return result.toString();
	}
	
	

	/* *** Aufgabenteil (2d) *** */

	
	private HashMap<String,List<Nominee>> getNomineeMap() {
		// TODO
		HashMap<String,List<Nominee>> hMap = new HashMap<>();
		
		
		for(Nominee n : nominees) {
			String name = n.getActor().getName();
			
			if(hMap.get(name) == null) {
				List<Nominee> list = new ArrayList<>();
				hMap.put(name, list);
				list.add(n);
			}else {
				hMap.get(name).add(n);
			}
			
		}
		return hMap;   // Dummy return
	}
	
	// TODO
	Comparator<Map.Entry<String, List<Nominee>>> compNom = new Comparator<Map.Entry<String, List<Nominee>>>() {
	    
	    @Override
	    public int compare(Map.Entry<String, List<Nominee>> e1, Map.Entry<String, List<Nominee>> e2) {
	        List<Nominee> list1 = e1.getValue();
	        List<Nominee> list2 = e2.getValue();
	        
	        if (list1.size() < list2.size()) {
	            return -1; 
	        } else if (list1.size() > list2.size()) {
	            return 1;  
	        } else {
	            return 0;  
	        }
	    }
	};
	
	

	private int countAwards(List<Nominee> nomList) {
		// TODO
		long count = nomList.stream()
				.filter(s -> s.isWon())
				.count();
		return (int) count;   // Dummy return
	}

	public String getTopThreeNomCount(){
		HashMap<String, List<Nominee>> hMap = getNomineeMap();
		List<Map.Entry<String, List<Nominee>>> list = new ArrayList<>(hMap.entrySet());
		
		list.sort(compNom);
		
		StringBuffer result = new StringBuffer("");
		
		int count = 0;
	    for (int i = list.size() - 1; i >= 0 && count < 3; i--) {
	        Map.Entry<String, List<Nominee>> entry = list.get(i);
	        
	        String actorName = entry.getKey();
	        List<Nominee> nominations = entry.getValue();
	        int nomCount = nominations.size();
	        int winCount = countAwards(nominations); 
	        
	        result.append(count+1)
	        		.append(".")
	        		.append(actorName)
	        		.append(":\n ")
	        		.append("Nominations: ")
	        		.append(nomCount)
	        		.append("\nAwards: ")
	              	.append(winCount)
	              	.append("\n\n");
	              
	              
	        count++;
	    }
	    
	    return result.toString();// Dummy return
	}
	
	public static void main(String[] args) throws InvalidTokenException{
		FilterAcademyAwards faw = new FilterAcademyAwards();
		System.out.println(faw.actorsBornInYear(1975));
	}


}
