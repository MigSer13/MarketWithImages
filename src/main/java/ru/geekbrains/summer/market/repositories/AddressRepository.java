package ru.geekbrains.summer.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.summer.market.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
