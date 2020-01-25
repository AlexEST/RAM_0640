/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolltoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author Alex
 */
public class JSONWriter {
    public static void writeJSON(List<Currency> list ) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File("currency.json"),list);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("JSON записан!");
    }   
}
