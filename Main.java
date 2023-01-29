/**
 * Multi-threading sum
 * @author Angel Salazar (A00513236)
 */
public class Main {

    /**
     * createListOfNumbers 
     * creates an array of the given size
     * which contains integers that are bound in the given range
     * @param size desire array size
     * @param min specifies the minimum value of the randomly generated integer
     * @param max specifies the maximum value of the randomly generated integer
     * @return int[]
     */
    public static int[] createListOfNumbers(int size, int min, int max) {
        // Initializing array of the given size
        int[] output = new int[size];

        // populating array with random numbers
        // that are bound to the lower and upper bound
        for (Integer i = 0; i < size; i++) {
            output[i] = (int)(
                Math.floor(
                    Math.random() * (max - min + 1) + min
                )
            );
        }

        // return array
        return output;
    }

    /**
     * parallelSum
     * Sums to arrays. computing the output array is based on threads where each thread will process 
     * a slice of the given arrays (this specify by the chunkSize)
     * 
     * 
     * @param output holds the sum of the array a + array b
     * @param a array to sum
     * @param b array to sum
     * @param chunkSize specifies the slices of the array that should be processed by a threat
     */
    public static void parallelSum(int[] output, int[] a, int[] b, int chunkSize) {
        // computing the array length
        // it is assumed that the arrays a and b have the same size
        int arrayLength = a.length;

        // computing the number of threads to comput the output array
        int chunks = arrayLength / chunkSize;
        
        for (int i = 0; i < chunks; i++) {
            // computing the slice of the array 
            // will be processed by a thread
            int start = i * chunkSize;
            int end = (i * chunkSize) + chunkSize;

            System.out.println("Start: " + start + " end: " + end);

            // initializng ThreadSum
            // specifying the slice of the array will processed
            ThreadSum worker = new ThreadSum(output, a, b, start, end);
            // running thread
            worker.start();
        }
    }
    

    public static void main(String[] args) throws InterruptedException {
        // specifying the length of the arrays
        int size = 10;
        // creating an array of intenger whose values will be between 1 and 10
        int[] a = createListOfNumbers(size, 1, 10);
        // creating an array of intenger whose values will be between 20 and 30
        int[] b = createListOfNumbers(size, 20, 30);
        // initializing output array
        int[] output = new int[size];
        
        // executing sum in parallale
        parallelSum(output, a, b, 5);
    }

}