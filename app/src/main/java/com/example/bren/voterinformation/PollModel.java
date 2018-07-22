package com.example.bren.voterinformation;

public class PollModel {
    private String creatorName, pollName, target, topic, a, b, c, d, e, f;
    private int pollId, question, resultA, resultB, resultC, resultD, resultE, resultF;


    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public void setQuestion(int question) { this.question = question; }
    public void setResultA(int resultA) { this.resultA = resultA; }
    public void setResultB(int resultB) { this.resultB = resultB; }
    public void setResultC(int resultC) { this.resultC = resultC; }
    public void setResultD(int resultD) { this.resultD = resultD; }
    public void setResultE(int resultE) { this.resultE = resultE; }
    public void setResultF(int resultF) { this.resultF = resultF; }

    public int getPollId() {
        return pollId;
    }


    @Override
    public String toString() {
        return "PollModel{" +
                "creatorName='" + creatorName + '\'' +
                ", pollName='" + pollName + '\'' +
                ", target='" + target + '\'' +
                ", topic='" + topic + '\'' +
                ", question='" + question + '\'' +
                ", a='" + a + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", f='" + f + '\'' +
                ", resultA='" + resultA + '\'' +
                ", resultB='" + resultB + '\'' +
                ", resultC='" + resultC + '\'' +
                ", resultD='" + resultD + '\'' +
                ", resultE='" + resultE + '\'' +
                ", resultF='" + resultF + '\'' +
                ", resultA='" + resultA +
                '}';
    }

    public PollModel() {
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setA(String a) {
        this.a = a;
    }
    public void setB(String b) { this.b = b; }
    public void setC(String c) {
        this.c = c;
    }
    public void setD(String d) {
        this.d = d;
    }
    public void setE(String e) { this.e = e; }
    public void setF(String f) { this.f = f; }

    public String getCreatorName() {
        return creatorName;
    }
    public String getPollName() {
        return pollName;
    }
    public String getTarget() {
        return target;
    }
    public String getTopic() {
        return topic;
    }
    public String getA() {
        return a;
    }
    public String getB() {
        return b;
    }
    public String getC() {
        return c;
    }
    public String getD() {
        return d;
    }
    public String getE() {
        return e;
    }
    public String getF() {
        return f;
    }
    public int getQuestion() {
        return question;
    }
    public int getResultA() {
        return resultA;
    }
    public int getResultB() {
        return resultB;
    }
    public int getResultC() {
        return resultC;
    }
    public int getResultD() {
        return resultD;
    }
    public int getResultE() {
        return resultE;
    }
    public int getResultF() {
        return resultF;
    }




}
