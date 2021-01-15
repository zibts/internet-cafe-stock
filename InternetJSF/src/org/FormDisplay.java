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

import org.perifericDisplay;

@ManagedBean @SessionScoped
public class FormDisplay {
    private perifericDisplay perifericDisplay;
    private List < perifericDisplay > perifericeDisplay = new ArrayList < perifericDisplay > ();
    private tipInventar tipInventar;
    private List < tipInventar > tipuriInventare = new ArrayList < tipInventar > ();
    private Boolean readOnlyId;

    public perifericDisplay getperifericDisplay() {
        return perifericDisplay;
    }
    public void setperifericDisplay(perifericDisplay perifericDisplay) {
        this.perifericDisplay = perifericDisplay;
    }
    public List < perifericDisplay > getperifericeDisplay() {
        return perifericeDisplay;
    }
    public void setperifericeDisplay(List < perifericDisplay > perifericeDisplay) {
        this.perifericeDisplay = perifericeDisplay;
    }

    public tipInventar getTipInventar() {
        return tipInventar;
    }
    public void setTipInventar(tipInventar tipInventar) {
        this.tipInventar = tipInventar;
    }
    public List < tipInventar > getTipuriInventare() {
        return tipuriInventare;
    }
    public void setTipuriInventare(List < tipInventar > tipuriInventare) {
        this.tipuriInventare = tipuriInventare;
    }
    public Boolean getReadOnlyId() {
        return readOnlyId;
    }

    EntityManager em;
    public FormDisplay() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InternetJPA");
        em = emf.createEntityManager();
        init();
    }
    public void init() {
        this.perifericeDisplay = em.createQuery("select c from perifericDisplay c").getResultList();
        this.perifericDisplay = this.perifericeDisplay.get(0);
        this.readOnlyId = this.checkIfThere(this.perifericDisplay);
    }
    public void prevperifericDisplay(ActionEvent e) {
        Integer poz = this.perifericeDisplay.indexOf(this.perifericDisplay);
        if (poz > 0)
            this.perifericDisplay = this.perifericeDisplay.get(poz - 1);
        this.readOnlyId = this.checkIfThere(this.perifericDisplay);
    }

    public void nextperifericDisplay(ActionEvent e) {
        Integer poz = this.perifericeDisplay.indexOf(this.perifericDisplay);
        if ((poz + 1) < this.perifericeDisplay.size())
            this.perifericDisplay = this.perifericeDisplay.get(poz + 1);
        this.readOnlyId = this.checkIfThere(this.perifericDisplay);
    }

    public void adaugaperifericDisplay(ActionEvent e) {
        this.perifericDisplay = new perifericDisplay();
        this.perifericDisplay.setObiectId(0);
        this.perifericDisplay.setObiectNume("Nume Nou");
        this.perifericDisplay.setStocTotal(0);
        this.perifericDisplay.setTipInventar(this.perifericeDisplay.get(0).getTipInventar());
        this.perifericeDisplay.add(this.perifericDisplay);
        this.readOnlyId = false;
    }

    public void salveazaperifericDisplay(ActionEvent e) {
        Integer intermed = this.perifericDisplay.getObiectId();
        this.readOnlyId = true;
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();

        try {
            if (this.em.contains(this.perifericDisplay)) {
                em.merge(this.perifericDisplay);
            }
        } catch (Exception ex) {
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
            if (!this.em.contains(this.perifericDisplay)) {
                this.perifericDisplay.setStocDisponibil(this.perifericDisplay.getStocTotal());
                this.perifericDisplay.setObiectId(null);
                em.persist(this.perifericDisplay);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);

            FacesContext fc = FacesContext.getCurrentInstance();

            // Afisare mesaj
            this.perifericeDisplay.remove(this.perifericDisplay);
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.getTransaction().rollback();
        }

        this.perifericDisplay = this.perifericeDisplay.get(0);
    }



    public void stergeperifericDisplay(ActionEvent e) {

        Query q1 = em.createQuery("Select c FROM stocInVanzare c where c.obiectId=:obiectId");
        q1.setParameter("obiectId", this.perifericDisplay);

        Query q2 = em.createQuery("Select c FROM furnizoriOferta c where c.obiect=:obiectId");
        q2.setParameter("obiectId", this.perifericDisplay);

        Query q3 = em.createQuery("Select c FROM Statii c where c.displayStatieId=:obiectId");
        q3.setParameter("obiectId", this.perifericDisplay);

        if (q1.getResultList().size() != 0 || q2.getResultList().size() != 0 || q3.getResultList().size() != 0) {

            FacesMessage facesMsg = new FacesMessage("Asigurati-va ca elementul nu este prezent in oferte de vanzare sau in ofertele furnizorilor sau intr-o statie");
            FacesContext fc = FacesContext.getCurrentInstance();
            // Afisare mesaj
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            return;
        }

        if (this.perifericeDisplay.size() == 1) {
            FacesMessage facesMsg = new FacesMessage("Este nevoie de cel putin o valuare in tabel");
            FacesContext fc = FacesContext.getCurrentInstance();
            // Afisare mesaj
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            return;
        }
        try {
            this.perifericeDisplay.remove(this.perifericDisplay);
            if (this.em.contains(this.perifericDisplay)) {
                this.em.getTransaction().begin();

                em.remove(this.perifericDisplay);
                this.perifericeDisplay.remove(this.perifericDisplay);
                this.em.getTransaction().commit();
            } else {
                this.perifericeDisplay.remove(this.perifericDisplay);
            }
            this.perifericDisplay = this.perifericeDisplay.get(0);

        } catch (StackOverflowError | Exception ex) {
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
        if (this.em.contains(this.perifericDisplay))
            em.refresh(this.perifericDisplay);
        init();
    }

    public Integer getIdperifericDisplay() {
        return this.perifericDisplay.getObiectId();
    }

    public void setIdperifericDisplay(Integer id) {
    	this.perifericDisplay = this.perifericeDisplay.stream().filter(c -> c.getObiectId().equals(id)).findFirst().get();
    	System.out.println(">>> >>> Rezultat cautare: " + this.perifericDisplay);
    }

    public Boolean checkIfThere(perifericDisplay Display) {
        if (em.find(Display.getClass(), Display.getObiectId()) != null) {
            return true;
        } else {
            return false;
        }
    }

}