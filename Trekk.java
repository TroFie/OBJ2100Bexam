import java.io.Serializable;
import java.util.ArrayList;

public class Trekk implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2432846986194274977L;
	String trekk;
    

    Trekk(){}
   
    Trekk(String trekk){
        setTrekk(trekk);
    }

    public String getTrekk() {
        return trekk;
    }

    public void setTrekk(String trekk) {
        this.trekk = trekk;
    }

    public String toString() {
        return trekk;
    }
    
}
