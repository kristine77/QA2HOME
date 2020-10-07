import org.junit.jupiter.api.Test;

public class JavaHomework {

    @Test
    public void homework1() {
        calculateLoanAmount();

        String stringToCount = "    Midsummer madness      in Latvia   ";

        countCharacters(stringToCount);

        coordinatesBetweenPoints();
    }

    public void calculateLoanAmount() {

        double principalAmount = 50000.00;
        double rate = 3.00;
        int time = 15;

        double interest = (principalAmount * rate * time) / 100;

        double totalLoanAmount = principalAmount + interest;

        System.out.println("If principal amount is " + principalAmount + " and interest rate is " + rate +
                " per annum, " +
                "interest over " + time + " years is " + interest);

        System.out.println("Total loan amount is " + totalLoanAmount);
    }

    public void countCharacters(String stringToCount) {

        int count = stringToCount.length();
        System.out.println("Total number of characters in a string: " + count);

        stringToCount = stringToCount.trim();

        stringToCount = stringToCount.replaceAll("[ ]{2,}", " ");
        System.out.println(stringToCount);

        int wordCount = stringToCount.length() - (stringToCount.replaceAll(" ", "").length()) + 1;

        System.out.println("Word count: " + wordCount);
    }


    public void coordinatesBetweenPoints() {

        // New York
        double lat1 = 40.7128;
        double lon1 = 74.0059;

        // London
        double lat2 = 51.5074;
        double lon2 = 0.1278;

        System.out.println("Distance: " + distance(lat1, lon1, lat2, lon2));
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371.01; //Kilometers
        return (int) (earthRadius * Math.acos(Math.sin(lat1)
                * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2)));
    }

}
