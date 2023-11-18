
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TerminalExperimentalServico{
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<Servico> listaServico = new ArrayList<Servico>();
        boolean loop = true;

        while (loop == true) {
            int opcao;   
        
            String[] opcoes = {"Cadastrar Novo Serviço", "Editar Serviço Cadastrado", "Listar Serviços Cadastrados","Excluir Serviço Cadastrado","Sair"};
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
                    Servico.cadastrarServico(listaServico,sdf);
                    break;

                case 1:
                    Servico.editarServico(listaServico);
                    break;    
                
                case 2:
                    Servico.listarServico(listaServico);
                    break;
                    
                case 3:
                    int idParaExcluir = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do serviço a ser excluído:"));
                    boolean excluido = Servico.excluirServico(idParaExcluir, listaServico);
                    if (excluido) {
                    JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!");
                         } else {
                           JOptionPane.showMessageDialog(null, "Serviço não encontrado. Nada foi excluído.");
                        }
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Programa encerrado.");
                    loop = false;
                    break;
            }
        }

        ler.close();

    }
}