package io.geewit.core.jackson.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.geewit.core.utils.lang.enums.EnumUtils;
import io.geewit.core.utils.lang.enums.Name;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * 枚举类型反序列化
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
