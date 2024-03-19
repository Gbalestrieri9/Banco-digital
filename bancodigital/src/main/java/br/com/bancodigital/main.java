package br.com.bancodigital;

import view.CartaoDeDebito;
import view.aberturaDeConta;

public class main {
  public static void main(String[] args) {
	  aberturaDeConta conta = new aberturaDeConta();
	  CartaoDeDebito cDebito = new CartaoDeDebito(0, conta);
	  
	  conta.cadastrarClienteEConta();
	  
	  cDebito.getSaldo();
    
  }
}
