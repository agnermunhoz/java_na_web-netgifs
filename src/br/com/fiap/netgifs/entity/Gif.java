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
	@Column(name="GIFID")
	private int id;
	@Column(name="DESCRIPTION")
	private String description;
	@OneToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGEID",referencedColumnName="IMAGEID")
	private Image image;
	@Column(name = "IMAGEID", insertable = false, updatable = false)
	private int imageId;
	@OneToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name = "MINIID",referencedColumnName="IMAGEID")
	private Image mini;
	@Column(name = "MINIID", insertable = false, updatable = false)
	private int miniId;
	@ManyToOne(optional=false)
	@JoinColumn(name="SESSIONID",referencedColumnName="SESSIONID")
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
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public int getMiniId() {
		return miniId;
	}
	public void setMiniId(int miniId) {
		this.miniId = miniId;
	}
}
