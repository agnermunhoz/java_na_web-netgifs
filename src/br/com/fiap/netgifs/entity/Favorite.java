package br.com.fiap.netgifs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FAVORITE")
public class Favorite implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FAVORITEID")
	private int id;
	@ManyToOne(optional=false)
	@JoinColumn(name="USERID",referencedColumnName="USERID")
	private User user;
	@ManyToOne(optional=false)
	@JoinColumn(name="GIFID",referencedColumnName="GIFID")
	private Gif gif;
	
	public Favorite() {
		super();
	}
	public Favorite(User user, Gif gif) {
		this();
		this.user = user;
		this.gif = gif;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Gif getGif() {
		return gif;
	}
	public void setGif(Gif gif) {
		this.gif = gif;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Favorite other = (Favorite) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
