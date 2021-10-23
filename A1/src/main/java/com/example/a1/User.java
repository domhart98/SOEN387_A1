package com.example.a1;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class PollException extends Exception {
    public PollException(String message) {
        super(message);
    }
}

public class User  implements java.io.Serializable{
    private Poll poll;
    private int votes;

    public User(){
        this.poll = null;
        this.votes = 1;
    }
    public User(Poll poll) {
        this.poll = poll;
        this.votes = 1;
    }

    //Create poll only if there are none in the system, otherwise throw exception
    public void createPoll(String name, String question, Hashtable<Choice, Integer> choices) throws PollException{
        if (this.poll == null){
            this.poll = new Poll(name, question, choices);
        }
        else{
            throw new PollException("A poll has already been created");
        }
    }

    //update poll and clear results only if one exists and is CREATED or RUNNING, otherwise throw exception. Puts poll back into CREATED state.
    public void updatePoll(Object...values) throws PollException{
        if(this.poll.getStatus() == PollStatus.RUNNING){
            this.poll.setStatus(PollStatus.CREATED);
        }
        else if(this.poll.getStatus() == PollStatus.RELEASED){
            throw new PollException("Poll is RELEASED. Poll can only be updated if it is in CREATED or RUNNING state");
        }

        if(values.length == 1){
            this.poll.setName((String) values[0]);
        }
        else if(values.length == 2){
            this.poll.setName((String) values[0]);
            this.poll.setQuestion((String) values[1]);
        }
        else if(values.length == 3){
            this.poll.setName((String) values[0]);
            this.poll.setQuestion((String) values[1]);
            this.poll.setChoices((Hashtable<Choice, Integer>) values[2]);
        }
        else{
            throw new PollException("");
        }
    }

    //Clear results of RUNNING or RELEASED poll. RUNNING -> RUNNING, RELEASED -> CREATED, CREATED -> throw exception
    public void clearPoll() throws PollException{
        if(this.poll.getStatus() == PollStatus.RUNNING){
            this.poll.setStatus(PollStatus.CREATED);
        }
        else if(this.poll.getStatus() == PollStatus.CREATED){
            throw new PollException("Poll is RELEASED. User can only clear poll in CREATED or RUNNING state");
        }
        Hashtable<Choice, Integer> choices = this.getPoll().getChoices();
        Enumeration<Choice> keys = choices.keys();
        while(keys.hasMoreElements()) {
            if(choices.containsKey(keys.nextElement())){
                choices.replace(keys.nextElement(), 0);
            }
        }
    }

    //If poll is RELEASED, delete it. Otherwise, throw exception
    public void closePoll() throws PollException {
        if (this.poll.getStatus() == PollStatus.RELEASED){
            this.poll = null;
        }
        else{
            throw new PollException("This poll must be RELEASED before it can be closed");
        }
    }
    //If poll is CREATED, change to RUNNING. Otherwise, throw exception
    public void runPoll() throws  PollException{
        if (this.poll.getStatus() == PollStatus.CREATED){
            this.poll.setStatus(PollStatus.RUNNING);
        }
        else{
            throw new PollException("Poll must be CREATED before it can run. Create poll");
        }
    }

    //If poll is RUNNING, change to RELEASED. Otherwise throw exception.
    public void releasePoll() throws  PollException{

        if (this.poll.getStatus() == PollStatus.RUNNING){
            this.poll.setStatus(PollStatus.RELEASED);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.getPoll().setReleaseDateTime(dtf.format(now));
        }
        else{
            throw new PollException("Poll must be RUNNING before it can be released. Run poll");
        }

    }

    //If poll is RELEASED, change to RUNNING. Otherwise throw exception.
    public void unreleasePoll() throws  PollException{
        if (this.poll.getStatus() == PollStatus.RELEASED){
            this.poll.setStatus(PollStatus.RUNNING);
            this.getPoll().setReleaseDateTime(null);
        }
        else{
            throw new PollException("Poll must be RELEASED before it can be unreleased. Release poll");
        }
    }


    public Hashtable<Choice, Integer> getPollResults() throws PollException{
        if(this.poll.getStatus() == PollStatus.RELEASED) {
            return this.poll.getChoices();
        }
        else {
            throw new PollException("Poll must be in RELEASED state to get results");
        }
    }

    //When poll is RELEASED, participant or pm can download
    public String downloadPollDetails(PrintWriter out, String filename) throws IOException, PollException {

        if (poll.getStatus() != PollStatus.RELEASED) {
            throw new PollException("Poll must be RELEASED for user to download poll details.");
        }

        Hashtable<Choice, Integer> ht = this.poll.getChoices();
        out.write("Name: " + poll.getName() + "\n");
        out.write("Question: " + poll.getQuestion() + "\n");
        for (Map.Entry<Choice, Integer> entry : ht.entrySet()) {
            Choice key = entry.getKey();
            Integer value = entry.getValue();
            out.write("Choice: " + key.getText() + " --> Votes: " + value + "\n");
        }

        return filename;

    }

    public void vote(Choice choice) throws PollException{
        if(this.votes > 0)
            this.poll.getChoices().computeIfPresent(choice, (k, v) -> v + 1);
        else{
            throw new PollException("User has already cast vote");
        }
    }

    //accessor and mutator methods
    public Poll getPoll(){return this.poll;}

    public void setPoll(Poll poll) {this.poll = poll;}
}
