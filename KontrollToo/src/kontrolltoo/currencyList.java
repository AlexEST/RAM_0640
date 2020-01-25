/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolltoo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */

@XmlRootElement( name = "currencyList")
@XmlAccessorType(XmlAccessType.FIELD)
public class currencyList {
    @XmlElement(name = "Currency")
    private List<Currency> currencyList = new ArrayList<>();

    public currencyList() {
    }
    
    public currencyList(List<Currency> list) {
        this.currencyList = list;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    } 
}
