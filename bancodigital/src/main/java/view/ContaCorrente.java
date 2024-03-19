package view;

public class ContaCorrente {
	
	private double saldo = 0;
	private boolean cartaoDebito;
	private Cliente cliente;
	
	public ContaCorrente(double saldo, boolean cartaoDebito) {
		this.saldo = saldo;
		this.cartaoDebito = cartaoDebito;
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
}