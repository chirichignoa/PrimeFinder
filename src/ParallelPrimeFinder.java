import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by chiri on 07/06/17.
 */
public class ParallelPrimeFinder {


    private int NUMBER_OF_CORES = 4;
    private ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_CORES);

    public ParallelPrimeFinder(){

    }

    public String findPrimeNumberParallel() throws ExecutionException, InterruptedException {
        Future<String> future1 = executor.submit(
                () -> {
//                         return PrimeNumberGenerator.calculatePrimeNumbers(2,2000000);
                    return PrimeNumberGenerator.calculatePrimeNumbers(2,1250000);
                }
        );

        Future<String> future2 =executor.submit(
                () -> {
//                         return PrimeNumberGenerator.calculatePrimeNumbers(2000001,3000000);
                    return PrimeNumberGenerator.calculatePrimeNumbers(1250001,2500000);
                }
        );

        Future<String> future3 =executor.submit(
                () -> {
//                        return PrimeNumberGenerator.calculatePrimeNumbers(3000001,4500000);
                    return PrimeNumberGenerator.calculatePrimeNumbers(2500001,3750000);
                }
        );

        Future<String> future4 =executor.submit(
                () -> {
//                         return PrimeNumberGenerator.calculatePrimeNumbers(4500000,5000000);
                    return PrimeNumberGenerator.calculatePrimeNumbers(3750001,5000000);
                }
        );

        return future1.get() + future2.get() + future3.get() + future4.get() ;
    }
}
