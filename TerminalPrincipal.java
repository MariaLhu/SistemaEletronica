import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalPrincipal {

    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<Recibo> listaRecibos = new ArrayList<Recibo>();
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
        ArrayList<Garantia> listaGarantias = new ArrayList<Garantia>();
        ArrayList<Servico> listaServicos = new ArrayList<Servico>();

        boolean loop = true;
        
        while (loop) {           
            
            int opcao;

            String[] opcoes = {"Recibo", "Equipamento", "Garantia", "Servico", "Cliente", "Sair"};
            opcao = JOptionPane.showOptionDialog(
                    null,
                    "Escolha qual módulo deseja usar:",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]);

            switch (opcao) {
                case 0: // Recibo
                    escolhaReciboOpcoes(listaRecibos, sdf);
                    break;
                case 1: // Equipamento
                    escolhaEquipamentoOpcoes(listaClientes, listaEquipamentos);
                    break;
                case 2: // Garantia
                    escolhaGarantiaOpcoes(listaClientes, listaServicos, listaGarantias);
                    break;
                case 3: // Servico
                    escolhaServicoOpcoes(listaClientes, listaEquipamentos, listaServicos, sdf);
                    break;
                case 4: // Cliente
                    escolhaClienteOpcoes(listaClientes);
                    break;          

                case 5: // Sair
                    JOptionPane.showMessageDialog(null, "Programa encerrado.");
                    loop = false;
                    break;
            }
        }
    }

    private static void escolhaReciboOpcoes(ArrayList<Recibo> listaRecibos, SimpleDateFormat sdf) throws ParseException {
        boolean reciboLoop = true;

        while (reciboLoop) {
            int reciboOpcao;

            String[] reciboOpcoes = {"Criar um novo recibo", "Listar os recibos cadastrados", "Editar um recibo cadastrado",
                    "Emitir um recibo em especifico", "Voltar"};
            reciboOpcao = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção de Recibo:",
                    "Menu Recibo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    reciboOpcoes,
                    reciboOpcoes[0]);

            switch (reciboOpcao) {
                case 0:
                    Recibo.criarNovoRecibo(listaRecibos, sdf);
                    break;

                case 1:
                    Recibo.listarRecibo(listaRecibos);
                    break;

                case 2:
                    Recibo.editarRecibo(listaRecibos);
                    break;

                case 3:
                    int idDesejado = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do Recibo que deseja:"));
                    Recibo.emitirRecibo(idDesejado, listaRecibos);
                    break;

                case 4:
                    reciboLoop = false;
                    break;
            }
        }
    }

    private static void escolhaEquipamentoOpcoes(ArrayList<Cliente> listaClientes, ArrayList<Equipamento> listaEquipamentos) {
        boolean equipamentoLoop = true;

        while (equipamentoLoop) {
            int equipamentoOpcao;

            String[] equipamentoOpcoes = {"Criar novo equipamento", "Editar equipamento", "Listar Equipamentos",
                    "Excluir equipamento", "Voltar"};
            equipamentoOpcao = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção de Equipamento:",
                    "Menu Recibo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    equipamentoOpcoes,
                    equipamentoOpcoes[0]);

            switch (equipamentoOpcao) {
                case 0:
                    Equipamento.cadastrarEquipamento(listaClientes, listaEquipamentos);
                    break;

                case 1:
                    Equipamento.editarEquipamento(listaEquipamentos);
                    break;

                case 2:
                    Equipamento.listarEquipamentos(listaEquipamentos);
                    break;

                case 3:
                    int idEquipamento = Equipamento.getIntegerInput("Digite o id do Equipamento que deseja editar:");

                    Equipamento.excluirEquipamento(idEquipamento, listaEquipamentos);
                    break;

                case 4:
                    equipamentoLoop = false;
                    break;
            }
        }
    }

    private static void escolhaGarantiaOpcoes(ArrayList<Cliente> listaClientes, ArrayList<Servico> listaServicos, ArrayList<Garantia> listaGarantias ) {
        boolean garantiaLoop = true;

        while (garantiaLoop) {
            int garantiaOpcao;

            String[] garantiaOpcoes = {"Cadastrar Garantia", "Editar Garantia", "Excluir Garantia", "Listar Garantia", "Voltar"};
            garantiaOpcao = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção de Recibo:",
                    "Menu Recibo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    garantiaOpcoes,
                    garantiaOpcoes[0]);

            switch (garantiaOpcao) {
                case 0:
                    Garantia.cadastrarGarantia(listaGarantias, listaServicos);
                    break;

                case 1:
                    Garantia.editarGarantia(listaGarantias, listaServicos);
                    break;

                case 2:
                    int idExcluir = Garantia.getIntegerInput("Digite o id do Servico que deseja:");
                    if (idExcluir == -1) {
                        break;
                    }
                    Garantia.excluirGarantia(idExcluir, listaGarantias);
                    break;

                case 3:
                    Garantia.listarGarantia(listaGarantias);
                    break;

                case 4:
                    garantiaLoop = false;
                    break;
            }
        }

    }
  
    private static void escolhaUsuarioOpcoes(ArrayList<Cliente> listaClientes, ArrayList<Equipamento> listaEquipamentos) {

    }

    private static void escolhaServicoOpcoes(ArrayList<Cliente> listaClientes,ArrayList<Equipamento> listaEquipamentos, ArrayList<Servico> listaServicos, SimpleDateFormat sdf) {
        boolean servicoLoop = true;

        while (servicoLoop) {
            int servicoOpcao;

            String[] servicoOpcoes = {"Cadastrar Servico", "Editar Servico", "Excluir Servico",
                    "Listar Servico", "Voltar"};
            servicoOpcao = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção de Servico:",
                    "Menu Servico",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    servicoOpcoes,
                    servicoOpcoes[0]);

            switch (servicoOpcao) {
                case 0:
                    Servico.cadastrarServico(listaClientes, listaEquipamentos, listaServicos, sdf);
                    break;

                case 1:
                    Servico.editarServico(listaClientes, listaEquipamentos, listaServicos);
                    break;

                case 2:
                    int idExcluir = Servico.getIntegerInput("Digite o id do Servico que deseja:");
                    if (idExcluir == -1) {
                        break;
                    }
                    Servico.excluirServico(idExcluir, listaServicos);

                    break;

                case 3:
                    Servico.listarServico(listaServicos, listaEquipamentos);
                    break;

                case 4:
                    servicoLoop = false;
                    break;
            }
        }

    }

    private static void escolhaClienteOpcoes(ArrayList<Cliente> listaClientes) {
        boolean clienteLoop = true;

        while (clienteLoop) {
            int clienteOpcao;

            String[] clienteOpcoes = {"Cadastrar Cliente", "Editar Cliente", "Excluir Cliente",
                    "Listar Cli", "Voltar"};
            clienteOpcao = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção de Recibo:",
                    "Menu Recibo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    clienteOpcoes,
                    clienteOpcoes[0]);

            switch (clienteOpcao) {
                case 0:
                    Cliente.cadastrarCliente(listaClientes);
                    break;

                case 1:
                    int idCliente = Cliente.getIntegerInput("Digite o id do Cliente que deseja:");
                    if (idCliente == -1) {
                        break;
                    }
                    Cliente.editarCliente(listaClientes);
                    break;

                case 2:
                    int idExcluir = Cliente.getIntegerInput("Digite o id do Cliente que deseja:");
                    if (idExcluir == -1) {
                        break;
                    }
                    Cliente.excluirCliente(idExcluir, listaClientes);
                    break;

                case 3:
                    Cliente.listarCliente(listaClientes);
                    break;

                case 4:
                    clienteLoop = false;
                    break;
            }
        }

    }

}
