//CLASSE PROCESSOS

package classes.processo;

import classes.sistemaOperacional.SistemaOperacional;
import classes.sistemaOperacional.Recursos;
import classes.sistemaOperacional.Recurso;

import java.util.ArrayList;



public class Processo{
	private int processoId; 
	private RecursosNecessarios recursosNecessarios;

	public Processo(int processoId,RecursosNecessarios recursosNecessarios){
		this.processoId=processoId;
		this.recursosNecessarios=recursosNecessarios;	
	}

	public int getProcessoId(){	
		return this.processoId;
	}

	public RecursosNecessarios getRecursosNecessarios(){	
		return this.recursosNecessarios;
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
	


}
