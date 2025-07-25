package utils;

//IMPORTAÇÕES
import classes.sistemaOperacional.SistemaOperacional;
import classes.recurso.Recurso;
import classes.recurso.Recursos;

import java.util.Scanner;
import java.util.ArrayList;

public class Prompts{
	
	public static void ola_usuario(){
		System.out.println("ola ,usuário!");
		System.out.println("Inicializando Sistema Operacional...");
	}
	
	public static void prompt_add_recurso(SistemaOperacional so){
		Scanner scanner=new Scanner(System.in);//instanceia classe para ler entrada do user
		String resposta="";
		ArrayList<Recurso> recursos_array=new ArrayList<Recurso>();

		//recebendo o nome do recurso
		while(!resposta.equals("n")){
			System.out.println("Deseja adicionar que recurso?");
			String recurso_nome=scanner.nextLine();
			//so.add_recurso(recurso);
			//scanner.nextLine();	//limpando buffer

			//recebendo id do recurso
			System.out.println("Qual o ID do recurso?");
			int recurso_id=scanner.nextInt();
			//so.add_recurso_id(recurso_id);
			scanner.nextLine();	//limpando buffer

			//recebendo quantidade de instancias desse recurso
			System.out.println("Quantas instâncias esse recurso tem?");
			int recurso_instancias=scanner.nextInt();
			//so.add_recurso_quantidade_instancias(recurso_instancias);
			scanner.nextLine();	//limpando buffer

			Recurso novoRecurso=new Recurso(recurso_nome,recurso_id,recurso_instancias);
			recursos_array.add(novoRecurso);
			System.out.println("Deseja adicionar mais um recurso[y/n]?");
			resposta=scanner.nextLine();
		}
		Recursos novosRecursos=new Recursos(recursos_array);
		so.set_recursos(novosRecursos);
	}	

}
