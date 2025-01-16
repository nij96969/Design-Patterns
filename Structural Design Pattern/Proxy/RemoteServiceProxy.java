import java.util.Random;
import java.util.concurrent.TimeUnit;

interface ApiService {
    String fetchData(String end_point);
}

class RealApiService implements ApiService{
    @Override
    public String fetchData(String end_point) {
        System.out.println("Making Call to : " + end_point);

        // Simulate random failure
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Simulated random failure");
        }

        return "Data from " + end_point;
    }
}

class ApiServiceProxy implements ApiService{
    private RealApiService realApiService;
    private int retry_count;
    private long delayBetweenCalls;

    ApiServiceProxy(int retry_count , int delayBetweenCalls){
        this.realApiService = new RealApiService();
        this.retry_count = retry_count;
        this.delayBetweenCalls = delayBetweenCalls;
    }

    @Override
    public String fetchData(String end_point) {
        int attempts = 0;

        while(attempts < retry_count){
            try{
                if (attempts > 0) { //Rate Limit for failed attaempts
                    TimeUnit.MILLISECONDS.sleep(delayBetweenCalls);
                }
                
                // int c = 1 / 0; // To execute failures

                String result = realApiService.fetchData(end_point);
                //Rate Limiting for succesful calls
                TimeUnit.MILLISECONDS.sleep(delayBetweenCalls);
                return result;
            }
            catch(Exception e){
                attempts++;

                System.out.println("Attempt : " + attempts + " | Retrieval failed...");

                if(attempts == retry_count) System.out.println("All Attempts Exhausted");
            }
        }
        return "Faield to fetch Data from " + end_point;
    }
}

public class RemoteServiceProxy {

    public static void main(String[] args) {
        ApiService apiService = new ApiServiceProxy(3 , 500);
        String result = apiService.fetchData("https://example.com/api/data");
        System.out.println(result);
        System.out.println(apiService.fetchData("https://example.com/api/data"));
        System.out.println(apiService.fetchData("https://example.com/api/data"));
        System.out.println(apiService.fetchData("https://example.com/api/data"));
        System.out.println(apiService.fetchData("https://example.com/api/data"));
        System.out.println(apiService.fetchData("https://example.com/api/data"));
        System.out.println(apiService.fetchData("https://example.com/api/data"));
    }
}