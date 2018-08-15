package test.futurepattern;

public class RealData implements Data {

    String result;

    public RealData(String param) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(param);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
        result = stringBuffer.toString();
    }

    public String getResult() {
        return result;
    }

}
