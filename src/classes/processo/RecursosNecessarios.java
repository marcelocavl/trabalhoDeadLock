package classes.processo;

import classes.sistemaOperacional.SistemaOperacional;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class RecursosNecessarios{
	private ArrayList<Integer> recursosNecessarios;
	private SistemaOperacional so;
	
	public RecursosNecessarios(SistemaOperacional so){
		this.so=so;	
		recursosNecessarios=new ArrayList<>();
	}
//----------------------------------------------------------------------------------
	public ArrayList<Integer> getRecursosNecessarios() {
    return this.recursosNecessarios;
	}


	public SistemaOperacional getSo() {
    return this.so;
	}

	public void setRecursosNecessarios(ArrayList<Integer> recursosNecessarios) {
    this.recursosNecessarios = recursosNecessarios;
	}

	public void setSo(SistemaOperacional so) {
    this.so = so;
	}

//----------------------------------------------------------------------------------
	public boolean addRecursoNecessario(int recursoNecessarioQuantidade){
		if(this.isRecursoSuperiorIncompativel(recursoNecessarioQuantidade))
			return false;
		this.getRecursosNecessarios().add(recursoNecessarioQuantidade);
		return true;
	}

public boolean isRecursoSuperiorIncompativel(int recursoNecessarioQuantidade){	
		ArrayList<Semaphore> sistemaOperacionalRecursosDisponiveis=this.retornaRecursosDisponiveisSistemaOperacional();
		int ultimo_indice=this.getRecursosNecessarios().size();	
		System.out.println(ultimo_indice);	
		if(recursoNecessarioQuantidade>sistemaOperacionalRecursosDisponiveis.get(ultimo_indice).availablePermits()){
			return true;
		}
		return false;
	}

	public ArrayList<Semaphore> retornaRecursosDisponiveisSistemaOperacional(){
		return this.getSo().getRecursosQuantidade();
	}	
//----------------------------------------------------------------------------------

}

