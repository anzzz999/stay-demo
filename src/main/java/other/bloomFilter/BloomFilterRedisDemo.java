package other.bloomFilter;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import redis.clients.jedis.Jedis;

import java.nio.charset.Charset;

/**
 * 布隆过滤器（Redis版）
 * 放在redis，方便集群判断
 * 优点：由于存放的不是完整的数据，所以占用的内存很少，而且新增，查询速度够快；
 * 缺点： 随着数据的增加，误判率随之增加；无法做到删除数据；只能判断数据是否一定不存在，而无法判断数据是否一定存在
 */
public class BloomFilterRedisDemo {
    static final int expectedInsertions = 10000;
    static final double fpp = 0.01;

    //bit数组长度
    private static long numBits;

    private static int numHashFunctions;

    static {
        numBits = optimalNumOfBits(expectedInsertions, fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);

    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.10", 6379);
        for (int i = 0; i < 10000; i++) {
            long[] indexs = getIndexs(String.valueOf(i));
            for (long index : indexs) {
                jedis.setbit("codebear:bloom", index, true);
            }
        }
        int count = 0;
        for (int i = 10000; i < 20000; i++) {
            long[] indexs = getIndexs(String.valueOf(i));
            for (long index : indexs) {
                Boolean isContain = jedis.getbit("codebear:bloom", index);
                if (!isContain){
                    count++;
                    System.out.println(i + "一定不存在");
                    break;
                }
            }
        }
            System.out.println("一定不存在的有:" + count + "个");
    }

    /**
     * 根据key获取bitmap下标
     */
    private static long[] getIndexs(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    private static long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
    }

    //计算hash函数个数
    private static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    //计算bit数组长度
    private static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }


}
