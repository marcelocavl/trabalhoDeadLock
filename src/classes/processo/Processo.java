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
	private RecursosNecessarios recursosNecessarios; 					 
	private ArrayList<Integer> recursosNecessariosReferencia; //Referência para quando recursosNecessarios for alterado
	private int intervaloRequisicao;
	private int intervaloExecucao;

	public Processo(int processoId,RecursosNecessarios recursosNecessarios,int intervaloRequisicao,int intervaloExecucao){
		this.processoId=processoId;
		this.recursosNecessarios=recursosNecessarios;	
		this.recursosNecessariosReferencia=this.recursosNecessarios.getRecursosNecessarios();
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

	public RecursosNecessarios getRecursosNecessarios(){	
		return this.recursosNecessarios;
	}
	public ArrayList<Integer> getRecursosNecessariosReferencia(){	
		return this.recursosNecessariosReferencia;
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

	public int sorteaRecurso(){
		Random random=new Random();	
		int indice;
		do{
			indice=random.nextInt(3);
		}while(this.getRecursosNecessarios().getRecursosNecessarios().get(indice)==0);
		return indice;
			
	}

	public void requisitaRecurso(int indiceRecurso){
		ArrayList<Recurso> recursosSo=this.getSistemaOperacional().getRecursos().getRecursos();		
		Recurso recursoRequisitado=recursosSo.get(indiceRecurso);
		Utils.down(recursoRequisitado.getRecursoQuantidade());
	}

	public void decrementaRecursosNecessarios(int indice){			
		int valor=(this.getRecursosNecessarios().getRecursosNecessarios().get(indice));
		valor--;
		this.getRecursosNecessarios().getRecursosNecessarios().set(indice,valor);
		return;
	}
	
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

	public void printarRecursosNecessarios(){	
		System.out.println(this.getRecursosNecessarios().getRecursosNecessarios());
	}	

	public boolean isRecursosNecessariosZerado(){	
		ArrayList<Integer> recursosNecessarios=this.getRecursosNecessarios().getRecursosNecessarios();
		int i;
		for(i=0;i<recursosNecessarios.size();i++){
			if(recursosNecessarios.get(i)!=0){	
				return false;
			}	
		}
		return true;
	}

	public void restaurarRecursosDisponiveis(){
		ArrayList<Integer> recursosNecessariosReferencia=this.getRecursosNecessariosReferencia();
		ArrayList<Recurso> recursosDisponiveis=this.getSistemaOperacional().getRecursos().getRecursos();
		int i;
		for(i=0;i<recursosNecessariosReferencia.size();i++){
			Utils.up(recursosDisponiveis.get(i).getRecursoQuantidade(),recursosNecessariosReferencia.get(i));
		}	
	}

	public void restaurarRecursosNecessarios(){
		ArrayList<Integer> recursosNecessariosReferencia=this.getRecursosNecessariosReferencia();
		ArrayList<Integer> recursosNecessarios=this.getRecursosNecessarios().getRecursosNecessarios();
		int i;
		for(i=0;i<recursosNecessarios.size();i++){
			recursosNecessarios.set(i,recursosNecessariosReferencia.get(i));
		}

	}
}
