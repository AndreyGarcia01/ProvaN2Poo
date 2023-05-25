import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciadorDeContatos implements Serializable {
    private Map<String, Contato> contatos;

    public GerenciadorDeContatos() {
        contatos = new HashMap<>();
    }

    public void adicionarContato(String nome, String numeroTelefone) {
        contatos.put(nome, new Contato(nome, numeroTelefone));
    }

    public void adicionarContatoComData(String nome, String numeroTelefone, LocalDate dataNascimento) {
        contatos.put(nome, new ContatoComData(nome, numeroTelefone, dataNascimento));
    }

    public void adicionarContatoAmigo(String nome, String numeroTelefone, String apelido) {
        contatos.put(nome, new ContatoAmigo(nome, numeroTelefone, apelido));
    }

    public void adicionarContatoTrabalho(String nome, String numeroTelefone, String empresa) {
        contatos.put(nome, new ContatoTrabalho(nome, numeroTelefone, empresa));
    }

    public void removerContato(String nome) {
        contatos.remove(nome);
    }

    public void buscarContato(String nome) {
        if (contatos.containsKey(nome)) {
            Contato contato = contatos.get(nome);
            System.out.println("Número de telefone de " + nome + ": " + contato.getNumeroTelefone());
        } else {
            System.out.println("Contato não encontrado: " + nome);
        }
    }

    public void listarContatos() {
        System.out.println("Lista de contatos:");
        for (Map.Entry<String, Contato> contato : contatos.entrySet()) {
            String nome = contato.getKey();
            String numeroTelefone = contato.getValue().getNumeroTelefone();
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
            System.out.println("2 - Adicionar contato com data de nascimento");
            System.out.println("3 - Adicionar contato amigo");
            System.out.println("4 - Adicionar contato do trabalho");
            System.out.println("5 - Remover contato");
            System.out.println("6 - Buscar contato");
            System.out.println("7 - Listar contatos");
            System.out.println("8 - Salvar contatos");
            System.out.println("9 - Carregar contatos");
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
                    System.out.print("Digite o nome do contato: ");
                    String nomeAdicionarComData = scanner.nextLine();
                    System.out.print("Digite o número de telefone: ");
                    String numeroTelefoneAdicionarComData = scanner.nextLine();
                    System.out.print("Digite a data de nascimento (yyyy-MM-dd): ");
                    String dataNascimentoStr = scanner.nextLine();
                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
                    gerenciador.adicionarContatoComData(nomeAdicionarComData, numeroTelefoneAdicionarComData, dataNascimento);
                    System.out.println("Contato com data de nascimento adicionado com sucesso!");
                    break;
                case 3:
                    System.out.print("Digite o nome do contato amigo: ");
                    String nomeAdicionarAmigo = scanner.nextLine();
                    System.out.print("Digite o número de telefone: ");
                    String numeroTelefoneAdicionarAmigo = scanner.nextLine();
                    System.out.print("Digite o apelido: ");
                    String apelido = scanner.nextLine();
                    gerenciador.adicionarContatoAmigo(nomeAdicionarAmigo, numeroTelefoneAdicionarAmigo, apelido);
                    System.out.println("Contato amigo adicionado com sucesso!");
                    break;
                case 4:
                    System.out.print("Digite o nome do contato do trabalho: ");
                    String nomeAdicionarTrabalho = scanner.nextLine();
                    System.out.print("Digite o número de telefone: ");
                    String numeroTelefoneAdicionarTrabalho = scanner.nextLine();
                    System.out.print("Digite o nome da empresa: ");
                    String empresa = scanner.nextLine();
                    gerenciador.adicionarContatoTrabalho(nomeAdicionarTrabalho, numeroTelefoneAdicionarTrabalho, empresa);
                    System.out.println("Contato do trabalho adicionado com sucesso!");
                    break;
                case 5:
                    System.out.print("Digite o nome do contato a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    gerenciador.removerContato(nomeRemover);
                    System.out.println("Contato removido com sucesso!");
                    break;
                case 6:
                    System.out.print("Digite o nome do contato a ser buscado: ");
                    String nomeBuscar = scanner.nextLine();
                    gerenciador.buscarContato(nomeBuscar);
                    break;
                case 7:
                    gerenciador.listarContatos();
                    break;
                case 8:
                    try {
                        salvarContatos(gerenciador);
                        System.out.println("Contatos salvos com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar contatos: " + e.getMessage());
                    }
                    break;
                case 9:
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

class Contato implements Serializable {
    private String nome;
    private String numeroTelefone;

    public Contato(String nome, String numeroTelefone) {
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }
}

class ContatoComData extends Contato {
    private LocalDate dataNascimento;

    public ContatoComData(String nome, String numeroTelefone, LocalDate dataNascimento) {
        super(nome, numeroTelefone);
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}

class ContatoAmigo extends Contato {
    private String apelido;

    public ContatoAmigo(String nome, String numeroTelefone, String apelido) {
        super(nome, numeroTelefone);
        this.apelido = apelido;
    }

    public String getApelido() {
        return apelido;
    }
}

class ContatoTrabalho extends Contato {
    private String empresa;

    public ContatoTrabalho(String nome, String numeroTelefone, String empresa) {
        super(nome, numeroTelefone);
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }
}
