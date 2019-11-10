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

    private CurrencyInfo currencyInfoResult;
    private CurrencyInfo[] currencysInfoResult;
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
    public CurrencyInfo[] getCurrenciesByDate(String date) throws ParserConfigurationException, SAXException, UserException, IOException {
        NodeList currencyElements = getCurrencyConnection(date);
        int currencyElementsCount = currencyElements.getLength();
        currencysInfoResult = new CurrencyInfo[currencyElementsCount];
        for (int i = 0; i < currencyElementsCount; i++) {
            Node currency = currencyElements.item(i);
            String charCode = currency.getChildNodes().item(1).getTextContent();
            NodeList childrenNodes = currency.getChildNodes();
            currencysInfoResult[i] = new CurrencyInfo(childrenNodes.item(1).getTextContent(),
                                                  Integer.valueOf(childrenNodes.item(2).getTextContent()),
                                                  childrenNodes.item(3).getTextContent(),
                                                  Float.valueOf(childrenNodes.item(4).getTextContent().replace(",", ".")));
        }
        return this.currencysInfoResult;
    }

    @Override
    public CurrencyInfo getCurrencyByDate(String code, String date) throws ParserConfigurationException, SAXException, UserException, IOException {
        NodeList currencyElements = getCurrencyConnection(date);
        for (int i = 0; i < currencyElements.getLength(); i++) {
            Node currency = currencyElements.item(i);
            String charCode = currency.getChildNodes().item(1).getTextContent();

            if (charCode.equalsIgnoreCase(code)) {
                NodeList childrenNodes = currency.getChildNodes();
                this.currencyInfoResult = new CurrencyInfo(childrenNodes.item(1).getTextContent(),
                        Integer.valueOf(childrenNodes.item(2).getTextContent()),
                        childrenNodes.item(3).getTextContent(),
                        Float.valueOf(childrenNodes.item(4).getTextContent().replace(",", ".")));
                break;
            }
            if(i == currencyElements.getLength()-1){
                throw new UserException("Проверьте корректность введенного CharCode и повторите ввод. Приме идентификатора: USD, JPY и т.д.");
            }
        }
        return this.currencyInfoResult;
    }

}
