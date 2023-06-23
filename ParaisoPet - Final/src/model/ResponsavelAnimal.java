package model;

import java.util.Random;

public class ResponsavelAnimal {
	private int codResponsavel;
	private String nome, cpf, email, telefone;

	public ResponsavelAnimal() {
		this.codResponsavel = gerarNumero();
		
	}
	public ResponsavelAnimal(String nome, String cpf, String email, String telefone) {
		super();
		this.codResponsavel = gerarNumero();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	public int getCodResponsavel() {
		return codResponsavel;
	}

	public void setCodResponsavel(int codResponsavel) {
		this.codResponsavel = codResponsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "ResponsavelAnimal [codResponsavel=" + codResponsavel + ", nome=" + nome + ", cpf=" + cpf + ", email="
				+ email + ", telefone=" + telefone + "]";
	}
	
	static int gerarNumero() {
		Random aleatorio = new Random();

		return aleatorio.nextInt(100000);
	}

}
