package com.ihealth.aijiakang.widgets.datasource;

import java.util.ArrayList;

/**
 * Created by YanJiaqi on 15/12/9
 */
public class ResultsCoordinateData {
    private boolean isBpToKpa = false;
    private ArrayList<ArrayList<ResultsCoordinateItem>> values = new ArrayList<ArrayList<ResultsCoordinateItem>>();
    private ArrayList<String> dates = new ArrayList<String>();
    public ResultsCoordinateData(){

    }

    public ResultsCoordinateData(boolean isBpToKpa,ArrayList<ArrayList<ResultsCoordinateItem>> values,ArrayList<String> dates){
        this.isBpToKpa = isBpToKpa;
        this.values = values;
        this.dates = dates;
    }

    public ArrayList<ArrayList<ResultsCoordinateItem>>  getValues() {
        return values;
    }

    public void setValues(ArrayList<ArrayList<ResultsCoordinateItem>>  values) {
        this.values = values;
    }

    public boolean isBpToKpa() {
        return isBpToKpa;
    }

    public void setIsBpToKpa(boolean isBpToKpa) {
        this.isBpToKpa = isBpToKpa;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }
}
