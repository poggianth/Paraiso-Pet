package model;

public class ItemAnimal {
	private int codigo;
	private String nome;
	private ResponsavelAnimal responsavel;

	public ItemAnimal(int codigo, String nome, ResponsavelAnimal responsavel) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.responsavel = responsavel;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ResponsavelAnimal getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ResponsavelAnimal responsavel) {
		this.responsavel = responsavel;
	}

}
