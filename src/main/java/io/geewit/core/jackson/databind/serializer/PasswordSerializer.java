package io.geewit.core.jackson.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * 密码加密
 * @author geewit
 * @since  2016/12/20
 */
@SuppressWarnings({"unused"})
@JsonComponent
public class PasswordSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        // put your desired money style here
        if(value != null) {
            String decodePassword = StringUtils.repeat('*', value.length());
            jgen.writeString(decodePassword);
        } else {
            jgen.writeString(StringUtils.EMPTY);
        }
    }
}
