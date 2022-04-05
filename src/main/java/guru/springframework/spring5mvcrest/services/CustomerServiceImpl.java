package guru.springframework.spring5mvcrest.services;


import guru.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.repositories.CustomerRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRespository customerRespository;
    private final CustomerMapper customerMapper;


    public CustomerServiceImpl(CustomerRespository customerRespository, CustomerMapper customerMapper) {
        this.customerRespository = customerRespository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRespository.findById(id).map(customerMapper::customerToCustomerDTO).orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public List<CustomerDTO> getAllcustomers() {
        return customerRespository.findAll().stream().map(customer -> {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl("api/v1/customer/" + customer.getId());
            return customerDTO;
        } ).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
       Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        return saveandReturnDTO(customer);

    }

    private CustomerDTO saveandReturnDTO(Customer customer) {
        Customer savedCustomer = customerRespository.save(customer);
        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDto.setCustomerUrl("/api/v1/customer" + savedCustomer.getId());
        return returnDto;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        return saveandReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRespository.findById(id).map(customer -> {
            if (customerDTO.getFirstname()!=null){
                customer.setFirstname(customerDTO.getFirstname());
            }
            if (customerDTO.getLastname()!=null){
                customer.setLastname(customerDTO.getLastname());
            }
            return customerMapper.customerToCustomerDTO(customerRespository.save(customer));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRespository.deleteById(id);
    }
}
