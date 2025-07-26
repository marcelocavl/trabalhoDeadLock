import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// MATRIZ           O QUE REPRESENTA
// E (Existente)	Quantidade total de cada tipo de recurso no sistema.
// A (Disponível)	Quantidade de recursos ainda disponíveis (não alocados).
// C (Atribuição)	Quais recursos já estão alocados para cada processo.
// R (Requisição)	Quais recursos cada processo ainda está esperando/solicitando.


public class SistemaInterface extends JFrame {

    private final JTable tabelaProcessos;
    private final JTable tabelaRecursos;
		private final JTable tabelaMatriz;
    private final JTextArea areaLog;
    private final JLabel labelDeadlock;
    private final AddProcessoDialog addProcessoDialog = new AddProcessoDialog(this);
    private final DefaultTableModel modeloProcessos;
    private final DefaultTableModel modeloRecursos;
    private SistemaOperacional sistema;
    int LOGINDEX = 0;

    public SistemaInterface() {
        setTitle("Detector de Deadlocks");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        modeloProcessos = new DefaultTableModel(
            new Object[]{"Processo", "∆T Solicitação", "∆T Utilização", "Status", "C", "R"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
				tabelaMatriz=new JTable(modeloProcessos);

        tabs.add("Matrizes", new JScrollPane(tabelaMatriz));

        tabelaProcessos = new JTable(modeloProcessos);

        tabs.add("Processos", new JScrollPane(tabelaProcessos));

        modeloRecursos = new DefaultTableModel(
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

    public void iniciarAtualizacaoAutomatica() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sistema != null) {
                    sistema.atualizarInterface();
                }
            }
        });
        timer.start();
    }

    public void setSistema(SistemaOperacional sistema) {
    this.sistema = sistema;
}
    public void addLog(String logText){
        areaLog.append("Log(" + LOGINDEX + "): " + logText + "\n");
        LOGINDEX++;
    }

    public void atualizarRecursos(List<Recursos> recursos) {
    modeloRecursos.setRowCount(0); 
    for (Recursos r : recursos) {
        modeloRecursos.addRow(new Object[]{
            r.getNome(),
            r.getTotal(),
            r.getDisponivel()
        });
    }
}

    public void atualizarProcessos(List<Processos> processos) {
        modeloProcessos.setRowCount(0); 
        for (Processos p : processos) {
            modeloProcessos.addRow(new Object[]{
                "P" + p.get_processo_id(),
                p.get_tempo_solicitacao(),
                p.get_tempo_utilizacao(),
                p.getStatus(),
                "-", 
                "-"  
            });
        }
    }

    public void setDeadlockStatus(boolean emDeadlock, List<Integer> idsEmDeadlock) {
        if (emDeadlock) {
            labelDeadlock.setText("DEADLOCK DETECTADO: Processos " + idsEmDeadlock);
            labelDeadlock.setForeground(Color.RED);
        } else {
            labelDeadlock.setText("Status: Sem deadlock");
            labelDeadlock.setForeground(Color.GREEN);
        }
    }


    public void addProcessoRow(int id, float tempo_solicitacao, float tempo_utilizacao){
        DefaultTableModel model = (DefaultTableModel) tabelaProcessos.getModel();
        model.addRow(new Object[]{"P" + id, tempo_solicitacao, tempo_utilizacao, "Dormindo", null, null});
}

    public void openAddProcessoDialog() {
    addProcessoDialog.setVisible(true);
        if (addProcessoDialog.isConfirmed()) {
            int id = addProcessoDialog.getId();
            float ts = addProcessoDialog.getTempoSolicitacao();
            float tu = addProcessoDialog.getTempoUtilizacao();

            addProcessoRow(id, ts, tu);

            Processos novo = new Processos(id, ts, tu, sistema);
            sistema.add_processos(novo); 
            novo.start(); 

            addLog("Processo P" + id + " criado e iniciado.");
        }
}

    public void addRecursoRow(String nome, int quantidade){
        DefaultTableModel model = (DefaultTableModel) tabelaRecursos.getModel();
        model.addRow(new Object[]{nome, quantidade, quantidade});
    }
}
