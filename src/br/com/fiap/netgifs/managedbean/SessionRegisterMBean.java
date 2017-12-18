package br.com.fiap.netgifs.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	public void save() {
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		String message = "";
		if (this.id != 0) 
			message = sessionDAO.update(new Session(id, description)) ? "Saved!" : "Oops, something went wrong....";
		else
			message = sessionDAO.save(new Session(description)) ? "Saved!" : "Oops, something went wrong....";
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		newSession();
    }
	
	public void search() {
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		session = (this.id == 0) ? sessionDAO.find("select s from Session s where s.description = ?", description) : sessionDAO.find(id);
		if (session != null) {
			this.id = session.getId();
			this.description = session.getDescription();
		}
    }
	
	public void delete() {
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		session = (this.id == 0) ? sessionDAO.find("select s from Session s where s.description = ?", description) : sessionDAO.find(id);
		String message = sessionDAO.delete(session) ? "Deleted!" : "Oops, something went wrong....";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
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
