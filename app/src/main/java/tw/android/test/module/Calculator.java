package tw.android.test.module;

/**
 * Created by hermes.hsieh on 2017/11/3.
 */

public class Calculator {

    /**
     * @param integer
     * @param integer2
     * @return
     */
    public int add(Integer integer, Integer integer2) {
        if (integer == null || integer2 == null) {
            throw new RuntimeException("input integer == null or integer2 == null");
        }
        return integer + integer2;
    }

    /**
     * Integer1 - Integer2
     *
     * @param integer
     * @param integer2
     * @return
     */
    public int minus(Integer integer, Integer integer2) {
        return integer - integer2;
    }

    /**
     * @param integer
     * @param integer2
     * @return
     */
    public int multiple(Integer integer, Integer integer2) {
        return integer * integer2;
    }

    public int division(Integer integer, Integer integer2) {
        return integer / integer2;
    }
}
