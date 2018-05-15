package br.com.rocha.teste;

import br.com.rocha.infracoes.dao.AutoInfracaoDao;
import br.com.rocha.infracoes.modelo.Infracao;
import br.com.rocha.infracoes.modelo.AutoInfracao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author DaRocha
 */
public class NotificacaoTeste {
    @Test
    //@Ignore
    public void filtrarData() throws ParseException {
        AutoInfracaoDao dao = new AutoInfracaoDao();
        AutoInfracao notificacao = new AutoInfracao();
        List<AutoInfracao> list = new ArrayList<>();
        
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Date dataInicial = (Date)formatter.parse("01/04/2018");  
        Date dataFinal = (Date)formatter.parse("30/04/2018");
        
       
        
        System.out.println(list);
        
    }
}
