package pleyer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/*
 * Benutzer Abfragen Klasse 
 * @author Pleyer Matthias, @date 29.09.17
 */
public class BenutzerLesen {
	private List<Benutzer> benutzer;

	public List<Benutzer> getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(List<Benutzer> benutzer) {
		this.benutzer = benutzer;
	}

	@SuppressWarnings("deprecation")
	public void leseBenutzer() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction trans = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Benutzer> q = session.createQuery("from Benutzer");
		benutzer = q.list();
		trans.commit();

	}
}