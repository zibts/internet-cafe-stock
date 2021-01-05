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
public class FormSala {
		//Master
	
		private List<salaCalculatoare> saliCalculatoare = new ArrayList<salaCalculatoare>();
		private salaCalculatoare salaCalculatoare;
		
		
		public List<salaCalculatoare> getSaliCalculatoare() {
			return saliCalculatoare;
		}
		public salaCalculatoare getSalaCalculatoare() {
			return salaCalculatoare;
		}
		
		private Boolean readOnlyId;
		
		public Integer getIdSala () {
			
			return this.salaCalculatoare.getSalaId();
			
		}
		
		public void setIdSala(Integer id){

			if(this.em.contains(this.salaCalculatoare)) {
				this.salaCalculatoare = em.find(salaCalculatoare.getClass(), id);
			}
		}
		
		public Boolean getReadOnlyId() {
			return readOnlyId;
		}
		//Detail
		private DataModel<Statii> statiiInSalaDataModel;
		private List<calculator> calculatoare = new ArrayList<calculator>();
		private List<perifericDisplay> perifericeDisplay = new ArrayList<perifericDisplay>();
		private List<perifericKeyboard> perifericeKeyboard = new ArrayList<perifericKeyboard>();
		private List<perifericMouse> perifericeMouse = new ArrayList<perifericMouse>();
		private List<perifericCasti> perifericeCasti = new ArrayList<perifericCasti>();
		private List<Mobilier> birouri = new ArrayList<Mobilier>();
		
		public DataModel<Statii> getStatiiInSalaDataModel(){
			statiiInSalaDataModel=new ListDataModel<Statii>(this.salaCalculatoare.getStatiiInSala());
			return statiiInSalaDataModel;
		}
		public List<calculator> getCalculatoare(){
			return calculatoare;
		}		
		public List<perifericDisplay> getPerifericeDisplay() {
			return perifericeDisplay;
		}
		public List<perifericKeyboard> getPerifericeKeyboard() {
			return perifericeKeyboard;
		}
		public List<perifericMouse> getPerifericeMouse() {
			return perifericeMouse;
		}
		public List<perifericCasti> getPerifericeCasti() {
			return perifericeCasti;
		}
		
		public List<Mobilier> getBirouStatie() {
			return birouri;
		}
		public Integer getIdCalculator() {
			return this.statiiInSalaDataModel.getRowData().getCalculatorStatieId().getObiectId();	
		}
		
		public void setIdCalculator(Integer id) {
			this.statiiInSalaDataModel.getRowData().setCalculatorStatieId(em.find(calculator.class, id));
		}
		
		public Integer getIdDisplay() {
			return this.statiiInSalaDataModel.getRowData().getDisplayStatieId().getObiectId();	
		}
		
		public void setIdDisplay(Integer id) {
			this.statiiInSalaDataModel.getRowData().setDisplayStatieId(em.find(perifericDisplay.class, id));
		}
		
		public Integer getIdMouse() {
			return this.statiiInSalaDataModel.getRowData().getMouseStatieId().getObiectId();	
		}
		
		public void setIdMouse(Integer id) {
			this.statiiInSalaDataModel.getRowData().setMouseStatieId(em.find(perifericMouse.class, id));
		}
		
		public Integer getIdKeyboard() {
			return this.statiiInSalaDataModel.getRowData().getKeyboardStatieId().getObiectId();
		}
		
		public void setIdKeyboard(Integer id) {
			this.statiiInSalaDataModel.getRowData().setKeyboardStatieId(em.find(perifericKeyboard.class, id));
		}
		
		public Integer getIdCasti() {
			return this.statiiInSalaDataModel.getRowData().getCastiStatieId().getObiectId();
		}
		
		public void setIdCasti(Integer id) {
			this.statiiInSalaDataModel.getRowData().setCastiStatieId(em.find(perifericCasti.class, id));
		}
		
		public void previousSalaCalculatoare(ActionEvent evt){
			System.out.println("Previous Action ");
			Integer idxCurent = this.saliCalculatoare.indexOf(this.salaCalculatoare);
			if (idxCurent - 1 >= 0)
				this.salaCalculatoare = this.saliCalculatoare.get(idxCurent - 1);
			this.readOnlyId = this.checkIfThere(this.salaCalculatoare);
		}
		
