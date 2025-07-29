
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KillProcessoDialog extends JDialog{
    private JTextField idField;
    private int id;
    private boolean confirmado = false;

    public JTextField getIdField(){
        return idField;	
    }
    
    public KillProcessoDialog(SistemaInterface parent) {
        super(parent, "Eliminar Processo", true);

        JPanel camposPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        camposPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        idField = new JTextField();

        Dimension campoSize = new Dimension(150, 25);
        idField.setPreferredSize(campoSize);
        
        camposPanel.add(new JLabel("ID do Processo:"));
        camposPanel.add(idField);

        JPanel botaoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addEliminarButton = new JButton("Matar");
        addEliminarButton.setPreferredSize(new Dimension(150, 30));
        botaoPanel.add(addEliminarButton);

        addEliminarButton.addActionListener(e -> {
            String texto = idField.getText().trim();	
            //System.out.println("Texto digitado: [" + texto + "]");

            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite um ID de processo.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idInput;
            try {
                idInput = Integer.parseInt(texto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "O ID deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            id = idInput;

            Processos processo = null;
            List<Processos> listaProcessosOriginal = parent.getSistema().get_processos();
            List<Processos> listaProcessos = new ArrayList<>(listaProcessosOriginal);

            try {
                for (Processos p : listaProcessos) {
                    if (p != null && id == p.get_processo_id()) {
                        processo = p;
                        break;
                    }
                }
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, "ID de processo inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (processo == null || !processo.isAlive()) {
                JOptionPane.showMessageDialog(this, "O processo com esse ID não está ativo.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            processo.interrupt();
            confirmado = true;

            idField.setText("");
            idField.requestFocus();

            this.setVisible(false);
        });


        setLayout(new BorderLayout(10, 10));
        add(camposPanel, BorderLayout.CENTER);
        add(botaoPanel, BorderLayout.SOUTH);

        setSize(400, 250);
        setLocationRelativeTo(parent);
    }

    private boolean validarCampos() {
        return !idField.getText().trim().isEmpty();
    }

    public boolean isConfirmed() {
        return confirmado;
    }

    public int getId() {
        return this.id;
    }
}
