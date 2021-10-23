package com.example.a1;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

enum PollStatus {CREATED, RUNNING, RELEASED}

public class Poll  implements java.io.Serializable{
    private String name;
    private String question;
    public Hashtable<Choice, Integer> choices;
    private PollStatus status;
    private String releaseDateTime;

    public Poll(){
        this.name = null;
        this.question = null;
        this.status = PollStatus.CREATED;
    }

    public Poll(String name, String question, Hashtable<Choice, Integer> choices){
        this.name = name;
        this.question = question;
        this.choices = choices;
        this.status = PollStatus.CREATED;
    }

    public String toString(){
        String str = "";
        //get enumeration of all keys using keys method
        Enumeration<Choice> keys = this.choices.keys();

        while( keys.hasMoreElements() )
            str += keys.nextElement().toString();
        return "Name: "+this.name+"\n   Question: "+this.question+"   Status: "+this.status+"\n   Choices: "+str;
    }
    public void changeStatus(PollStatus newStatus) {
        this.status = newStatus;
    }

    public void addChoice(Choice choice) {
        this.choices.put(choice, 0);
    }

    public Hashtable<String, Integer> getResults() {
        Hashtable<String, Integer> results = new Hashtable<>();
        choices.forEach((key, value) -> results.put(key.toString(), value));
        return results;
    }

    //accessor and mutator methods
    public void setChoices(Hashtable<Choice, Integer> choices) {this.choices = choices;}
    public void setName(String name) {this.name = name;}
    public void setQuestion(String question) {this.question = question;}
    public void setStatus(PollStatus status) {this.status = status;}

    public void setReleaseDateTime(String releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    public String getReleaseDateTime() {
        return releaseDateTime;
    }

    public String getName(){return this.name;}
    public String getQuestion(){return this.question;}
    public Hashtable<Choice, Integer> getChoices(){return this.choices;}
    public PollStatus getStatus(){return this.status;}
}
