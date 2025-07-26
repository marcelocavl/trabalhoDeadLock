import java.util.concurrent.Semaphore;

public class Utils {

	//funcao de timer de segs
	public static void timer_segs(double segs){
		long tempoInicial = System.currentTimeMillis();
  		while (System.currentTimeMillis() - tempoInicial < segs*1000) {
           // Espera ativa (consome CPU)
    	}
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
}
