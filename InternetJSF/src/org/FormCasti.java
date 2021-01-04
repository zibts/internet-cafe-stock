package org;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.perifericCasti;

@ManagedBean @SessionScoped
public class FormCasti {
	private perifericCasti perifericCasti;
	private List<perifericCasti> perifericeCasti=new ArrayList<perifericCasti>();
	private tipInventar tipInventar;
	private List<tipInventar> tipuriInventare=new ArrayList<tipInventar>();
	
	public perifericCasti getperifericCasti() {
		return perifericCasti;
	}
	public void setperifericCasti(perifericCasti perifericCasti) {
		this.perifericCasti = perifericCasti;
	}
	public List<perifericCasti> getperifericeCasti() {
		return perifericeCasti;
	}
	public void setperifericeCasti(List<perifericCasti> perifericeCasti) {
		this.perifericeCasti = perifericeCasti;
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
	public FormCasti() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("InternetJPA");
		em=emf.createEntityManager();
		init();
	}
	public void init() {
		this.perifericeCasti=em.createQuery("select c from perifericCasti c").getResultList();
		this.perifericCasti=this.perifericeCasti.get(0);
	}
	public void prevperifericCasti(ActionEvent e) {
		Integer poz=this.perifericeCasti.indexOf(this.perifericCasti);
		if(poz>0)
			this.perifericCasti=this.perifericeCasti.get(poz-1);
	}

	public void nextperifericCasti(ActionEvent e) {
		Integer poz=this.perifericeCasti.indexOf(this.perifericCasti);
		if((poz+1)<this.perifericeCasti.size())
			this.perifericCasti=this.perifericeCasti.get(poz+1);
	}

	public void adaugaperifericCasti(ActionEvent e) {
		this.perifericCasti=new perifericCasti();
		this.perifericCasti.setObiectId(999);
		this.perifericCasti.setObiectNume("Nume Nou");
		this.perifericCasti.setStocTotal(0);
		this.perifericCasti.setTipInventar(this.perifericeCasti.get(0).getTipInventar());
		this.perifericeCasti.add(this.perifericCasti);
	}
	
	public void salveazaperifericCasti(ActionEvent e) {
		Integer intermed = this.perifericCasti.getObiectId();
		if (!em.getTransaction().isActive()) 
			em.getTransaction().begin();
		
		try {
			if(this.em.contains(this.perifericCasti)) {
				em.merge(this.perifericCasti);
			}
		}
		catch(Exception ex) {
			FacesMessage facesMsg = 
		            new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);			
			
			FacesContext fc = FacesContext.getCurrentInstance();			
			
			// Afisare mesaj
			fc.addMessage(null, facesMsg);
			fc.renderResponse();
			if (!em.getTransaction().isActive()) 
				em.getTransaction().begin();
			em.getTransaction().rollback();
		}
		
		try {
			if(!this.em.contains(this.perifericCasti)) {
				this.perifericCasti.setStocDisponibil(this.perifericCasti.getStocTotal());
				em.persist(this.perifericCasti);	
			}
			this.perifericCasti=this.perifericeCasti.get(0);
		
			em.getTransaction().commit();
		}
		catch(Exception ex) {
			FacesMessage facesMsg = 
		            new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);			
			
			FacesContext fc = FacesContext.getCurrentInstance();			
			
			// Afisare mesaj
			
			fc.addMessage(null, facesMsg);
			fc.renderResponse();
			if (!em.getTransaction().isActive()) 
				em.getTransaction().begin();
			em.getTransaction().rollback();
		}
	}
	
	
	
	public void stergeperifericCasti(ActionEvent e) {
		
		Query q1 =em.createQuery("Select c FROM stocInVanzare c where c.obiectId=:obiectId");
		q1.setParameter("obiectId", this.perifericCasti);
		
		Query q2 = em.createQuery("Select c FROM furnizoriOferta c where c.obiect=:obiectId");
		q2.setParameter("obiectId", this.perifericCasti);
		
		Query q3 = em.createQuery("Select c FROM Statii c where c.castiStatieId=:obiectId");
		q3.setParameter("obiectId", this.perifericCasti);
		
		if(q1.getResultList().size()!=0 || q2.getResultList().size()!=0 || q3.getResultList().size()!=0) {
			
			FacesMessage facesMsg = new FacesMessage("Asigurati-va ca elementul nu este prezent in oferte de vanzare sau in ofertele furnizorilor");			
			FacesContext fc = FacesContext.getCurrentInstance();			
			// Afisare mesaj
			fc.addMessage(null, facesMsg);
			fc.renderResponse();
			return;
		}
		
		if(this.perifericeCasti.size()==1) {
			FacesMessage facesMsg = new FacesMessage("Este nevoie de cel putin o valuare in tabel");			
			FacesContext fc = FacesContext.getCurrentInstance();			
			// Afisare mesaj
			fc.addMessage(null, facesMsg);
			fc.renderResponse();
			return;
		}
		try {
		this.perifericeCasti.remove(this.perifericCasti);
		if(this.em.contains(this.perifericCasti)) {
			this.em.getTransaction().begin();
			
			em.remove(this.perifericCasti);
			this.perifericeCasti.remove(this.perifericCasti);
			this.em.getTransaction().commit();
		}
		else {
			this.perifericeCasti.remove(this.perifericCasti);
		}
		this.perifericCasti=this.perifericeCasti.get(0);
		
		}catch(StackOverflowError | Exception ex) {
			FacesMessage facesMsg = 
		            new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE Stergere: " + ex.getMessage(), null);			
			FacesContext fc = FacesContext.getCurrentInstance();			
			// Afisare mesaj
			fc.addMessage(null, facesMsg);
			fc.renderResponse();
			ex.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Item is in other places");
		}
	}
	public void abandon(ActionEvent e) {
		if(this.em.contains(this.perifericCasti))
		em.refresh(this.perifericCasti);
		init();
	}
	
	public Integer getIdperifericCasti(){
		return this.perifericCasti.getObiectId();
		}	
	
	public void setIdperifericCasti(Integer id){
		if(this.em.contains(this.perifericCasti)) {
			this.perifericCasti = em.find(perifericCasti.class, id);
		}
	}
	
}
