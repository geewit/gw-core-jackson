package io.geewit.core.jackson.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.geewit.core.utils.enums.EnumUtils;
import io.geewit.core.utils.enums.Value;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * 枚举类型反序列化
 * @author geewit
 */
@SuppressWarnings({"unused"})
public abstract class EnumValueDeserializer<E extends Enum<E> & Value> extends JsonDeserializer<E> {
    @SuppressWarnings({"unchecked"})
    public EnumValueDeserializer() {
        clazz = (Class <E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<E> clazz;


    @Override
    public E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue();
        return EnumUtils.forToken(clazz, value);
    }
}
