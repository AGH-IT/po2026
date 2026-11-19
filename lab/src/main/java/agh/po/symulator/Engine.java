package agh.po.symulator;

public class Engine extends Component{
    private Integer revolutions;
    private Integer revolutionsMax;

    public void engineOn() {
        revolutions = 1000;
    }

    public void engineOff(){
        revolutions = 0;
    }
}
