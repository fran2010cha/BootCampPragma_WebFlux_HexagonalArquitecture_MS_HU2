package co.com.pragma.r2dbc;

import co.com.pragma.r2dbc.entities.EstadoEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoReactRepository extends ReactiveCrudRepository<EstadoEntity, Long>, ReactiveQueryByExampleExecutor<EstadoEntity> {
}
