/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolltoo;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Alex
 */
public class XMLWriter {
    private static final String FILENAME = "CurrencyList.xml";

    public static void listToXML(List<Currency> list) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(currencyList.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(new currencyList(list), new File(FILENAME));
        System.out.println("XML записан!");
    }
}
