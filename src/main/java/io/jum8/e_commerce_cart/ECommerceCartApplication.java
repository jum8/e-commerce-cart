package io.jum8.e_commerce_cart;

import io.jum8.e_commerce_cart.domain.Customer;
import io.jum8.e_commerce_cart.domain.Product;
import io.jum8.e_commerce_cart.domain.PromotionalDate;
import io.jum8.e_commerce_cart.repos.CustomerRepository;
import io.jum8.e_commerce_cart.repos.ProductRepository;
import io.jum8.e_commerce_cart.repos.PromotionalDateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ECommerceCartApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ECommerceCartApplication.class, args);
    }


    @Bean
    CommandLineRunner run(ProductRepository productRepository, PromotionalDateRepository promotionalDateRepository,
                          CustomerRepository customerRepository) {
        return args -> {  // inserting data after application is up


            productRepository.saveAll(
                    List.of(
                            Product.builder().name("Jabón").description("Nivea 120 gramos").price(800.0).build(),
                            Product.builder().name("Shampoo").description("Pantene 400 ml").price(1200.0).build(),
                            Product.builder().name("Acondicionador").description("Seda 300 ml").price(1000.0).build(),
                            Product.builder().name("Crema").description("Nivea Soft 200 ml").price(1500.0).build(),
                            Product.builder().name("Desodorante").description("Rexona Aerosol 150 ml").price(700.0).build(),
                            Product.builder().name("Pasta Dental").description("Colgate Total 100 ml").price(500.0).build(),
                            Product.builder().name("Enjuague Bucal").description("Listerine 500 ml").price(900.0).build(),
                            Product.builder().name("Jabón Líquido").description("Dove 250 ml").price(850.0).build(),
                            Product.builder().name("Gel para el Cabello").description("Gatsby 200 ml").price(950.0).build(),
                            Product.builder().name("Crema de Afeitar").description("Gillette 200 ml").price(800.0).build(),
                            Product.builder().name("Aftershave").description("Old Spice 100 ml").price(1100.0).build(),
                            Product.builder().name("Loción Corporal").description("Vaseline 400 ml").price(1300.0).build(),
                            Product.builder().name("Jabón Antibacterial").description("Dettol 125 gramos").price(600.0).build(),
                            Product.builder().name("Protector Solar").description("Banana Boat SPF 50 200 ml").price(1700.0).build(),
                            Product.builder().name("Crema para Manos").description("Neutrogena 50 ml").price(950.0).build(),
                            Product.builder().name("Gel de Ducha").description("Axe 250 ml").price(750.0).build(),
                            Product.builder().name("Aceite Corporal").description("Johnson's 200 ml").price(1200.0).build(),
                            Product.builder().name("Bálsamo Labial").description("Carmex 10 gramos").price(450.0).build(),
                            Product.builder().name("Espuma para el Cabello").description("Pantene 200 ml").price(950.0).build(),
                            Product.builder().name("Crema Facial").description("Nivea Q10 50 ml").price(1800.0).build()
                    )
            );


            promotionalDateRepository.saveAll(
                    List.of(
                            PromotionalDate.builder().description("Día del Padre").promotionalDate(LocalDate.parse("2024-06-16")).build(),
                            PromotionalDate.builder().description("Día de la Madre").promotionalDate(LocalDate.parse("2024-10-20")).build(),
                            PromotionalDate.builder().description("Día del Niño").promotionalDate(LocalDate.parse("2024-08-18")).build(),
                            PromotionalDate.builder().description("Navidad").promotionalDate(LocalDate.parse("2024-12-25")).build(),
                            PromotionalDate.builder().description("Año Nuevo").promotionalDate(LocalDate.parse("2024-01-01")).build(),
                            PromotionalDate.builder().description("San Valentín").promotionalDate(LocalDate.parse("2024-02-14")).build(),
                            PromotionalDate.builder().description("Pascua").promotionalDate(LocalDate.parse("2024-03-31")).build(),
                            PromotionalDate.builder().description("Día de la Independencia").promotionalDate(LocalDate.parse("2024-07-09")).build(),
                            PromotionalDate.builder().description("Halloween").promotionalDate(LocalDate.parse("2024-10-31")).build(),
                            PromotionalDate.builder().description("Black Friday").promotionalDate(LocalDate.parse("2024-11-29")).build()
                    )
            );



            customerRepository.saveAll(
                    List.of(
                            Customer.builder().firstName("Corey").lastName("Stetnett").email("cstenett0@oracle.com").vip(true).build(),
                            Customer.builder().firstName("Jeni").lastName("Elmore").email("jelmore1@tumblr.com").vip(false).build(),
                            Customer.builder().firstName("Claudius").lastName("Escalante").email("cescalante2@forbes.com").vip(true).build(),
                            Customer.builder().firstName("Alison").lastName("Rearie").email("arearie3@cargocollective.com").vip(false).build(),
                            Customer.builder().firstName("Judd").lastName("Yokley").email("jyokley4@delicious.com").vip(true).build(),
                            Customer.builder().firstName("Sean").lastName("Hanlon").email("shanlon5@archive.org").vip(false).build(),
                            Customer.builder().firstName("Den").lastName("Borel").email("dborel6@illinois.edu").vip(false).build(),
                            Customer.builder().firstName("Hugo").lastName("Eagell").email("heagell7@thetimes.co.uk").vip(false).build(),
                            Customer.builder().firstName("Rod").lastName("Desport").email("rdesport8@fastcompany.com").vip(false).build(),
                            Customer.builder().firstName("Nicolas").lastName("Sinclaire").email("nsinclaire9@cbslocal.com").vip(false).build()
                    )
            );

        };
    }

}
