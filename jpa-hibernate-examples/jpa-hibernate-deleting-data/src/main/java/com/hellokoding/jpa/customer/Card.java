package com.hellokoding.jpa.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @ToString(exclude = "customer")
@SQLDelete(sql = "UPDATE Card SET deleted = 1 WHERE card_number = ?")
@Where(clause = "deleted = 0")
@Entity
public class Card {
    public static final String VISA = "VISA";
    public static final String MASTER = "MASTER";

    @Id
    private Integer cardNumber;

    private String type;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    private Boolean deleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Card(Integer cardNumber, String type) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.deleted = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(cardNumber, card.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }
}
