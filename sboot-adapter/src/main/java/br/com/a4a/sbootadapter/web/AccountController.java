package br.com.a4a.sbootadapter.web;

import br.com.a4a.subpay.domain.vo.Money;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @GetMapping
    public ResponseEntity<Money> hello() {

        return ResponseEntity.ok( Money.of(200));
    }
}
