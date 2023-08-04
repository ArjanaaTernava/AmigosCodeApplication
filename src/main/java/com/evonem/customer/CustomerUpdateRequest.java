package com.evonem.customer;

public record CustomerUpdateRequest(
        String name, String email, Integer age
) {
}
