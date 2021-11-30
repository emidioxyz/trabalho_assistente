package trabalho_assistente;

import jandl.wizard.WizardBase;
import jandl.wizard.WizardFactory;
import javax.swing.SwingUtilities;
import jandl.wizard.WizardText;
import jandl.wizard.Data;


public class Assistente {

    public static void main(String[] args) {
    
        //Criando as janelas
        
        //Janela 1 - Apresentação
        WizardBase janela1 = WizardFactory.createBase("Assistente");
        janela1.setImage("TELA1.1.png");
        
        //Janela 2 - Coletando informações do aluno(a)1
       //Criando campos de interação
        String[] tags = {"Nome","Telefone","Email","Vestibular"};
        String[] label = {"Digite seu nome completo: ","Digite seu número de telefone: ","Digite seu email:","Digite qual foi a sua nota do vestibular: "};
        String[] tips = {"Seu nome", "Seu telefone","Seu email","Nota Vestibular"};
        WizardBase janela2 = WizardFactory.createField("Coleta Dados 1", tags, label, tips);
        janela2.setImage("Dadoscoleta.png");
        
        //Janela 3 - Coletando informações do aluno(a)2
        //Criando campos de interação
        String[] tags3 = {"Nome","Telefone","Email","Vestibular"};
        String[] label3 = {"Digite seu nome completo: ","Digite seu número de telefone: ","Digite seu email:","Digite qual foi a sua nota do vestibular: "};
        String[] tips3 = {"Seu nome", "Seu telefone","Seu email","Nota Vestibular"};
        WizardBase janela3 = WizardFactory.createField("Coleta Dados 2", tags3, label3, tips3);
        janela3.setImage("Dadoscoleta.png"); 
        
        //Janela 4 Mostrando todas as informações dos alunos(a)
        WizardBase janela4 = WizardFactory.createText("Informações dos alunos","TDados.png", true);
        
        //Janela 5 - Analisando se os alunos(a) foi aprovado ou reprovado
        WizardBase janela5 = WizardFactory.createText("Analisando notas","MAR.png", true);
       
        //Janela 6 - Finalização do trabalho
        WizardBase janela6 = WizardFactory.createBase("Fim");
        janela6.setImage("Nomes dos Alunos.png");
        
        
        
        //Encadeamento das janelas
        janela1.nextWizard(janela2).nextWizard(janela3).nextWizard(janela4).nextWizard(janela5).nextWizard(janela6);
        
        //Ativando a aplicação
        SwingUtilities.invokeLater(()-> janela1.setVisible(true));
        
        //Pós processamento
        janela2.addPostProcessor((wiz)->janela2PostProcessor(wiz));
        janela3.addPostProcessor((wiz)->janela3PostProcessor(wiz));
        
        //Pré processamento
        janela4.addPreProcessor((wiz)->janela4PreProcessor(wiz));
        janela5.addPreProcessor((wiz)->janela5PreProcessor(wiz));
        
            
        
    }

    private static void janela2PostProcessor(WizardBase wiz) {
        
        Data data = Data.instance();
        String nome = data.getAsString("Wizard2.fieldPane0.Nome");
        String telefone = data.getAsString("Wizar2.fieldPane0.Telefone");
        String email = data.getAsString("Wizard2.fieldPane0.Email");
        Double vestibular = data.getAsDouble("Wizard2.fieldPane0.Vestibular");
       
    }
    
    private static void janela3PostProcessor(WizardBase wiz) {
        
        Data data = Data.instance();
        String nome = data.getAsString("Wizard2.fieldPane0.Nome");
        String telefone = data.getAsString("Wizar2.fieldPane0.Telefone");
        String email = data.getAsString("Wizard2.fieldPane0.Email");
        Double vestibular = data.getAsDouble("Wizard2.fieldPane0.Vestibular");
       
    }

