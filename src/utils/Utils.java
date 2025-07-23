package utils;

import java.util.concurrent.Semaphore;
import java.util.ArrayList;


public class Utils{
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

	public static ArrayList<Integer> subtrair_arrays(ArrayList<Integer> array1,ArrayList<Integer> array2){
		int i;
		ArrayList<Integer> resultado=new ArrayList<>();
		for(i=0;i<array1.size();i++){
			resultado.add(array1.get(i)-array1.get(i));	
		}
		return resultado;
	}

	public static ArrayList<Integer> adicionar_arrays(ArrayList<Integer> array1,ArrayList<Integer> array2){
		int i;
		ArrayList<Integer> resultado=new ArrayList<>();
		for(i=0;i<array1.size();i++){
			resultado.add(array1.get(i)+array1.get(i));	
		}
		return resultado;
	}



}
