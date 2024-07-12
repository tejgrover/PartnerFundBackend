package com.PartnersFunds.Entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="xxpf_pages")
public class pages {

	@Id
	@Column(name = "page_id")
	private Integer page_id;
	
	@Column(name = "page_name")
	private String page_name;
	
	@Column(name = "title_bar")
	private String title_bar;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_by")
	private  String created_by;
	
	@Column(name = "creation_date")
	private Date creation_date;
	
	@Column(name = "last_updated_by")
	private String last_updated_by;
	
	@Column(name = "last_updated_date")
	private Date last_updated_date;

	public pages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public pages(Integer page_id, String page_name, String title_bar, String description, String created_by,
			Date creation_date, String last_updated_by, Date last_updated_date) {
		super();
		this.page_id = page_id;
		this.page_name = page_name;
		this.title_bar = title_bar;
		this.description = description;
		this.created_by = created_by;
		this.creation_date = creation_date;
		this.last_updated_by = last_updated_by;
		this.last_updated_date = last_updated_date;
	}

	public Integer getPage_id() {
		return page_id;
	}

	public void setPage_id(Integer page_id) {
		this.page_id = page_id;
	}

	public String getPage_name() {
		return page_name;
	}

	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}

	public String getTitle_bar() {
		return title_bar;
	}

	public void setTitle_bar(String title_bar) {
		this.title_bar = title_bar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getLast_updated_by() {
		return last_updated_by;
	}

	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public Date getLast_updated_date() {
		return last_updated_date;
	}

	public void setLast_updated_date(Date last_updated_date) {
		this.last_updated_date = last_updated_date;
	}
	
	
}
