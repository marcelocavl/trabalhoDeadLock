//CLASSE PROCESOS
public class Processos{
	public int processo_id; //indetificador do processo
	public ArrayList<integer> recursos_necessarios=new ArrayList<>()
	//metodos getters and setters
	public void get_processo_id(){	
		return self.processo_id;
	}

	public boolean set_processo_id(int processo_id){
		if(processo_id==null)
			return false;
		self.processo_id=processo_id;
		return true;
	}

	public void get_recursos_necessarios(){	
		return self.recursos_necessarios;
	}

	public boolean set_recursos_necessarios(Arraylist<integer> recursos_necessarios){
		if(recursos_necessarios==null)
			return false;
		self.recursos_necessarios=recursos_necessarios;
		return true;
	}

	//metodo de adicionar mais um recurso necessario ao processo
	public void add_recurso_necessario(int quantidade_de_instancias_necessarias_do_recurso){
		self.get_recursos_necessarios().add(quantidade_de_instancias_necessarias_do_recurso);
	}
	//metodos de verificação
	//verifica se a quantidade de instancias de um recurso que o processo precisa é superior a quantidade de instancias que o sistema  		operacional possui desse recurso
	public void is_quantidade_necessaria_daquele_recurso_superior(int quantidade_de_instancias_necessarias_do_recurso){	
	}

	//verifica se o sistema operacional possui todos os recursos necessarios para o processo
	public void is_quantidade_necessaria_de_recursos_superior(int quantidade_de_instancias_necessarias_do_recurso){	
	}


}
