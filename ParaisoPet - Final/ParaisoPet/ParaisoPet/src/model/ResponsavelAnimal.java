package model;

public class ResponsavelAnimal {
	private int codResponsavel;
	private String nome, cpf, email;

	public ResponsavelAnimal(int codResponsavel, String nome, String cpf, String email) {
		super();
		this.codResponsavel = codResponsavel;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
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

}
