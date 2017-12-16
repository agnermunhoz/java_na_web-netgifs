package br.com.fiap.netgifs.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sun.org.apache.bcel.internal.generic.F2D;

import br.com.fiap.netgifs.entity.Favorite;
import br.com.fiap.netgifs.entity.Gif;
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
			//TODO enviar messagem de usuário não encontrado
			//FacesContext.getCurrentInstance().addMessage("txtnome", new FacesMessage("User not found!"));
			return ".";
		}
		if (!user.getPassword().equals(password)) {
			//TODO enviar messagem de password inválido
			//FacesContext.getCurrentInstance().addMessage("txtsenha", new FacesMessage("Invalid password!"));
		    return ".";
		}
		return "main?faces-redirect=true";
	}
	
	public void addFavorite(Gif gif) {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		Favorite favorite = new Favorite(user, gif);
		user.getFavorites().add(favorite);
		userDAO.saveOrUpdate(user);
	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        return "index?faces-redirect=true";
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
