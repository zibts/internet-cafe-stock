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
	private tipInventar tipInventar;
	private List<tipInventar> tipuriInventare=new ArrayList<tipInventar>();
	
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
	
	public tipInventar getTipInventar() {
		return tipInventar;
	}
	public void setTipInventar(tipInventar tipInventar) {
		this.tipInventar = tipInventar;
	}
	public List<tipInventar> getTipuriInventare() {
		return tipuriInventare;
	}
	public void setTipuriInventare(List<tipInventar> tipuriInventare) {
		this.tipuriInventare = tipuriInventare;
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
		if(this.tipuriInventare.isEmpty())
			this.tipuriInventare=em.createQuery("select t from tipInventar t").getResultList();
		this.calculator=this.calculatoare.get(0);
		this.tipInventar=this.tipuriInventare.get(0);
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
		this.calculator.setTipInventar(tipuriInventare.get(1));;
		this.calculator.setObiectNume("Nume Nou");;
		this.calculatoare.add(this.calculator);
	}
	
	public void salveazacalculator(ActionEvent e) {
		if (!em.getTransaction().isActive()) 
			em.getTransaction().begin();
		
		if(this.em.contains(this.calculator)) {
			em.merge(this.calculator);
		}
		else {
			em.persist(this.calculator);	
		}
		this.calculator=this.calculatoare.get(0);
		
		em.getTransaction().commit();
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
	
	public Integer getIdCalculator(){
		return this.calculator.getObiectId();
		}	
		public void setIdCalculator(Integer id){
		if(this.em.contains(this.calculator)) {
			this.calculator = em.find(calculator.class, id);
		}
		}

	public Integer getIdCalculatorInventar(){
		return this.calculator.getTipInventar().getTipId()+1;
	}
	
	public void setIdCalculatorInventar(Integer id){
		this.calculator.setTipInventar(this.calculator.getTipInventar());
	}
	
	



}

