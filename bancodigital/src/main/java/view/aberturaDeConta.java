package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class aberturaDeConta {
    private Scanner input = new Scanner(System.in);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String arquivoClientes = "clientes.json";
    private List<Cliente> clientes;

    public aberturaDeConta() {
        clientes = carregarClientes();
    }

    public void cadastrarClienteEConta() {
        System.out.println("Cadastro de Cliente e Conta");
        System.out.println("Coloque seu CPF:");
        String cpf = input.next();
        
        System.out.println("Coloque seu nome:");
        String nome = input.next();
        
        input.nextLine();
        
        System.out.println("Coloque sua data de nascimento (dd/MM/yyyy):");
        String data = input.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento;
        try {
            dataNascimento = formato.parse(data);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Cadastro não realizado.");
            return;
        }

        System.out.println("Coloque seu endereço:");
        String endereco = input.nextLine();

        Cliente novoCliente = new Cliente(cpf, nome, dataNascimento, endereco);
        
        System.out.println("Qual conta você deseja? (Corrente ou Poupança)");
        String tipoConta = input.next();
        
        System.out.println("Senha:");
        String senha = input.next();
        
        System.out.println("Qual categoria você quer? "
                +"|------------------------------------Comum----------------------------------------------|"
              +  "| taxa de manutençäo mensal:R$12,00                                                       |"
              +  "| taxa de rendimento anual:0,5%                                                           |"
              +  "| limite de crédito: 1 mil                                                                |"
              +  "| seguro viagem: opicional:R$50,00 por mes                                                |"
              +  "| seguro de fraude: cobertura automatica para todos os cartoes, aólice base de R$5.000,00 |"
              +  "|-----------------------------------------------------------------------------------------|"
               + "|------------------------------------Super------------------------------------------------|"
               + "| taxa de manutençäo mensal:R$8,00                                                        |"
               + "| taxa de rendimento anual:0,7%                                                           |"
               + "| limite de crédito: 5 mil                                                                |"
               + "| seguro viagem: opicional:R$50,00 por mes                                                |"
               + "| seguro de fraude: cobertura automatica para todos os cartoes, aólice base de R$5.000,00 |"
               + "|-----------------------------------------------------------------------------------------|"
               + "|-----------------------------------Premium-----------------------------------------------|"
               + "| taxa de manutençäo mensal:insento                                                       |"
               + "| taxa de rendimento anual:0,9%                                                           |"
               + "| limite de crédito: 10 mil                                                               |"
               + "| seguro viagem: opicional:GRATUITO                                                       |"
               + "| seguro de fraude: cobertura automatica para todos os cartoes, aólice base de R$5.000,00 |"
               + "|-----------------------------------------------------------------------------------------|");
        String categoriaConta = input.next(); 
        
        Conta novaConta = new Conta(tipoConta, senha, categoriaConta);
        novoCliente.getConta().add(novaConta);

        
        System.out.println("Você deseja um cartão de debito? (sim ou não)");
        String respostarCartaoDeDebito = input.next();
        
        double saldoInicial = 0;
        int limiteTransacoesDebito = 5;
        
        ContaCorrente novaContaCorrente = new ContaCorrente(saldoInicial,vereficaRespostaCartao(respostarCartaoDeDebito),limiteTransacoesDebito );
        novoCliente.getContaCorrente().add(novaContaCorrente);
        
        
        System.out.println("Você deseja um cartão de credito? (sim ou não)");
        String cartaoCredito = input.next();
        
        double limiteCartaoCreditoInicial = calcularLimiteCartao(categoriaConta);
        
        CartaoDeCredito novoCartaoCredito = new CartaoDeCredito(limiteCartaoCreditoInicial,vereficaRespostaCartao(cartaoCredito));
        novoCliente.getCartaoCredito().add(novoCartaoCredito);
        
        clientes.add(novoCliente);
        
        salvarClientes();
        
        System.out.println("Cliente e Conta cadastrados com sucesso.");
    }
    
    public static Double calcularLimiteCartao(String segmento) {
        if (segmento == null || segmento.isEmpty()) {
            throw new IllegalArgumentException("Segmento inválido");
        }

        Double valorBase = 1000.0; 

        switch (segmento.toLowerCase()) {
            case "comum":
                return valorBase * 1.0;
            case "super":
                return valorBase * 5.0;
            case "premium":
                return valorBase * 10.0;
            default:
                throw new IllegalArgumentException("Segmento inválido: " + segmento);
        }
    }
    
    private boolean vereficaRespostaCartao(String opcaoEscolhida ) {
    	if (opcaoEscolhida.equalsIgnoreCase("sim")) {
            return true;
        } else if (opcaoEscolhida.equalsIgnoreCase("não")) {
            return false;
        } else {
            System.out.println("Entrada inválida. Por favor, digite 'sim' ou 'não'.");
            return false;
        }
    }

    private void salvarClientes() {
        try (Writer writer = new FileWriter(arquivoClientes)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Cliente> carregarClientes() {
        try (Reader reader = new FileReader(arquivoClientes)) {
            return gson.fromJson(reader, new TypeToken<List<Cliente>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Cliente fazerLogin(String cpf, String senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf) && cliente.getConta().get(0).getSenha().equals(senha)) {
            	System.out.println("Seja Bem-vindo " + cliente.getNome() );
            	return cliente; // criar um variavel para depois usar ( listar saldo...)
            }
        }
        return null; // Login falhou
    }
	
}
