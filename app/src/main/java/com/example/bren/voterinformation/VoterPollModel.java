package com.example.bren.voterinformation;

public class VoterPollModel {
    private int voterPollsId, voterInt, complete, pending;
    private String poll;


    public void setVoterPollsId(int voterPollsId) {
        this.voterPollsId = voterPollsId;
    }
    public void setVoterInt(int voterInt) {
        this.voterInt = voterInt;
    }
    public void setPoll(String poll) {
        this.poll = poll;
    }
    public void setComplete(int complete) {
        this.complete = complete;
    }
    public void setPending(int pending) {
        this.pending = pending;
    }

    public int getVoterPollsId() {
        return voterPollsId;
    }
    public int getVoterInt() {
        return voterInt;
    }
    public String getPoll() {
        return poll;
    }
    public int getComplete() {
        return complete;
    }
    public int getPending() {
        return pending;
    }


    @Override
    public String toString() {
        return "VoterPollModel{" +
                "voterInt='" + voterInt + '\'' +
                ", poll='" + poll + '\'' +
                ", completeInt='" + complete + '\'' +
                ", pendingInt='" + pending +
                '}';
    }
}
