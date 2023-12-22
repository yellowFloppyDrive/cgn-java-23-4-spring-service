package de.neuefische.cgnjava234springservice;

public record Product(
        String id,
        String name,
        double price,
        long createdAt
) {
}
