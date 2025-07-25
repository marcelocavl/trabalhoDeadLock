//EMPACOTAMENTO
package classes.sistemaOperacional;

//IMPORTAÇÕES
import classes.processo.Processo;
import classes.recurso.Recurso;
import classes.recurso.Recursos;

import utils.Utils;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

//CLASSE SISTEMA OPERACIONAL
public class SistemaOperacional extends Thread{
	private Recursos recursos;	
	private ArrayList<Processo> processos;

	//CONSTRUTOR
	public SistemaOperacional(Recursos recursos){
		this.recursos=recursos;
	}	

	public void run(){
		System.out.println();
		this.inciarProcessos();
		this.printarProcessos();
	}

	//METODOS
	//metodos gets and setters
	public Recursos getRecursos(){
		return this.recursos;
	}

	public ArrayList<Processo> getProcessos(){
		return this.processos;
	}	

	public boolean setProcessos(ArrayList<Processo> processos){
		this.processos=processos;
		return true;
	}

	public boolean set_recursos(Recursos recursos){
		this.recursos=recursos;
		return true;
	}

	//metodos add

	//metodos de decremento de quantidade de instancia de um recurso
/*
	public boolean remove_uma_instancia_recurso(String recurso){		
		return this.getRecursos().recursosDecrementaQuant(recurso);
	}
*/	
	//metodos de retorno de indice e tamanho do array
	//retorna o indice do recurso no array recursos

	public int retorna_tamanho_array_recursos(){
		return this.getRecursos().getRecursosQuantidade();
	}


	public ArrayList<Semaphore> getRecursosQuantidade(){
		return this.getRecursos().retorneArrayRecursosQuantidade();
	}

	public void inciarProcessos(){	
		int i;
		for (i=0;i<this.getProcessos().size();i++){	
			this.getProcessos().get(i).start();
		}
	}

	public void printarProcessos(){
		while(true){
		int i;
		for (i=0;i<this.getProcessos().size();i++){	
			this.getProcessos().get(i).printarRecursosNecessarios();
		}	
		Utils.limparTela();
	}
	}
/*
	public boolean setRecursosQuantidade(ArrayList<Integer> recursosQuantidades){
		return this.getRecursos().atualizarRecursosQuantidade(recursosQuantidades);
	}
*/
}

