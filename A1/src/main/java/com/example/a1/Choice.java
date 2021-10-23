package com.example.a1;

import java.util.Objects;

public class Choice  implements java.io.Serializable{
    private String text;
    private String description;

    public Choice(){
        this.text = "1";
        this.description = "";
    }
    public Choice(String text, String description){
        this.text = text;
        this.description = description;
    }

    public String toString(){
        return "[Text: "+this.text+"\nDescription: "+this.description+"]";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return Objects.equals(text, choice.text);
    }

    //accessor and mutator methods
    public void setText(String text) {this.text = text;}
    public void setDescription(String description) {this.description = description;}

    public String getText(){return this.text;}
    public String getDescription(){return this.description;}
}
