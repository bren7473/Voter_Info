package com.example.bren.voterinformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "voterinfo-mobilehub-1525701676-Polls")

public class PollsDO {
    private String _userId;
    private Double _pollId;
    private String _a;
    private Double _aTally;
    private String _b;
    private Double _bTally;
    private String _c;
    private Double _cTally;
    private String _d;
    private Double _dTally;
    private String _e;
    private Double _eTally;
    private String _f;
    private Double _fTally;
    private String _que;
    private String _targetCity;
    private String _targetCongressionalDistrict;
    private String _targetCounty;
    private String _targetCountyCommissionDistrict;
    private String _targetParty;
    private String _targetSchoolboardDistrict;
    private String _targetState;
    private String _targetStateHouseDistrict;
    private Double _totalResponse;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBRangeKey(attributeName = "pollId")
    @DynamoDBAttribute(attributeName = "pollId")
    public Double getPollId() {
        return _pollId;
    }

    public void setPollId(final Double _pollId) {
        this._pollId = _pollId;
    }
    @DynamoDBAttribute(attributeName = "a")
    public String getA() {
        return _a;
    }

    public void setA(final String _a) {
        this._a = _a;
    }
    @DynamoDBAttribute(attributeName = "aTally")
    public Double getATally() {
        return _aTally;
    }

    public void setATally(final Double _aTally) {
        this._aTally = _aTally;
    }
    @DynamoDBAttribute(attributeName = "b")
    public String getB() {
        return _b;
    }

    public void setB(final String _b) {
        this._b = _b;
    }
    @DynamoDBAttribute(attributeName = "bTally")
    public Double getBTally() {
        return _bTally;
    }

    public void setBTally(final Double _bTally) {
        this._bTally = _bTally;
    }
    @DynamoDBAttribute(attributeName = "c")
    public String getC() {
        return _c;
    }

    public void setC(final String _c) {
        this._c = _c;
    }
    @DynamoDBAttribute(attributeName = "cTally")
    public Double getCTally() {
        return _cTally;
    }

    public void setCTally(final Double _cTally) {
        this._cTally = _cTally;
    }
    @DynamoDBAttribute(attributeName = "d")
    public String getD() {
        return _d;
    }

    public void setD(final String _d) {
        this._d = _d;
    }
    @DynamoDBAttribute(attributeName = "dTally")
    public Double getDTally() {
        return _dTally;
    }

    public void setDTally(final Double _dTally) {
        this._dTally = _dTally;
    }
    @DynamoDBAttribute(attributeName = "e")
    public String getE() {
        return _e;
    }

    public void setE(final String _e) {
        this._e = _e;
    }
    @DynamoDBAttribute(attributeName = "eTally")
    public Double getETally() {
        return _eTally;
    }

    public void setETally(final Double _eTally) {
        this._eTally = _eTally;
    }
    @DynamoDBAttribute(attributeName = "f")
    public String getF() {
        return _f;
    }

    public void setF(final String _f) {
        this._f = _f;
    }
    @DynamoDBAttribute(attributeName = "fTally")
    public Double getFTally() {
        return _fTally;
    }

    public void setFTally(final Double _fTally) {
        this._fTally = _fTally;
    }
    @DynamoDBAttribute(attributeName = "que")
    public String getQue() {
        return _que;
    }

    public void setQue(final String _que) {
        this._que = _que;
    }
    @DynamoDBAttribute(attributeName = "targetCity")
    public String getTargetCity() {
        return _targetCity;
    }

    public void setTargetCity(final String _targetCity) {
        this._targetCity = _targetCity;
    }
    @DynamoDBAttribute(attributeName = "targetCongressionalDistrict")
    public String getTargetCongressionalDistrict() {
        return _targetCongressionalDistrict;
    }

    public void setTargetCongressionalDistrict(final String _targetCongressionalDistrict) {
        this._targetCongressionalDistrict = _targetCongressionalDistrict;
    }
    @DynamoDBAttribute(attributeName = "targetCounty")
    public String getTargetCounty() {
        return _targetCounty;
    }

    public void setTargetCounty(final String _targetCounty) {
        this._targetCounty = _targetCounty;
    }
    @DynamoDBAttribute(attributeName = "targetCountyCommissionDistrict")
    public String getTargetCountyCommissionDistrict() {
        return _targetCountyCommissionDistrict;
    }

    public void setTargetCountyCommissionDistrict(final String _targetCountyCommissionDistrict) {
        this._targetCountyCommissionDistrict = _targetCountyCommissionDistrict;
    }
    @DynamoDBAttribute(attributeName = "targetParty")
    public String getTargetParty() {
        return _targetParty;
    }

    public void setTargetParty(final String _targetParty) {
        this._targetParty = _targetParty;
    }
    @DynamoDBAttribute(attributeName = "targetSchoolboardDistrict")
    public String getTargetSchoolboardDistrict() {
        return _targetSchoolboardDistrict;
    }

    public void setTargetSchoolboardDistrict(final String _targetSchoolboardDistrict) {
        this._targetSchoolboardDistrict = _targetSchoolboardDistrict;
    }
    @DynamoDBAttribute(attributeName = "targetState")
    public String getTargetState() {
        return _targetState;
    }

    public void setTargetState(final String _targetState) {
        this._targetState = _targetState;
    }
    @DynamoDBAttribute(attributeName = "targetStateHouseDistrict")
    public String getTargetStateHouseDistrict() {
        return _targetStateHouseDistrict;
    }

    public void setTargetStateHouseDistrict(final String _targetStateHouseDistrict) {
        this._targetStateHouseDistrict = _targetStateHouseDistrict;
    }
    @DynamoDBAttribute(attributeName = "totalResponse")
    public Double getTotalResponse() {
        return _totalResponse;
    }

    public void setTotalResponse(final Double _totalResponse) {
        this._totalResponse = _totalResponse;
    }

}
