package model;

import java.util.Random;

public class ItemAnimal {
	private int codigo;
	private String nome, especie, raca, sexo;
	private String dtEntrada, dtSaida;
	private ResponsavelAnimal responsavel;

	public ItemAnimal() {
		this.codigo = gerarNumero();
	}
	public ItemAnimal(String nome, String especie, String raca, String sexo, String dtEntrada,
			String dtSaida, ResponsavelAnimal responsavel) {
		super();
		this.codigo = gerarNumero();
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
		this.sexo = sexo;
		this.dtEntrada = dtEntrada;
		this.dtSaida = dtSaida;
		this.responsavel = responsavel;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEspecie() {
		return especie;
	}


	public void setEspecie(String especie) {
		this.especie = especie;
	}


	public String getRaca() {
		return raca;
	}


	public void setRaca(String raca) {
		this.raca = raca;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getDtEntrada() {
		return dtEntrada;
	}


	public void setDtEntrada(String dtEntrada) {
		this.dtEntrada = dtEntrada;
	}


	public String getDtSaida() {
		return dtSaida;
	}


	public void setDtSaida(String dtSaida) {
		this.dtSaida = dtSaida;
	}


	public ResponsavelAnimal getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(ResponsavelAnimal responsavel) {
		this.responsavel = responsavel;
	}


	@Override
	public String toString() {
		return "\n__________________________" +
				"\ncodigo = " + codigo + "\n"
				+ "nome = " + nome + "\n" 
				+ "especie = " + especie + "\n"
				+ "raca = " + raca + "\n"
				+ "sexo = " + sexo + "\n"
				+ "dtEntrada = " + dtEntrada + "\n"
				+ "dtSaida = " + dtSaida + "\n"
				+ "responsavel = " + responsavel.getNome();
	}
	
	static int gerarNumero() {
		Random aleatorio = new Random();

		return aleatorio.nextInt(100000);
	}

}
