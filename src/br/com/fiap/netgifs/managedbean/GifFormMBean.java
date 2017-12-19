package br.com.fiap.netgifs.managedbean;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import br.com.fiap.netgifs.core.FormMBeanImpl;
import br.com.fiap.netgifs.entity.Gif;
import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.helper.GenericaDAO;
import br.com.fiap.netgifs.helper.GifDAO;

@ManagedBean
@ViewScoped
public class GifFormMBean extends FormMBeanImpl<Gif> {

	private Part file;
	private int sessionId;
	private List<Session> sessions = null;

	@Override
	protected GenericaDAO<Gif> createDao() {
		return new GifDAO();
	}

	@Override
	protected Gif createEntity() {
		file = null;
		return new Gif();
	}

	@Override
	public void saveEntity() {
		try {
			System.out.println("File name:"+file.getName());
			System.out.println("File size: "+file.getSize());
			System.out.println("File type: "+file.getContentType());
			if (file != null && file.getSize() > 0) {
				InputStream is = file.getInputStream();
				getEntity().setInputStream(is);
				getEntity().setImageFormat(file.getContentType());
			}
			GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
			Session session = sessionDAO.find(sessionId);
			getEntity().setSession(session);		
			super.saveEntity();
		} catch (Exception e) {
			e.printStackTrace();
			newEntity();
		}
	}

	@Override
	public void selectEntity() {
		file = null;
		sessionId = getEntity().getSession().getId();
		super.selectEntity();
	}

	public List<Session> getSessions() {
		if (sessions == null) {
			GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
			sessions = sessionDAO.list();
		}
		return sessions;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
}
