package com.sszz.CBT.domain;

import javax.persistence.*;

@Entity
@Table(name = "TEST_WTEST_TB")
public class WrittenTestVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer questionid;

    String subject;
    String hoecha;
    String question;
    String data;
    String bogi1;
    String bogi2;
    String bogi3;
    String bogi4;
    String answer;


    public WrittenTestVO() {
    }

    public WrittenTestVO(String subject, String hoecha, String question, String data, String bogi1, String bogi2, String bogi3, String bogi4, String answer) {
        this.subject = subject;
        this.hoecha = hoecha;
        this.question = question;
        this.data = data;
        this.bogi1 = bogi1;
        this.bogi2 = bogi2;
        this.bogi3 = bogi3;
        this.bogi4 = bogi4;
        this.answer = answer;
    }

    public WrittenTestVO(Integer questionid, String subject, String hoecha, String question, String data, String bogi1, String bogi2, String bogi3, String bogi4, String answer) {
        this.questionid = questionid;
        this.subject = subject;
        this.hoecha = hoecha;
        this.question = question;
        this.data = data;
        this.bogi1 = bogi1;
        this.bogi2 = bogi2;
        this.bogi3 = bogi3;
        this.bogi4 = bogi4;
        this.answer = answer;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHoecha() {
        return hoecha;
    }

    public void setHoecha(String hoecha) {
        this.hoecha = hoecha;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBogi1() {
        return bogi1;
    }

    public void setBogi1(String bogi1) {
        this.bogi1 = bogi1;
    }

    public String getBogi2() {
        return bogi2;
    }

    public void setBogi2(String bogi2) {
        this.bogi2 = bogi2;
    }

    public String getBogi3() {
        return bogi3;
    }

    public void setBogi3(String bogi3) {
        this.bogi3 = bogi3;
    }

    public String getBogi4() {
        return bogi4;
    }

    public void setBogi4(String bogi4) {
        this.bogi4 = bogi4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
