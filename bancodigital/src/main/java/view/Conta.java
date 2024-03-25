package view;

import java.util.Date;

public class Conta {
    
    private String tipoConta;
    private String senha;
    private String categoriaConta;
    private Cliente cliente;
    private ContaCorrente contaCorrente;
    private static Date dataDeAbertura;
    private boolean contaAtivada;

    public Conta(String tipoConta, String senha, String categoriaConta) {
        this.tipoConta = tipoConta;
        this.senha = senha;
        this.categoriaConta = categoriaConta;
        this.dataDeAbertura = new Date();
        this.contaAtivada = true;
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
    
    public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	
	public static Date getDataDeAbertura() {
		return dataDeAbertura;
	}

	public static void setDataDeAbertura(Date dataDeAbertura) {
		Conta.dataDeAbertura = dataDeAbertura;
	}

	public boolean isContaAtivada() {
		return contaAtivada;
	}

	public void setContaAtivada(boolean contaAtivada) {
		this.contaAtivada = contaAtivada;
	}

	public void aplicarTaxaOuRendimentoDiario() {
		double saldo = contaCorrente.getSaldo();
        if ("corrente".equals(tipoConta)) {
            switch (categoriaConta) {
                case "Comum":
                    contaCorrente.setSaldo(saldo -= 12.0 / 30);
                    break;
                case "Super":
                	contaCorrente.setSaldo(saldo -= 8.0 / 30);
                    break;
                case "Premium":
                    break;
                default:
                    System.out.println("Categoria de conta corrente não reconhecida.");
                    break;
            }
        } else if ("poupança".equals(tipoConta)) {
            switch (categoriaConta) {
                case "Comum":
                	contaCorrente.setSaldo(saldo * 0.005 / 30);
                    break;
                case "Super":
                	contaCorrente.setSaldo(saldo * 0.007 / 30);
                    break;
                case "Premium":
                	contaCorrente.setSaldo(saldo * 0.009 / 30);
                    break;
                default:
                    System.out.println("Categoria de conta poupança não reconhecida.");
                    break;
            }
        } else {
            System.out.println("Tipo de conta não reconhecido.");
        }
    }
	
	public void ativarOuDesativarConta(boolean ativarDesativar) {
		setContaAtivada(ativarDesativar);
	}
}
