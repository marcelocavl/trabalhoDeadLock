import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


// MATRIZ           O QUE REPRESENTA
// E (Existente)	Quantidade total de cada tipo de recurso no sistema.
// A (Disponível)	Quantidade de recursos ainda disponíveis (não alocados).
// C (Atribuição)	Quais recursos já estão alocados para cada processo.
// R (Requisição)	Quais recursos cada processo ainda está esperando/solicitando.


public class SistemaInterface extends JFrame {

    private final JTable tabelaProcessos;
    private final JTable tabelaRecursos;
    private final JTextArea areaLog;
    private final JLabel labelDeadlock;
    private final AddProcessoDialog addProcessoDialog = new AddProcessoDialog(this);
    int LOGINDEX = 0;

    public SistemaInterface() {
        setTitle("Detector de Deadlocks");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        DefaultTableModel modeloProcessos = new DefaultTableModel(
            new Object[]{"Processo", "∆T Solicitação", "∆T Utilização", "Status", "C", "R"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaProcessos = new JTable(modeloProcessos);

        tabs.add("Processos", new JScrollPane(tabelaProcessos));

        DefaultTableModel modeloRecursos = new DefaultTableModel(
            new Object[]{"Recurso", "A", "E"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaRecursos = new JTable(modeloRecursos);
        
        tabs.add("Recursos", new JScrollPane(tabelaRecursos));

        areaLog = new JTextArea(10, 80);
        areaLog.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(areaLog);
        tabs.add("Log", scrollLog);

        add(tabs, BorderLayout.CENTER);

        JButton addProcessoButton = new JButton("Adicionar Processo");

        addProcessoButton.addActionListener(e -> {
            openAddProcessoDialog();
        });

        labelDeadlock = new JLabel("Status: Sem deadlock");
        labelDeadlock.setForeground(Color.GREEN);
        labelDeadlock.setHorizontalAlignment(SwingConstants.CENTER);
        labelDeadlock.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addProcessoButton.setPreferredSize(new Dimension(200, 30));
        topPanel.add(addProcessoButton);

        add(labelDeadlock, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void addLog(String logText){
        areaLog.append("Log(" + LOGINDEX + "): " + logText + "\n");
        LOGINDEX++;
    }

    public void addProcessoRow(int id, float tempo_solicitacao, float tempo_utilizacao){
        DefaultTableModel model = (DefaultTableModel) tabelaProcessos.getModel();
        model.addRow(new Object[]{"P" + id, tempo_solicitacao, tempo_utilizacao, "Dormindo", null, null});
    }

    public void openAddProcessoDialog(){
        addProcessoDialog.setVisible(true);
    }

    public void addRecursoRow(String nome, int quantidade){
        DefaultTableModel model = (DefaultTableModel) tabelaRecursos.getModel();
        model.addRow(new Object[]{nome, quantidade, quantidade});
    }
}
