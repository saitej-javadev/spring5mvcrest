package guru.springframework.spring5mvcrest.api.v1.mapper;

import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.domain.Category;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-06T18:49:40+0530",
    comments = "version: 1.2.0.CR2, compiler: javac, environment: Java 1.8.0_05 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }
}
