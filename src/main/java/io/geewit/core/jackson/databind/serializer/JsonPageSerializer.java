package io.geewit.core.jackson.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * org.springframework.data.domain.Page 序列化
 * @author geewit
 */
@SuppressWarnings({"unused"})
@JsonComponent
public class JsonPageSerializer extends JsonSerializer<Page> {
    private final ObjectMapper mapper;
    private final SpringDataWebProperties springDataWebProperties;

    public JsonPageSerializer(ObjectMapper mapper, SpringDataWebProperties springDataWebProperties) {
        this.mapper = mapper;
        this.springDataWebProperties = springDataWebProperties;
    }

    @Override
    public void serialize(Page page, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        String sizeParameter = "size";
        String pageParameter = "number";
        if(springDataWebProperties != null) {
            SpringDataWebProperties.Pageable pageable = springDataWebProperties.getPageable();
            if(pageable != null) {
                sizeParameter = pageable.getSizeParameter();
                pageParameter = pageable.getPageParameter();
            }
        }
        generator.writeFieldName(sizeParameter);
        generator.writeNumber(page.getSize());

        generator.writeFieldName(pageParameter);
        generator.writeNumber(page.getNumber());
        generator.writeFieldName("totalElements");
        generator.writeNumber(page.getTotalElements());
        generator.writeFieldName("last");
        generator.writeBoolean(page.isLast());
        generator.writeFieldName("totalPages");
        generator.writeNumber(page.getTotalPages());
        generator.writeObjectField("sort", page.getSort());
        generator.writeFieldName("first");
        generator.writeBoolean(page.isFirst());
        generator.writeFieldName("numberOfElements");
        generator.writeNumber(page.getNumberOfElements());
        generator.writeFieldName("content");
        String json;
        if(provider.getActiveView() != null) {
            boolean defaultViewInclusionEnabled = mapper.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
            if(defaultViewInclusionEnabled) {
                mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            }
            json = mapper.writerWithView(provider.getActiveView()).writeValueAsString(page.getContent());

            if(defaultViewInclusionEnabled) {
                mapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            }
        } else {
            json = mapper.writeValueAsString(page.getContent());
        }
        generator.writeRawValue(json);
        generator.writeEndObject();
    }

}
