package io.geewit.core.jackson.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.geewit.core.utils.lang.enums.EnumUtils;
import io.geewit.core.utils.lang.enums.Value;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * @author geewit
 */
@SuppressWarnings({"unused"})
@JsonComponent
public class LongDeserializer extends JsonDeserializer<Long> {
    @SuppressWarnings({"unchecked"})
    public LongDeserializer() {
    }


    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getValueAsString();
        try {
            Long value = Long.parseLong(text);
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unknown text '" + text + "' for long");
        }
    }
}
