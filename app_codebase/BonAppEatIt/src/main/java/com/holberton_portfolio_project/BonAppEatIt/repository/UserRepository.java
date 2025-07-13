package com.holberton_portfolio_project.BonAppEatIt.repository;

import com.holberton_portfolio_project.BonAppEatIt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /*
    JpaRepository class gives access to all the below :

    From CrudRepository<T, ID>

    SAVE methods
    <S extends T> S save(entity), <S extends T> Iterable<S> saveAll(Iterable<S> entities)
    FIND methods
    Optional<T> findById(ID id), Iterable<T> findAll(), ITERABLE<T> findAllById(Iterable<ID> id),
    boolean existsById(ID id), long count()
    DELETE methods
    void deleteById(ID id), void delete(T entity), void deleteAllById(Iterable<? extends ID> ids),
    void deleteAll(Iterable<? extends T> entities), void deleteAll()

    From PagingAndSortingRepository<T, ID>

    FIND methods
    Iterable<T> findAll(Sort sort)      => get all with sorting
    Page<T> findAll(Pageable pageable)  => get paginated result

    From JpaRepository<T, ID>

    SAVE method
    <S extends T> List<S> saveAll(Iterable<S> entities)
    FIND method
    List<T> findAll()
    List<T> findAll(Sort sort)
    List<T> findAllById(Iterable<ID> ids)
    SYNC methods
    void flush()
    <S extends T> S saveAndFlush(S entity)
    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities)
    DELETE methods
    void deleteAllInBatch()
    void deleteAllByIdInBatch(Iterable<ID> ids)
    void deleteALlInBatch(Iterable<T, entities)
    LAZY LOADING
    T getReferenceById(ID, id)
     */


    /*
    Custom methods don't need to be implemented : their signature is parsed automatically by Spring

    find...By      // SELECT * FROM table WHERE...
    get...By       // Same as find (alias)
    read...By      // Same as find (alias)
    query...By     // Same as find (alias)
    stream...By    // Returns Stream<T>
    count...By     // SELECT COUNT(*) FROM table WHERE...
    exists...By    // SELECT COUNT(*) > 0 FROM table WHERE...
    delete...By    // DELETE FROM table WHERE...
    remove...By    // Same as delete (alias)

    // Equality

    findByEmail(String email)                    // email = ?
    findByEmailNot(String email)                 // email != ?

    // Comparison
    findByAgeGreaterThan(int age)               // age > ?
    findByAgeLessThan(int age)                  // age < ?
    findByAgeGreaterThanEqual(int age)          // age >= ?
    findByAgeBetween(int start, int end)        // age BETWEEN ? AND ?

    // Text operations
    findByNameContaining(String substring)       // name LIKE %?%
    findByNameStartingWith(String prefix)       // name LIKE ?%
    findByNameEndingWith(String suffix)         // name LIKE %?
    findByNameIgnoreCase(String name)           // UPPER(name) = UPPER(?)

    // Null checks
    findByEmailIsNull()                         // email IS NULL
    findByEmailIsNotNull()                      // email IS NOT NULL

    // Collections
    findByCollectionsIsEmpty()                  // collections is empty
    findByCollectionsIsNotEmpty()               // collections is not empty

    // Date/Time
    findByCreatedAtBefore(LocalDateTime date)   // created_at < ?
    findByCreatedAtAfter(LocalDateTime date)    // created_at > ?

    // AND
    findByEmailAndIsActive(String email, boolean active)
    // WHERE email = ? AND is_active = ?

    // OR
    findByEmailOrPasswordUpdatedAtAfter(String email, LocalDateTime date)
    // WHERE email = ? OR password_updated_at > ?

    // Sorting
    findByIsActiveOrderByEmailAsc(boolean active)           // ORDER BY email ASC
    findByIsActiveOrderByEmailAsc(boolean active)           // ORDER BY email ASC
    findByIsActiveOrderByCreatedAtDesc(boolean active)      // ORDER BY created_at DESC
    findByEmailOrderByCreatedAtDescEmailAsc(String email)   // ORDER BY created_at DESC, email ASC

    // Limiting results
    findFirstByEmail(String email)              // LIMIT 1
    findTop5ByIsActiveOrderByCreatedAt(boolean active)  // LIMIT 5
     */
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    List<User> findByIsActive(boolean active);
}
