
//IMPORTAÇÕES
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//CLASSE SISTEMA OPERACIONAL
public class SistemaOperacional extends Thread{
	//ATRIBUTOS
	
	// A capacidade máxima de cada arraylist deve ser a quantidade de tipos de recurso
	// para isso, ao invés de arraylist, deve ser usado somente array
	public ArrayList<Recursos> recursos = new ArrayList<>();
    private final ArrayList<Processos> processos = new ArrayList<>();
    private static SistemaInterface interfaceGrafica;

    public SistemaOperacional(SistemaInterface ui, int intervaloVerificacaoSegundos) {
        SistemaOperacional.interfaceGrafica = ui;
    }

    @Override
    public void run() {
        System.out.println("thread so iniciada");
        while (true) {
            try {
			int[][] matrizCR = combinarCReR(processos);
			interfaceGrafica.atualizarMatrizVisual(matrizCR);
			printMatriz(matrizCR);

                atualizarInterface();
								printarRecursos();
								ArrayList<Integer> processosEmDeadlock=retornarArrayIds(this.conferirDeadLock());
								interfaceGrafica.setDeadlockStatus(processosEmDeadlock);	
                Thread.sleep(1000);
                Utils.limparTela();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

		public ArrayList<Integer> retornarArrayIds(ArrayList<Processos> processos){
			ArrayList<Integer> arrayIds=new ArrayList<>();
			for(int i=0;i<processos.size();i++){	
				arrayIds.add(processos.get(i).get_processo_id());	
			}
			return arrayIds;
		}

    public void atualizarInterface() {
        interfaceGrafica.atualizarRecursos(recursos);
        interfaceGrafica.atualizarProcessos(processos);
        interfaceGrafica.atualizarMatrizVisual(combinarCReR(processos));
    }
		public ArrayList<Processos> conferirDeadLock(){
			//gerando variaveis para o algoritmo
			//<INTEGER>RECURSOS DISPONIVEIS
			//<PROCESSOS>PROCESSOS
			//INT PROCESSOSTAM
			//INT RECURSOSTAM
			ArrayList<Integer> recursosDisponiveis=this.gerarArrayRecursosDisponiveis();
			ArrayList<Processos> processos=this.get_processos();
			int processosArrayTam=processos.size();
			int recursosArrayTam=recursos.size();
			boolean processoPodeRodar=true;
			ArrayList<Processos>processosEmDeadLock=new ArrayList<>();
	 		processosEmDeadLock=Utils.copiarProcessos(processos);
			//processosEmDeadLock=Utils.preencherSeq(processosArrayTam,recursosArrayTam);
			int i=0;	
			if(processosArrayTam>0){
			while(i<processosArrayTam){		
				if(Utils.arrayMaior(recursosDisponiveis,processos.get(i).get_recursos_requisitados())){
						//ArrayList<Integer> processosRecursosAlocados=this.retornaArrayAlocados(processosEmDeadLock,i);
						ArrayList<Integer> processosRecursosAlocados=processosEmDeadLock.get(i).get_recursos_alocados();
						ArrayList<Integer> processosRecursosRequisitados=processosEmDeadLock.get(i).get_recursos_requisitados();

						recursosDisponiveis=Utils.subtrairArrays(recursosDisponiveis,processosRecursosRequisitados);
						ArrayList<Integer> aux=Utils.somarArrays(processosRecursosAlocados,processosRecursosRequisitados);
						recursosDisponiveis=Utils.somarArrays(recursosDisponiveis,aux);
						processosEmDeadLock.remove(i);	
						processosArrayTam--;
						i=0;
				}else{		
					i++;
				}
			}
			}
			return processosEmDeadLock;
			}
			


		public ArrayList<Integer> gerarArrayRecursosDisponiveis(){
			ArrayList <Integer> recursosDisponiveis=new ArrayList<>();
			ArrayList<Recursos>	recursos=this.get_recursos();
			int arrayRecursosTam=recursos.size();
			for (int i=0;i<arrayRecursosTam;i++){
				recursosDisponiveis.add(recursos.get(i).getDisponivel().availablePermits());
			}
			return recursosDisponiveis;
		}
		
		public int[][] gerarMatrizRecursosAlocados(){
			ArrayList<Processos> processos=this.get_processos();			
			ArrayList<Recursos> recursos=this.get_recursos();			
			int processosArrayTam=processos.size();
			int recursosArrayTam=recursos.size();

			int[][] matrizRecursosAlocados=new int[processosArrayTam][recursosArrayTam];
			for(int i=0;i<processosArrayTam;i++){
				for(int j=0;j<processosArrayTam;j++){
					matrizRecursosAlocados[i][j]=processos.get(i).get_recursos_requisitados().get(j);
				}
			}
			return matrizRecursosAlocados;
		}
			

	
	
	public ArrayList<Integer> retornaArrayRequisitados(ArrayList<Processos> processos,int indice){
		return processos.get(indice).get_recursos_requisitados();

	}
	public ArrayList<Integer> retornaArrayAlocados(ArrayList<Processos> processos,int indice){
		return processos.get(indice).get_recursos_alocados();
	}


	public void printarRecursos(){
		ArrayList<Recursos> recursos=this.get_recursos();
		int recursosSize=recursos.size();
		for(int i=0;i<recursosSize;i++){
			System.out.println(recursos.get(i).toString());
		}
	}
	
	public void printMatriz(int[][] matriz) {
		System.out.println("Matriz:");
		for (int[] linha : matriz) {
			for (int val : linha) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}

    public int[][] combinarCReR(List<Processos> processos) {
        if (processos.isEmpty()) return new int[0][0];
        int n = processos.size();
        int m = processos.get(0).get_recursos_alocados().size();
        int[][] combinada = new int[n][2 * m];

        for (int i = 0; i < n; i++) {
            List<Integer> c = processos.get(i).get_recursos_alocados();
            List<Integer> r = processos.get(i).get_recursos_requisitados();
            for (int j = 0; j < m; j++) {
                combinada[i][j] = c.get(j);
                combinada[i][j + m] = r.get(j);
            }
        }
        return combinada;
    }

    // GETTERS
    public ArrayList<Recursos> get_recursos() {
        return recursos;
    }

    public ArrayList<Processos> get_processos() {
        return processos;
    }

    public int get_recursos_size() {
        return recursos.size();
    }

    public int getRecursoQuantidadeDisponivel(int indice) {
        return recursos.get(indice).getDisponivel().availablePermits();
    }

    public Recursos getRecurso(int indice) {
        return recursos.get(indice);
    }

    public int getRecursoQuantidadeTotal(int indice) {
        return recursos.get(indice).getTotal();
    }

    // ADD
    public void add_recursos(ArrayList<Recursos> novos) {
        recursos.addAll(novos);
    }

    public void add_processos(Processos p) {
        processos.add(p);
    }

    // Verificações
    public boolean is_recurso_existente(Recursos recurso) {
        return recursos.contains(recurso);
    }

    // Sorteios
    public int sortearNumero() {
        return new Random().nextInt(get_recursos_size());
    }

    public Recursos sortearRecursoAleatorio() {
        return recursos.get(sortearNumero());
    }

    public Recursos retornarRecursoPorIndice(int indice) {
        return recursos.get(indice);
    }

    // Interface getter
    public SistemaInterface getInterface() {
        return interfaceGrafica;
    }

    public int retorna_num_processos() {
        return processos.size();
    }

    public Processos retornarProcessoIndice(int indice) {
        return processos.get(indice);
    }

    public ArrayList<Integer> retorna_vetor_alocados_processo_indice(int indice) {
        return processos.get(indice).get_recursos_alocados();
    }

    public ArrayList<Integer> retorna_vetor_requisitados_processo_indice(int indice) {
        return processos.get(indice).get_recursos_requisitados();
    }

    public void printarRecursosAlocados() {
        for (Processos p : processos) {
            p.printar_recursos_alocados();
        }
    }

    public void printarRecursosRequisitados() {
        for (Processos p : processos) {
            p.printar_recursos_requisitados();
        }
    }
}
