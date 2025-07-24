package classes.processo;

import classes.sistemaOperacional.SistemaOperacional;

import java.util.ArrayList;


public class RecursosNecessarios{
	private ArrayList<Integer> recursosNecessarios;
	private SistemaOperacional so;
	
	public RecursosNecessarios(SistemaOperacional so){
		this.so=so;
	}
//----------------------------------------------------------------------------------
	public ArrayList<Integer> getRecursosNecessarios() {
    return recursosNecessarios;
	}

	public SistemaOperacional getSo() {
    return so;
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
		return this.getRecursosNecessarios().add(recursoNecessarioQuantidade);
	}

public boolean isRecursoSuperiorIncompativel(int recursoNecessarioQuantidade){	
		ArrayList<Integer> sistemaOperacionalRecursosDisponiveis=this.retornaRecursosDisponiveisSistemaOperacional();
		int ultimo_indice=this.getRecursosNecessarios().size();	
	
		if(recursoNecessarioQuantidade>sistemaOperacionalRecursosDisponiveis.get(ultimo_indice)){
			return true;
		}
		return false;
	}

	public ArrayList<Integer> retornaRecursosDisponiveisSistemaOperacional(){
		return this.getSo().getRecursosQuantidade();
	}	
//----------------------------------------------------------------------------------

}

