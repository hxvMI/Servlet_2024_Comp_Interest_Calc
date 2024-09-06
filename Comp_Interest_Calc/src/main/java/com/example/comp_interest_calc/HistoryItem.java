package com.example.comp_interest_calc;

import com.sun.xml.txw2.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.concurrent.ThreadLocalRandom;

@XmlRootElement
public class HistoryItem{

    int id;
    double principleD;
    double interestPercentD; //as a Percent
    int yearsInt;
    int perYearInt;
    double result;

    /**
     * An EMPTY constructor is needed when
     * producing an XML or JSON return
     */
    protected HistoryItem() {
    }

    /**
    * CONSTRUCTOR
    * Sets global variable values
    * and calls Database setup method
    */
    protected HistoryItem(double principleD, double interestPercentD, int yearsInt, int perYearInt, double result) {
        setId(ThreadLocalRandom.current().nextInt(0, 99998 + 1));
        setPrincipleD(principleD);
        setInterestPercentD(interestPercentD);
        setYearsInt(yearsInt);
        setPerYearInt(perYearInt);
        setResult(result);

        //Setup Database on Construction
        //and establish Connection
        CalcDAO.connect();
    }

    public int getId() {
        return id;
    }

    public double getPrincipleD() {
        return principleD;
    }

    public double getInterestPercentD() {
        return interestPercentD;
    }

    public int getYearsInt() {
        return yearsInt;
    }

    public int getPerYearInt() {
        return perYearInt;
    }

    public double getResult() {
        return result;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public void setPrincipleD(double principleD) {
        this.principleD = principleD;
    }

    @XmlElement
    public void setInterestPercentD(double interestPercentD) {
        this.interestPercentD = interestPercentD;
    }

    @XmlElement
    public void setYearsInt(int yearsInt) {
        this.yearsInt = yearsInt;
    }

    @XmlElement
    public void setPerYearInt(int perYearInt) {
        this.perYearInt = perYearInt;
    }

    @XmlElement
    public void setResult(double result) {
        this.result = result;
    }
}
