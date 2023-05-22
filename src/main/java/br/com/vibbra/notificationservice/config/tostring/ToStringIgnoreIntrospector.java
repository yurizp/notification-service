package br.com.vibbra.notificationservice.config.tostring;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class ToStringIgnoreIntrospector extends JacksonAnnotationIntrospector {
    @Override
    protected boolean _isIgnorable(Annotated a) {
        boolean isIgnorable = super._isIgnorable(a);
        if (!isIgnorable) {
            ToStringIgnore ann = a.getAnnotation(ToStringIgnore.class);
            isIgnorable = ann != null;
        }
        return isIgnorable;
    }
}
