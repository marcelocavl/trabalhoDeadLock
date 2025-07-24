//EMPACOTAMENTO
package classes.sistemaOperacional;

//IMPORTAÇÕES
import classes.processo.Processos;

import java.util.ArrayList;

//CLASSE SISTEMA OPERACIONAL
public class SistemaOperacional{
	//ATRIBUTOS
	public ArrayList<String>  recursos=new ArrayList<>();
	public ArrayList<Integer> recursos_id=new ArrayList<>();
	public ArrayList<Integer> recursos_quantidade_instancias=new ArrayList<>();

	//METODOS
	//metodos gets and setters
	public ArrayList<String> get_recursos(){
		return this.recursos;
	}

	public ArrayList<Integer> get_recursos_id(){
		return this.recursos_id;
	}

	public ArrayList<Integer> get_recursos_quantidade_instancias(){
		return this.recursos_quantidade_instancias;
	}

	public boolean set_recursos(ArrayList<String> recursos){
		if(recursos==null)
			return false;
		this.recursos=recursos;
		return true;
	}

	public boolean set_recursos_id(ArrayList<Integer> recursos_id){
		if(recursos_id==null)
			return false;
		this.recursos_id=recursos_id;
		return true;
	}

	public boolean set_recursos_quantidade_instancias(ArrayList<Integer> recursos_quantidade_instancias){
		if(recursos_quantidade_instancias==null)
			return false;
		this.recursos_quantidade_instancias=recursos_quantidade_instancias;
		return true;
	}

	//metodos add
	public boolean add_recurso(String recurso){
		return this.get_recursos().add(recurso);
	}

	public boolean add_recurso_id(int recurso_id){
		return this.get_recursos_id().add(recurso_id);
	}

	public boolean add_recurso_quantidade_instancias(int quantidade_instancias){
		return this.get_recursos_quantidade_instancias().add(quantidade_instancias);
	}

	//metodos de decremento de quantidade de instancia de um recurso
	public boolean remove_uma_instancia_recurso(String recurso){	
		if(!this.is_recurso_existente(recurso))
			return false;	
		return this.get_recursos().remove(recurso);
	}
	
	//metodos de retorno de indice e tamanho do array
	//retorna o indice do recurso no array recursos
	public int retorna_indice_do_recurso_no_array_recursos(String recurso){
		if(!this.is_recurso_existente(recurso))
			return -1;
		return this.get_recursos().indexOf(recurso);
			
	}

	//retorna o indice do recurso no array recursos_id
	public int retorna_indice_do_recurso_no_array_recursos_id(int recurso_id){
		if(!this.is_recurso_existente(recurso_id))
			return -1;
		return this.get_recursos_id().indexOf(recurso_id);
			
	}

	public int retorna_tamanho_array_recursos(){
		return this.get_recursos().size();
	}

	//metodos de verificacao
	//verifica se o recurso existe no arraylist de recursos
	public boolean is_recurso_existente(String recurso){
		return this.get_recursos().contains(recursos);
	}
	//verifica se o recurso existe no arraylist de recursos_id
	public boolean is_recurso_existente(int recurso_id){
		return this.get_recursos_id().contains(recursos_id);
	}


}

