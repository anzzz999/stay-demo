package other.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器（Guava版）
 * 优点：由于存放的不是完整的数据，所以占用的内存很少，而且新增，查询速度够快；
 * 缺点： 随着数据的增加，误判率随之增加；无法做到删除数据；只能判断数据是否一定不存在，而无法判断数据是否一定存在
 */
public class BloomFilterDemo {

    private static int size = 1000_000;//预计要插入多少数据

    private static double fpp = 0.01;//期望的误判率

//    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    public static void main(String[] args) {
        //插入100w数据
        for (int i = 0; i < 1000_000; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        //检查100w条数据
        for (int i = 1000_000; i < 2000_000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);
    }
}
