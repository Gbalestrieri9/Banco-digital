package br.com.bancodigital;
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
                        + "2.Logar\n"
                        + "3.Visualize seus dados\n"
                        + "4.Sair \n");
                
                System.out.println("Digite a numeração que deseja selecionar acima:");
                acaoDigitada = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                
                if(acaoDigitada == 4) {
                    executando = false;
                    input.close();
                    System.out.println("Programa finalizado com sucesso!");
                } else {
                    menu.controle(acaoDigitada);
                }
            } catch(Exception e) {
                System.out.println("Digite apenas os numeros disponiveis no menu de controle\n");
                input.nextLine();
            }
        }   
    }
}

    

