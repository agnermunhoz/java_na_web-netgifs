package br.com.fiap.netgifs.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.netgifs.entity.User;
import br.com.fiap.netgifs.helper.GenericaDAO;

@ManagedBean
@SessionScoped
public class UserRegisterMBean {
	private User user; 
	private int id;
	private String name;
	private String login;
	private String password;
	private boolean admin = false;
	
	public String userRegister() {
        return "userRegister";
    }
	
	// TODO: msg retorno
	public String save() {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		if (this.id != 0) 
			userDAO.update(new User(id, login, password, name, admin));
		else
			userDAO.save(new User(login, password, name, admin));	
			
		return "userRegister";
    }
	
	public String search() {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		user = userDAO.find("select u from User u where u.login = ?", login);
		if (user != null) {
			this.id 	  = user.getId();
			this.name 	  = user.getName();
			this.login 	  = user.getLogin();
			this.password = user.getPassword();
			this.admin    = user.isAdmin();
		}
		return "userRegister";
    }
	
	// TODO: msg retorno
	public String delete() {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		user = userDAO.find(id);
		userDAO.delete(user);
		user = null;
		return "userRegister";
	}
	
	public int getId() {
		return id;
	}
	public String getLogin() {
		return this.login;
	}
	public String getPassword() {
		return this.password;
	}
	public String getName() {
		return this.name;
	}
	public boolean getAdmin() {
		return this.admin;
	}
	
	
	public void setId(int id) {
		this.id = id;
	} 
	public void setName(String name) {
		this.name = name;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
