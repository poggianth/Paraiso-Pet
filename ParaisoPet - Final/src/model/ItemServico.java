package model;

import java.util.Random;

public class ItemServico {
	private int codigo;
	private String descricao;
	private int idAnimal;
	private double preco;

	public ItemServico() {
		this.codigo = gerarNumero();
	}
	public ItemServico(String descricao, int idAnimal, double preco) {
		super();
		this.codigo = gerarNumero();
		this.descricao = descricao;
		this.idAnimal = idAnimal;
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "\n__________________________" +
				"\ncodigo = " + codigo + "\n"
				+ "Descrição: = " + descricao + "\n" 
				+ "Preço = " + preco + "\n"
				+ "IdAnimal = " + idAnimal;
	}

	static int gerarNumero() {
		Random aleatorio = new Random();

		return aleatorio.nextInt(100000);
	}

}
