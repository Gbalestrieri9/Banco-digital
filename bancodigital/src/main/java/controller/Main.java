package controller;
import java.util.Scanner;

import view.menuDeControle;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        menuDeControle menu = new menuDeControle();
        
        
        int acaoDigitada;
    
        boolean executando = true;
                
        while(executando) {
            try {
                System.out.println("---MENU DE CONTROLE---\n"
                        + "1.Se cadastre\n"
                        + "2.Acessar conta\n"
                        + "3.Visualize seu saldo\n"
                        + "4.Depositar\n"
                        + "5.Transferencia via Pix\n"
                        + "6.Alterar senha\n"
                        + "7.Altere seu limite de trasações\n"
                        + "8.Ative/desative sua conta\n"
                        + "9.Compre \n"
                        + ".Sair \n");
                
                System.out.println("Digite a numeração que deseja selecionar acima:");
                acaoDigitada = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                
                if(acaoDigitada == 10) {
                	menu.controle(acaoDigitada);
                    executando = false;
                    input.close();
                    System.out.println("Programa finalizado com sucesso!");
                } else if(acaoDigitada ==1) {
                	menu.controle(acaoDigitada);
                }
                else {
                	if((acaoDigitada>= 3 && acaoDigitada<=9) && menu.clienteLogado != null) {
                		menu.controle(acaoDigitada);
                	}else {
                		menu.controle(2);
                	}
                }
            } catch(Exception e) {
                System.out.println("Digite apenas os numeros disponiveis no menu de controle\n");
                input.nextLine();
            }
        }   
    }
}