import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Cliente {
    private int idCliente;
    private static int idAutomatico = 1;
    private String tipo;
    private String nome;
    private String documento;
    private String endereco;
    private String celular;
 


    //Construtor

    public Cliente (String tipo, String nome, String documento, String endereco, String celular) {
        this.idCliente = idAutomatico ++;
        this.tipo = tipo;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.celular = celular;
        
    }

    //Getters
    
    public int getIdCliente() {
        return idCliente;
    }

        public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCelular() {
        return celular;
    }


    //Setters

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    //Metodos

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

    //Solicitar que o usuario insira os detalhes dos clientes:

    public static void cadastrarCliente(ArrayList<Cliente> listaClientes){
    
       
        //Cadastro de Tipo
        String tipo = getStringInput("Tipo (PF ou PJ):");
        if (tipo == null) {
            return;
        }
        //Cadastro de Nome do Cliente
        String nome = getStringInput("Nome:");
        if (nome == null) {
            return;
        }
        //Cadastro de Documento (CPF ou CNPJ):
        String documento = getStringInput("Documento (CPF ou CNPJ)");
        if (documento == null) {
            return;
        }
        //Cadastro de endereco
        String endereco = getStringInput("Endereço (Rua, Nº, Bairro, CEP):");
        if (endereco == null) {
            return;
        }
        //Cadastro de celular
        String celular = getStringInput("Celular (Ex: 69 9999 9999):");
        if (celular == null) {
            return;
        }

        Cliente clienteCadastrar = new Cliente (tipo, nome, documento, endereco, celular);
        
        // Adicione o novo cliente a lista.
        listaClientes.add (clienteCadastrar);
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        
}

//editar Cliente
    public static void editarCliente(ArrayList<Cliente> listaClientes) {        
        boolean encontrado = false;
        Cliente clienteEditar = null;
            
        if (listaClientes.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
        } else {         
            listarCliente(listaClientes);
            int idEditar = getIntegerInput("Qual o ID do cliente que deseja editar?");
            if (idEditar == -1){
                return;
            }
            for (Cliente Cliente : listaClientes) {
                if (Cliente.getIdCliente() == idEditar) {
                    clienteEditar = Cliente;
                    encontrado = true;
                    break;
            }
            }if (encontrado) {
                               
                String[] opcoes = {"Editar Tipo", "Editar Nome", "Editar Documento", "Editar Endereço", "Editar Celular", "Sair"};
                
                int opcao = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
                );

                switch (opcao) {

                case 0:    //Edição do Tipo
                    String novoTipo = getStringInput("Edite o Tipo:");
                    if (novoTipo == null) {
                        return;
                    }
                    clienteEditar.setTipo(novoTipo);
                    break;

                case 1:    //Edição do Nome
                    String novoNome = getStringInput("Edite o Nome:");
                    if (novoNome == null) {
                        return;
                    }
                    clienteEditar.setNome(novoNome);
                    break;
                
                case 2:    //Edição do Documento
                    String novoDocumento = getStringInput("Edite o Documento:");
                   if (novoDocumento == null) {
                        return;
                    }
                    clienteEditar.setDocumento(novoDocumento);
                    break;

                case 3:    //Edição do Endereço
                    String novoEndereco = getStringInput("Edite o Endereço:");
                    if (novoEndereco == null) {
                        return;
                    }
                    clienteEditar.setEndereco(novoEndereco);
                    break;

                case 4:    //Edição do Celular
                    String novoCelular = getStringInput("Edite o Celular:");
                    if (novoCelular == null) {
                        return;
                    }
                    clienteEditar.setCelular(novoCelular);
                    break;
                
                case 5:
                    JOptionPane.showMessageDialog(null, "Edição concluída.");
                    break;                

                }
               
             JOptionPane.showMessageDialog(null, "Cliente editado!");
                            
        }else {
                JOptionPane.showMessageDialog(null, "CLiente não existe.");
        }
    }
}

    //Excluir Cliente
    public static boolean excluirCliente(int idCliente, ArrayList<Cliente> listaClientes) {
    for (Cliente Cliente : listaClientes) {
            if (Cliente.getIdCliente() == idCliente) {
                listaClientes.remove(Cliente);
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");

                return true; // Retorna true se o cliente foi excluído.
            }
        }
        JOptionPane.showMessageDialog(null, "Cliente não encontrado!");

        return false; // Retorna false se o cliente não foi encontrado.
    }

    //Listar Clientes
    public static void listarCliente(ArrayList<Cliente> listaClientes) {

        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
       } else {
            for (Cliente Cliente : listaClientes) {
                  JOptionPane.showMessageDialog(null, "ID: " + Cliente.getIdCliente() + 
                         "\nTipo: " + Cliente.getTipo()+
                         "\nNome: " + Cliente.getNome()+
                         "\nDocumento: " + Cliente.getDocumento()+
                         "\nEndereço: " + Cliente.getEndereco() + 
                         "\nCelular: " + Cliente.getCelular());                          
            }
        }
    }
    
    
}
