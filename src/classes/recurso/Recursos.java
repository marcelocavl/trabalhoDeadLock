package classes.recurso;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Recursos{
	private ArrayList<Recurso> recursos;
	int recursosQuantidade;

	public Recursos(ArrayList<Recurso> recursos){
		this.recursos=recursos;
		this.recursosQuantidade=recursos.size();	
	}
	
	public ArrayList<Recurso> getRecursos() {
    return recursos;
	}

	public int getRecursosQuantidade() {
    return this.recursosQuantidade;
	}

	public void setRecursos(ArrayList<Recurso> recursos) {
    this.recursos = recursos;
	}

	public void setRecursosQuantidade(int recursos_quantidade) {
    this.recursosQuantidade = recursos_quantidade;
	}

	//METODOS DE RETORNO 
	public Recurso retorneRecursosPorIndice(int indice){
		return this.getRecursos().get(indice);
	}

	public ArrayList<String> retorneArrayRecursosNomes(){
		ArrayList<String> recursos_nome=new ArrayList<>();
		int i;
		for (i=0;i<this.getRecursosQuantidade();i++){	
			Recurso recursoAtual=this.retorneRecursosPorIndice(i);
			recursos_nome.add(recursoAtual.getRecursoNome());
		}
		return recursos_nome;
		
	}

	public ArrayList<Integer> RetorneArrayRecursosId(){
		ArrayList<Integer> recursos_id=new ArrayList<>();
		int i;
		for (i=0;i<this.getRecursosQuantidade();i++){	
			Recurso recursoatual=this.retorneRecursosPorIndice(i);
			recursos_id.add(recursoatual.getRecursoId());
		}
		return recursos_id;

	}

	public ArrayList<Semaphore> retorneArrayRecursosQuantidade(){
		ArrayList<Semaphore> recursos_quantidade=new ArrayList<>();
		int i;
		for (i=0;i<this.getRecursosQuantidade();i++){	
			Recurso recursoatual=this.retorneRecursosPorIndice(i);
			recursos_quantidade.add(recursoatual.getRecursoQuantidade());
		}
		return recursos_quantidade;
	}

	//METODOS DE REMOCAO
/*
	public boolean recursosDecrementaQuant(String recurso){
		int indice_recurso=this.retornaIndiceDoRecurso(recurso);		
		this.getRecursos().get(indice_recurso).recursoDecrementaQuantidade();
		return true;
	}		
*/
	public int retornaIndiceDoRecurso(String recurso){
		return this.getRecursos().indexOf(recurso);
	}
/*
	public boolean atualizarRecursosQuantidade(ArrayList<Integer> recursosQuantidade){
		int i;
		Recurso recursoAtual;
		int novaQuantidade;
		for(i=0;i<this.getRecursosQuantidade();i++){
			recursoAtual=this.retorneRecursosPorIndice(i);
			novaQuantidade=recursosQuantidade.get(i);
			recursoAtual.setRecursoQuantidade(novaQuantidade);
		}
		return true;
	}
*/
}
