package model;

public class ArvoreAnimal {
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

	public NoAnimal inserir(ItemAnimal elem, NoAnimal no) {
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
}
