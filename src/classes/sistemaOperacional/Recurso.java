package classes.sistemaOperacional;

public class Recurso{
	private String recurso_nome;
	private int recurso_id;
	private int recurso_quantidade;
		
	public Recurso(String recurso_nome,int recurso_id,int recurso_quantidade){
		this.recurso_nome=recurso_nome;
		this.recurso_id=recurso_id;
		this.recurso_quantidade=recurso_quantidade;
	}

	public String getRecurso_nome() {
		return recurso_nome;
	}

	public int getRecurso_id() {
    return recurso_id;
	}

	public int getRecurso_quantidade() {
    return recurso_quantidade;
	}

	public void setRecurso_id(int recurso_id) {
    this.recurso_id = recurso_id;
	}


	public void setRecurso_nome(String recurso_nome) {
    this.recurso_nome = recurso_nome;
	}


	public void setRecurso_quantidade(int recurso_quantidade) {
    this.recurso_quantidade = recurso_quantidade;
	}

	//METODOS DE DECREMENTO
	public void recurso_decrementa_quantidade(){
		this.recurso_quantidade--;
	}
	
	

}
