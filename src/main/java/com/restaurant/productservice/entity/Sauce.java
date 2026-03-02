package com.restaurant.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sauce {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private Double extraPrice;

        @ManyToMany(mappedBy = "sauces")
        private Set<Product> products = new HashSet<>();

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Sauce)) return false;
                Sauce sauce = (Sauce) o;
                return id != null && id.equals(sauce.id);
        }

        @Override
        public int hashCode() {
                return getClass().hashCode();
        }
}
