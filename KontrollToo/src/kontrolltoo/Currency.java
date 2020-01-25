/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolltoo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alexander Chelpanov
 * Date: 07.12.2019
 * 
 * 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "Currency","CurrencyFullName","Country","Region", "SubUnit", "Symbol"})
public class Currency implements Serializable {
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    
    @XmlElement(name = "Currency")
    private String Currency;
    @XmlElement(name = "CurrencyFullname")
    private String CurrencyFullName;
    @XmlElement(name = "Country")
    private String Country;
    @XmlElement(name = "Region")
    private String Region;
    @XmlElement(name = "SubUnit")   
    private String SubUnit;
    @XmlElement(name = "Symbol")
    private String Symbol;

    public Currency() {
    }

    public Currency(String Currency, String CurrencyFullName, String Country, String Region, String SubUnit, String Symbol) {
        this.Currency = Currency;
        this.CurrencyFullName = CurrencyFullName;
        this.Country = Country;
        this.Region = Region;
        this.SubUnit = SubUnit;
        this.Symbol = Symbol;
    }
    
    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public String getCurrencyFullName() {
        return CurrencyFullName;
    }

    public void setCurrencyFullName(String CurrencyFullName) {
        this.CurrencyFullName = CurrencyFullName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getSubUnit() {
        return SubUnit;
    }

    public void setSubUnit(String SubUnit) {
        this.SubUnit = SubUnit;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    @Override
    public String toString() {
        return BLUE + "Currency: "+ RESET + Currency + BLUE + " CurrencyFullName: " 
                + RESET + CurrencyFullName + BLUE + " Country: " + RESET + Country
                + BLUE + " Region: " + RESET + Region + BLUE + " SubUnit: " 
                + RESET + SubUnit + BLUE + " Symbol: " + RESET + Symbol;
    }
    
    
}
