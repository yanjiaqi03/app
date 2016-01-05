package com.ihealth.aijiakang.database.data;

/**
 * Created by lanbaoshi on 15/12/7.
 */
public class Data_FAQ {

    private int questionId;
    private long questionTS;
    private String questionTitle;
    private String questContent;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public long getQuestionTS() {
        return questionTS;
    }

    public void setQuestionTS(long questionTS) {
        this.questionTS = questionTS;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestContent() {
        return questContent;
    }

    public void setQuestContent(String questContent) {
        this.questContent = questContent;
    }
}
