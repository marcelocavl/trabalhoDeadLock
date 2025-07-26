

//import java.Utils;

import java.util.ArrayList;
import java.util.Random;

public class Processos extends Thread {

	public int processo_id; //indentificador do processo
	private final float tempo_solicitacao;
    private final float tempo_utilizacao;
    private final SistemaOperacional sistema;
		private ArrayList<Integer> recursosAlocados;
		private ArrayList<Integer> recursosRequisitados;

    private String statusAtual = "Dormindo";

    public Processos(int processo_id, float tempo_solicitacao, float tempo_utilizacao, SistemaOperacional sistema) {
        this.processo_id = processo_id;
        this.tempo_solicitacao = tempo_solicitacao;
        this.tempo_utilizacao = tempo_utilizacao;
        this.sistema = sistema;
				this.recursosAlocados=new ArrayList<>();
				this.recursosRequisitados=new ArrayList<>();
				
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
	
    public ArrayList<Integer> get_recursos_requisitados() {
			return recursosRequisitados;
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

		public int get_recursos_size(){
			return this.get_sistema_operacional().get_recursos_size();
		}

		public void inicializarVetores(){
			this.inicializarVetorRecursosAlocados();
			this.inicializarVetorRecursosRequisitados();
		}

		public void inicializarVetorRecursosAlocados(){	
			int tamVetorRecursosSO=this.get_sistema_operacional().get_recursos_size();
			for(int i=0;i<tamVetorRecursosSO;i++){	
				this.get_recursos_alocados().add(0);
			}
		}

		public void inicializarVetorRecursosRequisitados(){	
			int tamVetorRecursosSO=this.get_sistema_operacional().get_recursos_size();
			for(int i=0;i<tamVetorRecursosSO;i++){	
				this.get_recursos_requisitados().add(0);
			}
		}

		public int getRecursosSOArraySize(){
			SistemaOperacional sistemaOperacional=this.get_sistema_operacional();	
			return sistemaOperacional.get_recursos().size();	
		}
	
		public ArrayList<Recursos> getRecursosSO(){	
			SistemaOperacional sistemaOperacional=this.get_sistema_operacional();			
			return sistemaOperacional.get_recursos();
		}


		public void incrementa_vetor_recursos_alocados(int indice){		
			int recurso=this.get_recursos_alocados().get(indice);
			recurso++;
		}

		public void incrementa_vetor_recursos_requisitados(int indice){		
			int recurso=this.get_recursos_requisitados().get(indice);
			recurso++;
		}

		public void enviaLogDormindo(){
			sistema.getInterface().addLog("Processo " + processo_id + " está dormindo.");
		}

		public void enviaLogSolicitacao(Recursos recurso){
			sistema.getInterface().addLog("Processo " + processo_id + " solicitou recurso " + recurso.getNome());
		}
		public void enviaLogLiberouRecurso(Recursos recurso){
			sistema.getInterface().addLog("Processo " + processo_id + " liberou recurso " + recurso.getNome());
		}

		public void enviaLogBloqueado(Recursos recurso){
			sistema.getInterface().addLog("Processo " + processo_id + " está BLOQUEADO aguardando recurso " + recurso.getNome());
		}

		public void enviaLogLiberarRecurso(Recursos recurso){
			sistema.getInterface().addLog("Processo " + processo_id + " liberou recurso " + recurso.getNome());
		}


		public void esperandoSolicitar(){
			try{
				Thread.sleep((long) (this.tempo_solicitacao * 1000));
			}catch (Exception e){}
		}

		public void executando(){
			try{	
			Thread.sleep((long) (this.tempo_utilizacao * 1000));
			}catch(Exception e){}
		}


		public int geraNumeroAleatorio(){
			Random rand = new Random();
			int random=rand.nextInt(get_recursos_size());
			return random;
		}
		
		public void alocaRecurso(int indice){
			SistemaOperacional sistemaOperacional=this.get_sistema_operacional();
			Recursos recurso=sistemaOperacional.get_recursos().get(indice);		
			Utils.down(recurso.getDisponivel());
			
		}
	
		public void incrementaVetorRequisicao(int indice){		
			ArrayList<Integer> vetorRequisicao=this.get_recursos_requisitados();
			int recursoSolicitado=vetorRequisicao.get(indice);
			recursoSolicitado++;
			
		}

		public void solicitar(int indice){
			SistemaOperacional sistemaOperacional=get_sistema_operacional();
//			ArrayList<Recursos> recursosSO=this.getRecursosSO();
			
			if(sistemaOperacional.getRecursoQuantidadeDisponivel(indice)>0){
				this.alocaRecurso(indice);
				this.enviaLogSolicitacao(sistemaOperacional.getRecurso(indice));		
			}else{
				this.enviaLogBloqueado(sistemaOperacional.getRecurso(indice));
				this.incrementaVetorRequisicao(indice);
			}
			
		}

		public void liberarRecurso(int indice){	
			SistemaOperacional sistemaOperacional=this.get_sistema_operacional();
			Recursos recurso=sistemaOperacional.get_recursos().get(indice);		
			Utils.up(recurso.getDisponivel());
		}


    @Override
    public void run() {
				this.inicializarVetores();
        while (true) {
            try {		
							int indiceAleatorio=geraNumeroAleatorio();
							esperandoSolicitar();
							solicitar(indiceAleatorio);
							executando();
							liberarRecurso(indiceAleatorio);
														
/*
	               setStatus("Dormindo");
                sistema.getInterface().addLog("Processo " + processo_id + " está dormindo.");
                sistema.atualizarInterface();
                Thread.sleep((long) (tempo_solicitacao * 1000));

                //Recursos recurso = sistema.sortearRecursoAleatorio();
								int indice_aleatorio=sistema.sortearNumero();
								Recursos recurso= sistema.retornarRecursoPorIndice(indice_aleatorio);

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
												this.incrementa_vetor_recursos_requisitados();
                        sistema.atualizarInterface();
                        sistema.getInterface().addLog("Processo " + processo_id + " está BLOQUEADO aguardando recurso " + recurso.getNome());

                        while (recurso.getDisponivel() == 0) {
                            Thread.sleep(1000);
                        }

                        setStatus("Rodando");
                        sistema.atualizarInterface();
                    }
                }
*/
            } catch (Exception e) {
                break;
            }
        }
    }
}
