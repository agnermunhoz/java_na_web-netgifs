package br.com.fiap.netgifs.entity;

import java.io.InputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGEID",referencedColumnName="IMAGEID")
	private Image image;
	@Column(name = "IMAGEID", insertable = false, updatable = false)
	private int imageId;
	@ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name = "MINIID",referencedColumnName="IMAGEID")
	private Image mini;
	@Column(name = "MINIID", insertable = false, updatable = false)
	private int miniId;
	@ManyToOne(optional=false)
	@JoinColumn(name="SESSIONID",referencedColumnName="SESSIONID")
	private Session session;
	@Transient
	private InputStream inputStream;
	@Transient
	private String imageFormat;
	public Gif() {
		super();
	}
	public Gif(String description, Image image, Image mini, Session session) {
		this();
		this.description = description;
		this.image = image;
		this.mini = mini;
		this.session = session;
		this.inputStream = null;
	}
	public Gif(String description, Session session, InputStream inputStream, String imageFormat) {
		this();
		this.description = description;
		this.inputStream = inputStream;
		this.mini = null;
		this.image = null;
		this.session = session;
		this.imageFormat = imageFormat;
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
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getImageFormat() {
		return imageFormat;
	}
	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
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
		Gif other = (Gif) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
