/**
 * ThreadSum 
 * sums an slice of the given arrays
 * @author Angel Salazar (A00513236)
 */
public class ThreadSum extends Thread {
    // holds the sum of the array a and b
    private int[] output;

    // holds the array the will be added
    private int[] a;

    // holds the array the will be added
    private int[] b;

    // specifies where the slice starts
    private int start;

    // specifies where the slice ends
    private int end;

    /**
     * initializes ThreadSum object
     * @param output
     * @param a
     * @param b
     * @param start
     * @param end
     */
    public ThreadSum(int[] output, int[] a, int[] b, int start, int end) {
        this.output = output;
        this.a = a;
        this.b = b;
        this.start = start;
        this.end = end;
    }

    /**
     * serialize
     * serializes the given array of intengers into a string by joining them using \t as delimitter
     * @param arr
     * @return String
     */
    public static String serialize(int[] arr) {
        // holds the serielized array
        StringBuilder builder = new StringBuilder("");

        for (Integer i = 0; i < arr.length; i++) {
            // adding entry to the string
            builder.append(String.valueOf(arr[i]));
            // if i is not the last item
            // then add delimitter
            if (i != arr.length - 1) {
                builder.append(" \t ");
            }
        }

        // return serielized array
        return builder.toString();
    }
    
    /**
     * run
     * specifies that work that needs to be performed by ThreadSum
     * sums a slice of array a and array b and store it in output
     */
    @Override
    public void run() {
        // process the specifed slice
        for (int i = this.start; i < this.end; i++) {
            // store the sum of item a and item b
            this.output[i] = this.a[i] + this.b[i];
            
            // adding Thread.sleep to prevent overlapping when printing
            // NOTE: This can be ommitted
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            // outputing how output gets filled out
            System.out.println("Thread processing slice from index " + this.start + " to " + this.end);
            System.out.println("A " + serialize(this.a));
            System.out.println("B " + serialize(this.b));
            System.out.println("R " + serialize(this.output));
            System.out.println("Thread processing slice from index " + this.start + " to " + this.end + " END \n");
        }
    }
}
