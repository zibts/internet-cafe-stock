package org;

import java.util.ArrayList;
import java.util.Collections;
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

import org.stocInVanzare;

@ManagedBean @SessionScoped
public class FormObiecteInVanzare {
	private stocInVanzare stocInVanzare;
	private List<stocInVanzare> stocuriInVanzare=new ArrayList<stocInVanzare>();
	private List<Inventar> Inventar = new ArrayList<Inventar>();
	private Boolean readOnlyId;
	
	public stocInVanzare getstocInVanzare() {
		return stocInVanzare;
	}
	public void setstocInVanzare(stocInVanzare stocInVanzare) {
		this.stocInVanzare = stocInVanzare;
	}
	public List<stocInVanzare> getstocuriInVanzare() {
		return stocuriInVanzare;
	}
	
	public List<Inventar> getInventar() {
		return Inventar;
	}
	public void setstocuriInVanzare(List<stocInVanzare> stocuriInVanzare) {
		this.stocuriInVanzare = stocuriInVanzare;
	}
	
	public Integer getIdInventar() {
		return this.stocInVanzare.getObiectId().getObiectId();
	}
	public void setIdInventar(Integer id) {
		this.stocInVanzare.setObiectId(em.find(Inventar.class, id));
	}
	
	public Boolean getReadOnlyId() {
		return readOnlyId;
	}
	
	


	EntityManager em;
	public FormObiecteInVanzare() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("InternetJPA");
		em=emf.createEntityManager();
		init();
	}
	public void init() {
		this.stocuriInVanzare=em.createQuery("select c from stocInVanzare c").getResultList();
		
		this.Inventar = em.createQuery("Select o from Inventar o", Inventar.class).getResultList();		
		if(this.Inventar != null && !this.Inventar.isEmpty())
			Collections.sort(this.Inventar, (c1,c2) -> c1.getObiectId().compareTo(c2.getObiectId()));

		this.stocInVanzare=this.stocuriInVanzare.get(0);
		this.readOnlyId = this.checkIfThere(this.stocInVanzare);
	}
	public void prevstocInVanzare(ActionEvent e) {
		Integer poz=this.stocuriInVanzare.indexOf(this.stocInVanzare);
		if(poz>0)
			this.stocInVanzare=this.stocuriInVanzare.get(poz-1);
		this.readOnlyId = this.checkIfThere(this.stocInVanzare);
	}

	public void nextstocInVanzare(ActionEvent e) {
		Integer poz=this.stocuriInVanzare.indexOf(this.stocInVanzare);
		if((poz+1)<this.stocuriInVanzare.size())
			this.stocInVanzare=this.stocuriInVanzare.get(poz+1);
		this.readOnlyId = this.checkIfThere(this.stocInVanzare);
	}

	public void adaugastocInVanzare(ActionEvent e) {
		this.stocInVanzare=new stocInVanzare();
		this.stocInVanzare.setOfertaVanzareId(0);
		this.stocInVanzare.setObiectId(this.stocuriInVanzare.get(0).getObiectId());
		this.stocInVanzare.setPretVanzare(0);
		this.stocuriInVanzare.add(this.stocInVanzare);
		this.readOnlyId = false;
	}
	
	public void salveazastocInVanzare(ActionEvent e) {
		Integer intermed = this.stocInVanzare.getOfertaVanzareId();
		this.readOnlyId = true;
		if (!em.getTransaction().isActive()) 
			em.getTransaction().begin();
		
		try {
			if(this.em.contains(this.stocInVanzare)) {
				em.merge(this.stocInVanzare);
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
			if(!this.em.contains(this.stocInVanzare)) {
				em.persist(this.stocInVanzare);	
			}
			this.stocInVanzare=this.stocuriInVanzare.get(0);
		
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
	
	
	
	public void stergestocInVanzare(ActionEvent e) {
		
//		Query q1 =em.createQuery("Select c FROM stocInVanzare c where c.obiectId=:obiectId");
//		q1.setParameter("obiectId", this.stocInVanzare);
//		
//		Query q2 = em.createQuery("Select c FROM furnizoriOferta c where c.obiect=:obiectId");
//		q2.setParameter("obiectId", this.stocInVanzare);
//		
//		Query q3 = em.createQuery("Select c FROM Statii c where c.mouseStatieId=:obiectId");
//		q3.setParameter("obiectId", this.stocInVanzare);
//		
//		if(q1.getResultList().size()!=0 || q2.getResultList().size()!=0 || q3.getResultList().size()!=0) {
//			
//			FacesMessage facesMsg = new FacesMessage("Asigurati-va ca elementul nu este prezent in oferte de vanzare sau in ofertele furnizorilor");			
//			FacesContext fc = FacesContext.getCurrentInstance();			
//			// Afisare mesaj
//			fc.addMessage(null, facesMsg);
//			fc.renderResponse();
//			return;
//		}
		
		if(this.stocuriInVanzare.size()==1) {
			FacesMessage facesMsg = new FacesMessage("Este nevoie de cel putin o valuare in tabel");			
			FacesContext fc = FacesContext.getCurrentInstance();			
			// Afisare mesaj
			fc.addMessage(null, facesMsg);
			fc.renderResponse();
			return;
		}
		try {
		this.stocuriInVanzare.remove(this.stocInVanzare);
		if(this.em.contains(this.stocInVanzare)) {
			this.em.getTransaction().begin();
			
			em.remove(this.stocInVanzare);
			this.stocuriInVanzare.remove(this.stocInVanzare);
			this.em.getTransaction().commit();
		}
		else {
			this.stocuriInVanzare.remove(this.stocInVanzare);
		}
		this.stocInVanzare=this.stocuriInVanzare.get(0);
		
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
		if(this.em.contains(this.stocInVanzare))
		em.refresh(this.stocInVanzare);
		init();
	}
	
	public Integer getIdstocInVanzare(){
		return this.stocInVanzare.getOfertaVanzareId();
		}	
	
	public void setIdstocInVanzare(Integer id){
		if(this.em.contains(this.stocInVanzare)) {
			this.stocInVanzare = em.find(stocInVanzare.class, id);
		}
	}
	
	public Boolean checkIfThere(stocInVanzare mouse) {
		if(em.find(mouse.getClass(), mouse.getOfertaVanzareId())!= null) {
			return true;
		}else{
			return false;
		}
	}
	
}