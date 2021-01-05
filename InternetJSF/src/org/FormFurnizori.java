package org;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean @SessionScoped
public class FormFurnizori {
		//Master
	
		private List<Furnizori> furnizorii = new ArrayList<Furnizori>();
		private Furnizori furnizori;
		
		
		public List<Furnizori> getfurnizorii() {
			return furnizorii;
		}
		
		public Furnizori getfurnizori() {
			return furnizori;
		}
		
		private Boolean readOnlyId;
		
		private Boolean disabledId;
		
		public Integer getIdFurnizori () {
			
			return this.furnizori.getFurnizorId();
			
		}
		
		public void setIdFurnizori(Integer id){

			if(this.em.contains(this.furnizori)) {
				this.furnizori = em.find(furnizori.getClass(), id);
			}
		}
		
		public Boolean getReadOnlyId() {
			return readOnlyId;
		}
		
		public Boolean getDisabledId() {
			return disabledId;
		}
		//Detail
		private DataModel<furnizoriOferta> furnizoriOfertantiDataModel;
		private List<Inventar> inventar = new ArrayList<Inventar>();
		
		public DataModel<furnizoriOferta> getfurnizoriOfertantiDataModel(){
			furnizoriOfertantiDataModel=new ListDataModel<furnizoriOferta>(this.furnizori.getFurnizoriOfertanti());
			return furnizoriOfertantiDataModel;
		}
		public List<Inventar> getinventar(){
			return inventar;
		}
		public Integer getIdInventar() {
			return this.furnizoriOfertantiDataModel.getRowData().getObiect().getObiectId();	
		}
		
		public void setIdInventar(Integer id) {
			this.furnizoriOfertantiDataModel.getRowData().setObiect(em.find(Inventar.class, id));
		}
		
		
		public void previousfurnizori(ActionEvent evt){
			System.out.println("Previous Action ");
			Integer idxCurent = this.furnizorii.indexOf(this.furnizori);
			if (idxCurent - 1 >= 0)
				this.furnizori = this.furnizorii.get(idxCurent - 1);
			this.readOnlyId = this.checkIfThere(this.furnizori);
		}
		
		public void nextfurnizori(ActionEvent evt){
			System.out.println("Next Action ");
			Integer idxCurent = this.furnizorii.indexOf(this.furnizori);
			if (idxCurent + 1 < this.furnizorii.size())
				this.furnizori = this.furnizorii.get(idxCurent + 1);
			this.readOnlyId = this.checkIfThere(this.furnizori);
		}
		
		// Actiuni tranzactionale
		public void adaugarefurnizori(ActionEvent e) {
			this.furnizori=new Furnizori();
			this.furnizori.setFurnizorId(999);
			this.furnizori.setFurnizorNume("Nume Nou");
			this.furnizorii.add(this.furnizori);
			this.readOnlyId = false;
		}
		public void adaugarefurnizori() {
			this.furnizori=new Furnizori();
			this.furnizori.setFurnizorId(999);
			this.furnizori.setFurnizorNume("Nume Nou");
			this.furnizorii.add(this.furnizori);
			this.readOnlyId = false;
		}
		
		public void salvarefurnizori(ActionEvent e) {
			try {
				if (!em.getTransaction().isActive()) 
					em.getTransaction().begin();
				if(this.em.contains(furnizori)) {
					em.merge(furnizori);
					em.flush();
				}
				em.getTransaction().commit();
			}
			catch(Exception ex) {
				FacesMessage facesMsg = 
			            new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);			
				
				FacesContext fc = FacesContext.getCurrentInstance();			
				if (!em.getTransaction().isActive()) 
					em.getTransaction().begin();
				// Afisare mesaj
				fc.addMessage(null, facesMsg);
				fc.renderResponse();
				em.getTransaction().rollback();
			}
			
			try {
				if (!em.getTransaction().isActive()) 
					em.getTransaction().begin();
				if(!this.em.contains(furnizori)) {
					em.persist(furnizori);	
				}
				this.furnizori=this.furnizorii.get(0);
			
				em.getTransaction().commit();
			}
			catch(Exception ex) {
				FacesMessage facesMsg = 
			            new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);			
				
				FacesContext fc = FacesContext.getCurrentInstance();			
				
