import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TerminalExperimentalRecibo {

    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<Recibo> listaRecibos = new ArrayList<Recibo>();
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();

        boolean loop = true;

        while (loop == true) {
            int opcao;

            String[] opcoes = { "Criar um novo recibo", "Listar os recibos cadastrados", "Editar um recibo cadastrado",
                    "Emitir um recibo em especifico", "Sair","Criar equipamento"};
            opcao = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]);

            switch (opcao) {
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
                    JOptionPane.showMessageDialog(null, "Programa encerrado.");
                    loop = false;
                    break;
                case 5:
                    Cliente cliente = new Cliente(0, "PF","Maria Teresinha","666666","Rua das Andorinhas", "69999999666");
                    listaClientes.add(cliente);
                    Equipamento.cadastrarEquipamento(listaClientes, listaEquipamentos);
                    Equipamento.listarEquipamentos(listaEquipamentos);
                    break;

            }
        }
    }
}