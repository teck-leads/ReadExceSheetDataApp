package com.springdatajpa.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STS_CTY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class States {
	@Id
	@Column(name = "ID")
	private Integer id;
	@Column(name = "STATE_NAME")
	private String name;
	@Column(name = "CAPITAL")
	private String capital;
	@Column(name = "CODE")
	private String code;

}
