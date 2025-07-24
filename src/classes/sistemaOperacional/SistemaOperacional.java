//EMPACOTAMENTO
package classes.sistemaOperacional;

//IMPORTAÇÕES
import classes.processo.Processos;

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
	public Recursos get_recursos(){
		return this.recursos;
	}


	public boolean set_recursos(Recursos recursos){
		this.recursos=recursos;
		return true;
	}

	//metodos add

	//metodos de decremento de quantidade de instancia de um recurso
	public boolean remove_uma_instancia_recurso(String recurso){		
		return this.get_recursos().recursos_decrementa_quant(recurso);
	}
	
	//metodos de retorno de indice e tamanho do array
	//retorna o indice do recurso no array recursos

	public int retorna_tamanho_array_recursos(){
		return this.get_recursos().getRecursos_quantidade();
	}


	public ArrayList<Integer> getRecursosQuantidade(){
		return this.get_recursos().recursos_retorne_array_recursos_quantidade();
	}

	public boolean setRecursosQuantidade(ArrayList<Integer> recursosQuantidades){
		return this.get_recursos().atualizar_recursos_quantidade(recursosQuantidades);
	}

}

