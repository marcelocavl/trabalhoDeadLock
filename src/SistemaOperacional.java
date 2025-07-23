
//IMPORTAÇÕES
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

//CLASSE SISTEMA OPERACIONAL
public class SistemaOperacional{
	//ATRIBUTOS

	// A capacidade máxima de cada arraylist deve ser a quantidade de tipos de recurso
	// para isso, ao invés de arraylist, deve ser usado somente array

	public ArrayList<Recursos> recursos = new ArrayList<>();
	public ArrayList<Semaphore> semaphores = new ArrayList<>();

	//METODOS
	//metodos gets
	public ArrayList<Recursos> get_recursos(){
		return this.recursos;
	}

	public ArrayList<String> get_lista_recursos(){
		ArrayList<String> lista_recursos = new ArrayList<>();

		for (int i = 0; i < recursos.size(); i++){
			lista_recursos.add(recursos.get(i).toString());
		}
		return lista_recursos;
	}

	//metodos add
	public void add_recursos(ArrayList<Recursos> recursos){
		for (int i = 0; i < recursos.size(); i++){
			this.get_recursos().add(recursos.get(i));
		}
	}

	//metodos de decremento de quantidade de instancia de um recurso
	public boolean remove_uma_instancia_recurso(Recursos recurso){	
		if(!this.is_recurso_existente(recurso))
			return false;	
		return this.get_recursos().remove(recurso);
	}
	
	//metodos de retorno de indice
	public int retorna_indice_do_recurso_no_arraylist(Recursos recurso){
		if(!this.is_recurso_existente(recurso))
			return -1;
		return this.get_recursos().indexOf(recurso);
			
	}

	//metodos de verificacao
	public boolean is_recurso_existente(Recursos recurso){
		return this.get_recursos().contains(recursos);
	}

}

