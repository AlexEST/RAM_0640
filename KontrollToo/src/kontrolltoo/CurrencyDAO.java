/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolltoo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class CurrencyDAO {
    public static List<Currency> currencies = new ArrayList<>();
    
    static{
       BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("currency.txt"), "CP1251"));
            String Line;
            int skip = 0;
            while ((Line = reader.readLine()) != null) {
                String[] line = Line.split(",");
                if (skip > 0) {
                    Currency currency = new Currency(line[0], line[1], line[2], line[3],
                            line[4], line[5]);
                    currencies.add(currency);
                }
                skip = 1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CurrencyDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CurrencyDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(CurrencyDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void toConsole() {
        currencies.stream().forEach(a -> System.out.println(a));
    }
    
}
