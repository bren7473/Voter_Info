package com.example.bren.voterinformation;

public class VoterModel {
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() { return address; }

    public String getBirthday() { return birthday; }
    public String getCity() {return city;}
    public int getDistrict() { return district; }
    public String getState() { return state; }

    private int district;
    private String username;
    private String password;
    private String email;
    private String address;
    private String state;
    private String city;


    private String birthday;
    private int voterId;

    public int getVoterId() {
        return voterId;
    }


    public void setState(String state) { this.state = state; }

    public void setCity(String city) { this.city = city; }

    public void setDistrict(int district) {
        this.district = district;
    }

    public void setBirthday(String birthday) {this.birthday = birthday;}

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public void setUsername(String username) {
        this.username = username;
    }

    public VoterModel() {

    }

    public VoterModel(String username, String password, String email, Integer district, String birthday, String address, String state, String city) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.district = district;
        this.city = city;
        this.state = state;
        this.birthday = birthday;
        this.address = address;
    }

    @Override
    public String toString() {
        return "VoterModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + voterId +
                '}';
    }
}
