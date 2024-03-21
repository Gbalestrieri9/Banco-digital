package view;

public class ContaCorrente {
	
	private double saldo = 0;
	private boolean cartaoDebito;
	private Cliente cliente;
	private int limiteTransacoes = 5;
	
	public ContaCorrente(double saldo, boolean cartaoDebito,int limiteTransacoes) {
		this.saldo = saldo;
		this.cartaoDebito = cartaoDebito;
		this.limiteTransacoes = limiteTransacoes;
	}

	public double getSaldo() {
		return saldo;
	}

	public double setSaldo(double saldo) {
		return this.saldo = saldo;
	}
	
	public boolean isCartaoDebito() {
		return cartaoDebito;
	}

	public void setCartaoDebito(boolean cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
	}
	
	public void retiradaSaldo(double valor) {
        this.saldo -= this.setSaldo(valor);
    }
	
	public void aumentarSaldo(double valor) {
        this.saldo += this.setSaldo(valor);
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getLimiteTransacoes() {
		return limiteTransacoes;
	}

	public void setLimiteTransacoes(int limiteTransacoes) {
		this.limiteTransacoes = limiteTransacoes;
	}
}