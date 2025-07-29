import java.awt.*;
import java.util.List;
import javax.swing.*;

public class PainelMatriz extends JPanel {
    public PainelMatriz(String titulo, int[][] matriz, List<Processos> processos, int numRecursos) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(titulo));

        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);

        StringBuilder sb = new StringBuilder();
        sb.append("      ");
        for (int j = 0; j < numRecursos; j++) {
            sb.append(String.format(" R%-3d", j));
        }
        sb.append("\n");

        for (int i = 0; i < matriz.length; i++) {
            sb.append(String.format("P%-3d ", processos.get(i).get_processo_id()));
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(String.format(" %-4d", matriz[i][j]));
            }
            sb.append("\n");
        }

        area.setText(sb.toString());
        add(new JScrollPane(area), BorderLayout.CENTER);
    }

    public PainelMatriz(String titulo, int[] vetor, int numRecursos) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(titulo));

        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < numRecursos; j++) {
            sb.append(String.format("R%-3d: %-4d\n", j, vetor[j]));
        }

        area.setText(sb.toString());
        add(area, BorderLayout.CENTER);
    }
}
