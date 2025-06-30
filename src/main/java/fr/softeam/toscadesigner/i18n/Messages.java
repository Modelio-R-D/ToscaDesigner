package fr.softeam.toscadesigner.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e2890b80-cdf8-4ac5-a0a4-03779d942d96")
public class Messages {
    @objid ("5d8ff171-09c7-46f4-9efd-572968ca8619")
    private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle ("fr.softeam.toscadesigner.i18n.messages");

    @objid ("a6c3a219-1f15-43d1-b9e8-fb7c55d5333e")
    private  Messages() {
        
    }

    @objid ("bd9cca81-9342-440a-8369-243a874896b4")
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString (key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    @objid ("2763e3c9-b55e-41ca-93d0-cbe5b32b841c")
    public static String getString(final String key, final String... params) {
        try {
            return MessageFormat.format (RESOURCE_BUNDLE.getString (key),(Object[]) params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

}
