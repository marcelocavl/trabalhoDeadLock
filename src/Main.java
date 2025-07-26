
import java.util.ArrayList;

//IMPORTAÇÕES

//CLASSE MAIN

public class Main{
	public static void main(String args[]){	

		SistemaInterface tela = new SistemaInterface();
		SistemaOperacional so = new SistemaOperacional(tela, 5);
		tela.setSistema(so);


		AddRecursosDialog dialog = new AddRecursosDialog(tela);
        dialog.setVisible(true);
			
        if (dialog.isConfirmed()) {
			ArrayList<Recursos> recursos = dialog.getRecursos();

            // System.out.println("Recurso: " + nome);
            // System.out.println("ID: " + identificador);
            // System.out.println("Quantidade: " + quantidade);

			so.add_recursos(recursos);

			for (int i = 0; i < recursos.size(); i++) {
   				 Recursos r = recursos.get(i);
   				tela.addLog("SO adicionou recurso: " + r.getNome() + 
                " ID (" + r.getId() + "), total: " + r.getTotal() + 
                ", disponível: " + r.getDisponivel());
    			tela.addRecursoRow(r.getNome(), r.getDisponivel().availablePermits()); 
}

            tela.setVisible(true);
						so.start();
        } else {
            System.exit(0);
        }
	}	
}
