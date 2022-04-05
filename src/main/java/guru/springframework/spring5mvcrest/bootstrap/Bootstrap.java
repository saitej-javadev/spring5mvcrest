package guru.springframework.spring5mvcrest.bootstrap;

import guru.springframework.spring5mvcrest.domain.Category;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.domain.Vendor;
import guru.springframework.spring5mvcrest.repositories.CategoryRepository;
import guru.springframework.spring5mvcrest.repositories.CustomerRespository;
import guru.springframework.spring5mvcrest.repositories.VendorRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRespository customerRespository;
    private final VendorRespository vendorRespository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRespository customerRespository, VendorRespository vendorRespository) {
        this.categoryRepository = categoryRepository;
        this.customerRespository = customerRespository;
        this.vendorRespository = vendorRespository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();


        loadCustomers();

        loadVendors();

    }




    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRespository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRespository.save(vendor2);

        System.out.println("Vendors Loaded: " + vendorRespository.count());

    }

    private void loadCustomers() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstname("Virat");
        customer.setLastname("kohil");
        customerRespository.save(customer);
        Customer customer1 = new Customer();
        customer1.setId(2L);
        customer1.setFirstname("Rohit");
        customer1.setLastname("Sharma");
        customerRespository.save(customer1);

        System.out.println("Customers Loaded: " + customerRespository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data loaded " + categoryRepository.count());
    }


}
