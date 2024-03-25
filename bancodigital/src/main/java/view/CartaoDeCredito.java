package view;

public class CartaoDeCredito {

	private double limiteCartaoCredito;
	private boolean clienteTemCartaoCredito;
	private Cliente cliente;
	private double fatura;
	private double limiteContratado;

	public CartaoDeCredito(double limiteCartaoCredito, boolean clienteTemCartaoCredito) {
		this.limiteCartaoCredito = limiteCartaoCredito;
		this.limiteContratado = limiteCartaoCredito;
		this.clienteTemCartaoCredito = clienteTemCartaoCredito;
		this.fatura = 0;
		if (!this.clienteTemCartaoCredito) {
			clienteNaoOptouPorCartaoCredito();
		}
	}

	public double getLimiteCartaoCredito() {
		return limiteCartaoCredito;
	}

	public void setLimiteCartaoCredito(double limiteCartaoCredito) {
		this.limiteCartaoCredito = limiteCartaoCredito;
	}

	public boolean isClienteTemCartaoCredito() {
		return clienteTemCartaoCredito;
	}

	public void setClienteTemCartaoCredito(boolean clienteTemCartaoCredito) {
		this.clienteTemCartaoCredito = clienteTemCartaoCredito;
	}

	public void clienteNaoOptouPorCartaoCredito() {
		this.limiteCartaoCredito = 0;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getFatura() {
		return fatura;
	}

	public void setFatura(double fatura) {
		this.fatura = fatura;
	}

	public double getLimiteContratado() {
		return limiteContratado;
	}

	public void setLimiteContratado(double limiteContratado) {
		this.limiteContratado = limiteContratado;
	}

	public double calcularTaxaDeUtilizacao() {
		double percentUtilizado = (fatura / limiteContratado) * 100;

		if (percentUtilizado >= 80) {
			return 0.05 * fatura;
		} else {
			return 0;
		}
	}
}
