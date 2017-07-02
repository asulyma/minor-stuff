package beans;

import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ColorsBeansInfo extends SimpleBeanInfo {
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor rectangular = new PropertyDescriptor("rectangular", Colors.class);
            return new PropertyDescriptor[]{rectangular};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
