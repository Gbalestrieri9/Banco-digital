package view;

public class CartaoDeCredito {
	
	private double limiteCartaoCredito;
	private boolean clienteTemCartaoCredito;
	private Cliente cliente;
	
	public CartaoDeCredito(double limiteCartaoCredito, boolean clienteTemCartaoCredito) {
		this.limiteCartaoCredito = limiteCartaoCredito;
		this.clienteTemCartaoCredito = clienteTemCartaoCredito;
		if(!this.clienteTemCartaoCredito) {
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
}
