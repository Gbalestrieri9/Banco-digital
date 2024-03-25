package dtos;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.Cliente;
import dao.Conta;
import dao.ContaCorrente;

public class AberturaDeConta {
    private Scanner input = new Scanner(System.in);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String arquivoClientes = "clientes.json";
    public List<Cliente> clientes;

    public AberturaDeConta() {
        clientes = carregarClientes();
    }

    public void cadastrarClienteEConta() {
        try {
        	System.out.println("Cadastro de Cliente e Conta");
        	
        	 String cpf = lerCPF();
             if (cpf == null || !validarCPF(cpf)) {
                 System.out.println("CPF inválido ou já cadastrado.");
                 return;
             }

             String nome = lerNome();
             if (nome == null) {
                 System.out.println("Nome inválido.");
                 return;
             }

             Date dataNascimento = lerDataNascimento();
             if (dataNascimento == null || !validarIdade(dataNascimento)) {
                 System.out.println("Data de nascimento inválida ou cliente menor de idade. O cadastro não será realizado.");
                 return;
             }

             System.out.println("Coloque seu endereço Deve incluir Rua, numero, complemento(se näo ouver coloque NA), cidade, estado, CEP(XXXXX-XXX):");
             input.nextLine();
             String endereco = input.nextLine();
             try {
                 validarEndereco(endereco);
                 
             } catch (IllegalArgumentException e) {
                 System.out.println("O endereço não é válido. O programa será encerrado.");
                
                 return;
             }

            Cliente novoCliente = new Cliente(cpf, nome, dataNascimento, endereco);
            
            System.out.println("Qual conta você deseja? (Corrente ou Poupança)");
            String tipoConta = input.next();
            
            System.out.println("Senha:");
            String senha = input.next();
            
            System.out.println("Qual categoria você quer? \n"
                    +"|------------------------------------Comum-----------------------------------------------|\n"
                  +  "| taxa de manutençäo mensal:R$12,00                                                        |\n"
                  +  "| taxa de rendimento anual:0,5%                                                            |\n"
                  +  "| limite de crédito: 1 mil                                                                 |\n"
                  +  "| seguro viagem: opicional:R$50,00 por mes                                                 |\n"
                  +  "| seguro de fraude: cobertura automatica para todos os cartoes, apólice base de R$5.000,00 |\n"
                  +  "|------------------------------------------------------------------------------------------|\n"
                   + "|------------------------------------Super-------------------------------------------------|\n"
                   + "| taxa de manutençäo mensal:R$8,00                                                         |\n"
                   + "| taxa de rendimento anual:0,7%                                                            |\n"
                   + "| limite de crédito: 5 mil                                                                 |\n"
                   + "| seguro viagem: opicional:R$50,00 por mes                                                 |\n"
                   + "| seguro de fraude: cobertura automatica para todos os cartoes, apólice base de R$5.000,00 |\n"
                   + "|------------------------------------------------------------------------------------------|\n"
                   + "|-----------------------------------Premium------------------------------------------------|\n"
                   + "| taxa de manutençäo mensal:insento                                                        |\n"
                   + "| taxa de rendimento anual:0,9%                                                            |\n"
                   + "| limite de crédito: 10 mil                                                                |\n"
                   + "| seguro viagem: opicional:GRATUITO                                                        |\n"
                   + "| seguro de fraude: cobertura automatica para todos os cartoes, apólice base de R$5.000,00 |\n"
                   + "|------------------------------------------------------------------------------------------|\n");
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
            
            System.out.println("Você deseja contratar a apolice de seguro viagem? (sim ou não)");
            String apoliceViagem = input.next();
            if(apoliceViagem.equals("sim")) {
            	System.out.println("\n A apólice será ativada somente após o recebimento do primeiro pagamento proveniente da conta corrente.\n");
            }
            
            ProdutosSeguro produtosSeguro = new ProdutosSeguro(categoriaConta,vereficaRespostaCartao(apoliceViagem));
            novoCliente.setProdutosSeguros(produtosSeguro);
            
            clientes.add(novoCliente);
            
            salvarClientes();
            
            System.out.println("Cliente e Conta cadastrados com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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

    public void salvarClientes() {
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
            	System.out.println("\nSeja Bem-vindo " + cliente.getNome() + "\n");
            	return cliente;
            }
        }
        return null;
    }
       
   public ContaCorrente buscarContaPorCpf(String cpfProcurado) {
       for (Cliente cliente : clientes) {
           if (cliente.getCpf().equals(cpfProcurado)) {
               return cliente.getContaCorrente().get(0);
           }
       }
       return null; 
   }
   
   private String lerCPF() {
       System.out.println("Coloque seu CPF(XXX.XXX.XXX-XX):");
       String cpf = input.next();

       if (!validarFormatoCPF(cpf)) {
           System.out.println("Formato de CPF inválido.");
           return null;
       }

       for (Cliente cliente : clientes) {
           if (cliente.getCpf().equals(cpf)) {
               System.out.println("CPF já cadastrado.");
               return null;
           }
       }

       return cpf;
   }

   private boolean validarFormatoCPF(String cpf) {

       String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(cpf);


       return matcher.matches();
   }

   private boolean validarCPF(String cpf) {

       return true; 
   }

   private String lerNome() {
       System.out.println("Coloque seu nome:");
       String nome = input.next();

       if (!nome.matches("[a-zA-Z\\s]+")) {
           return null;
       }


       if (nome.length() < 2 || nome.length() > 100) {
           return null;
       }

       return nome;
   }

   private Date lerDataNascimento() {
       System.out.println("Coloque sua data de nascimento (dd/MM/yyyy):");
       String data = input.next();
       SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
       formato.setLenient(false); 
       try {
           Date dataNascimento = formato.parse(data);
           return dataNascimento;
       } catch (ParseException e) {
           return null;
       }
   }
   private boolean validarIdade(Date dataNascimento) {
       Date hoje = new Date();

       long idadeEmMilissegundos = 18L * 365 * 24 * 60 * 60 * 1000; 
       boolean maiorDeIdade = hoje.getTime() - dataNascimento.getTime() >= idadeEmMilissegundos;

       return maiorDeIdade;
   }
   
   public static void validarEndereco(String endereco) {
       String[] partes = endereco.split("\\s*,\\s*");

       if (partes.length != 6) {
           throw new IllegalArgumentException("O endereço deve conter rua, número, complemento, cidade, estado e CEP.");
       }

       String cep = partes[5].trim();
       if (!cep.matches("\\d{5}-\\d{3}")) {
           throw new IllegalArgumentException("Formato de CEP inválido. Deve estar no formato XXXXX-XXX.");
       }
       for (String parte : partes) {
           if (parte.isEmpty()) {
               throw new IllegalArgumentException("Todas as partes do endereço devem ser preenchidas.");
           }
       }
   }
}