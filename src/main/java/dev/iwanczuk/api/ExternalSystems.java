package dev.iwanczuk.api;

public class ExternalSystems {


    private double currentRpm;
    private double angularSpeed = 150;
    private Lights lights = new Lights();

    //pozwoliłem dodoać sobie publiczny modyfikator dostępu, wydaje mi się że powinien on tutaj być w przypadku udostępnionego api :P
    public ExternalSystems() {

    }


    public double getCurrentRpm() {
        //sciagnij RPM z dostepnego miejsca
        return currentRpm;
    }



    public void setCurrentRpm(double currentRpm) {
        this.currentRpm = currentRpm;
    }

    public double getAngularSpeed() {
        return angularSpeed;
    }

    public void setAngularSpeed(double angularSpeed) {
        this.angularSpeed = angularSpeed;
    }

    public Lights getLights() {
        return lights;
    }
}
