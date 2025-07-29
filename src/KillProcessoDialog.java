
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
            if (validarCampos()) {
                id = Integer.parseInt(idField.getText());
                (parent.getSistema().get_processos().get(id - 1)).interrupt();
                confirmado = true;

                idField.setText("");
                idField.requestFocus();

                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
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
        return Integer.parseInt(idField.getText().trim());
    }
}
