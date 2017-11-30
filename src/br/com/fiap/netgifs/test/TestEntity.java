package br.com.fiap.netgifs.test;

import br.com.fiap.netgifs.entity.Gif;
import br.com.fiap.netgifs.entity.Image;
import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.entity.User;
import br.com.fiap.netgifs.helper.GenericaDAO;
import br.com.fiap.netgifs.listener.LocalEntityManagerFactory;

public class TestEntity {

	public static void main(String[] args) {
		
		new LocalEntityManagerFactory().contextInitialized(null);
		
		User user;// = new User("teste", "123456", "Admin", "admin");
		
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		
		//userDAO.saveOrUpdate(user);
		
		//System.out.println(user.toString());
		
		user = userDAO.find(1);
		
		System.out.println(user.toString());
		
        user = userDAO.find("select u from User u where u.login = ?", "admin");
		
		System.out.println(user.toString());
		
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		GenericaDAO<Image> imgDAO = new GenericaDAO<>(Image.class);
		GenericaDAO<Gif> gifDAO = new GenericaDAO<>(Gif.class);
		
		//Session session = sessionDAO.find(1);
		
		//Image img = new Image();
		
		//imgDAO.saveOrUpdate(img);
		
		Gif gif = gifDAO.find(3);
		
		//gifDAO.saveOrUpdate(gif);

		System.out.println(gif.getImageId());
		System.out.println(gif.getMiniId());
		
		//gif = new Gif("Gif 2", img, img, session);
		
		//gifDAO.saveOrUpdate(gif);
		
		new LocalEntityManagerFactory().contextDestroyed(null);
	}

}
