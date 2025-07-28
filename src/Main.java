
import java.util.ArrayList;

//IMPORTAÇÕES

//CLASSE MAIN


public class Main {
    public static void main(String args[]) {    
        SistemaInterface tela = new SistemaInterface();
        SistemaOperacional so = new SistemaOperacional(tela, 5);
        tela.setSistema(so);

        AddRecursosDialog dialog = new AddRecursosDialog(tela);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            ArrayList<Recursos> recursos = dialog.getRecursos();
            so.add_recursos(recursos);

            for (Recursos r : recursos) {
                tela.addLog("SO adicionou recurso: " + r); // usa toString() formatado
                tela.addRecursoRow(r.getNome(), r.getDisponivel().availablePermits()); 
            }

            tela.setVisible(true);
            so.start();
        } else {
            System.exit(0);
        }
    }    
}
