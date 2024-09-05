//import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.GridLayout;
import java.text.ParseException;
//import java.util.Base64; //NOTE: Base64.Encoder() and Base64.Decoder() are deprecated in Java 9
import java.security.NoSuchAlgorithmException;

public class cadastro {
    
    //To define the file are using
    public static String fileName = "userHash.csv";

    public static void Register_painel() {
        // Criando os campos de texto
        JTextField nomeField = new JTextField(10);
        JTextField emailField = new JTextField(10);

        // Criando os campos formatados
        JFormattedTextField idadeField = createFormattedField("##");
        JFormattedTextField cpfField = createFormattedField("###.###.###-##"); // Marca # representa digitos U para letras maiúsculas e L minúsculas
        JFormattedTextField celularField = createFormattedField("(##)#####-####");
        JFormattedTextField cepField = createFormattedField("#####-###");

        //Criando os campos da senha
        JPasswordField passwordField = new JPasswordField(10);
        JPasswordField ConfirmPasswordField = new JPasswordField(10);

        // Criando um painel para organizar os campos
        JPanel panel = new JPanel(new GridLayout(20, 20));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Idade:"));
        panel.add(idadeField);
        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Celular:"));
        panel.add(celularField);
        panel.add(new JLabel("CEP:"));
        panel.add(cepField);
        panel.add(new JLabel("Senha:"));
        panel.add(passwordField);
        panel.add(new JLabel("Confirmar Senha:"));
        panel.add(ConfirmPasswordField);

        // Exibindo o painel em uma caixa de diálogo
        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Coletando os dados
            String nome = nomeField.getText().trim();
            String idade = idadeField.getText().trim();
            String cpf = cpfField.getText().trim();
            String email = emailField.getText().trim();
            String celular = celularField.getText().trim();
            String cep = cepField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(ConfirmPasswordField.getPassword()).trim();

            // Verificando se algum campo está vazio
            if (nome.isEmpty() || idade.isEmpty() || cpf.isEmpty() || email.isEmpty() || celular.isEmpty() || cep.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Erro: Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) { //compara as senhas para verificar se são iguais ou não
                JOptionPane.showMessageDialog(null, "Erro: As senhas nao coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                /*//encode password whit Base64
                byte[] encodePass = Base64.getEncoder().encode(password.getBytes());
                String encodedStringPass = new String(encodePass);*/

                //hashing password
                String encodedStringPass = "";
                try {
                    encodedStringPass = hashing.hashString(password);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                // Exibindo os dados para verificação
                String dados = "Verifique seus dados:\n" +"Nome: " + nome + "\n" +"Idade: " + idade + "\n" +"CPF: " + cpf + "\n" +"Email: " + email + "\n" +"Celular: " + celular + "\n" +"CEP: " + cep + "\n";
                //"Senha: [Oculta]";
                // Confirma cadastro
                int confirm = JOptionPane.showConfirmDialog(null, dados, "Confirme seus Dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(confirm == JOptionPane.OK_OPTION) {
                    // Dados confirmados
                    JOptionPane.showMessageDialog(null, "Cadastro concluido com sucesso!");                    
                    //System.out.println("Here 1"); //LOG
                    int id = arquivo.nextId(fileName); //retire the last id from the file
                    int idade_int=Integer.parseInt(idade);
                    User user = new User(id, nome, idade_int, cpf,email,celular,cep,encodedStringPass);

                    //save csv
                    arquivo.user_saveCsv(user,fileName);

                } else {
                    // Usuário optou por cancelar
                    JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                }
            }
        }
    }
    // Método auxiliar para criar campos formatados
    private static JFormattedTextField createFormattedField(String format) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter(format);
            maskFormatter.setPlaceholderCharacter('_');  // Definindo um caractere de espaço reservado
            return new JFormattedTextField(maskFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String [] args){
        Register_painel();

        //hashing all passwords in the file / when the password wasnt hashed /  !!!!dont use again!!!!
        //arquivo.hashPassCsv(arquivo.nextId("user.csv")-1);
    }
}
