package com.pd.binding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "COUNTRY_MASTER")
@Data
public class UserCountry {
	@Id
	@GeneratedValue
	@Column(name="COUNTRY_ID")
	private Integer cId;
	@Column(name="COUNTRY_NAME")
	private String cName;

}
