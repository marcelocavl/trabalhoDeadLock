
import java.util.concurrent.Semaphore;

public class Recursos {
    private final int id;
    private final String nome;
    private final int total;
    private Semaphore disponivel;

    public Recursos(int id, String nome, int quantidade){
        this.id = id;
        this.nome = nome;
        this.total = quantidade;
        this.disponivel=new Semaphore(this.total);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    public int getTotal() {
        return total;
    }

    public Semaphore getDisponivel() {
        return disponivel;
    }
/*
    public void alocar() {
        if (disponivel.availablePermits() > 0) {
            disponivel--;
        }
    }
    public void liberar() {
        if (disponivel < total) {
            disponivel++;
        }
    }

*/
    @Override
    public String toString() {
        return "Recursos{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", total=" + total +
               ", disponivel=" + disponivel +
               '}';
    }

    
}
