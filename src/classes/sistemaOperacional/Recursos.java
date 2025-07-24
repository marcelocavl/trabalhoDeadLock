package classes.sistemaOperacional;

import java.util.ArrayList;

public class Recursos{
	private ArrayList<Recurso> recursos;
	int recursos_quantidade;

	public Recursos(ArrayList<Recurso> recursos){
		this.recursos=recursos;
		this.recursos_quantidade=recursos.size();	
	}
	
	public ArrayList<Recurso> getRecursos() {
    return recursos;
	}

	public int getRecursos_quantidade() {
    return recursos_quantidade;
	}

	public void setRecursos(ArrayList<Recurso> recursos) {
    this.recursos = recursos;
	}

	public void setRecursos_quantidade(int recursos_quantidade) {
    this.recursos_quantidade = recursos_quantidade;
	}

	//METODOS DE RETORNO 
	public Recurso recursos_retorne_recursos_por_indice(int indice){
		return this.getRecursos().get(indice);
	}

	public ArrayList<String> recursos_retorne_array_recursos_nomes(){
		ArrayList<String> recursos_nome=new ArrayList<>();
		int i;
		for (i=0;i<this.getRecursos_quantidade();i++){	
			Recurso recursoAtual=this.recursos_retorne_recursos_por_indice(i);
			recursos_nome.add(recursoAtual.getRecurso_nome());
		}
		return recursos_nome;
		
	}

	public ArrayList<Integer> recursos_retorne_array_recursos_id(){
		ArrayList<Integer> recursos_id=new ArrayList<>();
		int i;
		for (i=0;i<this.getRecursos_quantidade();i++){	
			Recurso recursoatual=this.recursos_retorne_recursos_por_indice(i);
			recursos_id.add(recursoatual.getRecurso_id());
		}
		return recursos_id;

	}

	public ArrayList<Integer> recursos_retorne_array_recursos_quantidade(){
		ArrayList<Integer> recursos_quantidade=new ArrayList<>();
		int i;
		for (i=0;i<this.getRecursos_quantidade();i++){	
			Recurso recursoatual=this.recursos_retorne_recursos_por_indice(i);
			recursos_quantidade.add(recursoatual.getRecurso_id());
		}
		return recursos_quantidade;
	}

	//METODOS DE REMOCAO
	public boolean recursos_decrementa_quant(String recurso){
		int indice_recurso=this.recursos_retorna_indice_do_recurso(recurso);		
		this.getRecursos().get(indice_recurso).recurso_decrementa_quantidade();
		return true;
	}		
	public int recursos_retorna_indice_do_recurso(String recurso){
		return this.getRecursos().indexOf(recurso);
	}

	public boolean atualizar_recursos_quantidade(ArrayList<Integer> recursosQuantidade){
		int i;
		Recurso recursoAtual;
		int novaQuantidade;
		for(i=0;i<this.getRecursos_quantidade();i++){
			recursoAtual=this.recursos_retorne_recursos_por_indice(i);
			novaQuantidade=recursosQuantidade.get(i);
			recursoAtual.setRecurso_quantidade(novaQuantidade);
		}
		return true;
	}

}
