package pleyer;

import javax.persistence.*;

/*
 * Entity Klasse 
 * @author Pleyer Matthias, @date 29.09.17
 */
@Entity
@Table(name = "benutzer")
public class Benutzer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// Attributes
	private int ID;
	private String vorname = "";
	private String nachname = "";
	private int balter;

	/**
	 * Klasse
	 * 
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getBenalter() {
		return balter;
	}

	public void setBenalter(int benalter) {
		this.balter = benalter;
	}

	@Override
	public String toString() {
		return "Benutzer mit der ID " + ID + ", dem Vornamen " + vorname + ", dem Nachnamen " + nachname
				+ " und dem Alter " + balter;
	}
}
