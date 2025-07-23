//IMPORTAÇÕES

import classes.SistemaOperacional;
import utils.Prompts;



//CLASSE MAIN
public class Main{
	public static void main(String args[]){	

		SistemaOperacional so=new SistemaOperacional();

		Prompts.ola_usuario();
		Prompts.prompt_add_recurso(so);
		System.out.println(so.get_recursos());
		System.out.println(so.get_recursos_id());


	}	
}
