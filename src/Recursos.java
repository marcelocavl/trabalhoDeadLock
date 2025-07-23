
public class Recursos {
    private final int id;
    private final String nome;
    private final int quantidade;

    public Recursos(int id, String nome, int quantidade){
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recursos{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome);
        sb.append(", quantidade=").append(quantidade);
        sb.append('}');
        return sb.toString();
    }

    
}
