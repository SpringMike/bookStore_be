package com.example.demo.controller;

import com.example.demo.dto.FavoriteDTO;
import com.example.demo.model.CartDetail;
import com.example.demo.model.Favorite;
import com.example.demo.service.IFavoriteService;
import com.example.demo.service.impl.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final IFavoriteService favoriteService;

    @GetMapping("/findByAccount/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<FavoriteDTO> findAllFavoriteByAccount(@PathVariable long id){
        return favoriteService.getAllFavoriteByAccountId(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Favorite save(@RequestBody Favorite favorite){
        return favoriteService.save(favorite);
    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Favorite updateStatus(@RequestBody Favorite favorite){
        return favoriteService.update(favorite);
    }
    @GetMapping("/findByAccountAndBook/{accountId}/{bookId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Favorite findAllFavoriteByAccount(@PathVariable long accountId,@PathVariable long bookId){
        return favoriteService.findById(accountId,bookId);
    }
}
