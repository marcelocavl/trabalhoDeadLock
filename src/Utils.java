import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Utils {

	//funcao de timer de segs
	public static void timer_segs(double segs){
		long tempoInicial = System.currentTimeMillis();
  		while (System.currentTimeMillis() - tempoInicial < segs*1000) {
           // Espera ativa (consome CPU)
    	}
	}

	public static void limparTela(){
		System.out.print("\033[H\033[2J");
    System.out.flush();	
	}

    public static void down(Semaphore semaforo){
		try{
			semaforo.acquire();
		} catch(InterruptedException e){
            e.printStackTrace();
		}
	}

	//funcao de up semaforo
	public static void up(Semaphore semaforo){
		semaforo.release();
	}
	//funcao de up semaforo em mais de um 
	public static void up(Semaphore semaforo,int count){
		semaforo.release(count);
	}

	public static ArrayList<Integer> somarArrays(ArrayList<Integer> a,ArrayList<Integer> b){
		ArrayList<Integer> resultado=new ArrayList<>();
		for(int	i=0;i<a.size();i++){
			resultado.add(a.get(i)+b.get(i));	
		}
		return resultado;
	}

	public static ArrayList<Integer> subtrairArrays(ArrayList<Integer> a,ArrayList<Integer> b){
		ArrayList<Integer> resultado=new ArrayList<>();
		for(int	i=0;i<a.size();i++){
			resultado.add(a.get(i)-b.get(i));	
		}
		return resultado;
	}

	public static ArrayList<Processos> copiarProcessos(ArrayList<Processos> original) {
    ArrayList<Processos> copia = new ArrayList<>();

    for (Processos p : original) {
        Processos copiaProcesso = new Processos(
            p.get_processo_id(),
            p.get_tempo_solicitacao(),
            p.get_tempo_utilizacao(),
            p.get_sistema_operacional()
        );

        // Copia as listas de recursos alocados e requisitados
				//copiaProcesso.inicializarVetores();
        copiaProcesso.get_recursos_alocados().addAll(p.get_recursos_alocados());
        copiaProcesso.get_recursos_requisitados().addAll(p.get_recursos_requisitados());


        // Copia o status atual
	
        copiaProcesso.setStatus(p.getStatus());

        copia.add(copiaProcesso);
    }

    return copia;
	}


	public static boolean arrayMaior(ArrayList<Integer> a,ArrayList<Integer> b){			
		int tam=a.size();		
		for(int i=0;i<tam;i++){
			if(a.get(i)<b.get(i))
				return false;
		}
		return true;
	}	
	public static ArrayList<Integer> preencherSeq(ArrayList<Integer> a,int tam){
		for(int i=1;i<=tam;i++){
			a.add(i);
		}	
		return a;
	}

	


}

	

