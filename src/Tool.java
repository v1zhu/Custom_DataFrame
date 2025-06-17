public class Tool {

    /**
     * Calculates the mean of a Series object.
     *
     * @param d the Series object to calculate the mean from
     */
    public static Integer mean(HashTable d) throws ArithmeticException, NullPointerException, IllegalArgumentException {
        // TODO: Implement mean calculation
        // exception raised if d is null, empty Series
        if (d == null) {
            throw new NullPointerException("mean (Series d): d can't be null");
        }
        if (d.getRowNames().length == 0) {
            throw new IllegalArgumentException("mean (Series d): d can't be an empty Series");
        }
        // use try catch to skip null
        Integer total = 0;
        Integer notnull = 0;
        for (int i = 0; i < d.getRowNames().length; i++) {
            try{
                total += d.iloc(i);
                notnull++;
            } catch (NullPointerException e) {
                continue;
            }
        }
        Integer average = total / notnull;
        return average;




    }

    /**
     * Finds the maximum value in a Series object.
     *
     * @param d the Series object to find the maximum value from
     */
    public static Integer max(HashTable d) throws IllegalArgumentException, ArithmeticException, NullPointerException {
        // TODO: Implement max value finder
        //exception if d is null or empty
        if (d == null) {
            throw new NullPointerException("max (Series d): d can't be null");
        }
        if (d.getRowNames().length == 0) {
            throw new IllegalArgumentException("max (Series d): d can't be an empty Series");
        }

        Integer maxvalue = null;

        //run through d trying to find max value
        for (int i = 0; i < d.getRowNames().length; i++) {
            try {
                Integer number = d.iloc(i);
                if (maxvalue == null || number > maxvalue) {
                    maxvalue = number;
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        if (maxvalue == null) {
            throw new ArithmeticException();
        }
        return maxvalue;
    }
}
