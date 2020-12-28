package org;

import java.util.ArrayList;
import java.util.List;

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
		
		Statii statia1 = new Statii(1,perifericeStatia1,mobilierStatia1,comp1);
	
		System.out.println(statia1);
		System.out.println(comp1.getObiectId());
		System.out.println(m1.toString());
		System.out.println(casti1.toString());
		
		
	}

}
