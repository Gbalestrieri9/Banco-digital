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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Conta {
	Scanner input = new Scanner(System.in);
	Cliente cliente = new Cliente("123.456.789-12","fulano",new Date(),"rua joao");
	private String contaCorrente;
	private String contaPoupanca;
	private double saldo;
	private Gson gson;
    private String arquivoClientes = "clientes.json";
	
	//private static Map<Integer, Cliente> clientes = new HashMap<>();
    private List<Cliente> clientes;
    
    public Conta() {
    	//clientes = carregarClientes();
        gson = new GsonBuilder().setPrettyPrinting().create();
        clientes = carregarClientes();
    }

	public void cadastrar() {
		System.out.println("Coloque seu CPF:");
        String cpf = input.next();
        cliente.setCpf(cpf);
        
        System.out.println("Coloque seu nome:");
        String nome = input.next();
        cliente.setNome(nome);
        
        input.nextLine();
        
        System.out.println("Coloque sua data de nascimento (dd/MM/yyyy):");
        String data = input.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataNascimento = formato.parse(data);
            cliente.setDataNascimento(dataNascimento);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Cadastro não realizado.");
            return;
        }

        System.out.println("Coloque seu endereço:");
        String endereco = input.next();
        cliente.setEndereco(endereco);
        
        adicionarCliente(cliente);
        
        System.out.println("Usuario criado com sucesso\n");
    }
	
	public void adicionarCliente(Cliente clienteCadastrado) {
        if (clientes == null) {
        	clientes = new ArrayList<>();
        }
		clientes.add(clienteCadastrado);
        salvarClientes();
    }
	
    public void CadastroClientes() {
        clientes = new ArrayList<>();
        gson = new GsonBuilder().setPrettyPrinting().create();
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
            return gson.fromJson(reader, new TypeToken<List<Cliente>>(){}.getType());
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
	
	void tipoConta() {
		
	}
}
