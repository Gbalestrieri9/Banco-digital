package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente {
	
	private String cpf;
	private String nome;
	private Date dataNascimento;
	private String endereco;
	private List<Conta> conta = new ArrayList<>();
	private List<ContaCorrente> contaCorrente = new ArrayList<>();
	private List<CartaoDeCredito> cartaoCredito = new ArrayList<>();
	
	public Cliente(String cpf, String nome, Date dataNascimento, String endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public List<Conta> getConta() {
		return conta;
	}

	public void setConta(List<Conta> conta) {
		this.conta = conta;
	}
	
	public List<ContaCorrente> getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(List<ContaCorrente> contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public List<CartaoDeCredito> getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(List<CartaoDeCredito> cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
}
