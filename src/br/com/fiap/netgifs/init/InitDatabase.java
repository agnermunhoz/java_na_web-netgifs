package br.com.fiap.netgifs.init;

import java.io.InputStream;

import br.com.fiap.netgifs.entity.Gif;
import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.entity.User;
import br.com.fiap.netgifs.helper.GenericaDAO;
import br.com.fiap.netgifs.helper.GifDAO;
import br.com.fiap.netgifs.listener.LocalEntityManagerFactory;

public class InitDatabase {

	public static void main(String[] args) {
		new LocalEntityManagerFactory().contextInitialized(null);
	
		initDataBase();
		
		new LocalEntityManagerFactory().contextDestroyed(null);
	}

	private static void initDataBase() {
		System.out.println("Carga inicial de dados");
		try {
			System.out.println("Verificar usuário admin");
			GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
			User user = userDAO.find("select u from User u where u.login = ?", "admin");
			if (user == null) {
				user = new User("admin", "admin", "Admin User", true);
				userDAO.saveOrUpdate(user);
			}
			System.out.println("Verificar sessions");
			GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
			Session session = sessionDAO.find(1);
			if (session == null) {
				session = new Session("Exemplo");
				sessionDAO.saveOrUpdate(session);
			}
			
			GifDAO gifDAO = new GifDAO();
			Gif gif = gifDAO.find(1);
			if (gif == null) {
				InputStream is = InitDatabase.class.getResourceAsStream("/example1.gif");
				gif = new Gif("Kill me", session, is);
				gifDAO.saveOrUpdate(gif);
				is =  InitDatabase.class.getResourceAsStream("/example2.gif");
				gif = new Gif("Orcas", session, is);
				gifDAO.saveOrUpdate(gif);
			}
		} catch (Exception e) {
			
		}
	}
	
}
