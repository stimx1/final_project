package by.epam.web.repository;

import by.epam.web.entity.Entity;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;

import java.util.List;

public interface EntityRepository <E extends Entity>{
    void addEntity(E e) throws EntityRepositoryException;
    void removeEntity(E e) throws EntityRepositoryException;
    void updateEntity(E e) throws EntityRepositoryException;

    List<E> query(EntitySpecification specification) throws EntityRepositoryException;
}
