//EMPACOTAMENTO
package classes.sistemaOperacional;

//IMPORTAÇÕES
import classes.processo.Processo;

import java.util.ArrayList;

//CLASSE SISTEMA OPERACIONAL
public class SistemaOperacional{
	//ATRIBUTOS
	private Recursos recursos;	

	//CONSTRUTOR
	public SistemaOperacional(Recursos recursos){
		this.recursos=recursos;
	}	

	//METODOS
	//metodos gets and setters
	public Recursos getRecursos(){
		return this.recursos;
	}


	public boolean set_recursos(Recursos recursos){
		this.recursos=recursos;
		return true;
	}

	//metodos add

	//metodos de decremento de quantidade de instancia de um recurso
	public boolean remove_uma_instancia_recurso(String recurso){		
		return this.getRecursos().recursosDecrementaQuant(recurso);
	}
	
	//metodos de retorno de indice e tamanho do array
	//retorna o indice do recurso no array recursos

	public int retorna_tamanho_array_recursos(){
		return this.getRecursos().getRecursosQuantidade();
	}


	public ArrayList<Integer> getRecursosQuantidade(){
		return this.getRecursos().retorneArrayRecursosQuantidade();
	}

	public boolean setRecursosQuantidade(ArrayList<Integer> recursosQuantidades){
		return this.getRecursos().atualizarRecursosQuantidade(recursosQuantidades);
	}

}

