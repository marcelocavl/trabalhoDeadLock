import java.awt.*;
import javax.swing.*;

public class AddProcessoDialog extends JDialog {
    private JTextField idField;
    private JTextField tempo_solicitacao_field;
    private JTextField tempo_utilizacao_field;
    private boolean confirmado = false;

    public AddProcessoDialog(SistemaInterface parent) {
        super(parent, "Adicionar Processo", true);

        JPanel camposPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        camposPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        idField = new JTextField();
        tempo_solicitacao_field = new JTextField();
        tempo_utilizacao_field = new JTextField();

        Dimension campoSize = new Dimension(150, 25);
        idField.setPreferredSize(campoSize);
        tempo_solicitacao_field.setPreferredSize(campoSize);
        tempo_utilizacao_field.setPreferredSize(campoSize);

        camposPanel.add(new JLabel("ID do Processo:"));
        camposPanel.add(idField);
        camposPanel.add(new JLabel("Tempo de solicitação do Processo (ΔTs):"));
        camposPanel.add(tempo_solicitacao_field);
        camposPanel.add(new JLabel("Tempo de utilização do Processo (ΔTu):"));
        camposPanel.add(tempo_utilizacao_field);

        JPanel botaoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addProcessoButton = new JButton("Adicionar");
        addProcessoButton.setPreferredSize(new Dimension(150, 30));
        botaoPanel.add(addProcessoButton);

        addProcessoButton.addActionListener(e -> {
            if (validarCampos()) {
                confirmado = true;
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
        return !idField.getText().trim().isEmpty() && tempoValido();
    }

    private boolean tempoValido() {
        try {
            Float.parseFloat(tempo_solicitacao_field.getText().trim());
            Float.parseFloat(tempo_utilizacao_field.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isConfirmed() {
        return confirmado;
    }

    public int getId() {
        return Integer.parseInt(idField.getText().trim());
    }

    public float getTempoSolicitacao() {
        return Float.parseFloat(tempo_solicitacao_field.getText().trim());
    }

    public float getTempoUtilizacao() {
        return Float.parseFloat(tempo_utilizacao_field.getText().trim());
    }
}