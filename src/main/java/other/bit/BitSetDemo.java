package other.bit;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.RamUsageEstimator;

import java.util.BitSet;

/**
 * @author zhanmingwei
 */
@Slf4j
public class BitSetDemo {
    public static void main(String[] args) {
        int size = 1_000_000_000;
        BitSet bitSet = new BitSet(size);

        // 设置第5个位置为1，表示第5个元素存在
        bitSet.set(5);

        // 检查第5个位置是否已设置
        boolean exists = bitSet.get(5);
        System.out.println("Element at position 5 exists: " + exists);  // 输出: Element at position 5 exists: true

        // 设置从索引10到20的所有位置为1
        bitSet.set(10,20);  // 参数是包含起始点和不包含终点的区间

        // 计算bitset中所有值为1的位的数量，相当于计算设置了的元素个数
        int count = bitSet.cardinality();
        System.out.println("Number of set bits: " + count);

        // 清除第5个位置
        bitSet.clear(5);
        exists = bitSet.get(5);
        System.out.println("Element at position 5 exists: " + exists);

        // 判断位图是否为空
        boolean isEmpty = bitSet.isEmpty();
        System.out.println("Is the bitset empty after clearing some bits? " + isEmpty);

        // 计算对象大小
        log.info("10亿位的bitSet:{}", RamUsageEstimator.humanSizeOf(bitSet));
    }
}
