//CLASSE PROCESOS

package classes;

import java.util.ArrayList;



public class Processos{
	public int processo_id; //indetificador do processo
	public ArrayList<Integer> recursos_necessarios=new ArrayList<>();
	public SistemaOperacional sistemaOperacional;
	//metodos getters and setters
	public int get_processo_id(){	
		return this.processo_id;
	}

	public ArrayList<Integer> get_recursos_necessarios(){	
		return this.recursos_necessarios;
	}
	public SistemaOperacional get_sistemaOperacional(){	
		return this.sistemaOperacional;
	}

	public boolean set_processo_id(Integer processo_id){
		if(processo_id==null)
			return false;
		this.processo_id=processo_id;
		return true;
	}

	public boolean set_recursos_necessarios(ArrayList<Integer> recursos_necessarios){
		if(recursos_necessarios==null)
			return false;
		this.recursos_necessarios=recursos_necessarios;
		return true;
	}

	public boolean set_sistemaOperacional(SistemaOperacional sistemaOperacional){
		if(sistemaOperacional==null)
			return false;
		this.sistemaOperacional=sistemaOperacional;
		return true;
	}
	//metodos para interagir com o sistema operacional
	public ArrayList<Integer> retorna_quantidade_de_instancias_dos_recursos_do_sistemaOperacional(){
		return this.get_sistemaOperacional().get_recursos_quantidade_instancias();
	}	
	public ArrayList<String> retorna_recursos_do_sistemaOperacional(){
		return this.get_sistemaOperacional().get_recursos();
	}	


	//metodo de adicionar mais um recurso necessario ao processo
	public boolean add_recurso_necessario(int quantidade_de_instancias_necessarias_do_recurso){
		if(this.is_quantidade_necessaria_daquele_recurso_superior(quantidade_de_instancias_necessarias_do_recurso))
			return false;
		return this.get_recursos_necessarios().add(quantidade_de_instancias_necessarias_do_recurso);
	}
	//metodos de verificação

	//verifica se a quantidade de instancias de um recurso que o processo precisa é superior a quantidade de instancias que o sistema  		operacional possui desse recurso
	public boolean is_quantidade_necessaria_daquele_recurso_superior(int quantidade_de_instancias_necessarias_do_recurso){	
		//inicializando variaveis importantes
		//ArrayList<Integer> processo_recursos_necessarios=this.get_recursos_necessarios();
		ArrayList<Integer> sistemaOperacional_quantidade_de_instancias_dos_recursos=this.retorna_quantidade_de_instancias_dos_recursos_do_sistemaOperacional();
		int ultimo_indice=this.get_recursos_necessarios().size();	
	
		//comparando se o nu
		if(quantidade_de_instancias_necessarias_do_recurso>sistemaOperacional_quantidade_de_instancias_dos_recursos.get(ultimo_indice)){
			return true;
		}
		return false;
		
	}

	//verifica se o sistema operacional possui todos os recursos necessarios para o processo
	public void is_quantidade_necessaria_de_recursos_superior(int quantidade_de_instancias_necessarias_do_recurso){	
	}


}
