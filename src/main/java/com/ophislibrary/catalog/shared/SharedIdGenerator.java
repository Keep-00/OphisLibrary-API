package com.ophislibrary.catalog.shared;

import org.hibernate.id.uuid.UuidVersion7Strategy;

import java.util.UUID;

public final class SharedIdGenerator {

    private SharedIdGenerator() {}

    public static UUID generateV7() {
        return UuidVersion7Strategy.INSTANCE.generateUuid(null);
    }
}
