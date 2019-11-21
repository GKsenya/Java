package Les7;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ResourceWorker {
    public CurrencyInfo[] getCurrenciesByDate(String code, String date) throws ParserConfigurationException, SAXException, UserException, IOException;
}
