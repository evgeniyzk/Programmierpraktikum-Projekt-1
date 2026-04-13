package model;

// TODO
public class Nominee implements Comparable<Nominee> {
	private Category category;
	private int year;
	private String movie;
	private boolean won;
	private Actor actor;

	
	public Nominee(Category c, int y, String movie, boolean wonP, Actor a){
		this.category = c;
		this.year = y;
		this.movie = movie;
		this.won = wonP;
		this.actor = a;
	}


	public Category getCategory() {
		return category;
	}


	public int getYear() {
		return year;
	}


	public String getMovie() {
		return movie;
	}


	public boolean isWon() {
		return won;
	}


	public Actor getActor() {
		return actor;
	}
	
	public String toString() {
		return this.actor.getName() + ": \""+ this.movie + 
				"\", Category: " + this.category + " (" + + this.year + ")";
	}
	
	// TODO
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if (!(obj instanceof Nominee))
	        return false;
		
		Nominee nom = (Nominee) obj;
		
		
		return this.getActor().equals(nom.getActor());
	}


	@Override
	public int compareTo(Nominee o) {
		// TODO Auto-generated method stub
		if(this.movie.equals(o.getMovie())) {
			return this.actor.compareTo(o.getActor());
		}
		return this.movie.compareTo(o.getMovie());
	}

}
