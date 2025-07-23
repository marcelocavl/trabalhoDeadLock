//IMPORTAÇÕES

import classes.SistemaOperacional;
import classes.Processos;
import classes.SistemaOperacional_rodando;
import utils.Prompts;
import utils.Utils;
import java.util.ArrayList;



//CLASSE MAIN
public class Main{
	public static void main(String args[]){	

		SistemaOperacional so=new SistemaOperacional();

		Prompts.ola_usuario();
		Prompts.prompt_add_recurso(so);
		System.out.println(so.get_recursos());
		System.out.println(so.get_recursos_id());
		System.out.println(so.get_recursos_quantidade_instancias());

		Utils.limparTela();
		Processos p1=new Processos();
		Processos p2=new Processos();
		Processos p3=new Processos();
	
		p1.set_sistemaOperacional(so);	
		p2.set_sistemaOperacional(so);	
		p3.set_sistemaOperacional(so);	
		
		p1.add_recurso_necessario(2);
		p1.add_recurso_necessario(3);
		p1.add_recurso_necessario(2);
		
		//System.out.println("recursos necessarios p1:");
		//System.out.println(p1.get_recursos_necessarios());

		p2.add_recurso_necessario(1);
		p2.add_recurso_necessario(3);
		p2.add_recurso_necessario(2);
		
		//System.out.println("recursos necessarios p2:");
		//System.out.println(p2.get_recursos_necessarios());

		p3.add_recurso_necessario(1);
		p3.add_recurso_necessario(2);
		p3.add_recurso_necessario(2);
				
		//System.out.println("recursos necessarios p3:");
		//System.out.println(p3.get_recursos_necessarios());

		ArrayList<Processos> processos=new ArrayList<>();
		processos.add(p1);
		processos.add(p2);
		processos.add(p3);
		
		SistemaOperacional_rodando sistemaOperacional_rodando=new SistemaOperacional_rodando();	
		sistemaOperacional_rodando.sistemaOperacional=so;
		sistemaOperacional_rodando.processos=processos;

		sistemaOperacional_rodando.printar_tela();






	}	
}
