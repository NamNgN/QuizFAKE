/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Asus
 */
public class Question {

    private int questionid;
    private String image;
    private String term;
    private String definition;
    private int courseid;
    private boolean isCorrect;

    public Question() {
    }

    public Question( String image, String term, String definition, int courseid) {
        this.image = image;
        this.term = term;
        this.definition = definition;
        this.courseid = courseid;
    }

    public Question(int questionid, String image, String term, String definition, int courseid) {
        this.questionid = questionid;
        this.image = image;
        this.term = term;
        this.definition = definition;
        this.courseid = courseid;
    }
    
    
    
    public String getIdString() {
        return "" + questionid;
    }
    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
