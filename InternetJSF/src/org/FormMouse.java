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

import org.perifericMouse;

@ManagedBean @SessionScoped
public class FormMouse {
	private perifericMouse perifericMouse;
	private List<perifericMouse> perifericeMouse=new ArrayList<perifericMouse>();
	private tipInventar tipInventar;
	private List<tipInventar> tipuriInventare=new ArrayList<tipInventar>();
	
	public perifericMouse getperifericMouse() {
		return perifericMouse;
	}
	public void setperifericMouse(perifericMouse perifericMouse) {
		this.perifericMouse = perifericMouse;
	}
	public List<perifericMouse> getperifericeMouse() {
		return perifericeMouse;
	}
	public void setperifericeMouse(List<perifericMouse> perifericeMouse) {
		this.perifericeMouse = perifericeMouse;
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
	public FormMouse() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("InternetJPA");
		em=emf.createEntityManager();
		init();
	}
	public void init() {
		this.perifericeMouse=em.createQuery("select c from perifericMouse c").getResultList();
		this.perifericMouse=this.perifericeMouse.get(0);
	}
	public void prevperifericMouse(ActionEvent e) {
		Integer poz=this.perifericeMouse.indexOf(this.perifericMouse);
		if(poz>0)
			this.perifericMouse=this.perifericeMouse.get(poz-1);
	}

	public void nextperifericMouse(ActionEvent e) {
		Integer poz=this.perifericeMouse.indexOf(this.perifericMouse);
		if((poz+1)<this.perifericeMouse.size())
			this.perifericMouse=this.perifericeMouse.get(poz+1);
	}

	public void adaugaperifericMouse(ActionEvent e) {
		this.perifericMouse=new perifericMouse();
		this.perifericMouse.setObiectId(999);
		this.perifericMouse.setObiectNume("Nume Nou");
		this.perifericMouse.setStocTotal(0);
		this.perifericMouse.setTipInventar(this.perifericeMouse.get(0).getTipInventar());
		this.perifericeMouse.add(this.perifericMouse);
	}
	
	public void salveazaperifericMouse(ActionEvent e) {
		Integer intermed = this.perifericMouse.getObiectId();
		if (!em.getTransaction().isActive()) 
			em.getTransaction().begin();
		
		try {
			if(this.em.contains(this.perifericMouse)) {
				em.merge(this.perifericMouse);
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
			if(!this.em.contains(this.perifericMouse)) {
				this.perifericMouse.setStocDisponibil(this.perifericMouse.getStocTotal());
				em.persist(this.perifericMouse);	
			}
			this.perifericMouse=this.perifericeMouse.get(0);
		
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
	
	
	
	public void stergeperifericMouse(ActionEvent e) {
		
		Query q1 =em.createQuery("Select c FROM stocInVanzare c where c.obiectId=:obiectId");
		q1.setParameter("obiectId", this.perifericMouse);
		
		Query q2 = em.createQuery("Select c FROM furnizoriOferta c where c.obiect=:obiectId");
		q2.setParameter("obiectId", this.perifericMouse);
		
		Query q3 = em.createQuery("Select c FROM Statii c where c.mouseStatieId=:obiectId");
		q3.setParameter("obiectId", this.perifericMouse);
		
		if(q1.getResultList().size()!=0 || q2.getResultList().size()!=0 || q3.getResultList().size()!=0) {
			
			FacesMessage facesMsg = new FacesMessage("Asigurati-va ca elementul nu este prezent in oferte de vanzare sau in ofertele furnizorilor");			
			FacesContext fc = FacesContext.getCurrentInstance();			
			// Afisare mesaj
			fc.addMessage(null, facesMsg);
			fc.renderResponse();
			return;
		}
		
		if(this.perifericeMouse.size()==1) {
			FacesMessage facesMsg = new FacesMessage("Este nevoie de cel putin o valuare in tabel");			
			FacesContext fc = FacesContext.getCurrentInstance();			
			// Afisare mesaj
			fc.addMessage(null, facesMsg);
			fc.renderResponse();
			return;
		}
		try {
		this.perifericeMouse.remove(this.perifericMouse);
		if(this.em.contains(this.perifericMouse)) {
			this.em.getTransaction().begin();
			
			em.remove(this.perifericMouse);
			this.perifericeMouse.remove(this.perifericMouse);
			this.em.getTransaction().commit();
		}
		else {
			this.perifericeMouse.remove(this.perifericMouse);
		}
		this.perifericMouse=this.perifericeMouse.get(0);
		
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
		if(this.em.contains(this.perifericMouse))
		em.refresh(this.perifericMouse);
		init();
	}
	
	public Integer getIdperifericMouse(){
		return this.perifericMouse.getObiectId();
		}	
	
	public void setIdperifericMouse(Integer id){
		if(this.em.contains(this.perifericMouse)) {
			this.perifericMouse = em.find(perifericMouse.class, id);
		}
	}
	
}