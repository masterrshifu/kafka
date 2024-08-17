package com.spring.kafka.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PREvent {

    @Id
    private int prId;
    private String clientId;
    private String householdName;
    private LocalDate dueDate;

    @Override
    public String toString () {
        return "PREvent{" + "prId=" + prId + ", clientId='" + clientId + '\'' + ", householdName='" + householdName + '\'' + ", dueDate=" + dueDate + '}';
    }
}