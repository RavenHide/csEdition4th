import org.junit.Test;
import sun.misc.FloatingDecimal;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {


    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, 5, TimeUnit.SECONDS);
//        Property p1 = new Property("叫了个鸡", 1000, 500, 2);
//        Property p2 = new Property("张三丰饺子馆", 2300, 1500, 3);
//        Property p3 = new Property("永和大王", 580, 3000, 1);
//        Property p4 = new Property("肯德基", 6000, 200, 4);
//        List<Property> properties = Arrays.asList(p1, p2, p3, p4);
//        showLog(date2String(new Date(118, 3, 2), null));
//        List<Property> psfs = properties.stream()
//                .sorted(Comparator.comparingInt(x -> x.distance))
//                .limit(2)
//                .reduce()
//                .collect(Collectors.toList());


//        showLog(name);


//        String d = null;
////        Optional o = Optional.ofNullable(d);
////        showLog(o.orElse("isNull"));
//        StringBuilder s = new StringBuilder("hello");
//
//        s.insert(1, "我");
//        showLog(ITest.instance.InitDev("COM1", 115200l));
    }

    public static <T> void showLog(T o) {
        System.out.println(o);
    }

    public static String date2String(Date date, String format) {
        Optional<String> fm = Optional.ofNullable(format);
        SimpleDateFormat fmt = new SimpleDateFormat(fm.orElse("yyyy-MM-dd"));
        Optional<Date> opDate = Optional.ofNullable(date);

        return fmt.format(opDate.orElse(new Date()));
    }
}
