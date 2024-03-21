package view;

public class menuDeControle {
    private aberturaDeConta aberturaDeConta;

    public menuDeControle() {
        this.aberturaDeConta = new aberturaDeConta();
    }

    public void controle(int acaoSelecionada) {
        switch (acaoSelecionada) {
            case 1:
                aberturaDeConta.cadastrarClienteEConta();
                break;
            case 2:
            	aberturaDeConta.fazerLogin("111.111.111-11","senha123");
            	
                break;
            case 3:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
