package cse.swengineering.smtm.menus;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping("/menus/{date}")
    public Diet getDiet(@PathVariable Long id){
        return null;
    }

    @GetMapping("/menus/{date}")
    public List<Diet> getDiets(@PathVariable Long id){
        return null;
    }

    @GetMapping("/menus/images/{id}")
    public ResponseEntity<byte[]> getMenuImage(@PathVariable Long id) throws IllegalAccessException {
        Optional<Menu> byId = menuRepository.findById(id);
        Menu menu = null;
        if(byId.isPresent()) {
             menu = byId.get();
        }
        else {
            throw new IllegalAccessException();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(menu.getImg());
    }

}
