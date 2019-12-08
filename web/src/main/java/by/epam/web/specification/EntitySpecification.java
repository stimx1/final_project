package by.epam.web.specification;

import by.epam.web.entity.Entity;

import java.sql.PreparedStatement;

public interface EntitySpecification {
    PreparedStatement specified();
}
