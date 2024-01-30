package com.agg.certificados.controllers;

import com.agg.certificados.dtos.request.BotaderoRequestDto;
import com.agg.certificados.dtos.response.BotaderoResponseDto;
import com.agg.certificados.entity.Botadero;
import com.agg.certificados.services.botaderoServices.IBotaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/botadero")
public class BotaderoController {

    @Autowired
    private IBotaderoService botaderoService;

    @PostMapping
    public ResponseEntity<Integer> saveBotadero(@Valid @RequestBody BotaderoRequestDto dto) {
        return ResponseEntity.ok(botaderoService.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BotaderoResponseDto> getBotaderoById(@PathVariable("id") int id) {
        Optional<Botadero> botaderoEntity = botaderoService.getById(id);

        BotaderoResponseDto dto = new BotaderoResponseDto();

        assert botaderoEntity.orElse(null) != null;
        dto.setId_botadero(botaderoEntity.orElse(null).id_botadero);
        dto.setCity(botaderoEntity.orElse(null).city);
        dto.setProperty_name(botaderoEntity.orElse(null).property_name);
        dto.setCreate_date(botaderoEntity.orElse(null).create_date);
        dto.setUser_id(botaderoEntity.orElse(null).getUser_id().getId());
        dto.setStatus(botaderoEntity.orElse(null).status);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BotaderoResponseDto>> getAllBotaderos() {
        return ResponseEntity.ok(botaderoService.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<BotaderoResponseDto>> getAllActiveBotaderos() {
        return ResponseEntity.ok(botaderoService.getAllActive());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBotadero(@PathVariable("id") int id) {
        return ResponseEntity.ok(botaderoService.delete(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Boolean> updateBotadero(@RequestBody BotaderoRequestDto dto,@PathVariable("id") int id) {
        return ResponseEntity.ok(botaderoService.update(dto,id));
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Boolean> updateStatusBotadero(@RequestBody boolean status,@PathVariable("id") int id) {
        return ResponseEntity.ok(botaderoService.updateStatus(status,id));
    }
}
