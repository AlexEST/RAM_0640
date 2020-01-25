/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Alexander Chelpanov 182730 EDTR34
 * Date: 16.09.2019
 * 
 * Все классы поместил в один файл для компактности. 
 */

public abstract class Event {
    public static final String blue = "\u001B[34m";
    public static final String reset = "\u001B[0m";
    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy");
    public String name;
    public Calendar date;
    
    public String getName() { return name; }
    public Calendar getDate() { return date; }
}

class StandartEvent extends Event{
    
    StandartEvent(){
    }
    
    StandartEvent(String name, Calendar date){
        this.name = name;
        this.date = date;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setDate(Calendar date){
        this.date = date;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public Calendar getDate(){
        return date;
    }
    
    @Override
    public String toString(){
        return blue+"Название: "+reset+getName()+blue+" Дата: "+reset
                +dateFormat.format(getDate().getTime());
    }
}

class EventReminder extends StandartEvent{
    private Calendar timeRemind;
    private int remCount;
    
    public EventReminder(String name, Calendar date, Calendar timeRemind, int remCount) {
        super.setName(name);
        super.setDate(date);
        this.timeRemind = timeRemind;
        this.remCount = remCount;
    }
    
    @Override
    public void setDate(Calendar date) {
        super.setDate(date); 
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
    
    public void setTimeRemind(Calendar timeRemind){
        this.timeRemind = timeRemind;
    }
    
    public void setRemCount(int remCount){
        this.remCount = remCount;
    }
    
    
    public Calendar getTimeRemind(){
        return timeRemind;
    }

    public int getRemCount(){
        return remCount;
    }
    
    @Override
    public String getName() {
        return super.getName(); 
    }

    @Override
    public Calendar getDate() {
        return super.getDate(); 
    }
    
    @Override
    public String toString() {
        return super.toString()+ blue+" Время напоминания: "+reset+
                dateFormat.format(getTimeRemind().getTime())+blue+
                " Частота нопоминания: "+reset+getRemCount(); 
    }
}

class EventDistribution extends StandartEvent{
    private String mailList;
    private String mailText;
    private Calendar mailDate;

 

    public EventDistribution(String name, Calendar date, String mailList, String mailText, Calendar mailDate) {
        this.name = name;
        this.date = date;
        this.mailList = mailList;
        this.mailText = mailText;
        this.mailDate = mailDate;
    }

    @Override
    public void setName(String name) {
        super.setName(name); 
    }

    @Override
    public void setDate(Calendar date) {
        super.setDate(date); 
    }
    
    public void setMailList(String mailList){
        this.mailList = mailList;
    }
    
    public void setMailText(String mailText){
        this.mailText = mailText;
    }
    
    public void setMailDate(Calendar mailDate){
        this.mailDate = mailDate;
    }
    
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Calendar getDate() {
        return super.getDate(); 
    }
    
    public String getMailList() {
        return mailList; 
    }
    
    public String getMailText() {
        return mailText; 
    }
    
    public Calendar getMailDate() {
        return mailDate; 
    }
    
    @Override
    public String toString() {
        return super.toString()+blue+" Список рассылки: "+reset+getMailList()+blue+" Текстt: "+reset+
                getMailText()+blue+" Дата рассылки: "+reset+dateFormat.format(getMailDate().getTime());
    }
    
}