		public void nextSalaCalculatoare(ActionEvent evt){
			System.out.println("Next Action ");
			Integer idxCurent = this.saliCalculatoare.indexOf(this.salaCalculatoare);
			if (idxCurent + 1 < this.saliCalculatoare.size())
				this.salaCalculatoare = this.saliCalculatoare.get(idxCurent + 1);
			this.readOnlyId = this.checkIfThere(this.salaCalculatoare);
		}
		
		// Actiuni tranzactionale
		public void adaugareSalaCalculatoare(ActionEvent e) {
			this.salaCalculatoare=new salaCalculatoare();
			this.salaCalculatoare.setSalaId(999);
			this.salaCalculatoare.setDenumireSala("Introduceti denumire");
			this.salaCalculatoare.setNrStatiiAmplasate(null);
			this.saliCalculatoare.add(this.salaCalculatoare);
			this.readOnlyId = false;
		}
		
		public void salvareSalaCalculatoare(ActionEvent e) {
			if (!em.getTransaction().isActive()) 
				em.getTransaction().begin();
			
			try {
				if(this.em.contains(salaCalculatoare)) {
					em.merge(salaCalculatoare);
					em.flush();
					em.getTransaction().commit();
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
				this.initModelSali();
			}
			
			try {
				if(!this.em.contains(salaCalculatoare)) {
					em.persist(salaCalculatoare);	
					em.getTransaction().commit();
				}
				this.salaCalculatoare=this.saliCalculatoare.get(0);
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
				this.initModelSali();
			}
			this.salaCalculatoare = this.saliCalculatoare.get(0);
			
			this.readOnlyId = true;
		}
		
		
		public void stergereSalaCalculatoare(ActionEvent evt){		
			
			if(this.saliCalculatoare.size()==1) {
				
				FacesMessage facesMsg = new FacesMessage("Asigurati-va ca exista cel putin o statie");			
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
			if (this.em.contains(this.salaCalculatoare)){
				this.em.remove(this.salaCalculatoare);
				this.saliCalculatoare.remove(this.salaCalculatoare);
			}
			if (this.saliCalculatoare.size() > 0)
				this.salaCalculatoare = this.saliCalculatoare.get(0);
			else
				this.salaCalculatoare = null;
			// @ Transactional --------------------
			em.getTransaction().commit();
			// ----------------------------------
		}
		
		public void abandon(ActionEvent evt){
			if (this.em.contains(this.salaCalculatoare)){
				this.em.refresh(this.salaCalculatoare);
			}else
				this.initModelSali();
		}
		
		public void adaugaStatie(ActionEvent e) {
			Random r = new Random();
			Statii statieNoua=new Statii();
		
			this.salaCalculatoare.getStatiiInSala().add(statieNoua);
			
			statieNoua.setStatieId(this.statiiInSalaDataModel.getRowCount() +r.nextInt(10000-1));
			statieNoua.setSalaStatieAmplasataId(this.salaCalculatoare);
			statieNoua.setCalculatorStatieId(this.calculatoare.get(0));
			statieNoua.setDisplayStatieId(this.perifericeDisplay.get(0));
			statieNoua.setCastiStatieId(this.perifericeCasti.get(0));
			statieNoua.setKeyboardStatieId(this.perifericeKeyboard.get(0));
			statieNoua.setMouseStatieId(this.perifericeMouse.get(0));
			statieNoua.setBirouStatie(this.birouri.get(0));
		}
		
		public void stergeStatie(ActionEvent evt){
			
			// @ Transactional --------------------
			if (!em.getTransaction().isActive()) 
				em.getTransaction().begin();
			// ----------------------------------	
			try {
			if (this.em.contains(this.statiiInSalaDataModel.getRowData())){
				this.em.remove(this.statiiInSalaDataModel.getRowData());
				this.salaCalculatoare.getStatiiInSala().remove(this.statiiInSalaDataModel.getRowData());
			}
			else {
				this.salaCalculatoare.getStatiiInSala().remove(this.statiiInSalaDataModel.getRowData());
			}
			// @ Transactional --------------------
			em.getTransaction().commit();
			// ----------------------------------
			}
			
			catch(Exception ex) {
				FacesMessage facesMsg = 
			            new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);			
				
				FacesContext fc = FacesContext.getCurrentInstance();			
				
				// Afisare mesaj
				fc.addMessage(null, facesMsg);
				fc.renderResponse();
				em.getTransaction().rollback();
			}
			
		}
		
		
		public void salveazaStatie(ActionEvent evt){
			// @ Transactional --------------------
			if (!em.getTransaction().isActive()) 
				em.getTransaction().begin();
			
			try {
				// ----------------------------------	
				if (this.em.contains(this.statiiInSalaDataModel.getRowData())){
					this.em.merge(this.statiiInSalaDataModel.getRowData());
				}
				else {
					em.persist(this.statiiInSalaDataModel.getRowData());
				}
				// @ Transactional --------------------
				em.getTransaction().commit();
				// ----------------------------------
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
			
		}
		
		
		
		EntityManager em;
		public FormSala() {
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("InternetJPA");
			em = emf.createEntityManager();
			initModelSali();
			initModelCalculatoare();
		}
		
		private void initModelSali() {
			this.saliCalculatoare=em.createQuery("Select o From salaCalculatoare o",salaCalculatoare.class).getResultList();
			if(this.saliCalculatoare!=null && !this.saliCalculatoare.isEmpty()) {
				Collections.sort(this.saliCalculatoare,(c1,c2) -> c1.getSalaId().compareTo(c2.getSalaId()));
				if(!this.saliCalculatoare.contains(this.salaCalculatoare))
					this.salaCalculatoare=this.saliCalculatoare.get(0);
				this.readOnlyId = this.checkIfThere(this.salaCalculatoare);
			}
			if(this.saliCalculatoare==null || this.saliCalculatoare.isEmpty()) {
				this.salaCalculatoare=new salaCalculatoare();
				this.salaCalculatoare.setSalaId(999);
				this.salaCalculatoare.setDenumireSala("Introduceti denumire statie noua");
				this.salaCalculatoare.setNrStatiiAmplasate(null);
				this.saliCalculatoare.add(this.salaCalculatoare);
				this.readOnlyId = this.checkIfThere(this.salaCalculatoare);
			}
		}
		
		private void initModelCalculatoare() {
			this.calculatoare = em.createQuery("Select o from calculator o", calculator.class).getResultList();
			if(this.calculatoare != null && !this.calculatoare.isEmpty())
				Collections.sort(this.calculatoare, (c1,c2) -> c1.getObiectId().compareTo(c2.getObiectId()));
			this.perifericeDisplay = em.createQuery("Select o from perifericDisplay o", perifericDisplay.class).getResultList();
			if(this.perifericeDisplay != null && !this.perifericeDisplay.isEmpty())
				Collections.sort(this.perifericeDisplay, (c1,c2) -> c1.getObiectId().compareTo(c2.getObiectId()));
			this.perifericeKeyboard = em.createQuery("Select o from perifericKeyboard o", perifericKeyboard.class).getResultList();
			if(this.perifericeKeyboard != null && !this.perifericeKeyboard.isEmpty())
				Collections.sort(this.perifericeKeyboard, (c1,c2) -> c1.getObiectId().compareTo(c2.getObiectId()));
			this.perifericeMouse = em.createQuery("Select o from perifericMouse o", perifericMouse.class).getResultList();
			if(this.perifericeMouse != null && !this.perifericeMouse.isEmpty())
				Collections.sort(this.perifericeMouse, (c1,c2) -> c1.getObiectId().compareTo(c2.getObiectId()));
			this.perifericeCasti = em.createQuery("Select o from perifericCasti o", perifericCasti.class).getResultList();
			if(this.perifericeCasti != null && !this.perifericeCasti.isEmpty())
				Collections.sort(this.perifericeCasti, (c1,c2) -> c1.getObiectId().compareTo(c2.getObiectId()));
			this.birouri = em.createQuery("Select o from Mobilier o", Mobilier.class).getResultList();
			if(this.birouri != null && !this.birouri.isEmpty())
				Collections.sort(this.birouri, (c1,c2) -> c1.getObiectId().compareTo(c2.getObiectId()));
		}
		
		public Boolean checkIfThere(salaCalculatoare mouse) {
			if(em.find(mouse.getClass(), mouse.getSalaId())!= null) {
				return true;
			}else{
				return false;
			}
		}

}