				// Afisare mesaj
				if (!em.getTransaction().isActive()) 
					em.getTransaction().begin();
				fc.addMessage(null, facesMsg);
				fc.renderResponse();
				em.getTransaction().rollback();
			}
			this.furnizori=this.furnizorii.get(0);
			this.readOnlyId = true;
		}
		
		
		public void stergerefurnizori(ActionEvent evt){			
			if(this.furnizoriOfertantiDataModel.getRowCount()!=0) {
				
				FacesMessage facesMsg = new FacesMessage("Asigurati-va ca in sala nu sunt inventar");			
				FacesContext fc = FacesContext.getCurrentInstance();			
				// Afisare mesaj
				fc.addMessage(null, facesMsg);
				fc.renderResponse();
				return;
			}
			// @ Transactional --------------------
			if (!em.getTransaction().isActive()) 
				em.getTransaction().begin();
			// ----------------------------------		
			if (this.em.contains(this.furnizori)){
				this.em.remove(this.furnizori);
				this.furnizorii.remove(this.furnizori);
			}
			if (this.furnizorii.size() > 0)
				this.furnizori = this.furnizorii.get(0);
			else
				this.furnizori = null;
			// @ Transactional --------------------
			em.getTransaction().commit();
			// ----------------------------------
		}
		
		public void abandon(ActionEvent evt){
			if (this.em.contains(this.furnizori)){
				this.em.refresh(this.furnizori);
			}else
				this.initModelFurnizori();
		}
		
		public void adaugaOferta(ActionEvent e) {
			try{
			Random r = new Random();
			furnizoriOferta ofertaNoua=new furnizoriOferta();
			ofertaNoua.setFurnizor(this.furnizori);
			ofertaNoua.setObiect(this.inventar.get(0));
			this.furnizori.getFurnizoriOfertanti().add(ofertaNoua);
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
				// Afisare mesaj
				em.getTransaction().rollback();
			}
		}
		public void adaugaOferta() {
			
			this.disabledId = false;
			
			try{ Random r = new Random();
			furnizoriOferta ofertaNoua=new furnizoriOferta();
			ofertaNoua.setFurnizor(this.furnizori);
			ofertaNoua.setObiect(this.inventar.get(0));
			this.furnizori.getFurnizoriOfertanti().add(ofertaNoua);
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
				// Afisare mesaj
				em.getTransaction().rollback();
			}
			
		}
		
		public void stergeOferta(ActionEvent evt){
			
			// @ Transactional --------------------
			if (!em.getTransaction().isActive()) 
				em.getTransaction().begin();
			// ----------------------------------		
			if (this.em.contains(this.furnizoriOfertantiDataModel.getRowData())){
				this.em.remove(this.furnizoriOfertantiDataModel.getRowData());
				this.furnizori.getFurnizoriOfertanti().remove(this.furnizoriOfertantiDataModel.getRowData());
			}
			else {
				this.furnizori.getFurnizoriOfertanti().remove(this.furnizoriOfertantiDataModel.getRowData());
			}
			// @ Transactional --------------------
			em.getTransaction().commit();
			// ----------------------------------
		}
		
		
		public void salveazaOferta(ActionEvent evt){
			this.readOnlyId = true;
			try {
				if (!em.getTransaction().isActive()) 
					em.getTransaction().begin();
				if (this.em.contains(this.furnizoriOfertantiDataModel.getRowData())){
					this.em.merge(this.furnizoriOfertantiDataModel.getRowData());
				}
			}
			catch(Exception ex) {
				FacesMessage facesMsg = 
			            new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);			
				FacesContext fc = FacesContext.getCurrentInstance();			
				fc.addMessage(null, facesMsg);
				fc.renderResponse();
				
				if (!em.getTransaction().isActive()) 
					em.getTransaction().begin();
				em.getTransaction().rollback();
				
			}try {
				if (!em.getTransaction().isActive()) 
					em.getTransaction().begin();
				
				if(!this.em.contains(this.furnizoriOfertantiDataModel.getRowData())){
					em.persist(this.furnizoriOfertantiDataModel.getRowData());
				}
			}
			
			catch(Exception ex) {
				FacesMessage facesMsg = 
			            new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);			
				FacesContext fc = FacesContext.getCurrentInstance();			
				fc.addMessage(null, facesMsg);
				fc.renderResponse();
				if (!em.getTransaction().isActive()) 
					em.getTransaction().begin();
				em.getTransaction().rollback();
			}
			
			if (!em.getTransaction().isActive()) 
				em.getTransaction().begin();
			em.getTransaction().commit();
		}
		
		
		
		EntityManager em;
		public FormFurnizori() {
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("InternetJPA");
			em = emf.createEntityManager();
			initModelFurnizori();
			initModelOferte();
		}
		
		private void initModelFurnizori() {
			this.furnizorii=em.createQuery("Select o From Furnizori o",Furnizori.class).getResultList();
			if(this.furnizorii!=null && !this.furnizorii.isEmpty()) {
				Collections.sort(this.furnizorii,(c1,c2) -> c1.getFurnizorId().compareTo(c2.getFurnizorId()));
				if(!this.furnizorii.contains(this.furnizori))
					this.furnizori=this.furnizorii.get(0);
				this.readOnlyId = this.checkIfThere(this.furnizori);
				this.disabledId = true;
			}
		}
		
		private void initModelOferte() {
			this.inventar = em.createQuery("Select o from Inventar o", Inventar.class).getResultList();
			if(this.inventar != null && !this.inventar.isEmpty())
				Collections.sort(this.inventar, (c1,c2) -> c1.getObiectId().compareTo(c2.getObiectId()));
		}
		
		public Boolean checkIfThere(Furnizori mouse) {
			if(em.find(mouse.getClass(), mouse.getFurnizorId())!= null) {
				return true;
			}else{
				return false;
			}
		}

}