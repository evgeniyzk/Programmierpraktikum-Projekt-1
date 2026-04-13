package io;


import java.util.HashMap;
import java.util.TreeSet;

import general.Parameters;
import model.Actor;
import model.Category;
import model.Nominee;

public class TextParser {
	public static final int ACTORS = 1;   // Flag for file with actors
	public static final int AWARDS = 2;   // Flag for file with nominees
	
	
	/* a map to store objects of class Actor, using his or her name
	 * as the key 
	 * */
	private static HashMap<String, Actor> aMap = new HashMap<>();


	private static void parseActorsLine(String line, TreeSet<Actor> aSet) throws InvalidTokenException{
		// TODO
		String[] arr = line.split(";");
		
		if(arr.length > 3 || arr.length < 2) {
			throw new InvalidTokenException("Erwartete Anzahl 2-3. Reele Anzhal: " + arr.length);
		}
		

	    String name = arr[0];
	    int birth = Integer.parseInt(arr[1]);
	    Actor a;

	    if (arr.length == 3 && !arr[2].isEmpty()) {
	        int death = Integer.parseInt(arr[2]);
	        a = new Actor(name, birth, death);
	    } else {
	        a = new Actor(name, birth);
	    }

	    aSet.add(a);
	    aMap.put(name, a);
	}
	
	
	private static void parseNomineesLine(String line, TreeSet<Nominee> nomSet) throws InvalidTokenException{
		// TODO
		String[] arr = line.split(";");
		
		if(arr.length != 5) {
			throw new InvalidTokenException("Erwartete Anzahl 5. Die reele Anzhal:"+ arr.length);
		}
		
		int year = Integer.parseInt(arr[0]);
		Category c = Category.toCategory(arr[1]);
		String name = arr[2];
		String film = arr[3];
		boolean win = Boolean.parseBoolean(arr[4]);
		
		if(aMap.get(name) != null) {
			Nominee n = new Nominee(c,year,film,win,aMap.get(name));
			nomSet.add(n);
		}else {
			Actor a = new Actor(name);
			aMap.put(name, a);
			Nominee n = new Nominee(c,year,film,win,a);
			nomSet.add(n);
			
		}
					
	}
	
	@SuppressWarnings("unchecked")
	public static <T>  void parseData (int objectType, TreeSet<T> set) throws InvalidTokenException{
		StringBuffer fileContent;
		String[] lines;            // array to store lines of a text
		switch (objectType) {
		case ACTORS:
			fileContent = TextReader.getText(Parameters.actorsFile);
			// TODO
			lines = fileContent.toString().split("\n");
			for(int i = 0; i<lines.length;i++) {
				try {
					parseActorsLine(lines[i], (TreeSet <Actor>) set);
				}catch(InvalidTokenException e) {
					System.out.println("Die Fehler hat auf Zele " + i + "aufgetreten.");
				}
			}
			break;
		case AWARDS:
			fileContent = TextReader.getText(Parameters.nomineesFile);
			// TODO
			lines = fileContent.toString().split("\n");
			for(int i = 0; i<lines.length;i++) {
				try {
					parseNomineesLine(lines[i], (TreeSet <Nominee>) set);
				}catch(InvalidTokenException e) {
					System.out.println("Die Fehler hat auf Zele " + i + "aufgetreten.");
				}
			}
			break;
		default:
			break;
		}
	
	}
	
	public static void main(String[] args) throws InvalidTokenException {
		TreeSet< Actor> actors = new TreeSet< Actor>();
		TextParser.parseData(ACTORS, actors);
		System.out.println(actors.size() + " actors found, e.g. ");
		System.out.println(actors.first());
		TreeSet< Nominee> nominees = new TreeSet< >();
		TextParser.parseData(AWARDS, nominees);
		System.out.println(nominees.size() + " nominees found, e.g. ");
		System.out.println(nominees.first());

	}

}
