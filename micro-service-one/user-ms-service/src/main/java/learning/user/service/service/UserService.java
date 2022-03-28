package learning.user.service.service;

import learning.user.service.dto.UserDTO;
import learning.user.service.repository.UserRepo;
import learning.user.service.util.EntityToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Flux<UserDTO> all(){
        return this.userRepo.findAll().map(EntityToDTO::toDTO);
    }

    public Mono<UserDTO> getUserById(Integer id){
        return this.userRepo.findById(id).map(EntityToDTO::toDTO);
    }

    public Mono<UserDTO> saveUser(Mono<UserDTO> mono){
        return mono.map(EntityToDTO::toEntity)
                .flatMap(this.userRepo::save)
                .map(EntityToDTO::toDTO);
    }


    public Mono<UserDTO> updateUser(Integer id,Mono<UserDTO> mono){
        return this.userRepo.findById(id)
                .flatMap(user -> mono.map(EntityToDTO::toEntity).doOnNext(e->e.setId(id)))
                .flatMap(this.userRepo::save)
                .map(EntityToDTO::toDTO);
    }

    public Mono<Void> deleteUserById(Integer id){
        return this.userRepo.deleteById(id);
    }



}
