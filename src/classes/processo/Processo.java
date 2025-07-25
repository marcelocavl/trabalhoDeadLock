//CLASSE PROCESSOS

package classes.processo;

import classes.sistemaOperacional.SistemaOperacional;
import classes.recurso.Recursos;
import classes.recurso.Recurso;

import utils.Utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;



public class Processo extends Thread{
	private int processoId; 
	private ArrayList<Integer> recursosAlocados;
	private int intervaloRequisicao;
	private int intervaloExecucao;

	public Processo(int processoId,RecursosNecessarios recursosNecessarios,int intervaloRequisicao,int intervaloExecucao){
		this.processoId=processoId;
		this.recursosAlocados=recursosAlocados;
		this.intervaloRequisicao=intervaloRequisicao;
		this.intervaloExecucao=intervaloExecucao;
	}

	public void run(){
		System.out.println("processo "+this.getProcessoId()+" iniciou");
		try{
		while(true){
			int indiceRecurso=this.sorteaRecurso();
			this.intervaloRequisicao();
			this.requisitaRecurso(indiceRecurso);
			this.decrementaRecursosNecessarios(indiceRecurso);
			if(this.isRecursosNecessariosZerado()){
				this.executando();	
				this.restaurarRecursosDisponiveis();
			}
		}
	}catch(Exception e){		
		while(true){
		e.printStackTrace();		
		}
	}
	}

	public int getProcessoId(){	
					return this.processoId;
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

//--------------------------------------------------------	


	
	public void intervaloRequisicao(){
		try{
			Thread.sleep(this.getIntervaloRequisicao()*1000);
		}catch(InterruptedException e){
		}
	}

	public void executando(){	
		Utils.timer_segs(this.getIntervaloExecucao());
		this.restaurarRecursosNecessarios();
	}



}
