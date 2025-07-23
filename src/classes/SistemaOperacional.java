//EMPACOTAMENTO
package classes;

//IMPORTAÇÕES
import java.util.ArrayList;

//CLASSE SISTEMA OPERACIONAL
public class SistemaOperacional{
	//ATRIBUTOS
	public ArrayList<String>  recursos=new ArrayList<>();
	public ArrayList<Integer> recursos_id=new ArrayList<>();
	public ArrayList<Integer> recursos_quantidade_instancias=new ArrayList<>();

	//METODOS
	//metodos gets
	public ArrayList<String> get_recursos(){
		return this.recursos;
	}

	public ArrayList<Integer> get_recursos_id(){
		return this.recursos_id;
	}

	public ArrayList<Integer> get_recursos_quantidade_instancias(){
		return this.recursos_quantidade_instancias;
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
	
	//metodos de retorno de indice
	public int retorna_indice_do_recurso_no_arraylist(String recurso){
		if(!this.is_recurso_existente(recurso))
			return -1;
		return this.get_recursos().indexOf(recurso);
			
	}

	//metodos de verificacao
	public boolean is_recurso_existente(String recurso){
		return this.get_recursos().contains(recursos);
	}

}

