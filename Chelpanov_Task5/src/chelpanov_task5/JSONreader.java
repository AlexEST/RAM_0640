/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chelpanov_task5;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





/**
 *
 * @author Alexander Chelpanov
 * Date: 6.11.2019
 * 
 * 
 */
public class JSONreader {
    public static List getPersons(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
        List<Person> personList = new ArrayList<>();
        try {
            personList = Arrays.asList(mapper.readValue(json, Person[].class));
        } catch (IOException e) {
            System.out.println(e);
        }
        return personList;
    }   
}
