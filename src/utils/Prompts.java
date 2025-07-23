package utils;

//IMPORTAÇÕES
import classes.SistemaOperacional;
import java.util.Scanner;

public class Prompts{
	
	public static void ola_usuario(){
		System.out.println("ola ,usuário!");
		System.out.println("Inicializando Sistema Operacional...");
	}
	
	public static void prompt_add_recurso(SistemaOperacional so){
		Scanner scanner=new Scanner(System.in);//instanceia classe para ler entrada do user
		String resposta="";

		//recebendo o nome do recurso
		while(!resposta.equals("n")){
			System.out.println("Deseja adicionar que recurso?");
			String recurso=scanner.nextLine();
			so.add_recurso(recurso);
			//scanner.nextLine();	//limpando buffer

			//recebendo id do recurso
			System.out.println("Qual o ID do recurso?");
			int recurso_id=scanner.nextInt();
			so.add_recurso_id(recurso_id);
			scanner.nextLine();	//limpando buffer

			//recebendo quantidade de instancias desse recurso
			System.out.println("Quantas instâncias esse recurso tem?");
			int recurso_instancias=scanner.nextInt();
			so.add_recurso_quantidade_instancias(recurso_instancias);
			scanner.nextLine();	//limpando buffer

			System.out.println("Deseja adicionar mais um recurso[y/n]?");
			resposta=scanner.nextLine();
		}

	}	
/*
	public static void prompt_add_mais_um_recurso(SistemaOperacional so){
		Scanner scanner=new Scanner(System.in);//instanceia classe para ler entrada do user
		System.out.println("Deseja adicionar mais um recurso[y/n]?");
		String resposta=scanner.nextLine();

		if(resposta=="y"){
			prompt_add_recurso(so);
		}else{
			return;
		}

	}
*/
}
