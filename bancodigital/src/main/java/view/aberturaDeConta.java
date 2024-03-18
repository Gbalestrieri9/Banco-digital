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
        
        System.out.println("Qual categoria você quer? (Simples, Premium)");
        String categoriaConta = input.next();
        
        Conta novaConta = new Conta(tipoConta, senha, categoriaConta);
        novoCliente.getContas().add(novaConta);

        clientes.add(novoCliente);
        
        salvarClientes();
        
        System.out.println("Cliente e Conta cadastrados com sucesso.");
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

//    public boolean fazerLogin(String cpf, String senha) {
//        for (Cliente cliente : clientes) {
//            if (cliente.getCpf().equals(cpf) && cliente.getSenha().equals(senha)) {
//                return true; // Login bem-sucedido
//            }
//        }
//        return false; // Login falhou
//    }
	
}
