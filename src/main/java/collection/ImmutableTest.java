package collection;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author: an_zzz
 * @Description: 不可变集合
 * @Date: 2021/9/26 16:57
 */
public class ImmutableTest {

    // ImmutableMap

    @Test
    public void Test() {

        // 创建方式
        ImmutableMap<String, List> immutableMap =
                ImmutableMap.<String, List>builder().put("list", Lists.newArrayList()).build();

        ImmutableMap<String, List> immutableMap1 = ImmutableMap.of("list", Lists.newArrayList());
    }
}
