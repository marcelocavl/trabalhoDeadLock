package classes.recurso;

import java.util.concurrent.Semaphore;

public class Recurso{
	private String recursoNome;
	private int recursoId;
	private Semaphore recursoQuantidade;

		
	public Recurso(String recurso_nome,int recurso_id,int recurso_quantidade){
		this.recursoNome=recurso_nome;
		this.recursoId=recurso_id;
		this.recursoQuantidade=new Semaphore(recurso_quantidade);
	}

	public String getRecursoNome() {
		return this.recursoNome;
	}

	public int getRecursoId() {
    return this.recursoId;
	}

	public Semaphore getRecursoQuantidade() {
    return this.recursoQuantidade;
	}

	public void setRecursoId(int recurso_id) {
    this.recursoId = recurso_id;
	}


	public void setRecursoNome(String recurso_nome) {
    this.recursoNome = recurso_nome;
	}


	public void setRecursoQuantidade(Semaphore recurso_quantidade) {
    this.recursoQuantidade = recurso_quantidade;
	}

	//METODOS DE DECREMENTO
/*
	public void recursoDecrementaQuantidade(){
		this.recursoQuantidade--;
	}
*/	
	

}
