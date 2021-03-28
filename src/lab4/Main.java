package lab4;

public class Main {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente("Vitor");
		Anuncio anuncio = new Anuncio("imagem", 0);
		Anuncio anuncio2 = new Anuncio("video", 1);
		Anuncio anuncio3 = new Anuncio("texto", 2);
		Compra compra = new Compra(anuncio, 1);
		Compra compra2 = new Compra(anuncio2, 2);
		Compra compra3 = new Compra(anuncio3, 3);
		
		cliente.addCompra(compra);
		cliente.addCompra(compra2);
		cliente.addCompra(compra3);
		
		System.out.println(cliente.imprimeHistorico());
		
		

	}

}
