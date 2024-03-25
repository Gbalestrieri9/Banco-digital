package view;

import java.text.DecimalFormat;
import java.util.Scanner;

public class menuDeControle {
	private AberturaDeConta aberturaDeConta;
	private Scanner input = new Scanner(System.in);
	public Cliente clienteLogado;

	public menuDeControle() {
		this.aberturaDeConta = new AberturaDeConta();
	}

	public void realizarLogin() {
		System.out.println("Digite seu CPF:");
		String cpf = input.next();

		System.out.println("Senha:");
		String senha = input.next();

		Cliente clienteLogin = aberturaDeConta.fazerLogin(cpf, senha);
		if (clienteLogin != null) {
			clienteLogado = clienteLogin;
			
			vereficarContaAtiva();
		} else {
			System.out.println("Credenciais invalidas. Tente novamente.");
		}
	}

	private void vereficarContaAtiva() {
		boolean situacaoConta = clienteLogado.getConta().get(0).isContaAtivada();
		
		if(situacaoConta) {
			visualizarSaldo();
			visualizarFaturaCartaoCredito();
		}else {
			System.out.println("Aviso: Esta conta está atualmente desativada. Entre em contato com o suporte para obter assistência.");
			clienteLogado = null;
		}
		
	}

	public void encontrarPosicaoPessoaPorCPF() {
		if (clienteLogado != null) {
			for (int i = 0; i < aberturaDeConta.clientes.size(); i++) {
				if (aberturaDeConta.clientes.get(i).getCpf().equals(clienteLogado.getCpf())) {
					aberturaDeConta.clientes.get(i).getContaCorrente().get(0)
							.setSaldo(clienteLogado.getContaCorrente().get(0).getSaldo());

					aberturaDeConta.clientes.get(i).getConta().get(0)
							.setSenha(clienteLogado.getConta().get(0).getSenha());

					aberturaDeConta.clientes.get(i).getContaCorrente().get(0)
							.setLimiteTransacoes(clienteLogado.getContaCorrente().get(0).getLimiteTransacoes());
				}
			}
		}
	}

	public void selecionarConta() {
		System.out.println("Selecione uma conta:");
		for (int i = 0; i < clienteLogado.getContaCorrente().size(); i++) {
			System.out.println(i + 1 + ": Conta com Saldo R$" + clienteLogado.getContaCorrente().get(i).getSaldo());
		}
		int escolha = input.nextInt();
		escolha = escolha - 1;

		if (escolha >= 0 && escolha < clienteLogado.getContaCorrente().size()) {
			visualizarSaldo();
		} else {
			System.out.println("Opção inválida.");
		}
	}

	public void visualizarSaldo() {
		double saldo = clienteLogado.getContaCorrente().get(0).getSaldo();
		System.out.println("Saldo da conta: R$" + saldo);
	}
	
	public void visualizarFaturaCartaoCredito() {
	    double fatura = clienteLogado.getCartaoCredito().get(0).getFatura();
	    System.out.println("Fatura do cartão de crédito:");
	    System.out.println("Valor total: R$" + fatura);
	}

	public void depositar() {
		System.out.println("Quanto você deseja depositar:");
		double depositoCliente = input.nextDouble();
		clienteLogado.getContaCorrente().get(0).aumentarSaldo(depositoCliente);
		double saldoAtual = clienteLogado.getContaCorrente().get(0).getSaldo();
		System.out.println("Saldo atual: R$" + saldoAtual);
	}

	public void transferenciaPix() {
		System.out.println("Quanto você deseja transferir:");
		double valorTransferencia = input.nextDouble();
		System.out.println("Informe o CPF do destinatário:");
		String cpfDestinatario = input.next();

		ContaCorrente contaDestinatario = aberturaDeConta.buscarContaPorCpf(cpfDestinatario);

		if (contaDestinatario != null) {
			boolean transferenciaRealizada = clienteLogado.getContaCorrente().get(0)
					.TransferirViaPix(valorTransferencia, contaDestinatario);
			if (transferenciaRealizada) {
				System.out.println("Transferência realizada com sucesso.");
			} else {
				System.out.println("Não foi possível realizar a transferência. Verifique o saldo e tente novamente.");
			}
		} else {
			System.out.println("CPF do destinatário não encontrado.");
		}
	}

