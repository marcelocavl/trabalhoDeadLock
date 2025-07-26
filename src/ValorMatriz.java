import javax.swing.*;
import java.awt.*;

public class ValorMatriz extends JPanel {
    private int[][] valor;

    public ValorMatriz(int[][] matriz) {
        this.valor =matriz;
    }
/*
    public void setValor(int novoValor) {
        this.valor = novoValor;
        repaint(); // Redesenha a tela com o novo valor
    }
*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(Color.BLUE);

        String texto = String.valueOf(valor);
        g.drawString(texto, 100, 100); // posição x=100, y=100
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }

    public void iniciar(int[][] matriz){
        JFrame frame = new JFrame("Mostrar valor");
        ValorMatriz painel = new ValorMatriz(matriz); // <- valor a mostrar

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(painel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Exemplo de atualizar o valor após 2 segundos
        new Timer(2000, e -> {
        }).start();
    }

}

