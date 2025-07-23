//CLASSE PROCESOS

package classes

public class Processos{
	public int processo_id; //indetificador do processo
	public ArrayList<integer> recursos_necessarios=new ArrayList<>()
	public SistemaOperacional sistemaOperacional;
	//metodos getters and setters
	public void get_processo_id(){	
		return self.processo_id;
	}

	public void get_recursos_necessarios(){	
		return self.recursos_necessarios;
	}
	public void get_sistemaOperacional(){	
		return self.sistemaOperacional;
	}

	public boolean set_processo_id(int processo_id){
		if(processo_id==null)
			return false;
		self.processo_id=processo_id;
		return true;
	}

	public boolean set_recursos_necessarios(Arraylist<integer> recursos_necessarios){
		if(recursos_necessarios==null)
			return false;
		self.recursos_necessarios=recursos_necessarios;
		return true;
	}

	public boolean set_recursos_necessarios(SistemaOperacional sistemaOperacional){
		if(sistemaOperacional==null)
			return false;
		self.sistemaOperacional=sistemaOperacional;
		return true;
	}
	//metodos para interagir com o sistema operacional
	public void retorna_quantidade_de_instancias_dos_recursos_do_sistemaOperacional(){
		return this.get_sistemaOperacional().get_recursos_quantidade_instancias();
	}	
	public void retorna_recursos_do_sistemaOperacional(){
		return this.get_sistemaOperacional().get_recursos();
	}	


	//metodo de adicionar mais um recurso necessario ao processo
	public void add_recurso_necessario(int quantidade_de_instancias_necessarias_do_recurso){
		self.get_recursos_necessarios().add(quantidade_de_instancias_necessarias_do_recurso);
	}
	//metodos de verificação

	//verifica se a quantidade de instancias de um recurso que o processo precisa é superior a quantidade de instancias que o sistema  		operacional possui desse recurso
	public boolean is_quantidade_necessaria_daquele_recurso_superior(int quantidade_de_instancias_necessarias_do_recurso){	
		//inicializando variaveis importantes
		ArrayList<Integer> processo_recursos_necessarios=this.get_recursos_necessarios();
		ArrayList<Integer> sistemaOperacional_quantidade_de_instancias_dos_recursos=this.retorna_quantidade_de_instancias_dos_recursos_do_sistemaOperacional();
		int ultimo_indice=this.get_recursos_necessarios().size()-1;	
	
		//comparando se o nu
		if(processo_recursos_necessarios.get(ultimo_indice)>sistemaOperacional_quantidade_de_instancias_dos_recursos(ultimo_indice)){
			return true;
		}
		return false;
		
	}

	//verifica se o sistema operacional possui todos os recursos necessarios para o processo
	public void is_quantidade_necessaria_de_recursos_superior(int quantidade_de_instancias_necessarias_do_recurso){	
	}


}
