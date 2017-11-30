package br.com.fiap.netgifs.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fiap.netgifs.entity.User;
import br.com.fiap.netgifs.helper.GenericaDAO;

@ManagedBean
@SessionScoped
public class UserBean {

	private User user;
	private String login;
	private String password;
	public String submit() {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		user = userDAO.find("select u from User u where u.login = ?", login);
		if (user == null) {
			return ".";
		}
		return "gifs/main?faces-redirect=true";
	}
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        return "/index?faces-redirect=true";
    }

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
}
