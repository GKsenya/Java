package Les7;

import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.IOException;

public class HelloWorld {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, UserException{

        EnteredData userData = UserWork.collectUserData();
        ResourceWorker resource = new CBResourceWorker();
        CurrencyInfo currencyInfo = resource.getCurrencyByDate(userData.getCharCode(),userData.getDate());
        CurrencyProcessor fw = new FileCurrencyProcessor(userData.getOutputPath(), userData.getDate());
        fw.saveCurrency(currencyInfo);
    }
}
