package agh.po.symulator;

public class Clutch extends Component{
    private Boolean clutchState;

    public void clutchPress() {
        clutchState = true;
    }

    public void clutchRelease() {
        clutchState = false;
    }
}
