import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursivePrimeFinder extends RecursiveTask<String> {

    private static final double SEQUENTIAL_THRESHOLD = 1250000L;
    private double first, last;

    public RecursivePrimeFinder(double first, double last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected String compute() {
        if((last - first) <= SEQUENTIAL_THRESHOLD){
            return PrimeNumberGenerator.calculatePrimeNumbers(first,last);
        } else {
            double mid = first + (last - first) / 2;
            //System.out.println("Mid: " + mid);
            RecursivePrimeFinder left = new RecursivePrimeFinder(first,mid);
            RecursivePrimeFinder right = new RecursivePrimeFinder(mid+1,last);
            left.fork();
            String rightRes = right.compute();
            String leftRes = left.join();
            return leftRes + rightRes;
        }
    }

    public static String forkJoinPrimeFinder(double first, double last){
        return ForkJoinPool.commonPool().invoke(new RecursivePrimeFinder(first,last));
    }
}