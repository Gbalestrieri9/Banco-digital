package view;

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
			System.out.println("Login realizado com sucesso.");
		} else {
			System.out.println("Login falhou. Tente novamente.");
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
			visualizarSaldo(escolha);
		} else {
			System.out.println("Opção inválida.");
		}
	}

	public void visualizarSaldo(int indiceConta) {
		double saldo = clienteLogado.getContaCorrente().get(indiceConta).getSaldo();
		System.out.println("Saldo da conta selecionada: R$" + saldo);
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
		//fazer um metodo que vai escolher credito ou debito
		//deposito (fazer o contrario - retirar saldo) 
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
			sairSalvar();
			break;
		default:
			System.out.println("Opção inválida. Tente novamente.");
		}
	}
}