package guru.springframework.spring5mvcrest.api.v1.mapper;

import guru.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import guru.springframework.spring5mvcrest.domain.Vendor;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-06T18:48:23+0530",
    comments = "version: 1.2.0.CR2, compiler: javac, environment: Java 1.8.0_05 (Oracle Corporation)"
)
@Component
public class VendorMapperImpl implements VendorMapper {

    @Override
    public VendorDTO vendorToVendorDTO(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorDTO vendorDTO = new VendorDTO();

        vendorDTO.setName( vendor.getName() );

        return vendorDTO;
    }

    @Override
    public Vendor vendorDTOtoVendor(VendorDTO vendorDTO) {
        if ( vendorDTO == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        vendor.setName( vendorDTO.getName() );

        return vendor;
    }
}
