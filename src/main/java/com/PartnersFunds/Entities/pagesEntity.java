package com.PartnersFunds.Entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="xxpf_pages")
public class pagesEntity {

	@Id
	@Column(name = "page_id")
	private Integer page_id;
	
	@Column(name = "page_name")
	private String page_name;
	
	@Column(name = "page_file_name")
	private String page_file_name;
	
	@Column(name = "page_title")
	private String page_title;
	
	@Column(name = "parent_page_id")
	private Integer parent_page_id;
	
	@Column(name = "created_by")
	private  String created_by;
	
	@Column(name = "creation_date")
	private Date creation_date;
	
	@Column(name = "last_updated_by")
	private String last_updated_by;
	
	@Column(name = "last_update_date")
	private Date last_update_date;

	public pagesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public pagesEntity(Integer page_id, String page_name, String page_file_name, String page_title,
			Integer parent_page_id, String created_by, Date creation_date, String last_updated_by,
			Date last_update_date) {
		super();
		this.page_id = page_id;
		this.page_name = page_name;
		this.page_file_name = page_file_name;
		this.page_title = page_title;
		this.parent_page_id = parent_page_id;
		this.created_by = created_by;
		this.creation_date = creation_date;
		this.last_updated_by = last_updated_by;
		this.last_update_date = last_update_date;
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

	public String getPage_file_name() {
		return page_file_name;
	}

	public void setPage_file_name(String page_file_name) {
		this.page_file_name = page_file_name;
	}

	public String getPage_title() {
		return page_title;
	}

	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}

	public Integer getParent_page_id() {
		return parent_page_id;
	}

	public void setParent_page_id(Integer parent_page_id) {
		this.parent_page_id = parent_page_id;
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

	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	
}
