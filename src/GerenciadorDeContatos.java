import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciadorDeContatos implements Serializable {
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
        GerenciadorDeContatos gerenciador = null;
        Scanner scanner = new Scanner(System.in);

        boolean executando = true;

        while (executando) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Remover contato");
            System.out.println("3 - Buscar contato");
            System.out.println("4 - Listar contatos");
            System.out.println("5 - Salvar contatos");
            System.out.println("6 - Carregar contatos");
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
                case 5:
                    try {
                        salvarContatos(gerenciador);
                        System.out.println("Contatos salvos com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar contatos: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        gerenciador = carregarContatos();
                        System.out.println("Contatos carregados com sucesso!");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao carregar contatos: " + e.getMessage());
                    }
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

    private static void salvarContatos(GerenciadorDeContatos gerenciador) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("contatos.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(gerenciador);
        out.close();
        fileOut.close();
    }

    private static GerenciadorDeContatos carregarContatos() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("contatos.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        GerenciadorDeContatos gerenciador = (GerenciadorDeContatos) in.readObject();
        in.close();
        fileIn.close();
        return gerenciador;
    }
}
