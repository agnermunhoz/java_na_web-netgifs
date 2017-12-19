package br.com.fiap.netgifs.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fiap.netgifs.core.FormMBeanImpl;
import br.com.fiap.netgifs.entity.User;
import br.com.fiap.netgifs.helper.GenericaDAO;

@ManagedBean
@ViewScoped
public class UserFormMBean extends FormMBeanImpl<User> {

	@Override
	protected GenericaDAO<User> createDao() {
		return new GenericaDAO<>(User.class);
	}

	@Override
	protected User createEntity() {
		return new User();
	}

}
