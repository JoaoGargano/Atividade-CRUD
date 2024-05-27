import java.io.*;
import java.util.*;
class Celular implements Serializable {
    Scanner scanner = new Scanner(System.in);
    private static final long serialVersionUID = 1L;
    private String marca;
    private String modelo;
    private String cor;
    private String numero;
   

    public Celular(String marca, String modelo, String cor, String numero) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.numero = numero;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Cor: " + cor + ", Número: " + numero;
    }
}

class CelularManager {
    private List<Celular> celulares = new ArrayList<>();
    private final String FILENAME = "celulares.txt";

    public CelularManager() {
        loadFromFile();
    }

    public void create(String marca, String modelo, String cor, String numero) {
        celulares.add(new Celular(marca, modelo, cor, numero));
        saveToFile();
    }

    public List<Celular> readAll() {
        return celulares;
    }

    public void update(int index, String marca, String modelo, String cor, String numero) {
        if (index >= 0 && index < celulares.size()) {
            celulares.set(index, new Celular(marca, modelo, cor, numero));
            saveToFile();
        } else {
            System.out.println("Índice inválido");
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < celulares.size()) {
            celulares.remove(index);
            saveToFile();
        } else {
            System.out.println("Índice inválido");
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(celulares);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            celulares = (List<Celular>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CelularManager manager = new CelularManager();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Criar celular");
            System.out.println("2. Listar celulares");
            System.out.println("3. Atualizar celular");
            System.out.println("4. Deletar celular");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Cor: ");
                    String cor = scanner.nextLine();
                    System.out.print("Número: ");
                    String numero = scanner.nextLine();
                    manager.create(marca, modelo, cor, numero);
                    break;
                case 2:
                    List<Celular> celulares = manager.readAll();
                    for (int i = 0; i < celulares.size(); i++) {
                        System.out.println("[" + i + "] " + celulares.get(i));
                    }
                    break;
                case 3:
                    System.out.print("Índice do celular a ser atualizado: ");
                    int indexUpdate = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Nova marca: ");
                    marca = scanner.nextLine();
                    System.out.print("Novo modelo: ");
                    modelo = scanner.nextLine();
                    System.out.print("Nova cor: ");
                    cor = scanner.nextLine();
                    System.out.print("Novo número: ");
                    numero = scanner.nextLine();
                    manager.update(indexUpdate, marca, modelo, cor, numero);
                    break;
                case 4:
                    System.out.print("Índice do celular a ser deletado: ");
                    int indexDelete = scanner.nextInt();
                    manager.delete(indexDelete);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
                    scanner.close();
            }
        }
    }
}
