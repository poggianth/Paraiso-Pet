package model;

public class ArvoreServico {
	private NoServico raiz;
	private int quantNos;

	public ArvoreServico() {
		this.quantNos = 0;
		this.raiz = null;
	}

	public boolean eVazia() {
		return (this.raiz == null);
	}

	public NoServico getRaiz() {
		return this.raiz;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	public boolean inserir(ItemServico elem) {
		if (pesquisar(elem.getCodigo())) {
			return false;
		} else {
			this.raiz = inserir(elem, this.raiz);
			this.quantNos++;
			return true;
		}
	}

	public NoServico inserir(ItemServico elem, NoServico no) {
		if (no == null) {
			NoServico novo = new NoServico(elem);
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

	private NoServico pesquisar(int chave, NoServico no) {
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

	public NoServico remover(int chave, NoServico arv) {
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

	private NoServico Arrumar(NoServico arv, NoServico maior) {
		if (maior.getDir() != null) {
			maior.setDir(Arrumar(arv, maior.getDir()));
		} else {
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}
		return maior;
	}

	// caminhamento central
	public ItemServico[] CamCentral() {
		int[] n = new int[1];
		n[0] = 0;
		ItemServico[] vet = new ItemServico[this.quantNos];
		return (FazCamCentral(this.raiz, vet, n));
	}

	private ItemServico[] FazCamCentral(NoServico arv, ItemServico[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamCentral(arv.getEsq(), vet, n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamCentral(arv.getDir(), vet, n);
		}
		return vet;
	}

	// caminhamento pré-fixado
	public ItemServico[] CamPreFixado() {
		int[] n = new int[1];
		n[0] = 0;
		ItemServico[] vet = new ItemServico[this.quantNos];
		return (FazCamPreFixado(this.raiz, vet, n));
	}

	private ItemServico[] FazCamPreFixado(NoServico arv, ItemServico[] vet, int[] n) {
		if (arv != null) {
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamPreFixado(arv.getEsq(), vet, n);
			vet = FazCamPreFixado(arv.getDir(), vet, n);
		}
		return vet;
	}

	// caminhamento pós-fixado
	public ItemServico[] CamPosFixado() {
		int[] n = new int[1];
		n[0] = 0;
		ItemServico[] vet = new ItemServico[this.quantNos];
		return (FazCamPosFixado(this.raiz, vet, n));
	}

	private ItemServico[] FazCamPosFixado(NoServico arv, ItemServico[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamPosFixado(arv.getEsq(), vet, n);
			vet = FazCamPosFixado(arv.getDir(), vet, n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
		}
		return vet;
	}

	public String toString() {
		ItemServico[] todosServicosCadastrados = this.CamPosFixado();
		String msg = "";
		for (int c = 0; c < todosServicosCadastrados.length; c++) {
			msg += todosServicosCadastrados[c].toString() + "\n";
		}
		return msg;
	}

	public ItemServico[] servicoPorAnimal(int idAnimal) {
		ItemServico[] allServCad = this.CamCentral();
		ItemServico[] allServPorAnm = new ItemServico[this.quantNos];
		
		
		int posicao = 0;
		for (ItemServico servico : allServCad) {
			if (servico.getIdAnimal() == idAnimal) {
				allServPorAnm[posicao] = servico;
				posicao++;
			}
		}
		
		if(allServPorAnm[0] != null) {
			return allServPorAnm;	
		} else {
			return null;
		}
		
	}

	public boolean alterar(int chave, ItemServico novoServico) {
		NoServico resposta = pesquisar(chave, this.raiz);

		if (resposta != null) {
			ItemServico servico = resposta.getInfo();
			servico.setDescricao(novoServico.getDescricao());
			servico.setPreco(novoServico.getPreco());
			return true;
		} else {
			return false;
		}
	}

	// Retorna somente 1 objeto
	public ItemServico retornaServico(int chave) {
		NoServico resposta = pesquisar(chave, this.raiz);

		if (resposta != null) {
			return resposta.getInfo();
		} else {
			return null;
		}
	}
}
