import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MytestMutliThread implements Runnable {

    @Override
    public void run() {
        String url = "http://www.baidu.com";
        //System.out.println(url);
        // String json = HttpRequestUtil.doGet(url,"UTF-8");
        JsonResults jsonResults = null;

        try {
            String response = HttpRequestUtil.doGet(url, "UTF-8");
            System.out.println(response);

           // System.out.println("访问结果是：" + jsonObject.get("STATUS"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++)
            service.execute(new MytestMutliThread());//并发1000个用户
    }
}