package concurrent.lock;

import javax.xml.crypto.Data;
import java.util.concurrent.locks.StampedLock;

public class StampedSample {
    private final StampedLock sl = new StampedLock();
    void mutate() {
        long samp = sl.writeLock();
        try {
//            write();
        } finally {
            sl.unlockWrite(samp);
        }
    }
    Data access() {
        long samp = sl.tryOptimisticRead();
        Data data = null;
//        Data data = read();
        if (!sl.validate(samp)) {
            samp = sl.readLock();
            try {
//                data = read();
            } finally {
                sl.unlockRead(samp);
            }
        }
        return data;
    }
    // …
}