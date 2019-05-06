import java.io.Serializable;
public class Deltaker implements Serializable{
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
        return "Deltaker{" + ", name=" + name + '}';
    }
    
    
}

