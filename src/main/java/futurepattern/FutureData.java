package futurepattern;

public class FutureData implements Data {

    RealData realData = null;

    boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return realData.getResult();
    }

}