	public void alterarSenha() {
		System.out.println("Digite a nova senha:");
		String novaSenha = input.next();

		clienteLogado.getConta().get(0).setSenha(novaSenha);

		System.out.println("Senha alterada com sucesso!");
	}

	public void alterarLimiteTransacao() {
		System.out.println(
				"Seu limite de transação atual é: " + clienteLogado.getContaCorrente().get(0).getLimiteTransacoes());
		System.out.println("Digite o limite de transações que você deseja realizar durante o dia:");
		int limiteTransacao = input.nextInt();

		clienteLogado.getContaCorrente().get(0).setLimiteTransacoes(limiteTransacao);

		System.out.println("Limite de transações alterado com sucesso!");
	}

	public void EstadoConta(Conta conta) {
		boolean estadoAtual = conta.isContaAtivada();
		conta.ativarOuDesativarConta(!estadoAtual);

		if (!estadoAtual) {
			System.out.println("A conta foi ativada.");
		} else {
			System.out.println("A conta foi desativada.");
		}
	}

	public void realizarPagamento() {
		System.out.println("Escolha o método de pagamento:");
		System.out.println("1: Débito");
		System.out.println("2: Crédito");
		int metodoPagamento = input.nextInt();

		if (metodoPagamento != 1 && metodoPagamento != 2) {
			System.out.println("Escolha inválida.");
			return;
		}

		System.out.println("Digite o valor da compra:");
		double compraCliente = input.nextDouble();

		if (metodoPagamento == 1) {
			if (!clienteLogado.getContaCorrente().get(0).isCartaoDebito()) {
				System.out.println("Você não possui um cartão de débito vinculado.");
				return;
			}
			if (clienteLogado.getContaCorrente().get(0).getSaldo() < compraCliente) {
				System.out.println("Saldo insuficiente para a compra.");
				return;
			}
			clienteLogado.getContaCorrente().get(0).retiradaSaldo(compraCliente);
		} else {
			System.out.println("Pagamento por crédito selecionado.");

			double limiteDoCliente = clienteLogado.getCartaoCredito().get(0).getLimiteCartaoCredito();
			clienteLogado.getCartaoCredito().get(0).setLimiteCartaoCredito(limiteDoCliente - compraCliente);

			double faturaDoCliente = clienteLogado.getCartaoCredito().get(0).getFatura();
			clienteLogado.getCartaoCredito().get(0).setFatura(compraCliente + faturaDoCliente);

			taxaDeUtilizacaoDeLimite();
		}

		if (metodoPagamento == 1) {
			double saldoAtual = clienteLogado.getContaCorrente().get(0).getSaldo();
			System.out.println("Pagamento realizado. Saldo atual: R$" + saldoAtual);
		} else {
			System.out.println("Pagamento por crédito realizado com sucesso.");
		}
	}

	public void taxaDeUtilizacaoDeLimite() {
		double taxa = clienteLogado.getCartaoCredito().get(0).calcularTaxaDeUtilizacao();
		DecimalFormat df = new DecimalFormat("#.##");
		String taxaFormatada = df.format(taxa);

		if (taxa > 0) {
			System.out.println("Taxa de utilização do limite aplicada: R$" + taxaFormatada);
		} else {
			System.out.println("Você não excedeu 80% do limite de crédito neste mês. Nenhuma taxa aplicada.");
		}
	}

	public void sairSalvar() {
		encontrarPosicaoPessoaPorCPF();
		aberturaDeConta.salvarClientes();
	}

	public void controle(int acaoSelecionada) {
		switch (acaoSelecionada) {
		case 1:
			aberturaDeConta.cadastrarClienteEConta();
			break;
		case 2:
			realizarLogin();
			break;
		case 3:
			selecionarConta();
			break;
		case 4:
			depositar();
			break;
		case 5:
			transferenciaPix();
			break;
		case 6:
			alterarSenha();
			break;
		case 7:
			alterarLimiteTransacao();
			break;
		case 8:
			EstadoConta(clienteLogado.getConta().get(0));
			break;
		case 9:
			realizarPagamento();
			break;
		case 10:
			sairSalvar();
			break;
		default:
			System.out.println("Opção inválida. Tente novamente.");
		}
	}
}