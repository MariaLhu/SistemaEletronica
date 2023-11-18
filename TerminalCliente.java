
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalCliente {
    
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        boolean loop = true;

        while (loop == true) {
            int opcao;
        
        
            String[] opcoes = {"Cadastrar Novo Cliente", "Editar Cliente", "Listar Clientes Cadastrados","Excluir Cliente Cadastrado","Sair"};
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
                    Cliente.cadastrarCliente(listaClientes);
                    break;

                case 1:
                    Cliente.editarCliente(opcoes, listaClientes);
                    break;    
                
                case 2:
                    Cliente.listarCliente(listaClientes);
                    break;
                
                case 3:
                    int idParaExcluir = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do cliente a ser excluído:"));
                    boolean excluido = Cliente.excluirCliente(idParaExcluir, listaClientes);
                    if (excluido) {
                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
                         } else {
                           JOptionPane.showMessageDialog(null, "Cliente não encontrado. Nada foi excluído.");
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
