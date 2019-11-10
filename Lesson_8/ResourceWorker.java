package Les7;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ResourceWorker {
    public CurrencyInfo getCurrencyByDate(String code, String date) throws ParserConfigurationException, SAXException, UserException, IOException;
    public CurrencyInfo[] getCurrenciesByDate(String date) throws ParserConfigurationException, SAXException, UserException, IOException;
}
