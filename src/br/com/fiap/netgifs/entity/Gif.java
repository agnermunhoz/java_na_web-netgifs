package br.com.fiap.netgifs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="GIF")
public class Gif  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GIF-ID")
	private int id;
	@Column(name="DESCRIPTION")
	private String description;
	@OneToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE-ID",referencedColumnName="IMAGE-ID")
	private Image image;
	@OneToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name = "MINI-ID",referencedColumnName="IMAGE-ID")
	private Image mini;
	@ManyToOne(optional=false)
	@JoinColumn(name="SESSION-ID",referencedColumnName="SESSION-ID")
	private Session session;
	public Gif() {
		super();
	}
	public Gif(String description, Image image, Image mini, Session session) {
		this();
		this.description = description;
		this.image = image;
		this.mini = mini;
		this.session = session;
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
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Image getMini() {
		return mini;
	}
	public void setMini(Image mini) {
		this.mini = mini;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

}
