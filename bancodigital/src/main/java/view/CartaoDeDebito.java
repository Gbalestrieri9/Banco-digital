package view;

public class CartaoDeDebito {
	
	private double saldo = 0;
	private aberturaDeConta contaUser;
	
	public CartaoDeDebito(double saldo, aberturaDeConta contaUser) {
		this.saldo = saldo;
		this.contaUser = contaUser;
	}

	public double getSaldo() {
		return saldo;
	}

	public double setSaldo(double saldo) {
		return this.saldo = saldo;
	}

	public aberturaDeConta getContaUser() {
		return contaUser;
	}

	public void setContaUser(aberturaDeConta contaUser) {
		this.contaUser = contaUser;
	}
	
	public void retiradaSaldo(double valor) {
        this.saldo -= this.setSaldo(valor);
    }
	
	public void aumentarSaldo(double valor) {
        this.saldo += this.setSaldo(valor);
    }
}
