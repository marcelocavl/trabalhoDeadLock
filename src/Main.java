//IMPORTAÇÕES

import classes.SistemaOperacional;
import classes.Processos;
import classes.SistemaOperacional_rodando;
import classes.Processo_rodando;
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
		

		p2.add_recurso_necessario(1);
		p2.add_recurso_necessario(3);
		p2.add_recurso_necessario(2);
		

		p3.add_recurso_necessario(1);
		p3.add_recurso_necessario(2);
		p3.add_recurso_necessario(2);

		Processo_rodando processo1=new Processo_rodando(p1,5,5);		
		Processo_rodando processo2=new Processo_rodando(p2,5,5);		
		Processo_rodando processo3=new Processo_rodando(p2,5,5);		

			

		ArrayList<Processo_rodando> processos=new ArrayList<>();
		processos.add(processo1);
		processos.add(processo2);
		processos.add(processo3);
		
		SistemaOperacional_rodando sistemaOperacional_rodando=new SistemaOperacional_rodando(so,processos);	

		sistemaOperacional_rodando.printar_tela();





	}	
} 
