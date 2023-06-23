package model;

import java.util.Scanner;

public class ArvoreAnimal {
	Scanner input = new Scanner(System.in);

	private NoAnimal raiz;
	private int quantNos;

	public ArvoreAnimal() {
		this.quantNos = 0;
		this.raiz = null;
	}

	public boolean eVazia() {
		return (this.raiz == null);
	}

	public NoAnimal getRaiz() {
		return this.raiz;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	public boolean inserir(ItemAnimal elem) {
		if (pesquisar(elem.getCodigo())) {
			return false;
		} else {
			this.raiz = inserir(elem, this.raiz);
			this.quantNos++;
			return true;
		}
	}

	private NoAnimal inserir(ItemAnimal elem, NoAnimal no) {
		if (no == null) {
			NoAnimal novo = new NoAnimal(elem);
			return novo;
		} else {
			if (elem.getCodigo() < no.getInfo().getCodigo()) {
				no.setEsq(inserir(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(inserir(elem, no.getDir()));
				return no;
			}
		}
	}

	// Pesquisa se um determinado valor está na árvore
	public boolean pesquisar(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			return true;
		} else {
			return false;
		}
	}

	// Pesquisa se um determinado valor está na árvore
	public boolean alterar(int chave, ItemAnimal novoAnimal) {
		NoAnimal resposta = pesquisar(chave, this.raiz);

		if (resposta != null) {
			ItemAnimal animal = resposta.getInfo();
			animal.setNome(novoAnimal.getNome());
			animal.setEspecie(novoAnimal.getEspecie());
			animal.setRaca(novoAnimal.getRaca());
			animal.setSexo(novoAnimal.getSexo());
			animal.setDtEntrada(novoAnimal.getDtEntrada());
			animal.setDtSaida(novoAnimal.getDtSaida());
			animal.setResponsavel(novoAnimal.getResponsavel());

			return true;
		} else {
			return false;
		}
	}

	private NoAnimal pesquisar(int chave, NoAnimal no) {
		if (no != null) {
			if (chave < no.getInfo().getCodigo()) {
				no = pesquisar(chave, no.getEsq());
			} else {
				if (chave > no.getInfo().getCodigo()) {
					no = pesquisar(chave, no.getDir());
				}
			}
		}
		return no;
	}

	// remove um determinado nó procurando pela chave. O nó pode estar em qualquer
	// posição na árvore
	public boolean remover(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			this.raiz = remover(chave, this.raiz);
			this.quantNos--;
			return true;
		} else {
			return false;
		}
	}

	public NoAnimal remover(int chave, NoAnimal arv) {
		if (chave < arv.getInfo().getCodigo()) {
			arv.setEsq(remover(chave, arv.getEsq()));
		} else {
			if (chave > arv.getInfo().getCodigo()) {
				arv.setDir(remover(chave, arv.getDir()));
			} else {
				if (arv.getDir() == null) {
					return arv.getEsq();
				} else {
					if (arv.getEsq() == null) {
						return arv.getDir();
					} else {
						arv.setEsq(Arrumar(arv, arv.getEsq()));
					}
				}
			}
		}
		return arv;
	}

	private NoAnimal Arrumar(NoAnimal arv, NoAnimal maior) {
		if (maior.getDir() != null) {
			maior.setDir(Arrumar(arv, maior.getDir()));
		} else {
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}
		return maior;
	}

	// caminhamento central
	public ItemAnimal[] CamCentral() {
		int[] n = new int[1];
		n[0] = 0;
		ItemAnimal[] vet = new ItemAnimal[this.quantNos];
		return (FazCamCentral(this.raiz, vet, n));
	}

	private ItemAnimal[] FazCamCentral(NoAnimal arv, ItemAnimal[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamCentral(arv.getEsq(), vet, n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamCentral(arv.getDir(), vet, n);
		}
		return vet;
	}

	// caminhamento pré-fixado
	public ItemAnimal[] CamPreFixado() {
		int[] n = new int[1];
		n[0] = 0;
		ItemAnimal[] vet = new ItemAnimal[this.quantNos];
		return (FazCamPreFixado(this.raiz, vet, n));
	}

	private ItemAnimal[] FazCamPreFixado(NoAnimal arv, ItemAnimal[] vet, int[] n) {
		if (arv != null) {
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamPreFixado(arv.getEsq(), vet, n);
			vet = FazCamPreFixado(arv.getDir(), vet, n);
		}
		return vet;
	}

	// caminhamento pós-fixado
	public ItemAnimal[] CamPosFixado() {
		int[] n = new int[1];
		n[0] = 0;
		ItemAnimal[] vet = new ItemAnimal[this.quantNos];
		return (FazCamPosFixado(this.raiz, vet, n));
	}

	private ItemAnimal[] FazCamPosFixado(NoAnimal arv, ItemAnimal[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamPosFixado(arv.getEsq(), vet, n);
			vet = FazCamPosFixado(arv.getDir(), vet, n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
		}
		return vet;
	}

	public String toString() {
		ItemAnimal[] todosAnimaisCadastrados = this.CamPosFixado();
		String msg = "";
		for (int c = 0; c < todosAnimaisCadastrados.length; c++) {
			msg += todosAnimaisCadastrados[c].toString() + "\n";
		}
		return msg;
	}

	public ItemAnimal cadastrarDados() {
		try {
			ResponsavelAnimal responsavel = new ResponsavelAnimal();
			ItemAnimal animal = new ItemAnimal();

			System.out.print("Informe o NOME do responsável pelo animal: ");
			responsavel.setNome(input.next());

			System.out.print("Informe o CPF do responsável pelo animal: ");
			responsavel.setCpf(input.next());

			System.out.print("Informe o TELEFONE do responsável pelo animal: ");
			responsavel.setTelefone(input.next());

			System.out.print("Informe o E-MAIL do responsável pelo animal: ");
			responsavel.setEmail(input.next());

			System.out.println("\nAgora vamos inserir os dados do animal!");

			System.out.print("Informe o NOME do animal: ");
			animal.setNome(input.next());

			System.out.print("Informe a ESPÉCIE de " + animal.getNome() + ": ");
			animal.setEspecie(input.next().toUpperCase());

			System.out.print("Informe a RAÇA de " + animal.getNome() + ": ");
			animal.setRaca(input.next());

			System.out.print("Informe o SEXO de " + animal.getNome() + ": ");
			animal.setSexo(input.next().toUpperCase());

			System.out.print("Informe a DATA que " + animal.getNome() + " entrou: ");
			animal.setDtEntrada(input.next());

			// Relacionando responsável
			animal.setResponsavel(responsavel);

			return animal;
		} catch (Exception e) {
			System.err.println("\n[OPS] - Erro ao cadastrar animal: " + e);
			return null;
		}

	}

	// Retorna somente 1 objeto
	public ItemAnimal retornaAnimal(int chave) {
		NoAnimal resposta = pesquisar(chave, this.raiz);

		if (resposta != null) {
			return resposta.getInfo();
		} else {
			return null;
		}
	}
}
