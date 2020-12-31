package org;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.calculator;

@ManagedBean @SessionScoped
public class FormCalculatoare {
	private calculator calculator;
	private List<calculator> calculatoare=new ArrayList<calculator>();
	
	public calculator getcalculator() {
		return calculator;
	}
	public void setcalculator(calculator calculator) {
		this.calculator = calculator;
	}
	public List<calculator> getcalculatoare() {
		return calculatoare;
	}
	public void setcalculatoare(List<calculator> calculatoare) {
		this.calculatoare = calculatoare;
	}
	EntityManager em;

	public FormCalculatoare() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("InternetJPA");
		em=emf.createEntityManager();
		init();
	}
	public void init() {
		if(this.calculatoare.isEmpty())
			this.calculatoare=em.createQuery("select c from calculator c").getResultList();
		this.calculator=this.calculatoare.get(0);
	}
	public void prevcalculator(ActionEvent e) {
		Integer poz=this.calculatoare.indexOf(this.calculator);
		if(poz>0)
			this.calculator=this.calculatoare.get(poz-1);
	}

	public void nextcalculator(ActionEvent e) {
		Integer poz=this.calculatoare.indexOf(this.calculator);
		if((poz+1)<this.calculatoare.size())
			this.calculator=this.calculatoare.get(poz+1);
	}

	public void adaugacalculator(ActionEvent e) {
		this.calculator=new calculator();
		this.calculator.setObiectId(999);
		this.calculator.setObiectNume("DellXPSXD");;
		this.calculatoare.add(this.calculator);
	}
	public void salveazacalculator(ActionEvent e) {
		if(this.em.contains(this.calculator)) {
		em.getTransaction().begin();
		em.merge(this.calculator);
		em.getTransaction().commit();}
		this.calculator=this.calculatoare.get(0);


	}
	public void stergecalculator(ActionEvent e) {
		this.calculatoare.remove(this.calculator);
		if(this.em.contains(this.calculator)) {
			this.em.getTransaction().begin();
			this.em.remove(this.calculator);
			this.em.getTransaction().commit();
		}
		this.calculator=this.calculatoare.get(0);

	}
	public void abandon(ActionEvent e) {
		if(this.em.contains(this.calculator))
		em.refresh(this.calculator);
		init();
	}


	
	



}
