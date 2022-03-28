package learning.user.service.controller;

import learning.user.service.dto.UserDTO;
import learning.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("all")
    public Flux<UserDTO> getAll(){
        return userService.all();
    }
    @GetMapping("{id}")
    public Mono<ResponseEntity<UserDTO>> findUserId(@PathVariable Integer id){
        return this.userService
                .getUserById(id)
                .map(ResponseEntity::ok)
                 .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping
    public Mono<UserDTO> userDTOMono(@RequestBody Mono<UserDTO> userDTO){
        return this.userService.saveUser(userDTO);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDTO>> userDTOMono(@PathVariable Integer id,@RequestBody Mono<UserDTO> userDTO){
        return this.userService.updateUser(id,userDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteUserId(@PathVariable Integer id){
        return this.userService
                .deleteUserById(id);

    }
}
