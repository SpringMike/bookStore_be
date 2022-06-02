package com.example.demo.controller;

import com.example.demo.dto.CartDetailDTO;
import com.example.demo.model.CartDetail;
import com.example.demo.service.impl.CartDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cartDetails")
@CrossOrigin("*")
public class CartDetailController {
    private final CartDetailService cartDetailService;

    @GetMapping("/{cartId}")
    public List<CartDetailDTO> getAllCartDetailByCartId(@PathVariable long cartId){
        return cartDetailService.getAllCartDetailFeaturedByOrderId(cartId);
    }
    @PutMapping
    public CartDetail updateCartDetail(@RequestBody CartDetail cartDetail){
        return cartDetailService.update(cartDetail);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteCartDetail(@RequestParam("id") List<Integer> id){
        cartDetailService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public CartDetail save(@RequestBody CartDetail cartDetail){
        return cartDetailService.save(cartDetail);
    }
}
