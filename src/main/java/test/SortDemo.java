package test;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SortDemo {


    @Test
    public void test(){
        String [] arr = new String[]{"2020,张三,10","2020,李四,20","2020,赵吴,50","2020,张三,11","2020,李四,5","2020,赵吴,99",};
        HashMap<String, Double> map = new HashMap<>();
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (String s : arr) {
            String[] split = s.split(",");
            Double number = map.get(split[1]);
            if (number == null) {
                map.put(split[1], Double.valueOf(split[2]));
            }else {
                map.put(split[1], (number+Double.parseDouble(split[2]))/2);
            }
        }
        System.out.println("平均分-----------------");
        ArrayList<Double> list = new ArrayList<>();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            list.add(entry.getValue());
        }
        list.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("排序-----------------");
        for (Double aDouble : list) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                if (aDouble.equals(entry.getValue())){
                    System.out.println(entry.getKey()+":"+entry.getValue());
                    break;
                }
            }
        }
    }

    @Test
    public void test2(){
        String [] arr = new String[]{"2020,张三,10","2020,张三,50","2020,李四,50","2020,王五,100",};
        HashMap<String, Pair<Double, Integer>> map = new HashMap<>();
        List<Pair<String, Double>> list = new ArrayList<>();
        System.out.println("平均分-----------------");
        for (String s : arr) {
            String[] split = s.split(",");
            Pair<Double, Integer> integerPair = map.get(split[1]);
            if (integerPair == null) {
                map.put(split[1], new Pair<Double, Integer>(Double.valueOf(split[2]),1));
            }else {
                map.put(split[1], new Pair<Double, Integer>(Double.parseDouble(split[2]) + integerPair.getKey(),1 + integerPair.getValue()));
            }
            list.add(new Pair<>(split[1], Double.parseDouble(split[2])));
        }
        for (Map.Entry<String, Pair<Double, Integer>> pairEntry : map.entrySet()) {
            System.out.println(pairEntry.getKey() + " : " + pairEntry.getValue().getKey()/pairEntry.getValue().getValue());
        }

//        System.out.println("排序-----------------");
//        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
//        list.sort(new Comparator<Pair<String, Double>>() {
//            @Override
//            public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
//                return 0;
//            }
//        });
//        for (Pair<String, Double> stringDoublePair : list) {
//            System.out.println(stringDoublePair.getKey() +" : "+stringDoublePair.getValue());
//        }
    }

    @Test
    public void test3(){
        String [] arr = new String[]{"2020,张三,10","2020,张三,50","2020,李四,50","2020,王五,100",};
        List<Pair<String, Double>> list = new ArrayList<>();
        System.out.println("平均分-----------------");
        for (String s : arr) {
            String[] split = s.split(",");
            list.add(new Pair<>(split[1], Double.parseDouble(split[2])));
        }
        HashSet<String> set = new HashSet<>();
        //双指针
        for (int i = 0; i < list.size(); i++) {
            if (set.contains(list.get(i).getKey())){
                continue;
            }
            set.add(list.get(i).getKey());
            Double total = 0.0;
            int number = 0;
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).getKey().equals(list.get(j).getKey())){
                    total += list.get(j).getValue();
                    number++;
                }
            }
            System.out.println(list.get(i).getKey()+":"+total/number);
        }
    }

    @Test
    public void test4(){
        long startTime = System.currentTimeMillis(); //程序开始记录时间
        for (int i = 0; i < 1000; i++) {
            test2();
        }
        long endTime1   = System.currentTimeMillis(); //程序结束记录时间
        for (int i = 0; i < 1000; i++) {
            test3();
        }
        long endTime2   = System.currentTimeMillis(); //程序结束记录时间
        long totalTest2Time = endTime1 - startTime;       //总消耗时间
        long totalTest3Time = endTime2 - endTime1;       //总消耗时间
        System.out.println("------------------------------");
        System.out.println(totalTest2Time);
        System.out.println(totalTest3Time);
    }
}
