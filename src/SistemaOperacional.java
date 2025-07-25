
//IMPORTAÇÕES
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

//CLASSE SISTEMA OPERACIONAL
public class SistemaOperacional{
	//ATRIBUTOS

	// A capacidade máxima de cada arraylist deve ser a quantidade de tipos de recurso
	// para isso, ao invés de arraylist, deve ser usado somente array
	public ArrayList<Recursos> recursos = new ArrayList<>();
	public ArrayList<Semaphore> semaphores = new ArrayList<>();
	private final ArrayList<Processos> processos = new ArrayList<>();
	private static SistemaInterface interfaceGrafica;

    public SistemaOperacional(SistemaInterface ui, int intervaloVerificacaoSegundos) {
        SistemaOperacional.interfaceGrafica = ui;
    }

	//METODOS
	//metodos gets
	public ArrayList<Recursos> get_recursos(){
		return this.recursos;
	}
	
	public int get_recursos_size(){
		return this.get_recursos().size();
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
    public void add_processos(Processos p) {
        processos.add(p);
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

	public void atualizarInterface() {
    interfaceGrafica.atualizarRecursos(recursos);
    interfaceGrafica.atualizarProcessos(processos);
}
	//sorteio aleatorio do recursp
	
	public int sortearNumero()	{
		Random rand = new Random();
		int random=rand.nextInt(get_recursos_size());
		return random;
	}
	public Recursos sortearRecursoAleatorio() {
		Random rand = new Random();
		return recursos.get(rand.nextInt(recursos.size()));
	}
	public Recursos retornarRecursoPorIndice(int indice) {
		return recursos.get(indice);
	}


	public SistemaInterface getInterface() {
    	return interfaceGrafica;
}

	public int retorna_num_processos(){
		return this.processos.size();
	}
	
	
	public ArrayList<Processos> getArrayProcessos(){
		return this.processos;
	}
	public Processos retornarProcessoIndice(int indice){		
		return this.getArrayProcessos().get(indice);
	}

	public ArrayList<Integer> retorna_vetor_alocados_processo_indice(int indice){
		return this.retornarProcessoIndice(indice).get_recursos_alocados();
	}

	public void gerar_matriz_recursos_alocados(){
		int num_recursos=get_recursos_size();
		int num_processos=retorna_num_processos();
		
		int [][] matriz_alocados=new int[num_recursos][num_processos];
		int i,j;	
		for(i=0;i<num_processos;i++){
			ArrayList<Integer> vetorAlocadosProcesso=retorna_vetor_alocados_processo_indice(i);
			for(j=0;j<num_recursos;j++){	
				matriz_alocados[i][j]=vetorAlocadosProcesso.get(j);
			}
		}
		this.printar_matriz_recursos_alocados
	}
/*
	public void printar_matriz_recursos_alocados(int[][] matriz,int column,int lines){
		for(int i=0;i<co
	}
*/	
}

