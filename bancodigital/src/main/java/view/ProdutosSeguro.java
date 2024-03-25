package view;

public class ProdutosSeguro {

	private String apolice;
	private int seguroViagem;
	private int seguroFraude;
	
	public ProdutosSeguro(String segmentoConta) {
		this.apolice = "apolice1";
		this.seguroViagem = definirTaxaSeguroViagem(segmentoConta);
		this.seguroFraude = 5000;
	}

	public String getApolice() {
		return apolice;
	}

	public void setApolice(String apolice) {
		this.apolice = apolice;
	}

	public int getSeguroViagem() {
		return seguroViagem;
	}

	public void setSeguroViagem(int seguroViagem) {
		this.seguroViagem = seguroViagem;
	}

	public int getSeguroFraude() {
		return seguroFraude;
	}

	public void setSeguroFraude(int seguroFraude) {
		this.seguroFraude = seguroFraude;
	}
	
	public int definirTaxaSeguroViagem(String segmento) {
		int valorTaxa = 50;
		if(segmento.equals("premium")) {
			valorTaxa = 0;
		}
		return valorTaxa;
	}
}
