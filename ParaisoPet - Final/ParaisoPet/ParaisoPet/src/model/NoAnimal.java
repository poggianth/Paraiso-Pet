package model;

public class NoAnimal {
	private ItemAnimal info;
	private NoAnimal esq, dir;

	public NoAnimal(ItemAnimal elem) {
		this.info = elem;
	}
	
	/*public NoAnimal(ItemAnimal info, NoAnimal esq, NoAnimal dir) {
		super();
		this.info = info;
		this.esq = esq;
		this.dir = dir;
	}*/

	public ItemAnimal getInfo() {
		return info;
	}

	public void setInfo(ItemAnimal info) {
		this.info = info;
	}

	public NoAnimal getEsq() {
		return esq;
	}

	public void setEsq(NoAnimal esq) {
		this.esq = esq;
	}

	public NoAnimal getDir() {
		return dir;
	}

	public void setDir(NoAnimal dir) {
		this.dir = dir;
	}

}
