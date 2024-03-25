package view;

import java.util.Date;
import java.util.Random;

public class ProdutosSeguro {

	private int seguroViagem;
	private int seguroFraude;
	
	private static final int NUM_DIGITOS = 8; 
    private String numeroApoliceSeguroViagem;
    private String numeroApoliceSeguroFraude;
    private Date dataContratacao;
	
	public ProdutosSeguro(String segmentoConta,boolean desejaApolice) {
		if (desejaApolice) {
			this.seguroViagem = definirTaxaSeguroViagem(segmentoConta);
		}
		this.seguroFraude = 5000;
		this.numeroApoliceSeguroViagem = gerarNumeroApolice(); 
		this.numeroApoliceSeguroFraude = gerarNumeroApolice(); 
        this.dataContratacao = new Date();
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

	public String getNumeroApoliceSeguroViagem() {
		return numeroApoliceSeguroViagem;
	}

	public void setNumeroApoliceSeguroViagem(String numeroApoliceSeguroViagem) {
		this.numeroApoliceSeguroViagem = numeroApoliceSeguroViagem;
	}

	public String getNumeroApoliceSeguroFraude() {
		return numeroApoliceSeguroFraude;
	}

	public void setNumeroApoliceSeguroFraude(String numeroApoliceSeguroFraude) {
		this.numeroApoliceSeguroFraude = numeroApoliceSeguroFraude;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public static int getNumDigitos() {
		return NUM_DIGITOS;
	}
	
	public static String gerarNumeroApolice() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        // Gera os dígitos aleatórios
        for (int i = 0; i < NUM_DIGITOS; i++) {
            int digito = rand.nextInt(10); // Gera um número aleatório entre 0 e 9
            sb.append(digito);
        }

        return sb.toString();
    }
}
