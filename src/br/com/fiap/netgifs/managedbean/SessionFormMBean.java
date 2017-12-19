package br.com.fiap.netgifs.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fiap.netgifs.core.FormMBeanImpl;
import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.helper.GenericaDAO;

@ManagedBean
@ViewScoped
public class SessionFormMBean extends FormMBeanImpl<Session> {

	@Override
	protected GenericaDAO<Session> createDao() {
		return new GenericaDAO<>(Session.class);
	}

	@Override
	protected Session createEntity() {
		return new Session();
	}

}
