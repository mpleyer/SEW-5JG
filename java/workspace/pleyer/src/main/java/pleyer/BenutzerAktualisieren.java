package pleyer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
 * Benutzer Aktualisieren Klasse
 * @author Pleyer Matthias, @date 29.09.17
 */
public class BenutzerAktualisieren {
	private int ID;
	private String vorname;
	private String nachname;
	private int benalter;
	private String status;
	private Benutzer alterBenutzer;

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
		return benalter;
	}

	public void setBenalter(int benalter) {
		this.benalter = benalter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Benutzer getAlterBenutzer() {
		return alterBenutzer;
	}

	public void setAlterBenutzer(Benutzer alterBenutzer) {
		this.alterBenutzer = alterBenutzer;
	}

	public void ladeBenutzer(Session s) {
		try {
			Transaction transact = s.beginTransaction();
			alterBenutzer = s.find(Benutzer.class, this.ID);
			transact.commit();
		} catch (Exception e) {
			this.status = "Etwas ist schief gelaufen mit dem Laden des Benutzers";
		}

	}

	public void aktualisiereBenutzer() {
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			this.ladeBenutzer(session);
			Transaction transact = session.beginTransaction();

			Benutzer ben = session.find(Benutzer.class, alterBenutzer.getID());
			ben.setVorname(vorname.equals("") ? alterBenutzer.getVorname() : vorname);
			ben.setNachname(nachname.equals("") ? alterBenutzer.getNachname() : nachname);
			ben.setBenalter(benalter == 0 ? alterBenutzer.getBenalter() : benalter);

			session.saveOrUpdate(ben);
			transact.commit();
			session.close();
			this.status = ben + " wurde erfolgreich aktualisiert";
		} catch (NullPointerException e) {
			this.status = "Ausgewählte ID stimmt mit keiner aus der Datenbank überein";
		}
	}
}
