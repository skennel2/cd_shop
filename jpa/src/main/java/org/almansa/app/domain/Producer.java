package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Producer{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	@Column(name="producer_name")
	private String name;

	@ManyToOne()
	@JoinColumn(name="agency_company_id")
	private AgencyCompany agencyCompany;
	
	public Producer(Long id, String name, AgencyCompany agencyCompany) {
		super();
		this.id = id;
		this.name = name;
		this.agencyCompany = agencyCompany;
	}

	public Producer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public AgencyCompany getAgencyCompany() {
		return agencyCompany;
	}

	public void setAgencyCompany(AgencyCompany agencyCompany) {
		this.agencyCompany = agencyCompany;
	}

	@Override
	public String toString() {
		return "Producer [id=" + id + ", name=" + name + ", agencyCompany=" + agencyCompany + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Producer other = (Producer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}