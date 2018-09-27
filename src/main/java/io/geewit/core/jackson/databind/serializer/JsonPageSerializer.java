package io.geewit.core.jackson.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.geewit.core.utils.JsonUtils;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * @author geewit
 */
@SuppressWarnings({"unused"})
@JsonComponent
public class JsonPageSerializer extends JsonSerializer<Page> {

    @Override
    public void serialize(Page page, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeFieldName("size");
        jsonGen.writeNumber(page.getSize());
        jsonGen.writeFieldName("number");
        jsonGen.writeNumber(page.getNumber());
        jsonGen.writeFieldName("totalElements");
        jsonGen.writeNumber(page.getTotalElements());
        jsonGen.writeFieldName("last");
        jsonGen.writeBoolean(page.isLast());
        jsonGen.writeFieldName("totalPages");
        jsonGen.writeNumber(page.getTotalPages());
        jsonGen.writeObjectField("sort", page.getSort());
        jsonGen.writeFieldName("first");
        jsonGen.writeBoolean(page.isFirst());
        jsonGen.writeFieldName("numberOfElements");
        jsonGen.writeNumber(page.getNumberOfElements());
        jsonGen.writeFieldName("content");
        jsonGen.writeRawValue(JsonUtils.toJson(page.getContent(), serializerProvider.getActiveView()));
        jsonGen.writeEndObject();
    }

}
