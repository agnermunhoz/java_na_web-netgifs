package br.com.fiap.netgifs.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.helper.GenericaDAO;

@ManagedBean
@ViewScoped
public class SessionRegisterMBean {
	
	private Session session;
	private int id;
	private String description;
	
	public String sessionRegister() {
        return "sessionRegister";
    }
	
	// TODO: msg retorno
	public void save() {
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		if (this.id != 0) 
			sessionDAO.update(new Session(id, description));
		else
			sessionDAO.save(new Session(description));	
		newSession();
    }
	
	public void search() {
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		session = sessionDAO.find(id);
		if (session != null) {
			this.id = session.getId();
			this.description = session.getDescription();
		}
    }
	
	// TODO: msg retorno
	public void delete() {
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		session = sessionDAO.find(id);
		sessionDAO.delete(session);
		newSession();
	}
	
	public void newSession() {
		session = null;
		this.id = 0;
		this.description = "";
	}
	
	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
