//CLASSE PROCESSOS

import java.util.ArrayList;
public class Processos extends Thread {

	public int processo_id; //indentificador do processo
	private final float tempo_solicitacao;
    private final float tempo_utilizacao;
    private final SistemaOperacional sistema;
		private ArrayList<Integer> recursosAlocados;

    private String statusAtual = "Dormindo";

    public Processos(int processo_id, float tempo_solicitacao, float tempo_utilizacao, SistemaOperacional sistema) {
        this.processo_id = processo_id;
        this.tempo_solicitacao = tempo_solicitacao;
        this.tempo_utilizacao = tempo_utilizacao;
        this.sistema = sistema;
				
    }

    public int get_processo_id() {
        return processo_id;
    }

    public float get_tempo_solicitacao() {
        return tempo_solicitacao;
    }

    public float get_tempo_utilizacao() {
        return tempo_utilizacao;
    }

    public ArrayList<Integer> get_recursos_alocados() {
			return recursosAlocados;
    }
		
    public SistemaOperacional get_sistema_operacional() {
			return sistema;
    }


    public String getStatus() {
        return statusAtual;
    }

    public void setStatus(String status) {
        this.statusAtual = status;
    }

		public void inicializarVetorRecursosAlocados(){	
			int tamVetorRecursosSO=this.get_sistema_operacional().get_recursos_size();
			for(int i=0;i<tamVetorRecursosSO;i++){	
				this.get_recursos_alocados().add(0);
			}
		}

		public void incrementa_vetor_recurso(int indice){		
			int recurso=this.get_recursos_alocados().get(indice);
			recurso++;
		}

    @Override
    public void run() {
        while (true) {
            try {
                setStatus("Dormindo");
                sistema.getInterface().addLog("Processo " + processo_id + " está dormindo.");
                sistema.atualizarInterface();
                Thread.sleep((long) (tempo_solicitacao * 1000));

                //Recursos recurso = sistema.sortearRecursoAleatorio();
								int indice_aleatorio=sistema.sortearNumero();
								Recursos recurso= sistema.retornarRecursoPorIndice(indice_aleatorio);
                sistema.getInterface().addLog("Processo " + processo_id + " solicitou recurso " + recurso.getNome());

                synchronized (recurso) {
                    if (recurso.getDisponivel() > 0) {	
												this.incrementa_vetor_recurso(indice_aleatorio);	
												this.get_sistema_operacional().gerar_matriz_recursos_alocados();
                        setStatus("Rodando");
                        sistema.atualizarInterface();
                        recurso.alocar();
                        sistema.getInterface().addLog("Processo " + processo_id + " está utilizando recurso " + recurso.getNome());

                        Thread.sleep((long) (tempo_utilizacao * 1000));

                       	recurso.liberar();
                        sistema.getInterface().addLog("Processo " + processo_id + " liberou recurso " + recurso.getNome());
                    } else {
                        setStatus("Bloqueado");
                        sistema.atualizarInterface();
                        sistema.getInterface().addLog("Processo " + processo_id + " está BLOQUEADO aguardando recurso " + recurso.getNome());

                        while (recurso.getDisponivel() == 0) {
                            Thread.sleep(1000);
                        }

                        setStatus("Rodando");
                        sistema.atualizarInterface();
                    }
                }

            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
