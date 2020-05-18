package io.geewit.core.jackson.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

/**
 * org.springframework.data.domain.Page 序列化
 * @author geewit
 */
@SuppressWarnings({"unused"})
@EnableConfigurationProperties({SpringDataWebProperties.class})
@JsonComponent
public class JsonPageSerializer extends JsonSerializer<Page> {

    private final ObjectMapper mapper;

    public JsonPageSerializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired(required = false)
    protected SpringDataWebProperties springDataWebProperties;

    @Override
    public void serialize(Page page, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        String sizeParameter = "size";
        String pageParameter = "number";
        if(springDataWebProperties != null) {
            SpringDataWebProperties.Pageable pageable = springDataWebProperties.getPageable();
            if(pageable != null) {
                sizeParameter = pageable.getSizeParameter();
                pageParameter = pageable.getPageParameter();
            }
        }
        jsonGen.writeFieldName(sizeParameter);
        jsonGen.writeNumber(page.getSize());

        jsonGen.writeFieldName(pageParameter);
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
        jsonGen.writeRawValue(mapper.writerWithView(serializerProvider.getActiveView()).writeValueAsString(page.getContent()));
        jsonGen.writeEndObject();
    }

}
