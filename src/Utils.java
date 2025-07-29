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
}

