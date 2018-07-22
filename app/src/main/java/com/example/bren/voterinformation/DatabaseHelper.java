package com.example.bren.voterinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "adb5";
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Table Names
    private static final String TABLE_POLL = "Poll";
    private static final String TABLE_VOTER = "voter";
    private static final String TABLE_VOTER_POLLS = "voterPolls";


    //  create  Tables for poll, voter, and user
    private static final String CREATE_TABLE_POLL = "CREATE TABLE "  + TABLE_POLL+ " (POLLID INTEGER PRIMARY KEY AUTOINCREMENT, CREATORNAME TEXT, POLLNAME TEXT, TARGET TEXT, TOPIC TEXT, QUESTION INT, RESULTA INT, RESULTB INT, RESULTC INT, RESULTD INT, RESULTE INT, RESULTF INT, A TEXT, B TEXT, C TEXT, D TEXT, E TEXT, F TEXT)";
    private static final String CREATE_TABLE_VOTER = "CREATE TABLE "  + TABLE_VOTER+ " (VOTERID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, ADDRESS TEXT, DISTRICT INT, BIRTHDAY TEXT, STATE TEXT, CITY TEXT)";
    private static final String CREATE_TABLE_VOTER_POLLS  = "CREATE TABLE " + TABLE_VOTER_POLLS+ " (VOTERPOLLSID INTEGER PRIMARY KEY AUTOINCREMENT, POLL TEXT, VOTERINT INT,  COMPLETE INT, PENDING INT)";

    public Context dContext;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating required tables
        sqLiteDatabase.execSQL(CREATE_TABLE_POLL);
        sqLiteDatabase.execSQL(CREATE_TABLE_VOTER);
        sqLiteDatabase.execSQL(CREATE_TABLE_VOTER_POLLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_POLL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTER_POLLS);

        // create new tables
        onCreate(sqLiteDatabase);
    }

    //-----------------------"VOTERPOLLS" table methods -------------//
    public Boolean insertVoterPoll(VoterPollModel userPoll) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("VOTERINT", userPoll.getVoterInt());
        values.put("POLL", userPoll.getPoll());
        values.put("COMPLETE", userPoll.getComplete());
        values.put("PENDING", userPoll.getPending());

        long resultSet = db.insert(TABLE_VOTER_POLLS, null, values);
        return resultSet != -1;
    }

    public List<VoterPollModel> getAllVoterPolls(String username) {
        VoterModel voter;
        voter = getVoter(username);
        List<VoterPollModel> pollList = new ArrayList<VoterPollModel>();
        String selectQuery = "SELECT * FROM " + TABLE_VOTER_POLLS + " WHERE VOTERINT = " + voter.getVoterId();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery(selectQuery, null);

        if (resultSet.moveToFirst()) {
            do {
                VoterPollModel voterPoll = new VoterPollModel();
                voterPoll.setVoterInt((resultSet.getInt(resultSet.getColumnIndex("VOTERINT"))));
                voterPoll.setPoll((resultSet.getString(resultSet.getColumnIndex("POLL"))));
                voterPoll.setPending((resultSet.getInt(resultSet.getColumnIndex("PENDING"))));
                voterPoll.setComplete((resultSet.getInt(resultSet.getColumnIndex("COMPLETE"))));

                //adding poll to list
                pollList.add(voterPoll);
            } while (resultSet.moveToNext());
        }
        return pollList;
    }

    // ------------------------ "POLL" table methods ----------------//

    public Boolean insertPoll(PollModel poll) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("CREATORNAME", poll.getCreatorName());
        values.put("POLLNAME", poll.getPollName());
        values.put("TARGET", poll.getTarget());
        values.put("TOPIC", poll.getTopic());
        values.put("QUESTION", poll.getQuestion());
        values.put("RESULTA", poll.getResultA());
        values.put("RESULTB", poll.getResultB());
        values.put("RESULTC", poll.getResultC());
        values.put("RESULTD", poll.getResultD());
        values.put("RESULTE", poll.getResultE());
        values.put("RESULTF", poll.getResultF());
        values.put("A", poll.getA());
        values.put("B", poll.getB());
        values.put("C", poll.getC());
        values.put("D", poll.getD());
        values.put("E", poll.getE());
        values.put("F", poll.getF());

        // insert row
        db.insert(TABLE_POLL, null, values);
        VoterModel voterModel = getVoter(poll.getTarget());

        ContentValues vals = new ContentValues();
        vals.put("VOTERINT", voterModel.getVoterId());
        vals.put("POLL", poll.getPollName());



        long result = db.insert(TABLE_VOTER_POLLS, null, vals);
        return result != -1;
    }

    // get single candidate record from poll table
    public PollModel getPoll(String pollName) {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println(pollName);

        String selectQuery = "SELECT * FROM " + TABLE_POLL + " WHERE POLLNAME = '" + pollName + "'";
        Cursor resultSet = db.rawQuery(selectQuery, null);

        if (resultSet != null)
            resultSet.moveToFirst();


        PollModel poll = new PollModel();
        if (resultSet.moveToFirst()) {
            do {
        poll.setPollId((resultSet.getInt(resultSet.getColumnIndex("POLLID"))));
        poll.setCreatorName((resultSet.getString(resultSet.getColumnIndex("CREATORNAME"))));
        poll.setPollName((resultSet.getString(resultSet.getColumnIndex("POLLNAME"))));
        poll.setTarget((resultSet.getString(resultSet.getColumnIndex("TARGET"))));
        poll.setTopic((resultSet.getString(resultSet.getColumnIndex("TOPIC"))));
        poll.setQuestion((resultSet.getInt(resultSet.getColumnIndex("QUESTION"))));
        poll.setResultA((resultSet.getInt(resultSet.getColumnIndex("RESULTA"))));
        poll.setResultB((resultSet.getInt(resultSet.getColumnIndex("RESULTB"))));
        poll.setResultC((resultSet.getInt(resultSet.getColumnIndex("RESULTC"))));
        poll.setResultD((resultSet.getInt(resultSet.getColumnIndex("RESULTD"))));
        poll.setResultE((resultSet.getInt(resultSet.getColumnIndex("RESULTE"))));
        poll.setResultF((resultSet.getInt(resultSet.getColumnIndex("RESULTF"))));
        poll.setA((resultSet.getString(resultSet.getColumnIndex("A"))));
        poll.setB((resultSet.getString(resultSet.getColumnIndex("B"))));
        poll.setC((resultSet.getString(resultSet.getColumnIndex("C"))));
        poll.setD((resultSet.getString(resultSet.getColumnIndex("D"))));
        poll.setE((resultSet.getString(resultSet.getColumnIndex("E"))));
        poll.setF((resultSet.getString(resultSet.getColumnIndex("F"))));
    }
            while (resultSet.moveToNext()) ;
}

        return poll;
    }

    // GET POLL LIST
    public List<PollModel> getAllPolls(String creatorName) {
        List<PollModel> pollList = new ArrayList<PollModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_POLL + " WHERE CREATORNAME =" + creatorName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (resultSet.moveToFirst()) {
            do {
                PollModel poll = new PollModel();
                poll.setPollId((resultSet.getInt(resultSet.getColumnIndex("POLLID"))));
                poll.setCreatorName((resultSet.getString(resultSet.getColumnIndex("CREATORNAME"))));
                poll.setPollName((resultSet.getString(resultSet.getColumnIndex("POLLNAME"))));
                poll.setTarget((resultSet.getString(resultSet.getColumnIndex("TARGET"))));
                poll.setTopic((resultSet.getString(resultSet.getColumnIndex("TOPIC"))));
                poll.setQuestion((resultSet.getInt(resultSet.getColumnIndex("QUESTION    "))));
                poll.setResultA((resultSet.getInt(resultSet.getColumnIndex("RESULTA"))));
                poll.setResultB((resultSet.getInt(resultSet.getColumnIndex("RESULTB"))));
                poll.setResultC((resultSet.getInt(resultSet.getColumnIndex("RESULTC"))));
                poll.setResultD((resultSet.getInt(resultSet.getColumnIndex("RESULTD"))));
                poll.setResultE((resultSet.getInt(resultSet.getColumnIndex("RESULTE"))));
                poll.setResultF((resultSet.getInt(resultSet.getColumnIndex("RESULTF"))));
                poll.setA((resultSet.getString(resultSet.getColumnIndex("A"))));
                poll.setB((resultSet.getString(resultSet.getColumnIndex("B"))));
                poll.setC((resultSet.getString(resultSet.getColumnIndex("C"))));
                poll.setD((resultSet.getString(resultSet.getColumnIndex("D"))));
                poll.setE((resultSet.getString(resultSet.getColumnIndex("E"))));
                poll.setF((resultSet.getString(resultSet.getColumnIndex("F"))));

                // adding to poll list
                pollList.add(poll);
            } while (resultSet.moveToNext());
        }

        return pollList;
    }

    public int updatePoll(PollModel poll) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("CREATORNAME", poll.getCreatorName());
        values.put("POLLNAME", poll.getPollName());
        values.put("TARGET", poll.getTarget());
        values.put("TOPIC", poll.getTopic());
        values.put("QUESTION", poll.getQuestion());
        values.put("RESULTA", poll.getResultA());
        values.put("RESULTB", poll.getResultB());
        values.put("RESULTC", poll.getResultC());
        values.put("RESULTD", poll.getResultD());
        values.put("RESULTE", poll.getResultE());
        values.put("RESULTF", poll.getResultF());
        values.put("A", poll.getA());
        values.put("B", poll.getB());
        values.put("C", poll.getC());
        values.put("D", poll.getD());
        values.put("E", poll.getE());
        values.put("F", poll.getF());
        // updating row
        return db.update(TABLE_POLL, values, "POLLID" + " = ?",
                new String[] { String.valueOf(poll.getPollId()) });
    }

    /**
     * Deleting a poll
     */
    public void deletePoll(long pollId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POLL, "POLLID" + " = ?",
                new String[] { String.valueOf(pollId) });
    }


    // ------------------------ "VOTER" table methods ----------------//

    public Boolean insertVoter(VoterModel voter) {
        String password;
        password = getSecurePassword(voter.getPassword(), "Dont go breaking my heart.");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERNAME", voter.getUsername());
        values.put("PASSWORD", password);
        values.put("EMAIL", voter.getEmail());
        values.put("ADDRESS", voter.getAddress());
        values.put("BIRTHDAY",voter.getBirthday());
        values.put("DISTRICT", voter.getDistrict());
        values.put("STATE", voter.getState());
        values.put("CITY",voter.getCity());

        long result = db.insert(TABLE_VOTER, null, values);
        return result != -1;
    }
        // get single candidate record from poll table
    public VoterModel getVoter(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        VoterModel voter = new VoterModel();
        String selectQuery = "SELECT  * FROM " + TABLE_VOTER + " WHERE  USERNAME = '" + username + "'";

        Cursor resultSet = db.rawQuery(selectQuery, null);

        if (resultSet.moveToFirst()) {
            do {


                voter.setVoterId((resultSet.getInt(resultSet.getColumnIndex("VOTERID"))));
                voter.setUsername((resultSet.getString(resultSet.getColumnIndex("USERNAME"))));
                voter.setState((resultSet.getString(resultSet.getColumnIndex("STATE"))));
                voter.setCity((resultSet.getString(resultSet.getColumnIndex("CITY"))));
                voter.setAddress((resultSet.getString(resultSet.getColumnIndex("ADDRESS"))));
                voter.setDistrict((resultSet.getInt(resultSet.getColumnIndex("DISTRICT"))));
                voter.setEmail((resultSet.getString(resultSet.getColumnIndex("EMAIL"))));
                voter.setPassword((resultSet.getString(resultSet.getColumnIndex("PASSWORD"))));
                voter.setBirthday((resultSet.getString(resultSet.getColumnIndex("BIRTHDAY"))));


            }
            while (resultSet.moveToNext()) ;
        }
        return voter;
    }

    // GET Voter LIST
    public List<VoterModel> getAllVoters() {
        List<VoterModel> voterList = new ArrayList<VoterModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_VOTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (resultSet.moveToFirst()) {
            do {
                VoterModel voter = new VoterModel();
                voter.setVoterId((resultSet.getInt(resultSet.getColumnIndex("VOTERID"))));
                voter.setUsername((resultSet.getString(resultSet.getColumnIndex("USERNAME"))));
                voter.setEmail((resultSet.getString(resultSet.getColumnIndex("EMAIL"))));
                voter.setPassword((resultSet.getString(resultSet.getColumnIndex("PASSWORD"))));
                voter.setCity((resultSet.getString(resultSet.getColumnIndex("CITY"))));
                voter.setState((resultSet.getString(resultSet.getColumnIndex("STATE"))));
                voter.setAddress((resultSet.getString(resultSet.getColumnIndex("ADDRESS"))));
                voter.setBirthday((resultSet.getString(resultSet.getColumnIndex("BIRTHDAY"))));
                voter.setDistrict((resultSet.getInt(resultSet.getColumnIndex("DISTRICT"))));

                // adding to voter list
                voterList.add(voter);
            } while (resultSet.moveToNext());
        }

        return voterList;
    }

    public String getSecurePassword(String passwordToHash, String messageSalt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(messageSalt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public Boolean getLoginInfo(VoterModel user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String password;
        password = getSecurePassword(user.getPassword(),    "Dont go breaking my heart.");
        String query = "Select USERNAME, PASSWORD FROM " + TABLE_VOTER + " WHERE USERNAME = '"+user.getUsername() +"' AND PASSWORD= '"+password+"'";
        Cursor resultSet = db.rawQuery(query, null);
        return resultSet.getCount() != 0;
    }


}
