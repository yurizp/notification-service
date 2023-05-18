package br.com.vibbra.notificationservice.config.tostring;

import org.flywaydb.core.internal.util.JsonUtils;

public class Objects {
    public static String toString(Object o) {
        return JsonUtils.getGson()
                .newBuilder()
                .setExclusionStrategies(new AnnotationExclusionStrategy())
                .create()
                .toJson(o);
    }
}
