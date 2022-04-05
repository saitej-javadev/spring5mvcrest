package guru.springframework.spring5mvcrest.services;

import guru.springframework.spring5mvcrest.api.v1.mapper.VendorMapper;
import guru.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import guru.springframework.spring5mvcrest.api.v1.model.VendorListDTO;
import guru.springframework.spring5mvcrest.controllers.v1.VendorController;
import guru.springframework.spring5mvcrest.domain.Vendor;
import guru.springframework.spring5mvcrest.repositories.VendorRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRespository vendorRespository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRespository vendorRespository, VendorMapper vendorMapper) {
        this.vendorRespository = vendorRespository;
        this.vendorMapper = vendorMapper;
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;

    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRespository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                 return vendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDTO getAllVendors() {

     List<VendorDTO> vendorDTOs =  vendorRespository.findAll()
                .stream()
                .map(vendor -> {VendorDTO vendorDTO=vendorMapper.vendorToVendorDTO(vendor);
                vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                return  vendorDTO;
                }).collect(Collectors.toList());

        return new VendorListDTO(vendorDTOs);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveandReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
    }


    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

        Vendor vendorToSave = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveandReturnDTO(vendorToSave);
    }

    private VendorDTO saveandReturnDTO(Vendor vendor) {

        Vendor savedVendor = vendorRespository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRespository.findById(id)
             .map(vendor -> {
                 if (vendorDTO.getName() != null){
                     vendor.setName(vendorDTO.getName());
                 }

                 return saveandReturnDTO(vendor);
             }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRespository.deleteById(id);
    }
}
