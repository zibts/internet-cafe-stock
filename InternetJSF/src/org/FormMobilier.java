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

import org.Mobilier;

@ManagedBean @SessionScoped
public class FormMobilier {
    private Mobilier Mobilier;
    private List < Mobilier > mobiliere = new ArrayList < Mobilier > ();
    private tipInventar tipInventar;
    private List < tipInventar > tipuriInventare = new ArrayList < tipInventar > ();
    private Boolean readOnlyId;

    public Mobilier getMobilier() {
        return Mobilier;
    }
    public void setMobilier(Mobilier Mobilier) {
        this.Mobilier = Mobilier;
    }
    public List < Mobilier > getmobiliere() {
        return mobiliere;
    }
    public void setmobiliere(List < Mobilier > mobiliere) {
        this.mobiliere = mobiliere;
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
    public FormMobilier() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InternetJPA");
        em = emf.createEntityManager();
        init();
    }
    public void init() {
        this.mobiliere = em.createQuery("select c from Mobilier c").getResultList();
        this.Mobilier = this.mobiliere.get(0);
        this.readOnlyId = this.checkIfThere(this.Mobilier);
    }
    public void prevMobilier(ActionEvent e) {
        Integer poz = this.mobiliere.indexOf(this.Mobilier);
        if (poz > 0)
            this.Mobilier = this.mobiliere.get(poz - 1);
        this.readOnlyId = this.checkIfThere(this.Mobilier);
    }

    public void nextMobilier(ActionEvent e) {
        Integer poz = this.mobiliere.indexOf(this.Mobilier);
        if ((poz + 1) < this.mobiliere.size())
            this.Mobilier = this.mobiliere.get(poz + 1);
        this.readOnlyId = this.checkIfThere(this.Mobilier);
    }

    public void adaugaMobilier(ActionEvent e) {
        this.Mobilier = new Mobilier();
        this.Mobilier.setObiectId(0);
        this.Mobilier.setObiectNume("Nume Nou");
        this.Mobilier.setStocTotal(0);
        this.Mobilier.setTipInventar(this.mobiliere.get(0).getTipInventar());
        this.mobiliere.add(this.Mobilier);
        this.readOnlyId = false;
    }

    public void salveazaMobilier(ActionEvent e) {
        Integer intermed = this.Mobilier.getObiectId();
        this.readOnlyId = true;
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();

        try {
            if (this.em.contains(this.Mobilier)) {
                em.merge(this.Mobilier);
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
            if (!this.em.contains(this.Mobilier)) {
                this.Mobilier.setStocDisponibil(this.Mobilier.getStocTotal());
                this.Mobilier.setObiectId(null);
                em.persist(this.Mobilier);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);

            FacesContext fc = FacesContext.getCurrentInstance();

            // Afisare mesaj
            this.mobiliere.remove(this.Mobilier);
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.getTransaction().rollback();
        }

        this.Mobilier = this.mobiliere.get(0);
    }



    public void stergeMobilier(ActionEvent e) {

        Query q1 = em.createQuery("Select c FROM stocInVanzare c where c.obiectId=:obiectId");
        q1.setParameter("obiectId", this.Mobilier);

        Query q2 = em.createQuery("Select c FROM furnizoriOferta c where c.obiect=:obiectId");
        q2.setParameter("obiectId", this.Mobilier);

        Query q3 = em.createQuery("Select c FROM Statii c where c.birouStatie=:obiectId");
        q3.setParameter("obiectId", this.Mobilier);

        if (q1.getResultList().size() != 0 || q2.getResultList().size() != 0 || q3.getResultList().size() != 0) {

            FacesMessage facesMsg = new FacesMessage("Asigurati-va ca elementul nu este prezent in oferte de vanzare sau in ofertele furnizorilor sau intr-o statie");
            FacesContext fc = FacesContext.getCurrentInstance();
            // Afisare mesaj
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            return;
        }

        if (this.mobiliere.size() == 1) {
            FacesMessage facesMsg = new FacesMessage("Este nevoie de cel putin o valuare in tabel");
            FacesContext fc = FacesContext.getCurrentInstance();
            // Afisare mesaj
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            return;
        }
        try {
            this.mobiliere.remove(this.Mobilier);
            if (this.em.contains(this.Mobilier)) {
                this.em.getTransaction().begin();

                em.remove(this.Mobilier);
                this.mobiliere.remove(this.Mobilier);
                this.em.getTransaction().commit();
            } else {
                this.mobiliere.remove(this.Mobilier);
            }
            this.Mobilier = this.mobiliere.get(0);

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
        if (this.em.contains(this.Mobilier))
            em.refresh(this.Mobilier);
        init();
    }

    public Integer getIdMobilier() {
        return this.Mobilier.getObiectId();
    }

    public void setIdMobilier(Integer id) {
    	this.Mobilier = this.mobiliere.stream().filter(c -> c.getObiectId().equals(id)).findFirst().get();
    	System.out.println(">>> >>> Rezultat cautare: " + this.mobiliere);
    }

    public Boolean checkIfThere(Mobilier mouse) {
        if (em.find(mouse.getClass(), mouse.getObiectId()) != null) {
            return true;
        } else {
            return false;
        }
    }

}