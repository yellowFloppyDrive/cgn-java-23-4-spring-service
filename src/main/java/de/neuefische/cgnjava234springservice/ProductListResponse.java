package de.neuefische.cgnjava234springservice;

public record ProductListResponse(
        String name,
        double price,
        long createAt
) {
}
