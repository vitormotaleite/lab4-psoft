package lab4;

import java.util.Enumeration;
import java.util.Vector;

public class Cliente {
	
private String nome;
	
	//vetor sem tipo
	private Vector<Compra> compras = new Vector<>();

	public Cliente(String nome) {
		this.nome = nome;
	}

	//arg: variavel nao define bem o que funcao faz
	public void addCompra(Compra novaCompra) {
		compras.addElement(novaCompra);
	}

	public String getNome() {
		return nome;
	}
	//funcao que imprime o cabecalho
	public String imprimirCabecalho() {
		return "Historico de compras de anuncios por " + getNome() + "\n";
	}
	//funcao que imprime o valor total de todas as compras e os pontos acumulados
	public String imprimirResultados(double total, int pontosFRequentes) {
		return "Total devido é " + String.valueOf(total) + "\n" +
	"Voce ganhou " + String.valueOf(pontosFRequentes) + " pontos";
	}
	//funcao que imprime a compra com seu valor individual
	public String imprimeValorCompra(Compra cada, double totalParcial) {
		return "\t" + cada.getAnuncio().getDescricao() + "\t" + String.valueOf(totalParcial) + "\n";
	}
	
	//funcao que percorre a lista e retorna o valor individual de cada compra na lista.
	public double percorreLista(double totalParcial, Compra cada) {
		switch (cada.getAnuncio().getCodigoPreco()) {
		case Anuncio.IMAGEM:
			totalParcial += 2;
			if (cada.getDiasAnuncio() > 2)
				break;
		
		case Anuncio.VIDEO:
			totalParcial += cada.getDiasAnuncio() * 3;
			break;
		
		case Anuncio.TEXTO:
			totalParcial += 1.5;
			
			if (cada.getDiasAnuncio() > 3)
				totalParcial += (cada.getDiasAnuncio() - 3) * 1.5;
			break;
		}
		return totalParcial;
	}
	
	//metodo longo
	//Separar metodo ou criar classe historico
	public String historico() {
		double total = 0;
		int pontosFRequentes = 0;
		Enumeration<Compra> comprasAnuncio = compras.elements();
		String resultado = this.imprimirCabecalho();
		
		while (comprasAnuncio.hasMoreElements()) {
			double totalParcial = 0;
			Compra cada = comprasAnuncio.nextElement();
			
			double valorParcialCompra = this.percorreLista(totalParcial, cada);
			
			pontosFRequentes++;
			if ((cada.getAnuncio().getCodigoPreco() == Anuncio.VIDEO) && cada.getDiasAnuncio() > 1) {
				pontosFRequentes++;
			}
			resultado += this.imprimeValorCompra(cada, valorParcialCompra);
			total += valorParcialCompra;
		}
		
		resultado += this.imprimirResultados(total, pontosFRequentes);
		return resultado;
	}
}
