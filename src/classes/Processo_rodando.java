package classes;

import utils.Utils;
import java.util.ArrayList;

public class Processo_rodando extends Thread{
	private Processos processo;
	private int tempo_de_solicitacao;	
	private int tempo_de_execucao;


	public Processo_rodando(Processos processo,int tempo_de_solicitacao,int tempo_de_execucao){	
		this.processo=processo;
		this.tempo_de_solicitacao=tempo_de_solicitacao;
		this.tempo_de_execucao=tempo_de_execucao;
		return;
	}


	public void run(){
		while(true){
			this.intervalo_de_solicitacao();	
			this.solicitar_recursos();
		}
	}



	public Processos get_processo(){
		return this.processo;
	}

	public int get_tempo_de_solicitacao(){
		return this.tempo_de_solicitacao;
	}

	public int get_tempo_de_execucao(){
		return this.tempo_de_execucao;
	}

	public SistemaOperacional get_sistema_operacional(){
		return this.get_processo().get_sistemaOperacional();
	}
		


	public boolean set_processo(Processos processo){
		if(processo==null)
			return false;
		this.processo=processo;
		return true;
	}

	public boolean set_tempo_de_solicitacao(Integer tempo_de_solicitacao){
		if(tempo_de_solicitacao==null)
			return false;
		this.tempo_de_solicitacao=tempo_de_solicitacao;
		return true;
	}

	public boolean set_tempo_de_execucao(Integer tempo_de_execucao){
		if(tempo_de_execucao==null)
			return false;
		this.tempo_de_execucao=tempo_de_execucao;
		return true;
	}



	public void intervalo_de_solicitacao(){	
		try{
			Thread.sleep(this.get_tempo_de_solicitacao()*1000);
		}catch(InterruptedException e){
		}

	}

	public void intervalo_de_execucao(){	
		try{
			Thread.sleep(this.get_tempo_de_execucao()*1000);
		}catch(InterruptedException e){
		}
	}



	public void solicitar_recursos(){
		Utils.down(Semaphores.mutex);
		this.subtrair_dos_recursos_disponiveis_so();
		this.intervalo_de_execucao();
		this.adicionar_aos_recursos_disponiveis_so();
		Utils.up(Semaphores.mutex);
		
	}


	public void subtrair_dos_recursos_disponiveis_so(){
		ArrayList<Integer> recursos_disponiveis=this.get_sistema_operacional().get_recursos_quantidade_instancias();
		ArrayList<Integer> recursos_necessarios=this.get_processo().get_recursos_necessarios();
		recursos_disponiveis=Utils.subtrair_arrays(recursos_disponiveis,recursos_necessarios);
		
		this.get_sistema_operacional().set_recursos_quantidade_instancias(recursos_disponiveis);
		
		
	}

	public void adicionar_aos_recursos_disponiveis_so(){
		ArrayList<Integer> recursos_disponiveis=this.get_sistema_operacional().get_recursos_quantidade_instancias();
		ArrayList<Integer> recursos_necessarios=this.get_processo().get_recursos_necessarios();
		recursos_disponiveis=Utils.adicionar_arrays(recursos_disponiveis,recursos_necessarios);
		
		this.get_sistema_operacional().set_recursos_quantidade_instancias(recursos_disponiveis);
		
		
	}



}
