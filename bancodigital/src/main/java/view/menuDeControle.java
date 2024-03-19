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
                break;
            case 3:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
