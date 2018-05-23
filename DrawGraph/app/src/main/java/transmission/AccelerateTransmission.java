package transmission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxiaolong on 15/8/10.
 */
public class AccelerateTransmission extends Thread implements AbsTransmission {

    private AbsTransmissionSupport absTransmissionSupport;
    private static final int FLIPTIME = 50;

    public AccelerateTransmission() {
        setDaemon(true);
    }

    @Override
    public void transmission(AbsTransmissionSupport transmissionSupport) {
        absTransmissionSupport = transmissionSupport;
        start();
    }

    @Override
    public void run() {
        super.run();
        int sec = absTransmissionSupport.getDuration();
        int scale = sec / FLIPTIME;
        List<List<? extends Object>> lists = dataGrouping(scale, absTransmissionSupport.getTotalAtomic());
        for (int i = 0; i < lists.size(); i++) {
            absTransmissionSupport.go(lists.get(i));
            try {
                Thread.sleep(FLIPTIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加速分组算法。
     *
     * @param group
     * @param orginal
     * @return
     */
    public List<List<? extends Object>> dataGrouping(int group, List<? extends Object> orginal) {
        List<List<? extends Object>> lists = new ArrayList<>();
        int o = orginal.size() / group;
        for (int i = 0; i < group; i++) {
            int k = i + 1;
            int s = k * o;
            if (k == group) {
                s = orginal.size();
            }
            List<? extends Object> bs = orginal.subList(0, s);
            lists.add(bs);
        }
        return lists;
    }
}
