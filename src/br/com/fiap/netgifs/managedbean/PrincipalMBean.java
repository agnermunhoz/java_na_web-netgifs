package br.com.fiap.netgifs.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.netgifs.entity.Favorite;
import br.com.fiap.netgifs.entity.Gif;
import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.entity.User;
import br.com.fiap.netgifs.helper.GenericaDAO;

@ManagedBean
@SessionScoped
public class PrincipalMBean {

	// Controle de acesso
	private User user;
	private String login;
	private String password;
	// Navegação principal
	private List<Session> sessions = null;
	private Session selectedSession = null;
	private Session favoriteSession = null;
	private Gif selectedGif = null;

	// Controle de acesso
	public String doLogin() {
		GenericaDAO<User> userDAO = new GenericaDAO<>(User.class);
		user = userDAO.find("select u from User u where u.login = ?", login);
		if (user == null) {
			//TODO enviar messagem de usuário não encontrado
			this.login = "";
			this.password = "";
			return "index?faces-redirect=true";
		}
		if (!user.getPassword().equals(password)) {
			//TODO enviar messagem de password inválido
			this.login = "";
			this.password = "";
			user = null;
			return "index?faces-redirect=true";
		}
		return "private/main?faces-redirect=true";
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.login = "";
		this.password = "";
		user = null;
		return "../index?faces-redirect=true";
	}
	
	public boolean isLoggedin() {
		return (user != null);
	}

	public String watchGif() {
		System.out.println("Submit da gif");
		if (selectedGif != null) System.out.println(selectedGif.getId()+" - "+selectedGif.getDescription());
		return "watch?faces-redirect=true";
	}
	
	public String gifRegister() {
		sessions = null;
		return "../admin/gifForm?faces-redirect=true";
	}

	public String sessionRegister() {
		sessions = null;
		//return "../admin/sessionRegister?faces-redirect=true";
		return "../admin/sessionForm?faces-redirect=true";
	}

	public String userRegister() {
		return "../admin/userForm?faces-redirect=true";
	}

	public String changeSession() {
		System.out.println("SubmitSession da gif");
		if (selectedSession != null) System.out.println(selectedSession.getId()+" - "+selectedSession.getDescription());
		return "main?faces-redirect=true";
	}

	public boolean isSelectedGifFavorite() {
		if (selectedGif == null)
			return false;
		if (favoriteSession == null)
			return false;
		boolean resp = favoriteSession.getGifs().contains(selectedGif);
		return resp;
	}

	public String addFavorite() {
		System.out.println("Add gif to favorite");
		if (selectedGif != null) {
			System.out.println(selectedGif.getId()+" - "+selectedGif.getDescription());
			GenericaDAO<Favorite> favoriteDao = new GenericaDAO<>(Favorite.class);
			Favorite favorite = new Favorite(user, selectedGif);
			favoriteDao.saveOrUpdate(favorite);
			user.getFavorites().add(favorite);
			favoriteSession.getGifs().add(selectedGif);
		}
		return "watch?faces-redirect=true";
	}

	public String removeFavorite() {
		System.out.println("Remove gif from favorite");
		if (selectedGif != null) {
			System.out.println(selectedGif.getId()+" - "+selectedGif.getDescription());
			GenericaDAO<Favorite> favoriteDao = new GenericaDAO<>(Favorite.class);
			Favorite favorite = favoriteDao.find("select f from Favorite f where f.gif = ? and f.user = ?", selectedGif, user);
			if (favorite != null) {
				favoriteDao.delete(favorite);
				user.getFavorites().remove(favorite);
				favoriteSession.getGifs().remove(favorite.getGif());
			}

		}
		return "watch?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public List<Session> getSessions() {
		if (sessions == null) {
			GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
			GenericaDAO<Gif> gifDAO = new GenericaDAO<>(Gif.class);

			sessions = sessionDAO.list();

			Session session = new Session();
			session.setId(-1);
			session.setDescription("Todos");
			session.setGifs(gifDAO.list());
			sessions.add(0, session);
			selectedSession = session;

			favoriteSession = new Session();
			favoriteSession.setId(-2);
			favoriteSession.setDescription("Favoritos");
			List<Gif> favorites = new ArrayList<>();
			for (Favorite f: user.getFavorites())
				favorites.add(f.getGif());
			favoriteSession.setGifs(favorites);

			sessions.add(0, favoriteSession);

		}
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	public Session getSelectedSession() {
		return selectedSession;
	}
	public void setSelectedSession(Session selectedSession) {
		this.selectedSession = selectedSession;
	}
	public Session getFavoriteSession() {
		return favoriteSession;
	}
	public void setFavoriteSession(Session favoriteSession) {
		this.favoriteSession = favoriteSession;
	}
	public Gif getSelectedGif() {
		return selectedGif;
	}
	public void setSelectedGif(Gif selectedGif) {
		this.selectedGif = selectedGif;
	}

}
