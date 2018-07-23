import com.sun.jna.Library;
import com.sun.jna.Native;

public interface ITest extends Library{
    ITest instance = Native.loadLibrary("Lib160", ITest.class);
    public String InitDev(String comport, long bauRate);
}
