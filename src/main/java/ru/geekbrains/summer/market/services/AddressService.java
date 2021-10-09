package ru.geekbrains.summer.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.summer.market.repositories.AddressRepository;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;


}
