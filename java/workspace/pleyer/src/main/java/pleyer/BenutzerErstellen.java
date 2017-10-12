package pleyer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
 * Benutzer Erstellen Klasse 
 * @author Pleyer Matthias, @date 29.09.17
 */
public class BenutzerErstellen {
	private String vorname = "";
	private String nachname = "";
	private int benalter;
	private String status = "";

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

	public void erstelleBenutzer() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Benutzer ben = new Benutzer();
		ben.setVorname(this.vorname);
		ben.setNachname(this.nachname);
		ben.setBenalter(this.benalter);
		Transaction transact = session.beginTransaction();
		session.save(ben);
		this.status = ben + " erstellt";
		transact.commit();
		session.close();
	}
}
