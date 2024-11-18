package controle;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import dominio.Paciente;

public class ControlaPaciente {
	
	private ArrayList<Paciente> pacientes;
	private final String arquivoPacientes = "pacientes.txt";
	
	public ControlaPaciente() {
		this.pacientes = new ArrayList<>();
		carregarPacientes();
	}
	
	public Paciente buscarPacientePorNumero(int numero) {
		for (Paciente paciente : pacientes) {
			if (paciente.getNumero() == numero) {
				return paciente;
			}
		}
		return null;
	}
	
	// Métodos de manipulação de dados do paciente:
	public void cadastrarPaciente(int numero, double peso, double altura) {
		Paciente paciente = new Paciente(numero, peso, altura);
		pacientes.add(paciente);
		salvarPacientes();
		System.out.println("Paciente cadastrado.");
	}
	
	public void editarPaciente(int numero) {
		Paciente paciente = buscarPacientePorNumero(numero);
		
		if (paciente != null) {
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Novo peso:");
			double novoPeso = scanner.nextDouble();
			
			System.out.print("Nova altura:");
			double novaAltura = scanner.nextDouble();
			
			paciente.setPeso(novoPeso);
			paciente.setAltura(novaAltura);
			
			System.out.println("Paciente editado com sucesso.");
		}
		else {
			System.out.println("Paciente de número " +numero +" não encontrado.");
		}
	}
	
	public void excluirPaciente(int numero) {
		Paciente paciente = buscarPacientePorNumero(numero);
		
		if (paciente != null) {
			pacientes.remove(paciente);
			System.out.println("Paciente excluído com sucesso.");
		}
		else {
			System.out.println("Paciente de número " +numero +" não encontrado.");
		}
	}
	
	public void exibirPacientes() {
		if (pacientes.isEmpty()) {
			System.out.println("Nenhum paciente encontrado.");
		}
		else {
			System.out.println("Lista de pacientes: ");
			for (Paciente paciente : pacientes) {
				paciente.listarPacientes();
			}
		}
	}
	
	// Métodos relacionados ao arquivo:
	private void salvarPacientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPacientes))) {
            for (Paciente paciente : pacientes) {
                writer.write(paciente.getNumero() + ";" + paciente.getPeso() + ";" + paciente.getAltura());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar pacientes: " + e.getMessage());
        }
	}
	
	private void carregarPacientes() {
		try (BufferedReader reader = new BufferedReader(new FileReader(arquivoPacientes))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int numero = Integer.parseInt(dados[0]);
                double peso = Double.parseDouble(dados[1]);
                double altura = Double.parseDouble(dados[2]);
                pacientes.add(new Paciente(numero, peso, altura));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pacientes não encontrado. Será criado ao salvar novos pacientes.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar pacientes: " + e.getMessage());
        }
	}
	
	public static void main(String[] args) {
		
		ControlaPaciente controle = new ControlaPaciente();
        Scanner scanner = new Scanner(System.in);
        int opcao;
		
		do {
			System.out.println("\nMenu:");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Alterar Paciente");
            System.out.println("4. Excluir Paciente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
            case 1:
            	System.out.print("Número do paciente: ");
            	int numero = scanner.nextInt();
            	scanner.nextLine();
            	System.out.print("Peso do paciente: ");
            	double peso = scanner.nextDouble();
            	System.out.print("Altura do paciente: ");
            	double altura = scanner.nextDouble();
            	
            	controle.cadastrarPaciente(numero, peso, altura);
            	break;
            
            case 2:
            	controle.exibirPacientes();
            	break;
            
            case 3:
            	System.out.println("Número do paciente para editar: ");
            	int numeroAlterar = scanner.nextInt();
            	scanner.nextLine();
            	
            	controle.editarPaciente(numeroAlterar);
            	break;
            
            case 4:
            	System.out.println("Número do paciente para excluir: ");
            	int numeroExcluir = scanner.nextInt();
            	scanner.nextLine();
            	
            	controle.excluirPaciente(numeroExcluir);
            	break;
            	
            case 0:
            	System.out.println("Encerrando programa.");
            	break;
            	
            default:
            	System.out.println("Opção inválida. Tente novamente.");
            }
		} while (opcao != 0);
		
		scanner.close();
	}

}
