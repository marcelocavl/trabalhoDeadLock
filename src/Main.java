//IMPORTAÇÕES

//CLASSE MAIN
public class Main{
	public static void main(String args[]){	
		SistemaOperacional so = new SistemaOperacional();

		Recursos recurso1 = new Recursos(1, "impressora", 1);
		Recursos recurso2 = new Recursos(2, "cpu", 1);

		System.out.println(so.get_recursos());

		System.out.println(so.add_recursos(recurso1));
		System.out.println(so.add_recursos(recurso2));

		System.out.println(so.get_lista_recursos());
	}	
}
