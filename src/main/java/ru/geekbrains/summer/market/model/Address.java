package ru.geekbrains.summer.market.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    @OneToOne(mappedBy = "address")
    private Order order;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "phone")
    private String phone;

}

