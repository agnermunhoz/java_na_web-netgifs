package br.com.fiap.netgifs.managedbean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import br.com.fiap.netgifs.entity.Gif;
import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.helper.GenericaDAO;
import br.com.fiap.netgifs.helper.GifDAO;

@ManagedBean
@ViewScoped
public class GifRegisterMBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Gif gif;
	private int id;
	private String description;
	private Part file;
	private String fileName = "";
	private int sessionId;
	private List<Session> sessions = null;

	public void newGif() {
		gif = new Gif();
		id = 0;
		description = "";
		file = null;
		fileName = "";
	}

	public void save() throws IOException {
		GifDAO gifDAO = new GifDAO();
		if (gif == null) gif = new Gif();

		gif.setDescription(description);
		
		InputStream is = file.getInputStream();
		gif.setInputStream(is);
		
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		Session session = sessionDAO.find(sessionId);
		gif.setSession(session);
		
		String message = gifDAO.saveOrUpdate(gif) ? "Saved!" : "Oops, something went wrong....";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		newGif();
	}

	public void search() {
		GifDAO gifDAO = new GifDAO();
		this.sessionId = 0;
		gif = (this.id == 0) ? gifDAO.find("select g from Gif g where g.description = ?", description) : gifDAO.find(id);
		if (gif != null) {
			this.id = gif.getId();
			this.description = gif.getDescription();
			this.sessionId = gif.getSession().getId();
		} else {
			newGif();
		}
	}

	public void delete() {
		GifDAO gifDAO = new GifDAO();
		gif = (this.id == 0) ? gifDAO.find("select g from Gif g where g.description = ?", description) : gifDAO.find(id);
		String message = gifDAO.delete(gif) ? "Deleted!" : "Oops, something went wrong....";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		newGif();
	}



	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Gif getGif() {
		return gif;
	}

	public void setGif(Gif gif) {
		this.gif = gif;
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

	public List<Session> getSessions() {
		if (sessions == null) {
			GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
			sessions = sessionDAO.list();
		}
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

}
