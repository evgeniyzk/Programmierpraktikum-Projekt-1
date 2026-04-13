package model;

import java.io.*;
import java.time.LocalDate;

/* *** Aufgabenteil (1a) *** */
// TODO

public class Actor implements Comparable<Actor> {
	private String name;
	private Integer birth;
	private Integer death;
	
	public Actor(String name){
		this.name = name;
	}
	
	public Actor(String name, int birth) {
		this(name);
		this.birth = birth;
	}
	
	public Actor(String name, int birth, int death) {
		this(name,birth);
		this.death = death;
	}

	public String getName() {
		return this.name;
	}
	
	/* *** Aufgabenteil (1a) *** */
	// TODO
	public String getNameLastNameFirst() throws IOException {
	
		int index = this.name.lastIndexOf(" ");
		String vorname = this.name.substring(0,index);
		String nachname = this.name.substring(index + 1);
		
		
		
		return nachname + "," +vorname;   // Dummy Return
	}
	
	
	public int getBirth() {
		return (birth != null) ? birth.intValue() : -1;
	}
	
	public int getDeath() {
		return (death != null) ? death.intValue() : -1;
		}
	
	public String toString(){
		if (this.birth == null) { 
			return this.name;
		}
		else if (this.death != null) { 
			return this.name + " (" + this.birth + " - " + this.death + ")";
		} else { 
			int y1 = this.birth;
			int y2 = LocalDate.now().getYear();
			int age = (y2 - y1);
		    
			return this.name + " (" + this.birth + (", age: " + age + ")");
		}
	}
	
	/* *** Aufgabenteil (1a) *** */
	// TODO
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if (!(obj instanceof Actor))
	        return false;
	    

		Actor act = (Actor) obj;
		if(this.name.equals(act.getName()))
			return true;
		else
			return false;
	
	}
	
	public int compareTo(Actor other) {
	
        int lastSpaceThis = this.name.lastIndexOf(" ");
        int lastSpaceOther = other.name.lastIndexOf(" ");

        String nachnameThis = (lastSpaceThis == -1) ? this.name : this.name.substring(lastSpaceThis + 1);
        String nachnameOther = (lastSpaceOther == -1) ? other.name : other.name.substring(lastSpaceOther + 1);

        String vornameThis = (lastSpaceThis == -1) ? "" : this.name.substring(0, lastSpaceThis);
        String vornameOther = (lastSpaceOther == -1) ? "" : other.name.substring(0, lastSpaceOther);

        int result = nachnameThis.compareTo(nachnameOther);

        if (result == 0) {
            result = vornameThis.compareTo(vornameOther);
        }
        
        return result;
    }
}
