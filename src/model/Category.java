package model;

/* Oscar-Kategorien für Schauspieler*innen in Haupt- bzw. Nebenrollen
 */
public enum Category {
	ACTRESS_LR, ACTOR_LR, ACTRESS_SR, ACTOR_SR;

	public String toString(){
		switch(this){
		case ACTRESS_LR: return "Actress -- Leading Role";
		case ACTOR_LR: return "Actor -- Leading Role";
		case ACTRESS_SR: return "Actress -- Supporting Role";
		case ACTOR_SR: return "Actor -- Supporting Role";
		default: return "unknown";
		}
	}
	
	/* Mapping für Strings aus der Datei nominees2026.csv auf Konstanten */
	public static Category toCategory(String str) {
		switch(str) {
		case "ACTRESS IN A LEADING ROLE": return ACTRESS_LR;
		case "ACTOR IN A LEADING ROLE": return ACTOR_LR;
		case "ACTRESS IN A SUPPORTING ROLE": return ACTRESS_SR;
		case "ACTOR IN A SUPPORTING ROLE": return ACTOR_SR;
		default: return null;
		}
	}
}
