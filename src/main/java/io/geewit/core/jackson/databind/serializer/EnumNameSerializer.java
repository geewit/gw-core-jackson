package io.geewit.core.jackson.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.geewit.core.utils.enums.Name;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * 枚举类型序列化
 * @author geewit
 */
@SuppressWarnings({"unused"})
@JsonComponent
public class EnumNameSerializer extends JsonSerializer<Name> {
    @Override
    public void serialize(Name name, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        // put your desired money style here
        jgen.writeString(name.toString());
    }
}
