import java.util.concurrent.*;

/**
 * Created by chiri on 01/06/17.
 */
public class PrimeNumberGenerator {

    public PrimeNumberGenerator(){

    }

    public static boolean isPrimeNumber(double number){
        boolean result = true;
        if( number > 0 ){
            int sqrt = (int)Math.sqrt(number);
            for(int i = 2; i <= sqrt; i++){
                if(number % i == 0){
                    result = false;
                }
            }
        }
        return result;
    }

    public static String calculatePrimeNumbers(double begin, double end){
        double beginTime = System.currentTimeMillis();
        StringBuffer result = new StringBuffer();
        result.append("\n");
        result.append("El tiempo encontrando los numeros primos desde " + begin + " hasta "+ end +" son: \n");
        for(int i = (int)begin ; i <= end; i++){
            if(isPrimeNumber(i)){
                //result.append(i + "\t");
            }
        }
        double endTime = System.currentTimeMillis();
        result.append("Total time: " + Double.toString((endTime - beginTime)*1E-3));
        result.append("\n");
        return result.toString();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ParallelPrimeFinder parallelPrimeFinder = new ParallelPrimeFinder();
        double value = 5000000D; // 10.000.000.000
        double initValue = 2D;

        System.out.println("Secuencial:");
        System.out.println(PrimeNumberGenerator.calculatePrimeNumbers(initValue,value));
        System.out.println("Paralelo:");
        System.out.println(parallelPrimeFinder.findPrimeNumberParallel());
        System.out.println("ForkJoin:");
        System.out.println(RecursivePrimeFinder.forkJoinPrimeFinder(initValue,value));
    }
}
