
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
								if(!this.conferirDeadLock()){
									interfaceGrafica.setDeadlockStatus(true);	
								}
                Thread.sleep(1000);
                Utils.limparTela();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void atualizarInterface() {
        interfaceGrafica.atualizarRecursos(recursos);
        interfaceGrafica.atualizarProcessos(processos);
        interfaceGrafica.atualizarMatrizVisual(combinarCReR(processos));
    }
		public boolean conferirDeadLock(){
			ArrayList<Integer> recursosDisponiveis=this.gerarArrayRecursosDisponiveis();
			ArrayList<Processos> procesos=this.get_processos();
			int processosArrayTam=processos.size();
			int recursosArrayTam=recursos.size();
			boolean processoPodeRodar=true;

			for(int i=0;i<processosArrayTam;i++){		
				for(int j=0;j<processosArrayTam;j++){		
					processoPodeRodar=true;
					if(recursosDisponiveis.get(j)<processos.get(i).get_recursos_requisitados().get(j)){
						processoPodeRodar=false;
						break;	
					}
				}
				if (processoPodeRodar==true)
					break;
			}
			return processoPodeRodar;
			
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
