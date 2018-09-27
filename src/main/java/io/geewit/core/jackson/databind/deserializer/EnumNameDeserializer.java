package io.geewit.core.jackson.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.geewit.core.enums.Name;
import io.geewit.core.utils.EnumUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * @author geewit
 */
@SuppressWarnings({"unused"})
public abstract class EnumNameDeserializer<E extends Enum<E> & Name> extends JsonDeserializer<E> {
    @SuppressWarnings({"unchecked"})
    public EnumNameDeserializer() {
        clazz = (Class <E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<E> clazz;


    @Override
    public E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String name = p.getText();
        return EnumUtils.forToken(clazz, name);
    }
}
