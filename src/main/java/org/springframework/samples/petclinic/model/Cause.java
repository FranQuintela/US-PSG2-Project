package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name= "description")
    private String description;

    @Column (name = "budget_target")
    private Integer budgetTarget;

    @Column (name = "organization")
    private String organization;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause")
    private Set<Donation> donations;
    
    // private Double amountAchieved;

    public Cause() {
    }

    public Cause(String name, String description, Integer budgetTarget, String organization) {
        this.name = name;
        this.description = description;
        this.budgetTarget = budgetTarget;
        this.organization = organization;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBudgetTarget() {
        return this.budgetTarget;
    }

    public void setbudgetTarget(Integer budgetTarget) {
        this.budgetTarget = budgetTarget;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Cause name(String name) {
        this.name = name;
        return this;
    }

    public Cause description(String description) {
        this.description = description;
        return this;
    }

    public Cause budgetTarget(Integer budgetTarget) {
        this.budgetTarget = budgetTarget;
        return this;
    }

    public Cause organization(String organization) {
        this.organization = organization;
        return this;
    }
    
    protected Set<Donation> getDonationsInternal() {
		if (this.donations == null) {
			this.donations = new HashSet<>();
		}
		return this.donations;
	}

	protected void setDonationsInternal(Set<Donation> donations) {
		this.donations = donations;
	}

	public List<Donation> getDonations() {
		List<Donation> sortedDonations = new ArrayList<>(getDonationsInternal());
		PropertyComparator.sort(sortedDonations, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedDonations);
	}

	public void addDonation(Donation donation) {
		getDonationsInternal().add(donation);
		donation.setCause(this);
    }
    

    public Double getAmountAchieved() {
        return this.donations.stream().mapToDouble(d -> d.getAmount()).sum();
    }

}