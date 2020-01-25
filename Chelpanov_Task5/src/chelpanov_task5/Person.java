/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chelpanov_task5;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;






/**
 *
 * @author Alexander Chelpanov
 * Date: 10.11.2019
 * 
 * @JsonAutoDetect - аннотация для джексона чтобы видел все поля кроме геттеров и
 * сеттеров, а то при сериализации дубликаты появляются.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Person implements java.io.Serializable{
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    
    private int id; 
    private String Lastname;
    private String Firstname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")//паттерн по которому считываем дату
    @JsonDeserialize(using = LocalDateDeserializer.class)//используем при считывании
    @JsonSerialize(using = LocalDateSerializer.class)// при записи
    private LocalDate BirthDay;
    List<String> Emails = new ArrayList<>();
    List<String> Languages = new ArrayList<>();

    public Person() {
    }

    public Person(int id, String Lastname, String Firstname, LocalDate BirthDay, List<String> Emails, List<String>Languages) {
        this.id = id;
        this.Lastname = Lastname;
        this.Firstname = Firstname;
        this.BirthDay = BirthDay;
        this.Emails = Emails;
        this.Languages = Languages;
    }
    
    public Person(int id, String Lastname, String Firstname, LocalDate BirthDay) {
        this.id = id;
        this.Lastname = Lastname;
        this.Firstname = Firstname;
        this.BirthDay = BirthDay;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public LocalDate getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(LocalDate BirthDay) {
        this.BirthDay = BirthDay;
    }

    public List<String> getEmails() {
        return Emails;
    }

    public void setEmails(List<String> Emails) {
        this.Emails = Emails;
    }

    public List<String> getLanguages() {
        return Languages;
    }

    public void setLanguages(List<String> Languages) {
        this.Languages = Languages;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = getBirthDay().format(formatter);
        StringBuilder sb = new StringBuilder();
		sb.append(BLUE+"          PERSON DETAILS\n"+RESET);
		sb.append(GREEN+"Id: "+RESET).append(getId()).append("\n");
		sb.append(GREEN+"Lastname: "+RESET).append(getLastname()).append("\n");
		sb.append(GREEN+"Firstname: "+RESET).append(getFirstname()).append("\n");
		sb.append(GREEN+"Birthday: "+RESET).append(date).append("\n");              
		sb.append(GREEN+"Languages: "+RESET).append(getLanguages()).append("\n");
		sb.append(GREEN+"Emails: "+RESET).append(getEmails()).append("\n");
		sb.append("----------------------------------------------");		
		return sb.toString();
    }
    
}
