import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class AddRecursosDialog extends JDialog {
    private JTextField nomeField;
    private JTextField idField;
    private JTextField quantidadeField;
    private boolean confirmed = false;
    private ArrayList<Recursos> recursos = new ArrayList<>();

    private DefaultListModel<String> listModel;
    private final JList<String> recursoList;

    public AddRecursosDialog(JFrame parent) {
        super(parent, "Inserir Recurso", true);

        listModel = new DefaultListModel<>();
        recursoList = new JList<>(listModel);

        recursoList.setVisibleRowCount(10);
        JScrollPane scrollLista = new JScrollPane(recursoList);
        scrollLista.setPreferredSize(new Dimension(180, 0));
        scrollLista.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));

        JPanel camposPanel = new JPanel(new GridLayout(3, 2, 10, 20));
        camposPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        nomeField = new JTextField();
        idField = new JTextField();
        quantidadeField = new JTextField();

        Dimension campoSize = new Dimension(150, 25);
        nomeField.setPreferredSize(campoSize);
        idField.setPreferredSize(campoSize);
        quantidadeField.setPreferredSize(campoSize);

        camposPanel.add(new JLabel("Nome do Recurso:"));
        camposPanel.add(nomeField);
        camposPanel.add(new JLabel("Identificador do Recurso:"));
        camposPanel.add(idField);
        camposPanel.add(new JLabel("Quantidade de Instâncias:"));
        camposPanel.add(quantidadeField);

        JButton addRecursoButton = new JButton("Adicionar");
        JButton confirmButton = new JButton("Confirmar");

        addRecursoButton.setPreferredSize(new Dimension(140, 30));
        confirmButton.setPreferredSize(new Dimension(140, 30));

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoesPanel.add(addRecursoButton);
        botoesPanel.add(confirmButton);

        addRecursoButton.addActionListener(e -> {
            if (validarCampos()) {
                String nome = nomeField.getText().trim();
                int id = Integer.parseInt(idField.getText().trim());
                int quantidade = Integer.parseInt(quantidadeField.getText().trim());

                recursos.add(new Recursos(id, nome, quantidade));
                listModel.addElement("ID " + id + " - " + nome + " (" + quantidade + ")");

                nomeField.setText("");
                idField.setText("");
                quantidadeField.setText("");
                nomeField.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        confirmButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });

        setLayout(new BorderLayout(10, 10));
        add(camposPanel, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
        add(scrollLista, BorderLayout.EAST);

        setSize(600, 300);
        setLocationRelativeTo(parent);
    }

    private boolean validarCampos() {
        return !nomeField.getText().trim().isEmpty()
            && !idField.getText().trim().isEmpty()
            && quantidadeValida();
    }

    private boolean quantidadeValida() {
        try {
            Integer.valueOf(quantidadeField.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getNome() {
        return nomeField.getText().trim();
    }

    public int getIdentificador() {
        return Integer.parseInt(idField.getText().trim());
    }

    public int getQuantidade() {
        return Integer.parseInt(quantidadeField.getText().trim());
    }

    public ArrayList<Recursos> getRecursos() {
        return recursos;
    }
}
