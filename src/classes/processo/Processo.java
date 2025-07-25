//CLASSE PROCESSOS

package classes.processo;

import classes.sistemaOperacional.SistemaOperacional;
import classes.recurso.Recursos;
import classes.recurso.Recurso;

import utils.Utils;

import java.util.ArrayList;
import java.util.Random;



public class Processo extends Thread{
	private int processoId; 
	private RecursosNecessarios recursosNecessarios;
	private int intervaloRequisicao;
	private int intervaloExecucao;

	public Processo(int processoId,RecursosNecessarios recursosNecessarios){
		this.processoId=processoId;
		this.recursosNecessarios=recursosNecessarios;	
	}

	public void run(){

	}

	public int getProcessoId(){	
		return this.processoId;
	}

	public RecursosNecessarios getRecursosNecessarios(){	
		return this.recursosNecessarios;
	}

	public int getIntervaloRequisicao(){	
		return this.intervaloRequisicao;
	}

	public int getIntervaloExecucao(){	
		return this.intervaloExecucao;
	}


	public boolean setProcessoId(Integer processoId){
		this.processoId=processoId;
		return true;
	}

	public boolean setRecursosNecessarios(RecursosNecessarios recursosNecessarios){
		if(recursosNecessarios==null)
			return false;
		this.recursosNecessarios=recursosNecessarios;
		return true;
	}

	public SistemaOperacional getSistemaOperacional(){
		return this.getRecursosNecessarios().getSo();
	}
//--------------------------------------------------------	
	public ArrayList<Recurso> retornaRecursosSistemaOperacional(){
		return this.getSistemaOperacional().getRecursos().getRecursos();
	}	
	public void requisitaRecurso(){
		Random random=new Random();
		ArrayList<Recurso> soRecursos=this.retornaRecursosSistemaOperacional();	
		int indiceRecurso=random.nextInt(3);		
		Recurso recursoRequisitado=soRecursos.get(indiceRecurso);
		Utils.down(recursoRequisitado.getRecursoQuantidade());
	}
	
	public void intervaloRequisicao(){
		try{
			Thread.sleep(this.getIntervaloRequisicao()*1000);
		}catch(InterruptedException e){
		}

	}
	public void executando(){	
		try{
			Thread.sleep(this.getIntervaloExecucao()*1000);
		}catch(InterruptedException e){
		}
	}

	public void printarRecursosNecessarios(){	
		System.out.println(this.getRecursosNecessarios().getRecursosNecessarios());
	}	

}
