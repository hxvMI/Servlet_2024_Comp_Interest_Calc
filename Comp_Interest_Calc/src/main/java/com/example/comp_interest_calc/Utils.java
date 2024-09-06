package com.example.comp_interest_calc;

public class Utils {

    /**
     *  @param principle,interest,years,perYear
     *  @return double
     *  Parses Strings to double and
     *  Calculates compound interest
     */
    protected static double calculateInterest(String principle, String interest, String years, String perYear) {
        //Parse Strings to Double
        double principleD = Double.parseDouble(principle);
        double interestPercentD = Double.parseDouble(interest)/100; //as a Percent
        int yearsInt = Integer.parseInt(years);
        int perYearInt = Integer.parseInt(perYear);

        //Calculate Interest
        double result = principleD * Math.pow(1 + (interestPercentD / perYearInt), (perYearInt * yearsInt));
        result = Math.round(result);
        //Store Calculation Data in History
        HistoryItem historyItem = new HistoryItem(principleD,interestPercentD,yearsInt,perYearInt,result);
        HistoryItemRepo.insertItem(historyItem);

        return result;
    }

    /**
     *  @param principle,interest,years,perYear
     *  @return boolean
     *  is false
     *  if INPUT was not FOUND
     *  if length of INPUT was 0
     */
    //isEmpty() returns true when no characters
    //isBlank() returns true when no characters or whitespace
    protected static boolean checkInput(String principle, String interest, String years, String perYear){
        if (principle == null || interest == null || years == null || perYear == null || principle.isBlank() || interest.isBlank() || years.isBlank() || perYear.isBlank())
            return false;
        else
            return true;
    }
}
