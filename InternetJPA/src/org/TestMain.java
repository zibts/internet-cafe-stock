package org;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tipInventar t1= new tipInventar(null,"Calculatoare","CategorieDescriere");
		tipInventar t2= new tipInventar(null,"Periferice","CategorieDescriere");
		tipInventar t3= new tipInventar(null,"Mobilier","CategorieDescriere");
		t1.getTipId();
		System.out.println(t1.toString());
		
		Mobilier m1=new Mobilier(null,"Masa","eMag",20,2,t2,"Gaming","Lemn","Scaun de gaming si Masa ce se Ridica");
		Mobilier m2=new Mobilier(null,"Scaun","eMag",20,2,t2,"Simplu","Lemn si piele","Scaun simplu si Masa simpla");
		Mobilier m3=new Mobilier(null,"Masa si scaun","Lemnaria iasi",20,2,t2,"Simplu","Lemn si piele","Scaun simplu si Masa simpla");
		
		
		calculator comp1=new calculator(null,"APX-20","Dell",2000,2,t3,"Intel",8000,"RTX2060","SSD",2000);
		calculator comp2=new calculator(null,"MSI AfterBRR","MSI",1000,5,t3,"AMD",16000,"GTX1060","SSD",1000);
		calculator comp3=new calculator(null,"Razer Station Lite","Razer",2000,2,t3,"AMD",16000,"GTX1660","SSD",1500);
		calculator comp4=new calculator(null,"MacBook Pro","Apple",2000,4,t3,"M1",32000,"Integrata","SSD",1000);
		calculator comp5=new calculator(null,"Dell Gaming AfterLight","Dell",4000,7,t3,"Intel",32000,"RTX3080","SSD",3000);
		
		
		perifericCasti casti1=new perifericCasti(null,"LogitechG20","Logitech",200,2,t2,"Casti","USB",true,true);
		perifericCasti casti2=new perifericCasti(null,"Razer Kraken x Lite","Razer",200,4,t2,"Casti","USB",true,true);
		perifericCasti casti3=new perifericCasti(null,"HyperX Cloud Aplha","HyperX",200,5,t2,"Casti","Wireless",true,true);
		perifericCasti casti4=new perifericCasti(null,"HyperX Stinger","HyperX",200,2,t2,"Casti","Wireless",true,true);
		
		
		perifericDisplay display1=new perifericDisplay(null,"Samsung-XPS130","Samsung",300,2,t2,"Display","HDMI",21,120,true);
		perifericDisplay display2=new perifericDisplay(null,"DELL 330","Dell",250,2,t2,"Display","VGA",18,60,false);
		perifericDisplay display3=new perifericDisplay(null,"DELL 450","Dell",350,2,t2,"Display","HDMI",21,60,false);
		
		perifericMouse mouse1=new perifericMouse(null,"Logitech G403","Logitech",200,2,t2,"Mouse","USB",25000.0,20.0);
		perifericMouse mouse2=new perifericMouse(null,"Steelseries Katana","Steelseries",150,2,t2,"Mouse","Wireless",14000.0,20.0);
		perifericMouse mouse3=new perifericMouse(null,"Razer Naga","Razer",200,2,t2,"Mouse","Wireless",12000.0,20.0);
		
		perifericKeyboard keyboard1=new perifericKeyboard(null,"ApexDragons","Razer",230,2,t2,"Keyboard","Bluetooth",true,true);
		perifericKeyboard keyboard2=new perifericKeyboard(null,"Razer Typopro","Razer",200,2,t2,"Keyboard","Bluetooth",true,true);
		perifericKeyboard keyboard3=new perifericKeyboard(null,"Logitech Mecha","Logitech",150,1,t2,"Keyboard","Bluetooth",true,true);
		
		
		salaCalculatoare sala1 = new salaCalculatoare(null,"Gaming",10);
		salaCalculatoare sala2 = new salaCalculatoare(null,"Gaming",10);
		
	
		stocInVanzare stocInVanzare1 = new stocInVanzare(null,comp1,2100);
		stocInVanzare stocInVanzare2 = new stocInVanzare(null,mouse3,200);
		stocInVanzare stocInVanzare3 = new stocInVanzare(null,keyboard2,200);
		stocInVanzare stocInVanzare4 = new stocInVanzare(null,comp4,2200);
		stocInVanzare stocInVanzare5 = new stocInVanzare(null,display3,400);
		
		Furnizori f1 = new Furnizori(null,"Altex","Bd.Carol 1","Iasi",true);
		Furnizori f2 = new Furnizori(null,"eMag","Calea Chisinaului 2","Iasi",true);
		Furnizori f3 = new Furnizori(null,"PCGarage","Bd. Stefan Voda","Chisinau",true);
		
		furnizoriOferta oferta1 = new furnizoriOferta(f1,comp1,2500,20);
		furnizoriOferta oferta2 = new furnizoriOferta(f3,mouse3,220,10);
		furnizoriOferta oferta3 = new furnizoriOferta(f3,casti1,200,5);
		furnizoriOferta oferta4 = new furnizoriOferta(f2,keyboard2,230,3);
		furnizoriOferta oferta5 = new furnizoriOferta(f3,comp4,2400,4);
		
		Statii s1 = new Statii();
		s1.setSalaStatieAmplasataId(sala1);
		comp2.seteazaCalculatorLaStatie(s1); 
		s1.setKeyboardStatieId(keyboard1);
		s1.setMouseStatieId(mouse1);
		s1.setDisplayStatieId(display1);
		s1.setCastiStatieId(casti1);
		s1.setBirouStatie(m2);
		

		Statii s2 = new Statii(null,sala1, comp3, keyboard2, mouse1, display1, casti1, m2);
		Statii s3 = new Statii(null,sala1, comp5, keyboard3, mouse1, display3, casti4, m1);
		Statii s4 = new Statii(null,sala2, comp2, keyboard3, mouse2, display2, casti4, m1);
		Statii s5 = new Statii(null,sala2, comp4, keyboard2, mouse3, display3, casti2, m3);
		
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("InternetJPA");
		EntityManager em=emf.createEntityManager();
		
		//Curatire Baze de date
//		em.getTransaction().begin();
//		em.createQuery("DELETE FROM Statii").executeUpdate();
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
		em.getTransaction().begin();	
		em.persist(t1);
		em.persist(t2);
		em.persist(t3);
		
		em.persist(m1);
		em.persist(m2);
		em.persist(m3);
		
		em.persist(comp1);
		em.persist(comp2);
		em.persist(comp3);
		em.persist(comp4);
		em.persist(comp5);
		
		em.persist(casti1);
		em.persist(casti2);
		em.persist(casti3);
		em.persist(casti4);
		
		em.persist(display1);
		em.persist(display2);
		em.persist(display3);
		
		
		em.persist(mouse1);
		em.persist(mouse2);
		em.persist(mouse3);
		
		em.persist(keyboard1);
		em.persist(keyboard2);
		em.persist(keyboard3);
		
		
		em.persist(sala1);
		em.persist(sala2);
		
		em.persist(stocInVanzare1);
		em.persist(stocInVanzare2);
		em.persist(stocInVanzare3);
		em.persist(stocInVanzare4);
		em.persist(stocInVanzare5);
		
		em.persist(f1);
		em.persist(f2);
		em.persist(f3);
		
		em.persist(oferta1);
		em.persist(oferta2);
		em.persist(oferta3);
		em.persist(oferta4);
		em.persist(oferta5);
		
		em.persist(s1);
		em.persist(s2);
		em.persist(s3);
		em.persist(s4);
		em.persist(s5);
		em.getTransaction().commit();
////		
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
