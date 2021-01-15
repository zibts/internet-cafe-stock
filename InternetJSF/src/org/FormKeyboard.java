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

import org.perifericKeyboard;

@ManagedBean @SessionScoped
public class FormKeyboard {
    private perifericKeyboard perifericKeyboard;
    private List < perifericKeyboard > perifericeKeyboard = new ArrayList < perifericKeyboard > ();
    private tipInventar tipInventar;
    private List < tipInventar > tipuriInventare = new ArrayList < tipInventar > ();
    private Boolean readOnlyId;

    public perifericKeyboard getperifericKeyboard() {
        return perifericKeyboard;
    }
    public void setperifericKeyboard(perifericKeyboard perifericKeyboard) {
        this.perifericKeyboard = perifericKeyboard;
    }
    public List < perifericKeyboard > getperifericeKeyboard() {
        return perifericeKeyboard;
    }
    public void setperifericeKeyboard(List < perifericKeyboard > perifericeKeyboard) {
        this.perifericeKeyboard = perifericeKeyboard;
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
    public FormKeyboard() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InternetJPA");
        em = emf.createEntityManager();
        init();
    }
    public void init() {
        this.perifericeKeyboard = em.createQuery("select c from perifericKeyboard c").getResultList();
        this.perifericKeyboard = this.perifericeKeyboard.get(0);
        this.readOnlyId = this.checkIfThere(this.perifericKeyboard);
    }
    public void prevperifericKeyboard(ActionEvent e) {
        Integer poz = this.perifericeKeyboard.indexOf(this.perifericKeyboard);
        if (poz > 0)
            this.perifericKeyboard = this.perifericeKeyboard.get(poz - 1);
        this.readOnlyId = this.checkIfThere(this.perifericKeyboard);
    }

    public void nextperifericKeyboard(ActionEvent e) {
        Integer poz = this.perifericeKeyboard.indexOf(this.perifericKeyboard);
        if ((poz + 1) < this.perifericeKeyboard.size())
            this.perifericKeyboard = this.perifericeKeyboard.get(poz + 1);
        this.readOnlyId = this.checkIfThere(this.perifericKeyboard);
    }

    public void adaugaperifericKeyboard(ActionEvent e) {
        this.perifericKeyboard = new perifericKeyboard();
        this.perifericKeyboard.setObiectId(999);
        this.perifericKeyboard.setObiectNume("Nume Nou");
        this.perifericKeyboard.setStocTotal(0);
        this.perifericKeyboard.setTipInventar(this.perifericeKeyboard.get(0).getTipInventar());
        this.perifericeKeyboard.add(this.perifericKeyboard);
        this.readOnlyId = false;
    }

    public void salveazaperifericKeyboard(ActionEvent e) {
        Integer intermed = this.perifericKeyboard.getObiectId();
        this.readOnlyId = true;
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();

        try {
            if (this.em.contains(this.perifericKeyboard)) {
                em.merge(this.perifericKeyboard);
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
            if (!this.em.contains(this.perifericKeyboard)) {
                this.perifericKeyboard.setStocDisponibil(this.perifericKeyboard.getStocTotal());
                this.perifericKeyboard.setObiectId(null);
                em.persist(this.perifericKeyboard);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);

            FacesContext fc = FacesContext.getCurrentInstance();

            // Afisare mesaj
            this.perifericeKeyboard.remove(this.perifericKeyboard);
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.getTransaction().rollback();
        }

        this.perifericKeyboard = this.perifericeKeyboard.get(0);
    }



    public void stergeperifericKeyboard(ActionEvent e) {

        Query q1 = em.createQuery("Select c FROM stocInVanzare c where c.obiectId=:obiectId");
        q1.setParameter("obiectId", this.perifericKeyboard);

        Query q2 = em.createQuery("Select c FROM furnizoriOferta c where c.obiect=:obiectId");
        q2.setParameter("obiectId", this.perifericKeyboard);

        Query q3 = em.createQuery("Select c FROM Statii c where c.keyboardStatieId=:obiectId");
        q3.setParameter("obiectId", this.perifericKeyboard);

        if (q1.getResultList().size() != 0 || q2.getResultList().size() != 0 || q3.getResultList().size() != 0) {

            FacesMessage facesMsg = new FacesMessage("Asigurati-va ca elementul nu este prezent in oferte de vanzare sau in ofertele furnizorilor sau intr-o statie");
            FacesContext fc = FacesContext.getCurrentInstance();
            // Afisare mesaj
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            return;
        }

        if (this.perifericeKeyboard.size() == 1) {
            FacesMessage facesMsg = new FacesMessage("Este nevoie de cel putin o valuare in tabel");
            FacesContext fc = FacesContext.getCurrentInstance();
            // Afisare mesaj
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            return;
        }
        try {
            this.perifericeKeyboard.remove(this.perifericKeyboard);
            if (this.em.contains(this.perifericKeyboard)) {
                this.em.getTransaction().begin();

                em.remove(this.perifericKeyboard);
                this.perifericeKeyboard.remove(this.perifericKeyboard);
                this.em.getTransaction().commit();
            } else {
                this.perifericeKeyboard.remove(this.perifericKeyboard);
            }
            this.perifericKeyboard = this.perifericeKeyboard.get(0);

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
        if (this.em.contains(this.perifericKeyboard))
            em.refresh(this.perifericKeyboard);
        init();
    }

    public Integer getIdperifericKeyboard() {
        return this.perifericKeyboard.getObiectId();
    }

    public void setIdperifericKeyboard(Integer id) {
    	this.perifericKeyboard = this.perifericeKeyboard.stream().filter(c -> c.getObiectId().equals(id)).findFirst().get();
    	System.out.println(">>> >>> Rezultat cautare: " + this.perifericKeyboard);
    }

    public Boolean checkIfThere(perifericKeyboard Keyboard) {
        if (em.find(Keyboard.getClass(), Keyboard.getObiectId()) != null) {
            return true;
        } else {
            return false;
        }
    }

}