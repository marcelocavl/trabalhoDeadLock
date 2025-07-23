package classes;

import utils.Utils;
import java.util.ArrayList;


public class SistemaOperacional_rodando{	
	public SistemaOperacional sistemaOperacional;
	public ArrayList<Processos> processos;


	public void rodar(){
		Utils.limparTela();
		
	}

	public void printar_tela(){
		int i;
		for(i=0;i<this.processos.size();i++){
			System.out.println("p"+i+" "+processos.get(i).get_recursos_necessarios());
		}
		System.out.println("recursos do sistema operacional: "+this.sistemaOperacional.get_recursos());
		System.out.println("quantidade de cada recurso: "+this.sistemaOperacional.get_recursos_quantidade_instancias());
	}
}
