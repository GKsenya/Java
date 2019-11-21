package Les7;

import kong.unirest.Unirest;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class CBResourceWorker implements ResourceWorker {

    private CurrencyInfo[] currencyInfoResult = new CurrencyInfo[1];
    private CurrencyInfo[] currenciesInfoResult;
    private String path = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    private NodeList getCurrencyConnection(String date)  throws UserException, ParserConfigurationException, IOException, SAXException {
        File out = Unirest.get(path+date)
                .asFile("FCM.xml")
                .getBody();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("FCM.xml"));

        NodeList currencyElements = document.getDocumentElement().getElementsByTagName("Valute");
        out.delete();
        if(currencyElements.getLength() == 0) {
            throw new UserException("Прогнозирование курса на данный момент недоступно");
        }else{
            return currencyElements;
        }
    }

    @Override
    public CurrencyInfo[] getCurrenciesByDate(String code, String date) throws ParserConfigurationException, SAXException, UserException, IOException {
        NodeList currencyElements = getCurrencyConnection(date);
        int currencyElementsCount = currencyElements.getLength();
        currenciesInfoResult = new CurrencyInfo[currencyElementsCount];
        for (int i = 0; i < currencyElementsCount; i++) {
            Node currency = currencyElements.item(i);
            String charCode = currency.getChildNodes().item(1).getTextContent();
            NodeList childrenNodes = currency.getChildNodes();
            currenciesInfoResult[i] = new CurrencyInfo(childrenNodes.item(1).getTextContent(),
                                                  Integer.valueOf(childrenNodes.item(2).getTextContent()),
                                                  childrenNodes.item(3).getTextContent(),
                                                  Float.valueOf(childrenNodes.item(4).getTextContent().replace(",", ".")));
        }
        if(code.equalsIgnoreCase("all")){
            return this.currenciesInfoResult;
        }
        for(CurrencyInfo currency: this.currenciesInfoResult){
            if(currency.getCharCod().equalsIgnoreCase(code)){
                this.currencyInfoResult[0] = currency;
                return this.currencyInfoResult;
            }
        }
        throw new UserException("роверьет правильность введенных данных и повторите попытку.");
    }
}
