//IMPORTAÇÕES

import classes.sistemaOperacional.SistemaOperacional;
import classes.sistemaOperacional.SistemaOperacional_rodando;

import classes.recurso.Recurso;
import classes.recurso.Recursos;

import classes.processo.Processo;
import classes.processo.RecursosNecessarios;


import utils.Prompts;
import utils.Utils;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;



//CLASSE MAIN
public class Main{
	public static void main(String args[]){	
		
//----------------------------------------------------------------------------------
//CRIACAO DO SISTEMA OPERACIONAL
		Recurso r1=new Recurso("impressora",1,3);
		Recurso r2=new Recurso("scanner",2,3);
		Recurso r3=new Recurso("plotter",2,3);
	
		ArrayList<Recurso> recursos_array=new ArrayList<>();
		recursos_array.add(r1);		
		recursos_array.add(r2);		
		recursos_array.add(r3);		
		
		Recursos recursos=new Recursos(recursos_array);
		
		SistemaOperacional so=new SistemaOperacional(recursos);
		
		System.out.println(so.getRecursos().retorneArrayRecursosNomes());
		System.out.println(so.getRecursosQuantidade());

//----------------------------------------------------------------------------------
//CRIACAO DOS PROCESSOS
//		Utils.limparTela();
		RecursosNecessarios recursosNecessarios1=new RecursosNecessarios(so);
		RecursosNecessarios recursosNecessarios2=new RecursosNecessarios(so);
		RecursosNecessarios recursosNecessarios3=new RecursosNecessarios(so);
		
		recursosNecessarios1.addRecursoNecessario(2);
		recursosNecessarios1.addRecursoNecessario(1);
		recursosNecessarios1.addRecursoNecessario(3);

		System.out.println(recursosNecessarios1.getRecursosNecessarios());

		recursosNecessarios2.addRecursoNecessario(2);
		recursosNecessarios2.addRecursoNecessario(1);
		recursosNecessarios2.addRecursoNecessario(2);


		System.out.println(recursosNecessarios2.getRecursosNecessarios());

		recursosNecessarios3.addRecursoNecessario(1);
		recursosNecessarios3.addRecursoNecessario(1);
		recursosNecessarios3.addRecursoNecessario(3);



		System.out.println(recursosNecessarios3.getRecursosNecessarios());

		Processo p1=new Processo(1,recursosNecessarios1);
		Processo p2=new Processo(2,recursosNecessarios2);
		Processo p3=new Processo(3,recursosNecessarios3);
	
		ArrayList<Processo> processos=new ArrayList<>();
		processos.add(p1);
		processos.add(p2);
		processos.add(p3);
	
		so.setProcessos(processos);			

		so.start();
		


/*	
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

*/




	}	
} 
