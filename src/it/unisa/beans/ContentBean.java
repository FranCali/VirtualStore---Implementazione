package it.unisa.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ContentBean implements Serializable {

	private int id, size;
	private float price;
	private String type, name, version, icon, description, cover;

	public ContentBean() {
		id = -1;
		type = "";
		name = "";
		size = -1;
		price = 0;
		version = "";
		icon = "";
		cover = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@Override
	public String toString() {
		return "ContentBean [id=" + id + ", size=" + size + ", price=" + price + ", type=" + type + ", name=" + name
				+ ", version=" + version + ", icon=" + icon + ", description=" + description + ", cover=" + cover + "]";
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

}
