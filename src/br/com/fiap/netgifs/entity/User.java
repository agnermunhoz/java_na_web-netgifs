package br.com.fiap.netgifs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USERID")
	private int id;
	@Column(name="LOGIN")
	private String login;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="NAME")
	private String name;
	@Column(name = "ADMIN")
	private boolean admin = false;
	@OneToMany(mappedBy="user", targetEntity=Favorite.class, fetch=FetchType.LAZY)
	private List<Favorite> favorites;
	public User() {
		super();
	}
	public User(String login, String password, String name, boolean admin) {
		this();
		this.login = login;
		this.password = password;
		this.name = name;
		this.admin = admin;
		this.favorites = new ArrayList<>();
	}
	public User(int id, String login, String password, String name, boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.admin = admin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public List<Favorite> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", name=" + name + "]";
	}

}
