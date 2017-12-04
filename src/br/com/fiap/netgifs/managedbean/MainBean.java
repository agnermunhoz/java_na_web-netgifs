package br.com.fiap.netgifs.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.fiap.netgifs.entity.Favorite;
import br.com.fiap.netgifs.entity.Gif;
import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.helper.GenericaDAO;

@ManagedBean
@SessionScoped
public class MainBean {

	private List<Session> sessions = null;
	private Session selectedSession = null;
	private Gif selectedGif = null;
	//@ManagedProperty(value="#{userBean}") 
	//private UserBean userBean;

	public List<Session> getSessions() {
		if (sessions == null) {
			GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
			GenericaDAO<Gif> gifDAO = new GenericaDAO<>(Gif.class);
			
			sessions = sessionDAO.list();
			
			Session session = new Session();
			session.setId(-1);
			session.setDescription("All");
			session.setGifs(gifDAO.list());
			sessions.add(0, session);
			selectedSession = session;
					
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UserBean userBean = (UserBean) elContext.getELResolver().getValue(elContext, null, "userBean");
		    
			session = new Session();
			session.setId(-2);
			session.setDescription("Favorite");
			List<Gif> favorites = new ArrayList<>();
			for (Favorite f: userBean.getUser().getFavorites())
				favorites.add(f.getGif());
			session.setGifs(favorites);
			sessions.add(0, session);
			
		}
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public String submit() {
		System.out.println("Submit da gif");
		if (selectedGif != null) System.out.println(selectedGif.getId()+" - "+selectedGif.getDescription());
		return "watch?faces-redirect=true";
	}
	
	public Session getSelectedSession() {
		return selectedSession;
	}

	public void setSelectedSession(Session selectedSession) {
		this.selectedSession = selectedSession;
	}
	
	public Gif getSelectedGif() {
		return selectedGif;
	}

	public void setSelectedGif(Gif selectedGif) {
		this.selectedGif = selectedGif;
	}

	
}
