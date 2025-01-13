package statics.app;

import java.util.Collection;
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
}
