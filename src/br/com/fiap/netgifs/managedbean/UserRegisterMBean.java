package br.com.fiap.netgifs.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.netgifs.entity.User;
import br.com.fiap.netgifs.helper.GenericaDAO;

@ManagedBean
@ViewScoped
public class UserRegisterMBean {
	private User user; 
	private int id;
	private String name;
	private String login;
	private String password;
	private boolean admin = false;
	
	public void userRegisterMBean() {
		this.id    = 0;
		this.name  = "";
		this.login = "";
		this.password = "";
		this.admin = false;
	}
	
	public String userRegister() {
        return "userRegister";
    }
	
	public void save() {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		String message = "";
		if (this.id != 0) 
			message = userDAO.update(new User(id, login, password, name, admin)) ? "Saved!" : "Oops, something went wrong....";
		else
			message = userDAO.save(new User(login, password, name, admin))       ? "Saved!" : "Oops, something went wrong....";	
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		userRegisterMBean();
    }
	
	public void search() {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		user = userDAO.find("select u from User u where u.login = ?", login);
		if (user != null) {
			this.id 	  = user.getId();
			this.name 	  = user.getName();
			this.login 	  = user.getLogin();
			this.password = user.getPassword();
			this.admin    = user.isAdmin();
		}
    }
	
	public void delete() {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		user = (this.id == 0) ? userDAO.find("select u from User u where u.login = ?", login) : userDAO.find(id); // se nao tiver id preenchido busca por login
		String message = userDAO.delete(user) ? "Deleted!" : "Oops, something went wrong....";
		userRegisterMBean();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
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
