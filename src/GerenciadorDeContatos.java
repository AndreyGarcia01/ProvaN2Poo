import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciadorDeContatos {
    private Map<String, String> contatos;

    public GerenciadorDeContatos() {
        contatos = new HashMap<>();
    }

    public void adicionarContato(String nome, String numeroTelefone) {
        contatos.put(nome, numeroTelefone);
    }

    public void removerContato(String nome) {
        contatos.remove(nome);
    }

    public void buscarContato(String nome) {
        if (contatos.containsKey(nome)) {
            String numeroTelefone = contatos.get(nome);
            System.out.println("Número de telefone de " + nome + ": " + numeroTelefone);
        } else {
            System.out.println("Contato não encontrado: " + nome);
        }
    }

    public void listarContatos() {
        System.out.println("Lista de contatos:");
        for (Map.Entry<String, String> contato : contatos.entrySet()) {
            String nome = contato.getKey();
            String numeroTelefone = contato.getValue();
            System.out.println("Nome: " + nome + ", Número de telefone: " + numeroTelefone);
        }
    }

    public static void main(String[] args) {
        GerenciadorDeContatos gerenciador = new GerenciadorDeContatos();
        Scanner scanner = new Scanner(System.in);

        boolean executando = true;

        while (executando) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Remover contato");
            System.out.println("3 - Buscar contato");
            System.out.println("4 - Listar contatos");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do contato: ");
                    String nomeAdicionar = scanner.nextLine();
                    System.out.print("Digite o número de telefone: ");
                    String numeroTelefoneAdicionar = scanner.nextLine();
                    gerenciador.adicionarContato(nomeAdicionar, numeroTelefoneAdicionar);
                    System.out.println("Contato adicionado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o nome do contato a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    gerenciador.removerContato(nomeRemover);
                    System.out.println("Contato removido com sucesso!");
                    break;
                case 3:
                    System.out.print("Digite o nome do contato a ser buscado: ");
                    String nomeBuscar = scanner.nextLine();
                    gerenciador.buscarContato(nomeBuscar);
                    break;
                case 4:
                    gerenciador.listarContatos();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println();
        }

        System.out.println("Programa encerrado.");
        scanner.close();
    }
}
