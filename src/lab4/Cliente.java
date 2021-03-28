package lab4;

import java.util.Enumeration;
import java.util.Vector;

public class Cliente {
	
private String nome;
	
	private Vector<Compra> compras = new Vector<>();

	public Cliente(String nome) {
		this.nome = nome;
	}

	public void addCompra(Compra novaCompra) {
		compras.addElement(novaCompra);
	}

	public String getNome() {
		return nome;
	}
	
	public String imprimirCabecalho() {
		return "Historico de compras de anuncios por " + getNome() + "\n";
	}
	
	public String imprimirResultados(double total, int pontosFRequentes) {
		return "Total devido é " + String.valueOf(total) + "\n" +
	"Voce ganhou " + String.valueOf(pontosFRequentes) + " pontos";
	}
	
	public String imprimeValorCompra(Compra compra, double totalParcial) {
		return "\t" + compra.getAnuncio().getDescricao() + "\t" + String.valueOf(totalParcial) + "\n";
	}
	
	public int adicionaPontosFrequentRentals(int pontos, Compra compra) {
		pontos++;
		if ((compra.getAnuncio().getCodigoPreco() == Anuncio.VIDEO) && compra.getDiasAnuncio() > 1) {
			pontos++;
		}
		return pontos;
	}
	
	public double calculaValorIndividualCompra(double totalParcial, Compra compra) {
		switch (compra.getAnuncio().getCodigoPreco()) {
		case Anuncio.IMAGEM:
			totalParcial += 2;
			if (compra.getDiasAnuncio() > 2)
				break;
		
		case Anuncio.VIDEO:
			totalParcial += compra.getDiasAnuncio() * 3;
			break;
		
		case Anuncio.TEXTO:
			totalParcial += 1.5;
			
			if (compra.getDiasAnuncio() > 3)
				totalParcial += (compra.getDiasAnuncio() - 3) * 1.5;
			break;
		}
		return totalParcial;
	}
	
	public String imprimeHistorico() {
		double total = 0;
		int pontosFrequentRentals = 0;
		Enumeration<Compra> comprasAnuncio = compras.elements();
		String resultado = this.imprimirCabecalho();
		
		while (comprasAnuncio.hasMoreElements()) {
			double totalParcial = 0;
			Compra compra = comprasAnuncio.nextElement();
			
			double valorParcialCompra = this.calculaValorIndividualCompra(totalParcial, compra);
			
			pontosFrequentRentals = this.adicionaPontosFrequentRentals(pontosFrequentRentals, compra);
			
			resultado += this.imprimeValorCompra(compra, valorParcialCompra);
			total += valorParcialCompra;
		}
		
		resultado += this.imprimirResultados(total, pontosFrequentRentals);
		return resultado;
	}
}
