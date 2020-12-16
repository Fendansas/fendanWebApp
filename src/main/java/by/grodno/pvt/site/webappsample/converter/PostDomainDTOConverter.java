package by.grodno.pvt.site.webappsample.converter;

import by.grodno.pvt.site.webappsample.domain.Post;
import by.grodno.pvt.site.webappsample.dto.PostDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;

public class PostDomainDTOConverter implements Converter<Post, PostDTO> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public PostDTO convert(Post source) {

        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MAPPER.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        return MAPPER.convertValue(source, PostDTO.class);
    }
}
