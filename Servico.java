import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class Servico {

    //Mais recente

    //atributos da Classe
    public int idServico;
    private static int idAutomatico = 1;
    public int idGarantia; 
    private String itensAdicionais;
    private String motivo;
    private String primeiroDiagnostico;
    private String servicoRealizado;
    private String testesRealizados;
    private Date dataRecebimento;
    private Date dataEntrega;
    private Date prazo;
    private double valor;
    private Cliente cliente;
    private Equipamento equipamento;


    //Construtor da classe
    public Servico(int idServico,String itensAdicionais, String motivo, String primeiroDiagnostico, String servicoRealizado, String testesRealizados, Date dataRecebimento, Date dataEntrega, Date prazo, double valor, Cliente cliente, Equipamento equipamento) {
        this.idServico = idAutomatico++;
        this.itensAdicionais = itensAdicionais;
        this.motivo = motivo;
        this.primeiroDiagnostico = primeiroDiagnostico;
        this.servicoRealizado = servicoRealizado;
        this.testesRealizados = testesRealizados;
        this.dataRecebimento = dataRecebimento;
        this.dataEntrega = dataEntrega;
        this.prazo = prazo;
        this.valor = valor;
        this.cliente = cliente;
        this.equipamento = equipamento;
    }

    //Método para exibir todos os serviços cadastrados, para não ficar decorando o ID.

    public Servico(int idServico, Equipamento equipamento, Cliente cliente) { 
        this.idServico = idServico;
        this.equipamento = equipamento;
        this.cliente = cliente;
    }

    //Getters and setters
    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
    

    public String getItensAdicionais() {
        return itensAdicionais;
    }

    public void setItensAdicionais(String itensAdicionais) {
        this.itensAdicionais = itensAdicionais;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPrimeiroDiagnostico() {
        return primeiroDiagnostico;
    }

    public void setPrimeiroDiagnostico(String primeiroDiagnostico) {
        this.primeiroDiagnostico = primeiroDiagnostico;
    }

    public String getServicoRealizado() {
        return servicoRealizado;
    }

    public void setServicoRealizado(String servicoRealizado) {
        this.servicoRealizado = servicoRealizado;
    }

    public String getTestesRealizados() {
        return testesRealizados;
    }

    public void setTestesRealizados(String testesRealizados) {
        this.testesRealizados = testesRealizados;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCliente() {
        return cliente.getNome();
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public int getEquipamento() {
        return equipamento.getIdEquipamento();
    }
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    //Métodos de composição
        //Cliente

    public Servico trocarCliente(Servico servico, ArrayList<Cliente> listaClientes) {

        int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Qual o ID do cliente corrento?"));
        Cliente clienteEncontrado = null;
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdCliente() == idCliente) {
                clienteEncontrado = cliente;
                break;
            }
        }
        Servico servicoEditado = new Servico(idServico,itensAdicionais, motivo, primeiroDiagnostico, servicoRealizado, testesRealizados, dataRecebimento, dataEntrega, prazo, valor, clienteEncontrado, equipamento);
        return servicoEditado;
    }

        //Equipamento
        //editarequipamento
    public Servico trocarEquipamento(Servico servico, ArrayList<Equipamento> listaEquipamentos) {

        int idEquipamento = Integer.parseInt(JOptionPane.showInputDialog("Qual o ID do equipamento corrento?"));
        Equipamento equipamentoEncontrado = null;
        for (Equipamento equipamento : listaEquipamentos) {
            if (servico.getEquipamento() == idEquipamento) {
                equipamentoEncontrado = equipamento;
                break;
            }
        }
        Servico servicoEditado = new Servico(idServico,itensAdicionais, motivo, primeiroDiagnostico, servicoRealizado, testesRealizados, dataRecebimento, dataEntrega, prazo, valor, cliente, equipamentoEncontrado);
        return servicoEditado;
    }

    //Metodos de exceções

    //Exceções para inteiros
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

    //Exceções para double
    public static double getDoubleInput(String prompt) {
        double value = -1;
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

    //Exceções para datas
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
                        "Formato de data inválido. Certifique-se de usar o padrão (dd/MM/yyyy)");
            }
        }
        return value;
    }

    //Exceções para String
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

    //Metodos da Classe

    //Cadastrar Serviços
    public static void cadastrarServico(ArrayList<Cliente> listaClientes,ArrayList<Equipamento> listaEquipamentos, ArrayList<Servico> listaServico, SimpleDateFormat sdf) {
        // Solicita ao usuário que insira os detalhes do serviço
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
                //Método cadastrar equipamento
            int idEquipamento = getIntegerInput("Digite o ID do Equipamento:");
            if (idEquipamento == -1) {
                return;
            }
            Equipamento equipamentoEncontrado = null;
                for (Equipamento equipamento : listaEquipamentos) {
                    if (equipamento.getIdEquipamento() == idEquipamento) {
                    equipamentoEncontrado = equipamento;
                    break;
                }
        }
             /*    //Cadastro do ID da Garantia
                int idGarantia = Integer.parseInt(JOptionPane.showInputDialog("ID da Garantia:"));
                if (idGarantia == -1) {
                    return;
                }*/
                //Cadastro itens adicionais
                String itensAdicionais = getStringInput("Itens Adicionais:");
                if (itensAdicionais == null) {
                    return;
                }

                //Cadastro motivo
                String motivo = getStringInput("Motivo:");
                if (motivo == null) {
                    return;
                }

                //Cadastro primeiro diagnóstico
                String primeiroDiagnostico = getStringInput("Primeiro Diagnóstico:");
                if (primeiroDiagnostico == null) {
                    return;
                }

                //Cadastro serviço realizado
                String servicoRealizado = getStringInput("Serviço Realizado:");
                if (servicoRealizado == null) {
                    return;
                }

                //Cadastro testes realizados
                String testesRealizados = getStringInput("Testes Realizados:");
                if (testesRealizados == null) {
                    return;
                }

                // Cadastro da data do recebimento
                Date dataRecebimento = getDateInput("Data de Recebimento (dd/MM/yyyy):", sdf);
                if (dataRecebimento == null) {
                    return;
                }
                // Cadastro da data de entrega
                Date dataEntrega = getDateInput("Data de Entrega (dd/MM/yyyy):", sdf);
                if (dataEntrega == null) {
                    return;
                }
                // Cadastro do prazo de entrega
                Date prazo = getDateInput("Prazo (dd/MM/yyyy):", sdf);
                if (prazo == null) {
                    return;
                }
                // Cadastro do valor
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor do Serviço:"));
                if (valor == -1) {
                    return;
            }
            // Adicionar o novo serviço à lista de serviços
            Servico servicoCadastrar = new Servico(idAutomatico,itensAdicionais, motivo, primeiroDiagnostico, servicoRealizado, testesRealizados, dataRecebimento, dataEntrega, prazo, valor, clienteEncontrado, equipamentoEncontrado);
            listaServico.add(servicoCadastrar);
            JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
    }

    //editar Serviços
    public static void editarServico(ArrayList<Cliente> listaClientes, ArrayList<Equipamento> listaEquipamentos, ArrayList<Servico> listaServico) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean encontrado = false;
        Servico servicoEditar = null;


        if (listaServico.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum serviço cadastrado.");
        } else {
            listarServico(listaServico);
            int idEditar = Integer.parseInt(JOptionPane.showInputDialog("Qual o ID do serviço que deseja editar?"));
            if (idEditar == -1) {
                return;
            }
            for (Servico servico : listaServico) {
                if (servico.getIdServico() == idEditar) {
                    servicoEditar = servico;
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                boolean edicaoConcluida = false;

                while (!edicaoConcluida) {
                    int opcaoEditar;

                    String[] opcoesEditar = {"Editar ID do Serviço", "Editar ID do Equipamento", "Trocar Cliente", "Editar ID da Garantia",
                            "Editar itens adicionais", "Editar motivo", "Editar primeiro diagnóstico", "Editar serviço realizado", "Editar a data de recebimento",
                            "Editar a data de entrega", "Editar o prazo", "Editar o valor", "Sair"};
                    opcaoEditar = JOptionPane.showOptionDialog(
                            null,
                            "Escolha uma opção para edição:",
                            "Editar serviço",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            opcoesEditar,
                            opcoesEditar[0]
                    );

                    switch (opcaoEditar) {

                        case 0:// Edição do ID do serviço
                            int novoIdServico = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ID do Serviço:"));
                            if (novoIdServico != -1) {
                                servicoEditar.setIdServico(novoIdServico);
                            }
                            break;

                        case 1:    //Edição do ID do equipamento
                            int trocaEquipamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ID do Equipamento:"));
                            if (trocaEquipamento != -1) {
                                //Servico.trocarCliente(servicoEditar,listaClientes);
                            }
                            break;
                        case 2:    //Trocar Cliente
                            int trocaCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ID do Cliente:"));
                            if (trocaCliente != -1) {
                                //Servico.trocarCliente(servicoEditar,listaClientes);
                            }
                            break;

                       /* case 3: //Edição do ID da Garantia
                            int novoIdGarantia = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ID da Garantia:"));
                            if (novoIdGarantia != -1) {
                                servicoEditar.setIdGarantia(novoIdGarantia);
                            }
                            break;*/

                        case 3: //Edição itens adicionais
                            String novoItensAdicionais = JOptionPane.showInputDialog("Edite os itens adicionais:");
                            servicoEditar.setItensAdicionais(novoItensAdicionais);
                            break;

                        case 4://Edição motivo
                            String novoMotivo = JOptionPane.showInputDialog("Altere os motivos:");
                            servicoEditar.setMotivo(novoMotivo);
                            break;

                        case 5://Edição primeiro diagnóstivo
                            String novoDiagnostico = JOptionPane.showInputDialog("Altere o primeiro diagnóstico:");
                            servicoEditar.setPrimeiroDiagnostico(novoDiagnostico);
                            break;

                        case 6://Edição serviço realizado
                            String novoServicoRealizado = JOptionPane.showInputDialog("Altere os serviços realizados:");
                            servicoEditar.setServicoRealizado(novoServicoRealizado);
                            break;

                        case 7://Edição testes realizados
                            String novoTesteRealizado = JOptionPane.showInputDialog("Altere os testes realizados:");
                            servicoEditar.setTestesRealizados(novoTesteRealizado);
                            break;

                        case 8:// Edição do valor
                            double novoValorServico = Double.parseDouble(JOptionPane.showInputDialog("Novo valor do serviço:"));
                            if (novoValorServico != -1) {
                                servicoEditar.setValor(novoValorServico);
                            }
                            break;

                        case 9: //Edição da data de recebimento
                            Date novaDataRecebimento = getDateInput(
                            "Nova data de recebimento (dd/MM/yyyy): ", sdf);
                            if (novaDataRecebimento != null) {
                                servicoEditar.setDataRecebimento(novaDataRecebimento);
                            }
                            break;                            

                        case 10: //Edição da data de entrega
                            Date novaDataEntrega = getDateInput(
                            "Nova data de entrega (dd/MM/yyyy):", sdf);
                            if (novaDataEntrega != null) {
                                servicoEditar.setDataRecebimento(novaDataEntrega);
                            }
                            break;          
                           
                        case 11:
                            JOptionPane.showMessageDialog(null, "Edição concluída.");
                            edicaoConcluida = true;
                            break;
                    }

                    JOptionPane.showMessageDialog(null, "Serviço editado!");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Serviço não existe.");
            }
        }
    }

    //Excluir Serviço
    public static boolean excluirServico(int idServico, ArrayList<Servico> listaServicos) {
        for (Servico servico : listaServicos) {
            if (servico.getIdServico() == idServico) {
                listaServicos.remove(servico);
                return true; // Retorna true se o serviço foi excluído
            }
        }
        return false; // Retorna false se o serviço não foi encontrado
    }

    //Listar Serviço
    public static void listarServico(ArrayList<Servico> listaServicos ) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (listaServicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum serviço cadastrado.");
        } else {
            for (Servico servico : listaServicos) {
                JOptionPane.showMessageDialog(null, "ID: " + servico.getIdServico() +
                        "\nID do Equipamento: " + servico.getEquipamento() +
                        "\nCliente: " + servico.getCliente() +
                     //   "\nID da Garantia: " + servico.getIdGarantia() +
                        "\nItens Adicionais: " + servico.getItensAdicionais() +
                        "\nMotivo: " + servico.getMotivo() +
                        "\nPrimeiro Diagnóstico: " + servico.getPrimeiroDiagnostico() +
                        "\nServiço Realizado: " + servico.getServicoRealizado() +
                        "\nTestes Realizados" + servico.getTestesRealizados() +
                        "\nData de Recebimento: " + sdf.format(servico.getDataRecebimento()) +
                        "\nData de Entrega: " + sdf.format(servico.getDataEntrega()) +
                        "\nPrazo: " + servico.getPrazo() +
                        "\nValor: R$" + servico.getValor());
            }
        }
    }

    @Override
    public String toString() { //formatar, organizar o método Listar.
        return "Servico{" +
                "idServico=" + idServico +
                ", idEquipamento=" + equipamento.getIdEquipamento() +
                ", cliente=" + cliente.getNome() +
                '}';
    }
}