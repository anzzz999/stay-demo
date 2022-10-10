package collection;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: zhanmingwei
 */
@Slf4j
public class PriorityQueueDemo {

    @Test
    public void test0(){
        List<Object> list = Arrays.asList(null);
        System.out.println(list);
    }


    @Test
    public void test1(){
        // 小顶堆：每个结点的值都小于或等于其左右孩子结点的值
        int[] arr = {1, 5, 6, 87, 12, 45, 15, 7, 56, 98, 784, 125, 435};
        int k = 5;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : arr) {
            if (queue.size() < k) {
                queue.add(num);
            } else if (queue.peek() < num){
                queue.poll();
                queue.add(num);
            }
        }
        log.info("priorityQueue:{}", queue);
    }

    @Test
    public void test2(){
        // 大顶堆：每个结点的值都大于或等于其左右孩子结点的值
        int[] arr = {1, 5, 6, 87, 12, 45, 15, 7, 56, 98, 784, 125, 435};
        int k = 5;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : arr) {
            if (queue.size() < k) {
                queue.add(num);
            } else if (queue.peek() > num){
                queue.poll();
                queue.add(num);
            }
        }
        log.info("priorityQueue:{}",queue);
        log.info("priorityQueue:{}", Arrays.toString(queue.toArray()));
    }
}
