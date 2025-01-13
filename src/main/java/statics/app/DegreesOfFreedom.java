package statics.app;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public enum DegreesOfFreedom {
    X,
    Y,
    M;

    public static Collection<DegreesOfFreedom> all(){
        return List.of(X,Y,M);
    }

    public static Collection<DegreesOfFreedom> translate(){
        return List.of(X,Y);
    }

    public static Collection<DegreesOfFreedom> rotate(){
        return List.of(M);
    }

    public static Collection<DegreesOfFreedom> none(){
        return List.of();
    }

    public static Collection<DegreesOfFreedom> x(){
        return List.of(X);
    }

    public static Collection<DegreesOfFreedom> y(){
        return List.of(Y);
    }

    public static Collection<DegreesOfFreedom> xr(){
        return List.of(X,M);
    }

    public static Collection<DegreesOfFreedom> yr(){
        return List.of(Y,M);
    }

    public static Collection<DegreesOfFreedom> subtract(
            Collection<DegreesOfFreedom> pD1,
            Collection<DegreesOfFreedom> pD2
    ){
        List<DegreesOfFreedom> ret = new LinkedList<>(pD1);

        for (DegreesOfFreedom deg : ret){
            if(pD2.contains(deg)){
                ret.remove(deg);
            }
        }

        return ret;
    }

    public static Collection<DegreesOfFreedom> opposite(Collection<DegreesOfFreedom> pD){
        return subtract(all(),pD);
    }

    public static boolean equal(
            Collection<DegreesOfFreedom> pD1,
            Collection<DegreesOfFreedom> pD2
    ){
        return pD1.containsAll(pD2)&&pD2.containsAll(pD1);
    }
}
