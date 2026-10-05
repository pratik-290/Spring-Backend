package org.example.config;

import org.example.model.Restaurant;
import org.example.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedRestaurants(
            RestaurantRepository restaurantRepository) {

        return args -> {

            if (restaurantRepository.count() > 0) {
                return;
            }

            Restaurant r1 = new Restaurant();
            r1.setName("Burger King");
            r1.setImage("https://images.unsplash.com/photo-1568901346375-23c9450c58cd");
            r1.setCuisine("Burgers, Fast Food");
            r1.setRating(4.3);
            r1.setDeliveryTime("25-30 min");
            r1.setDistance("2.1 km");
            r1.setPriceForTwo(400.0);
            r1.setOffer("50% OFF");

            Restaurant r2 = new Restaurant();
            r2.setName("Pizza House");
            r2.setImage("https://images.unsplash.com/photo-1579751626657-72bc17010498");
            r2.setCuisine("Pizza, Italian");
            r2.setRating(4.5);
            r2.setDeliveryTime("30-35 min");
            r2.setDistance("3.2 km");
            r2.setPriceForTwo(500.0);
            r2.setOffer("40% OFF");

            Restaurant r3 = new Restaurant();
            r3.setName("Biryani Express");
            r3.setImage("https://images.unsplash.com/photo-1563379926898-05f4575a45d8");
            r3.setCuisine("Biryani, North Indian");
            r3.setRating(4.4);
            r3.setDeliveryTime("30-40 min");
            r3.setDistance("2.8 km");
            r3.setPriceForTwo(450.0);
            r3.setOffer("20% OFF");

            Restaurant r4 = new Restaurant();
            r4.setName("Spice Garden");
            r4.setImage("https://images.unsplash.com/photo-1504674900247-0877df9cc836");
            r4.setCuisine("North Indian, Chinese");
            r4.setRating(4.2);
            r4.setDeliveryTime("35-40 min");
            r4.setDistance("4.0 km");
            r4.setPriceForTwo(600.0);
            r4.setOffer("₹125 OFF");

            Restaurant r5 = new Restaurant();
            r5.setName("South Indian Corner");
            r5.setImage("https://images.unsplash.com/photo-1630383249896-424e482df921");
            r5.setCuisine("South Indian");
            r5.setRating(4.6);
            r5.setDeliveryTime("20-25 min");
            r5.setDistance("1.5 km");
            r5.setPriceForTwo(300.0);
            r5.setOffer("30% OFF");

            Restaurant r6 = new Restaurant();
            r6.setName("Dessert Hub");
            r6.setImage("https://images.unsplash.com/photo-1551024506-0bccd828d307");
            r6.setCuisine("Desserts, Bakery");
            r6.setRating(4.7);
            r6.setDeliveryTime("20-30 min");
            r6.setDistance("2.5 km");
            r6.setPriceForTwo(350.0);
            r6.setOffer("Buy 1 Get 1");

            restaurantRepository.saveAll(
                    List.of(r1, r2, r3, r4, r5, r6)
            );
        };
    }
}