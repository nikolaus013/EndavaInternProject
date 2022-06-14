package example.repositories;


import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import example.models.MyUser;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCosmosRepository<MyUser,String> {

     Flux<MyUser> findByUsername(String username);


}
