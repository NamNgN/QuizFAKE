/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author User
 */
public class Progress {
    private int progressid;
    private boolean notstudied;
    private boolean stilllearning;
    private boolean mastered;
    private int questionid;

    public Progress() {
    }

    public int getProgressid() {
        return progressid;
    }

    public void setProgressid(int progressid) {
        this.progressid = progressid;
    }

    public boolean isNotstudied() {
        return notstudied;
    }

    public void setNotstudied(boolean notstudied) {
        this.notstudied = notstudied;
    }

    public boolean isStilllearning() {
        return stilllearning;
    }

    public void setStilllearning(boolean stilllearning) {
        this.stilllearning = stilllearning;
    }

    public boolean isMastered() {
        return mastered;
    }

    public void setMastered(boolean mastered) {
        this.mastered = mastered;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }
    
}