    private static void janela4PreProcessor(WizardBase wizard) {
        
        Data data = Data.instance();
        
        //Aluno(a) 1
        String nome1 = data.getAsString("Wizard2.fieldPane0.Nome");
        String telefone1 = data.getAsString("Wizar2.fieldPane0.Telefone");
        String email1 = data.getAsString("Wizard2.fieldPane0.Email");
        Double vestibular1 = data.getAsDouble("Wizard2.fieldPane0.Vestibular");
        
        //Aluno(a) 2
        String nome2 = data.getAsString("Wizard3.fieldPane0.Nome");
        String telefone2  = data.getAsString("Wizar3.fieldPane0.Telefone");
        String email2 = data.getAsString("Wizard3.fieldPane0.Email");
        Double vestibular2 = data.getAsDouble("Wizard3.fieldPane0.Vestibular");
        
        WizardText wizardtext = (WizardText)wizard;
        wizardtext.append("\n=============================================");
        wizardtext.append("\n\tInformações dos Alunos");
        wizardtext.append("\n=============================================");
        wizardtext.append("\n=============================================");
        wizardtext.append("\nAluno(a) 1");
        wizardtext.append("\nNome: "+data.get("Wizard2.fieldPane0.Nome"));
        wizardtext.append("\nTelefone: "+data.get("Wizard2.fieldPane0.Telefone"));
        wizardtext.append("\nEmail: "+data.get("Wizard2.fieldPane0.Email"));
        wizardtext.append("\nVestibular: "+data.get("Wizard2.fieldPane0.Vestibular"));
        wizardtext.append("\n=============================================");
        wizardtext.append("\n=============================================");
        wizardtext.append("\nAluno(a) 2");
        wizardtext.append("\nNome: "+data.get("Wizard3.fieldPane0.Nome"));
        wizardtext.append("\nTelefone: "+data.get("Wizard3.fieldPane0.Telefone"));
        wizardtext.append("\nEmail: "+data.get("Wizard3.fieldPane0.Email"));
        wizardtext.append("\nVestibular: "+data.get("Wizard3.fieldPane0.Vestibular"));
        wizardtext.append("\n=============================================");
    }

    private static void janela5PreProcessor(WizardBase wiz) {
        
        Data data = Data.instance();
        
        WizardText wizardtext = (WizardText)wiz;
        wizardtext.append("\n=============================================");
        wizardtext.append("\nA nota mínima para cursar é de 460 pontos");
        wizardtext.append("\n=============================================");
        wizardtext.append("\n=============================================");
        
        //Verificando se o aluno(a) 1 foi aprovado
        wizardtext.append("\nAluno(a) 1: "+data.get("Wizard2.fieldPane0.Nome"));
        wizardtext.append("\nA nota do vestibular foi de: "+data.get("Wizard2.fieldPane0.Vestibular"));
        Double notaAluno1 = data.getAsDouble("Wizard2.fieldPane0.Vestibular");
        if(notaAluno1 >= 460)
            wizardtext.append("\nVocê foi aprovado!");
        else
            wizardtext.append("\nVocê foi reporvado! \nTente novamente no próximo vestibular");
        
        wizardtext.append("\n=============================================");
        wizardtext.append("\n=============================================");
        wizardtext.append("\n=============================================");
        
        //Verificando se o aluno(a) 2 foi aprovado
        wizardtext.append("\nAluno(a) 2: "+data.get("Wizard3.fieldPane0.Nome"));
        wizardtext.append("\nA nota do vestibular foi de: "+data.get("Wizard3.fieldPane0.Vestibular"));
        Double notaAluno2 = data.getAsDouble("Wizard3.fieldPane0.Vestibular");
        if(notaAluno2 >= 460)
            wizardtext.append("\nVocê foi aprovado!");
        else
            wizardtext.append("\nVocê foi reprovado! \nTente novamente no próximo vestibular!");
        
        wizardtext.append("\n=============================================");
    }
}