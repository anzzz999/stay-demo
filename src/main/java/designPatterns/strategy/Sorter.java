package designPatterns.strategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanmingwei
 */
public class Sorter {
    private static final long GB = 1000 * 1000 * 1000;

    /*public void sortFile(String filePath) {
        // 省略校验逻辑
        File file = new File(filePath);
        long fileSize = file.length();
        ISortAlg sortAlg;
        if (fileSize < 6 * GB) { // [0, 6GB)
            sortAlg = new QuickSort();
        } else if (fileSize < 10 * GB) { // [6GB, 10GB)
            sortAlg = new ExternalSort();
        } else if (fileSize < 100 * GB) { // [10GB, 100GB)
            sortAlg = new ConcurrentExternalSort();
        } else { // [100GB, ~)
            sortAlg = new MapReduceSort();
        }
        sortAlg.sort(filePath);
    }*/

//    public void sortFile(String filePath) {
//        // 省略校验逻辑
//        File file = new File(filePath);
//        long fileSize = file.length();
//        ISortAlg sortAlg;
//        if (fileSize < 6 * GB) { // [0, 6GB)
//            sortAlg = SortAlgFactory.getSortAlg("QuickSort");
//        } else if (fileSize < 10 * GB) { // [6GB, 10GB)
//            sortAlg = SortAlgFactory.getSortAlg("ExternalSort");
//        } else if (fileSize < 100 * GB) { // [10GB, 100GB)
//            sortAlg = SortAlgFactory.getSortAlg("ConcurrentExternalSort");
//        } else { // [100GB, ~)
//            sortAlg = SortAlgFactory.getSortAlg("MapReduceSort");
//        }
//        sortAlg.sort(filePath);
//    }

    private static final List<AlgRange> algs = new ArrayList<>();

    static {
        algs.add(new AlgRange(0, 6 * GB, SortAlgFactory.getSortAlg("QuickSort")));
        algs.add(new AlgRange(6 * GB, 10 * GB, SortAlgFactory.getSortAlg("ExternalSort")));
        algs.add(new AlgRange(10 * GB, 100 * GB, SortAlgFactory.getSortAlg("ConcurrentExternalSort")));
        algs.add(new AlgRange(100 * GB, Long.MAX_VALUE, SortAlgFactory.getSortAlg("MapReduceSort")));
    }

    public void sortFile(String filePath) {
        // 省略校验逻辑
        File file = new File(filePath);
        long fileSize = file.length();
        ISortAlg sortAlg = null;
        for (AlgRange algRange : algs) {
            if (algRange.inRange(fileSize)) {
                sortAlg = algRange.getAlg();
                break;
            }
        }
        sortAlg.sort(filePath);

    }

    public static class AlgRange {
        private long start;
        private long end;
        private ISortAlg alg;

        public AlgRange(long start, long end, ISortAlg alg) {
            this.start = start;
            this.end = end;
            this.alg = alg;
        }

        public ISortAlg getAlg() {
            return alg;
        }

        public boolean inRange(long size) {
            return size >= start && size < end;
        }
    }


}