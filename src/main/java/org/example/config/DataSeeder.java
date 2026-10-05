package org.example.config;

import org.example.model.Food;
import org.example.model.Restaurant;
import org.example.repository.FoodRepository;
import org.example.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(
            RestaurantRepository restaurantRepository,
            FoodRepository foodRepository) {

        return args -> {

            if (restaurantRepository.count() == 0) {

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
            }

            if (foodRepository.count() > 0) {
                return;
            }

            List<Restaurant> restaurants = restaurantRepository.findAll();

            Restaurant burgerKing = getRestaurant(restaurants, "Burger King");
            Restaurant pizzaHouse = getRestaurant(restaurants, "Pizza House");
            Restaurant biryaniExpress = getRestaurant(restaurants, "Biryani Express");
            Restaurant spiceGarden = getRestaurant(restaurants, "Spice Garden");
            Restaurant southIndian = getRestaurant(restaurants, "South Indian Corner");
            Restaurant dessertHub = getRestaurant(restaurants, "Dessert Hub");

            Food f1 = createFood(
                    "Classic Chicken Burger",
                    "Grilled chicken patty with fresh vegetables and sauce",
                    199.0,
                    "https://images.unsplash.com/photo-1568901346375-23c9450c58cd",
                    "Burger",
                    burgerKing
            );

            Food f2 = createFood(
                    "Veg Whopper",
                    "Crispy vegetable patty with lettuce and creamy sauce",
                    179.0,
                    "https://images.unsplash.com/photo-1550547660-d9450f859349",
                    "Burger",
                    burgerKing
            );

            Food f3 = createFood(
                    "French Fries",
                    "Crispy golden salted french fries",
                    99.0,
                    "https://images.unsplash.com/photo-1573080496219-bb080dd4f877",
                    "Sides",
                    burgerKing
            );

            Food f4 = createFood(
                    "Margherita Pizza",
                    "Classic pizza with mozzarella cheese and tomato sauce",
                    249.0,
                    "https://images.unsplash.com/photo-1574071318508-1cdbab80d002",
                    "Pizza",
                    pizzaHouse
            );

            Food f5 = createFood(
                    "Farmhouse Pizza",
                    "Loaded with onion, capsicum, tomato and mushrooms",
                    349.0,
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38",
                    "Pizza",
                    pizzaHouse
            );

            Food f6 = createFood(
                    "White Sauce Pasta",
                    "Creamy pasta with herbs and vegetables",
                    229.0,
                    "https://images.unsplash.com/photo-1473093295043-cdd812d0e601",
                    "Pasta",
                    pizzaHouse
            );

            Food f7 = createFood(
                    "Chicken Biryani",
                    "Aromatic basmati rice cooked with spicy chicken",
                    299.0,
                    "https://images.unsplash.com/photo-1563379926898-05f4575a45d8",
                    "Biryani",
                    biryaniExpress
            );

            Food f8 = createFood(
                    "Veg Biryani",
                    "Basmati rice cooked with vegetables and Indian spices",
                    219.0,
                    "https://images.unsplash.com/photo-1589302168068-964664d93dc0",
                    "Biryani",
                    biryaniExpress
            );

            Food f9 = createFood(
                    "Chicken Kebab",
                    "Spicy grilled chicken kebabs served with chutney",
                    259.0,
                    "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398",
                    "Starter",
                    biryaniExpress
            );

            Food f10 = createFood(
                    "Paneer Butter Masala",
                    "Paneer cooked in creamy tomato gravy",
                    249.0,
                    "https://images.unsplash.com/photo-1631452180519-c014fe946bc7",
                    "Main Course",
                    spiceGarden
            );

            Food f11 = createFood(
                    "Butter Chicken",
                    "Chicken cooked in rich buttery tomato gravy",
                    329.0,
                    "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398",
                    "Main Course",
                    spiceGarden
            );

            Food f12 = createFood(
                    "Veg Fried Rice",
                    "Fried rice tossed with fresh vegetables",
                    189.0,
                    "https://images.unsplash.com/photo-1603133872878-684f208fb84b",
                    "Chinese",
                    spiceGarden
            );

            Food f13 = createFood(
                    "Masala Dosa",
                    "Crispy dosa filled with spicy potato masala",
                    129.0,
                    "https://images.unsplash.com/photo-1630383249896-424e482df921",
                    "South Indian",
                    southIndian
            );

            Food f14 = createFood(
                    "Idli Sambar",
                    "Soft idlis served with hot sambar and chutney",
                    99.0,
                    "https://images.unsplash.com/photo-1589301760014-d929f3979dbc",
                    "South Indian",
                    southIndian
            );

            Food f15 = createFood(
                    "Uttapam",
                    "Soft uttapam topped with onion and vegetables",
                    139.0,
                    "https://images.unsplash.com/photo-1668236543090-82eba5ee5976",
                    "South Indian",
                    southIndian
            );

            Food f16 = createFood(
                    "Chocolate Cake",
                    "Rich chocolate cake with creamy chocolate frosting",
                    199.0,
                    "https://images.unsplash.com/photo-1578985545062-69928b1d9587",
                    "Dessert",
                    dessertHub
            );

            Food f17 = createFood(
                    "Chocolate Donut",
                    "Soft donut topped with chocolate glaze",
                    99.0,
                    "https://images.unsplash.com/photo-1551024506-0bccd828d307",
                    "Dessert",
                    dessertHub
            );

            Food f18 = createFood(
                    "Ice Cream Sundae",
                    "Vanilla ice cream with chocolate sauce and toppings",
                    149.0,
                    "https://images.unsplash.com/photo-1563805042-7684c019e1cb",
                    "Dessert",
                    dessertHub
            );

            foodRepository.saveAll(
                    List.of(
                            f1, f2, f3,
                            f4, f5, f6,
                            f7, f8, f9,
                            f10, f11, f12,
                            f13, f14, f15,
                            f16, f17, f18
                    )
            );
        };
    }

    private Restaurant getRestaurant(
            List<Restaurant> restaurants,
            String name) {

        return restaurants.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    private Food createFood(
            String name,
            String description,
            Double price,
            String imageUrl,
            String category,
            Restaurant restaurant) {

        Food food = new Food();

        food.setName(name);
        food.setDescription(description);
        food.setPrice(price);
        food.setImageUrl(imageUrl);
        food.setCategory(category);
        food.setAvailable(true);
        food.setRestaurant(restaurant);

        return food;
    }
}