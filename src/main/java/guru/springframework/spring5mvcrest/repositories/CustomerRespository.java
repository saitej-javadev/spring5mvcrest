package guru.springframework.spring5mvcrest.repositories;

import guru.springframework.spring5mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<Customer,Long> {
}
