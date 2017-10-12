package pleyer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
 * Benutzer Löschen Klasse 
 * @author Pleyer Matthias, @date 29.09.17
 */
public class BenutzerLoeschen {
	private int zuLoeschendeID;
	private String status = "";

	public int getZuLoeschendeID() {
		return zuLoeschendeID;
	}

	public void setZuLoeschendeID(int zuLoeschendeID) {
		this.zuLoeschendeID = zuLoeschendeID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void loescheBenutzer() {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transact = session.beginTransaction();
			Benutzer ben = session.get(Benutzer.class, this.zuLoeschendeID);
			session.delete(ben);
			transact.commit();
			this.status = ben + " entfernt";
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			this.status = "Ausgewählte ID stimmt mit keiner aus der Datenbank überein";
		}
	}
}
