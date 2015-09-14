package sun.management;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by demos_000 on 15.09.2015.
 */
public class ResourcesMonitor {


    public static void main(String[] args) throws MalformedObjectNameException, ReflectionException, InstanceNotFoundException {

        MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
        ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
        while(true) {
            long time = System.currentTimeMillis();
            AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});


            Attribute att = (Attribute) list.get(0);
            Double value = (Double) att.getValue();
            time = System.currentTimeMillis() - time;
            System.out.println(((int) (value * 1000) / 10.0) + " " + time);
        }
    }
}
