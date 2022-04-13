package cs.controllers;

import cs.models.Ward;
import cs.services.WardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ward")
public class WardDataBaseController {

    private WardService wardService;

    public WardDataBaseController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping("/{id}")
    public Ward getWardById(@PathVariable Integer id) {
        return wardService.getWardById(id).get();
    }

    @PostMapping("/save")
    public void save(@RequestBody Ward ward) {
        wardService.save(ward);
    }

    @PutMapping("/put")
    public void update(@RequestBody Ward ward) {
        wardService.update(ward);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Ward ward) {
        wardService.delete(ward);
    }

}
