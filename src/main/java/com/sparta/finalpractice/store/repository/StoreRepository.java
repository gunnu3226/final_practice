package com.sparta.finalpractice.store.repository;

import com.sparta.finalpractice.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Store.class, idClass = Long.class)
public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryQuery {


}
