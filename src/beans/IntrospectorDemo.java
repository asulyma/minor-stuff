package beans;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class IntrospectorDemo {
    public static void main(String[] args) {
        try {
            Colors colors = new Colors();
            Class c = colors.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(c);

            System.out.println("Properties: ");
            PropertyDescriptor propertyDescriptor[] = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor aPropertyDescriptor : propertyDescriptor) {
                System.out.println("\t" + aPropertyDescriptor.getName());
            }

            System.out.println("Events: ");
            EventSetDescriptor eventSetDescriptor[] = beanInfo.getEventSetDescriptors();
            for (EventSetDescriptor anEventSetDescriptor : eventSetDescriptor) {
                System.out.println("\t" + anEventSetDescriptor.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
