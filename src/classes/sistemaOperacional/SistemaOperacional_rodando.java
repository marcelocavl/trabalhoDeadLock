package classes.sistemaOperacional;

import classes.processo.Processo_rodando;
import classes.processo.Processos;

import utils.Utils;
import java.util.ArrayList;
import java.util.concurrent.Semaphore; 



public class SistemaOperacional_rodando extends Thread{	
	private SistemaOperacional sistemaOperacional;
	private ArrayList<Processo_rodando> processos_rodando;

	public SistemaOperacional_rodando(SistemaOperacional sistemaOperacional,ArrayList<Processo_rodando> processos_rodando){
		this.sistemaOperacional=sistemaOperacional;	
		this.processos_rodando=processos_rodando;
	}

	public void run(){
		Utils.limparTela();
	}


	public SistemaOperacional get_sistemaOperacional(){
		return this.sistemaOperacional;
	}

	public ArrayList<Processo_rodando> get_processos_rodando(){
		return this.processos_rodando;
	}

	public boolean set_sistemaOperacional(SistemaOperacional sistemaOperacional){
		if(sistemaOperacional==null)
			return true;
		this.sistemaOperacional=sistemaOperacional;
		return false;
	}

	public boolean set_processos(ArrayList<Processo_rodando> processos_rodando){
		if(processos_rodando==null)
			return false;
		this.processos_rodando=processos_rodando;
		return true;
	}
/*
	public void printar_tela(){
		int i;
		for(i=0;i<this.get_processos_rodando().size();i++){
			Processo_rodando processo_rodando=this.get_processos_rodando().get(i);
			Processos processo_de_processo_rodando=processo_rodando.get_processo();
			System.out.println("p"+i+" "+processo_de_processo_rodando.getRecursosNecessarios());
		}
		System.out.println("recursos do sistema operacional: "+this.get_sistemaOperacional().get_recursos());
		System.out.println("quantidade de cada recurso: "+this.get_sistemaOperacional().getRecursosQuantidade());
	}
*/
}
