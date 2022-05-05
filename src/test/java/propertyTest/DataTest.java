package propertyTest;

import data.ChangeListener;
import data.Property;

import java.util.List;

public class DataTest {

    private Property<String> stringProperty = new Property<>("meow");

    public DataTest() {
        stringProperty.addListener((property, oldValue, newValue) -> {
            System.out.println("old: " + oldValue + " new: " + newValue);
        });

        System.out.println("stringProperty = " + stringProperty.get());

        stringProperty.set("wuff");
        System.out.println("removing listener");

        List<ChangeListener<String>> listeners = stringProperty.getListeners();
        System.out.println(listeners.size());
        stringProperty.removeListener(listeners.get(0));


        stringProperty.set("miez");

        System.out.println(stringProperty.get());
    }

    public static void main (String... args){
        DataTest test = new DataTest();
    }
}
