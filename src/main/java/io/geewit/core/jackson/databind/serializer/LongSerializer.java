package io.geewit.core.jackson.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 *  Long 序列化为 String
 *  @author geewit
 */
@JsonComponent
public class LongSerializer extends StdSerializer<Long> {
    private final static Logger logger = LoggerFactory.getLogger(LongSerializer.class);

    public LongSerializer() {
        super(Long.class);
    }

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) {
        if (value.toString().length() > 15) {
            try {
                gen.writeString(value.toString());
            } catch (IOException e) {
                logger.warn(e.getMessage());
            }
        } else {
            try {
                gen.writeNumber(value);
            } catch (IOException e) {
                logger.warn(e.getMessage());
            }
        }
    }
}
