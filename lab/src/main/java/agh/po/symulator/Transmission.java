package agh.po.symulator;

public class Transmission extends Component{
    private Integer gear;
    private Integer gearNumber;

    public void shiftUp() {
        if (gear < gearNumber) {
            gear++;
        }
    }

    public void shiftDown() {
        if (gear > -1){
            gear--;
        }
    }
}
