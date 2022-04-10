package andrei.krupskiy.myapplication;

import java.util.Date;

public class Massage {
    public String userName;
    public String textMassage;
    private long massageTime;

    public Massage() {}
    public Massage(String userName, String textMassage) {
        this.userName = userName;
        this.textMassage = textMassage;

        this.massageTime = new Date().getTime();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTextMassage() {
        return textMassage;
    }

    public void setTextMassage(String textMassage) {
        this.textMassage = textMassage;
    }

    public long getMassageTime() {
        return massageTime;
    }

    public void setMassageTime(long massageTime) {
        this.massageTime = massageTime;
    }
}
