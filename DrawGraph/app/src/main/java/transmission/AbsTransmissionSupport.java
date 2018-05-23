package transmission;

import java.util.List;

/**
 * Created by liuxiaolong on 15/8/6.
 */
public interface AbsTransmissionSupport<T> {

    public List<T> getTotalAtomic();

    public int getDuration();

    public void go(List<T> atomics);
}
