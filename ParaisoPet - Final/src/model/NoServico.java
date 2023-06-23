package model;

public class NoServico {
	private ItemServico info;
	private NoServico esq, dir;
	
	public NoServico(ItemServico elem) {
		this.info = elem;
	}

	public ItemServico getInfo() {
		return info;
	}

	public void setInfo(ItemServico info) {
		this.info = info;
	}

	public NoServico getEsq() {
		return esq;
	}

	public void setEsq(NoServico esq) {
		this.esq = esq;
	}

	public NoServico getDir() {
		return dir;
	}

	public void setDir(NoServico dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "NoServico [info=" + info + ", esq=" + esq + ", dir=" + dir + "]";
	}
	
	
}
