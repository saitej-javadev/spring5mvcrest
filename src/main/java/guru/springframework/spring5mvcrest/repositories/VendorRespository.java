package guru.springframework.spring5mvcrest.repositories;

import guru.springframework.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRespository extends JpaRepository<Vendor,Long> {
}
