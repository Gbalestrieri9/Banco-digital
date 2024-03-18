package view;

public class Conta {
	
	private String tipoConta; // correte/poupança
	private String senha;
	private String categoriaConta;
	private Cliente cliente;
	
	public Conta(String tipoConta, String senha, String categoriaConta) {
		this.tipoConta = tipoConta;
		this.senha = senha;
		this.categoriaConta = categoriaConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCategoriaConta() {
		return categoriaConta;
	}

	public void setCategoriaConta(String categoriaConta) {
		this.categoriaConta = categoriaConta;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
