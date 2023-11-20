import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Equipamento {

private int idEquipamento;
private String marca;
private String modelo;
private float capacidade;
private boolean equipamentoCompleto;
private Cliente cliente;

int idIncremento = 1;
public Equipamento(String marcaDaCentral, String modelo, float capacidade, boolean equipamentoCompleto, Cliente cliente) { 
    this.idEquipamento = idIncremento++;   
    this.marca = marcaDaCentral;
    this.modelo = modelo;
    this.capacidade = capacidade;
    this.equipamentoCompleto = equipamentoCompleto;
    this.cliente = cliente;
 }


public int getIdEquipamento() {
    return idEquipamento;
}

public void setIdEquipamento(int idEquipamento) {
    this.idEquipamento = idEquipamento;
}

public String getMarca() {
    return marca;
}

public void setMarca(String marca) {
    this.marca = marca;
}

public String getModelo() {
    return modelo;
}

public void setModelo(String modelo) {
    this.modelo = modelo;
}

public float getCapacidade() {
    return capacidade;
}

public void setCapacidade(float capacidade) {
    this.capacidade = capacidade;
}

public boolean isEquipamentoCompleto() {
    return equipamentoCompleto;
}
public void setEquipamentoCompleto(boolean equipamentoCompleto) {
    this.equipamentoCompleto = equipamentoCompleto;
}

public String getCliente() {
    return cliente.getNome();
}

// Exceções para entrada de dados

    public static String getStringInput(String prompt) {
        String input = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = JOptionPane.showInputDialog(prompt);
                if (input == null) {
                    return null;
                }
                validInput = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro de entrada. Por favor, tente novamente.");
            }
        }
        return input;
    }

    public static int getIntegerInput(String prompt) {
        int value = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(prompt);
                if (input == null) {
                    return value;
                }
                value = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número inteiro válido.");
            }
        }
        return value;
    }

    public static float getFloatInput(String prompt) {
        float value = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(prompt);
                if (input == null) {
                    return value;
                }
                value = Float.parseFloat(input);
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um valor numérico válido.");
            }
        }
        return value;
    }

    public static Date getDateInput(String prompt, SimpleDateFormat sdf) {
        Date value = null;
        boolean validInput = false;
        while (!validInput) {
            String input = JOptionPane.showInputDialog(prompt);
            if (input == null) {
                return null;
            }
            try {
                value = sdf.parse(input);
                validInput = true;
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null,
                        "Formato de data inválido. Certifique-se de usar o padrão dd/mm/aaaa.");
            }
        }
        return value;
    }

    public static boolean getBooleanInput(String prompt) {
        boolean value = false;
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(prompt);
                if (input == null) {
                    return value;
                }
                // Converte a entrada para minúsculas e remove espaços em branco ao redor
                input = input.trim().toLowerCase();

                // Verifica se a entrada é "true" ou "false"
                if (input.equals("true") || input.equals("false")) {
                    value = Boolean.parseBoolean(input);
                    validInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira 'true' ou 'false'.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro de entrada. Por favor, tente novamente.");
            }
        }
        return value;
    }
    
    public static void cadastrarEquipamento(ArrayList<Cliente> listaClientes, ArrayList<Equipamento> listaEquipamentos){
        Scanner ler = new Scanner(System.in);
        //chamada do método listar clientes
        int idCliente = getIntegerInput("Selecione o cliente desejado");
        if (idCliente == -1) {
            return;
        }
        Cliente clienteEncontrado = null;
    
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdCliente() == idCliente) {
                clienteEncontrado = cliente;
                break;
            }
        }
    
        if (clienteEncontrado != null) {
            String marca = getStringInput("Digite a marca do equipamento");
            String modelo = getStringInput("Digite o modelo de equipamento"); 
            float capacidade = getFloatInput("Digite a capacidade do equipamento");
            boolean equipamentoCompleto = getBooleanInput("O equipamento está completo? (true/false)");
        
            Equipamento novoEquipamento = new Equipamento(marca, modelo, capacidade, equipamentoCompleto, clienteEncontrado);
            listaEquipamentos.add(novoEquipamento);

            JOptionPane.showMessageDialog(null, "Equipamento cadastrado com sucesso!");

        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
    }

    public static void editarEquipamento(ArrayList<Equipamento> listaEquipamentos) {
        Scanner ler = new Scanner(System.in);
        boolean encontrado = false;
        Equipamento equipamentoEditar = null;

        if (listaEquipamentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");

            System.out.println("Nenhum equipamento cadastrado.");
        } else {
            listarEquipamentos(listaEquipamentos);
            int idEditar = getIntegerInput("Digite o ID do equipamento que deseja editar");

            for (Equipamento equipamento : listaEquipamentos) {
                if (equipamento.getIdEquipamento() == idEditar) {
                    equipamentoEditar = equipamento;
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {   
            
                    String novaMarca = getStringInput("Digite a nova marca do equipamento");
                    equipamentoEditar.setMarca(novaMarca);
                    String novoModelo = getStringInput("Digite o novo modelo de equipamento"); 
                    equipamentoEditar.setModelo(novoModelo);
                    float novaCapacidade = getFloatInput("Digite a nova capacidade do equipamento");
                    equipamentoEditar.setCapacidade(novaCapacidade);
                    boolean novoEquipamentoCompleto = getBooleanInput("O equipamento está completo? (true/false)");
                    equipamentoEditar.setEquipamentoCompleto(novoEquipamentoCompleto);
                    JOptionPane.showMessageDialog(null, "Equipamento editado!");
            } else {
                    JOptionPane.showMessageDialog(null, "Equipamento não existe!");
            } }           
    
    }

    public static void listarEquipamentos(ArrayList<Equipamento> listaEquipamentos) {
        if (listaEquipamentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum equipamento cadastrado.");
        } else {
            for (Equipamento equipamento : listaEquipamentos) {

                JOptionPane.showMessageDialog(null, "ID: " + equipamento.getIdEquipamento() +
                        "\nMarca " + equipamento.getMarca() +
                        "\nModelo: " + equipamento.getModelo() +
                        "\nCliente: " + equipamento.getCliente());        

            }
        }
    }

    //Excluir Equipamento
    public static boolean excluirEquipamento(int idEquipamento, ArrayList<Equipamento> listaEquipamentos) {
        for (Equipamento equipamento : listaEquipamentos) {
            if (equipamento.getIdEquipamento() == idEquipamento) {
                listaEquipamentos.remove(equipamento);
                JOptionPane.showMessageDialog(null, "Equipamento excluído!");
                return true; // Retorna true se o serviço foi excluído
            }
        }
        JOptionPane.showMessageDialog(null, "Equipamento não encontrado!");

        return false; // Retorna false se o serviço não foi encontrado

    }
   
}
    



