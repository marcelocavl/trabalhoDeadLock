
import java.util.ArrayList;

//IMPORTAÇÕES

//CLASSE MAIN

public class Main{
	public static void main(String args[]){	
		SistemaOperacional so = new SistemaOperacional();

		SistemaInterface tela = new SistemaInterface();

		AddRecursosDialog dialog = new AddRecursosDialog(tela);
        dialog.setVisible(true);
			
        if (dialog.isConfirmed()) {
			ArrayList<Recursos> recursos = dialog.getRecursos();

            // System.out.println("Recurso: " + nome);
            // System.out.println("ID: " + identificador);
            // System.out.println("Quantidade: " + quantidade);

			so.add_recursos(recursos);

			for (int i = 0; i < recursos.size(); i++){
				tela.addLog("SO adicionou recurso: " + recursos.get(i).getNome() + " ID (" + recursos.get(i).getId() + ") " + ", quantidade: " + recursos.get(i).getQuantidade());
				tela.addRecursoRow(recursos.get(i).getNome(),  recursos.get(i).getQuantidade());
			}

            tela.setVisible(true);
        } else {
            System.exit(0);
        }
	}	
}
