//IMPORTAÇÕES

import classes.SistemaOperacional;



//CLASSE MAIN
public class Main{
	public static void main(String args[]){	
		SistemaOperacional so=new SistemaOperacional();

		System.out.println(so.get_recursos());

		System.out.println(so.add_recursos("impressora"));

		System.out.println(so.get_recursos());
	}	
}
