import java.io.Serializable;
public class Deltaker implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -749426538439538803L;
	String name;
    Double score;

    Deltaker(){}
   
    Deltaker(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }
    
    
}

