package org;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tipInventar t1= new tipInventar(1,"Calculatoare","CategorieDescriere");
		tipInventar t2= new tipInventar(2,"Periferice","CategorieDescriere");
		tipInventar t3= new tipInventar(3,"Mobilier","CategorieDescriere");
		t1.getTipId();
		System.out.println(t1.toString());
		
		Mobilier m1=new Mobilier(2,"Masa","eMag",20,2,2,t2,"Masa","Lemn");
		Mobilier m2=new Mobilier(7,"Scaun","eMag",20,2,2,t2,"Scaun","Lemn si piele");
		calculator comp1=new calculator(1,"APX-20","Dell",20,2,2,t3,"Intel",8000,"RTX2060","SSD",20);
		calculator comp2=new calculator(8,"APX-20","Dell",20,2,2,t3,"Intel",8000,"RTX2060","SSD",20);
		perifericCasti casti1=new perifericCasti(3,"LogitechG20","Logitech",20,2,2,t2,"Casti","USB",true,true);
		perifericDisplay display1=new perifericDisplay(4,"Samsung-XPS130","Samsung",20,2,2,t2,"Display","HDMI",21,120,true);
		perifericMouse mouse1=new perifericMouse(5,"Logitech G403","Logitech",20,2,2,t2,"Mouse","USB",2500.0,20.0);
		perifericKeyboard keyboard1=new perifericKeyboard(6,"ApexDragons","Razer",20,2,2,t2,"Keyboard","Bluetooth",true,true);
		
		ArrayList<Periferice> perifericeStatia1= new ArrayList<Periferice>();
		perifericeStatia1.add(casti1);
		perifericeStatia1.add(display1);
		perifericeStatia1.add(mouse1);
		perifericeStatia1.add(keyboard1);
		ArrayList<Mobilier> mobilierStatia1 = new ArrayList<Mobilier>();
		mobilierStatia1.add(m1);
		mobilierStatia1.add(m2);
		
		salaCalculatoare sala1 = new salaCalculatoare(1,"Gaming",10);
		
	
		stocInVanzare stocInVanzare1 = new stocInVanzare(1,comp1,200);
		
		Furnizori f1 = new Furnizori(1,"Altex","Bd.Carol 1","Iasi",true);
		
		furnizoriOferta oferta1 = new furnizoriOferta(f1,casti1,200,20);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("InternetJPA");
		EntityManager em=emf.createEntityManager();
		
		//Curatire Baze de date
//		em.getTransaction().begin();
//		em.createQuery("DELETE FROM furnizoriOferta").executeUpdate();
//		em.createQuery("DELETE FROM Furnizori").executeUpdate();
//		em.createQuery("DELETE FROM stocInVanzare").executeUpdate();
//		em.createQuery("DELETE FROM salaCalculatoare").executeUpdate();
//		em.createQuery("DELETE FROM perifericKeyboard").executeUpdate();
//		em.createQuery("DELETE FROM perifericMouse").executeUpdate();
//		em.createQuery("DELETE FROM perifericDisplay").executeUpdate();
//		em.createQuery("DELETE FROM perifericCasti").executeUpdate();
//		em.createQuery("DELETE FROM calculator").executeUpdate();
//		em.createQuery("DELETE FROM Mobilier").executeUpdate();
//		em.createQuery("DELETE FROM tipInventar").executeUpdate();
		

//		Scriere
//		em.getTransaction().begin();	
//		em.persist(t1);
//		em.persist(t2);
//		em.persist(t3);
//		em.persist(m1);
//		em.persist(m2);
//		em.persist(comp2);
//		em.persist(casti1);
//		em.persist(display1);
//		em.persist(mouse1);
//		em.persist(keyboard1);
//		em.persist(sala1);
//		em.persist(stocInVanzare1);
//		em.persist(f1);
//		em.persist(oferta1);
//		em.getTransaction().commit();
//		
		//Stergere
//		em.getTransaction().begin();
//		tipInventar removeInventar = (tipInventar) em.
//				createQuery("SELECT o FROM tipInventar o where o.tipId=3").
//				getSingleResult();
//		if(removeInventar!=null) {
//			em.remove(removeInventar);
//		}
//		em.getTransaction().commit();
		
		//Citire
//		List<tipInventar> tipuriPersistente = em.
//				createQuery("Select c from tipInventar c").getResultList();
//		System.out.println("Tipuri de inventar in baza de date");
//		for(tipInventar t: tipuriPersistente)
//			System.out.println("TipId:"+t.getTipId()+" cu denumirea de "+t.getNume());
		
		//Update
//		em.getTransaction().begin();
//		calculator cIntmd = em.find(calculator.class, 1);
//		if(cIntmd != null) {
//			cIntmd.setMemorieRamMb(16000);
//			cIntmd.setProcesor("AMD");
//			em.persist(cIntmd);
//		}
//		em.getTransaction().commit();
	}

}
