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

import org.calculator;

@ManagedBean @SessionScoped
public class FormCalculatoare {
    private calculator calculator;
    private List < calculator > calculatoare = new ArrayList < calculator > ();
    private tipInventar tipInventar;
    private List < tipInventar > tipuriInventare = new ArrayList < tipInventar > ();
    private Boolean readOnlyId;

    public calculator getcalculator() {
        return calculator;
    }
    public void setcalculator(calculator calculator) {
        this.calculator = calculator;
    }
    public List < calculator > getcalculatoare() {
        return calculatoare;
    }
    public void setcalculatoare(List < calculator > calculatoare) {
        this.calculatoare = calculatoare;
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
    public FormCalculatoare() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InternetJPA");
        em = emf.createEntityManager();
        init();
    }
    public void init() {
        if (this.calculatoare.isEmpty())
            this.calculatoare = em.createQuery("select c from calculator c").getResultList();
        if (this.tipuriInventare.isEmpty())
            this.tipuriInventare = em.createQuery("select t from tipInventar t").getResultList();
        this.calculator = this.calculatoare.get(0);
        this.tipInventar = this.tipuriInventare.get(0);
        this.readOnlyId = this.checkIfThere(this.calculator);
    }
    public void prevcalculator(ActionEvent e) {
        Integer poz = this.calculatoare.indexOf(this.calculator);
        if (poz > 0)
            this.calculator = this.calculatoare.get(poz - 1);
        this.readOnlyId = this.checkIfThere(this.calculator);
    }

    public void nextcalculator(ActionEvent e) {
        Integer poz = this.calculatoare.indexOf(this.calculator);
        if ((poz + 1) < this.calculatoare.size())
            this.calculator = this.calculatoare.get(poz + 1);
        this.readOnlyId = this.checkIfThere(this.calculator);
    }

    public void adaugacalculator(ActionEvent e) {
        this.calculator = new calculator();
        this.calculator.setObiectId(999);
        this.calculator.setObiectNume("Nume Nou");
        this.calculator.setStocTotal(0);
        this.calculator.setTipInventar(this.calculatoare.get(0).getTipInventar());
        this.calculatoare.add(this.calculator);
        this.readOnlyId = false;
    }

    public void salveazacalculator(ActionEvent e) {
        Integer intermed = this.calculator.getObiectId();
        this.readOnlyId = true;
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();

        try {
            if (this.em.contains(this.calculator)) {
                em.merge(this.calculator);
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
            if (!this.em.contains(this.calculator)) {
                this.calculator.setStocDisponibil(this.calculator.getStocTotal());
                em.persist(this.calculator);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROARE SALVARE: " + ex.getMessage(), null);

            FacesContext fc = FacesContext.getCurrentInstance();

            // Afisare mesaj
            this.calculatoare.remove(this.calculator);
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.getTransaction().rollback();
        }

        this.calculator = this.calculatoare.get(0);
    }

    public void stergecalculator(ActionEvent e) {
        Query q1 = em.createQuery("Select c FROM stocInVanzare c where c.obiectId=:obiectId");
        q1.setParameter("obiectId", this.calculator);

        Query q2 = em.createQuery("Select c FROM furnizoriOferta c where c.obiect=:obiectId");
        q2.setParameter("obiectId", this.calculator);

        Query q3 = em.createQuery("Select c FROM Statii c where c.calculatorStatieId=:obiectId");
        q3.setParameter("obiectId", this.calculator);

        if (q1.getResultList().size() != 0 || q2.getResultList().size() != 0 || q3.getResultList().size() != 0) {

            FacesMessage facesMsg = new FacesMessage("Asigurati-va ca elementul nu este prezent in oferte de vanzare, in ofertele furnizorilor sau amplasat intr-o statie");
            FacesContext fc = FacesContext.getCurrentInstance();
            // Afisare mesaj
            fc.addMessage(null, facesMsg);
            fc.renderResponse();
            return;
        }

        if (this.em.contains(this.calculator)) {
            this.em.getTransaction().begin();

            em.remove(this.calculator);

            this.em.getTransaction().commit();

            this.calculatoare.remove(this.calculator);
        } else {
            this.calculatoare.remove(this.calculator);
        }
        this.calculator = this.calculatoare.get(0);

    }
    public void abandon(ActionEvent e) {
        if (this.em.contains(this.calculator))
            em.refresh(this.calculator);
        init();
    }

    public Integer getIdCalculator() {
        return this.calculator.getObiectId();
    }

    public void setIdCalculator(Integer id) {
        if (this.em.contains(this.calculator)) {
            this.calculator = em.find(calculator.class, id);
        }
    }

    public Boolean checkIfThere(calculator calculator) {
        if (em.find(calculator.getClass(), calculator.getObiectId()) != null) {
            return true;
        } else {
            return false;
        }
    }



}