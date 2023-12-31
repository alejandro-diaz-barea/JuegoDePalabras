package com.example.juegopalabras.controlador;

import com.example.juegopalabras.error.exepcionPartida;
import com.example.juegopalabras.modelo.Partida;
import com.example.juegopalabras.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(produces = "application/json")
@RestController
@RequiredArgsConstructor
public class ControladorPartida {
    @Autowired
    private PartidaService partidaService;
    @GetMapping("/partida")
    public List<Partida> obtenerTodos() {
        List<Partida> result =  partidaService.findAll();
        if(result.isEmpty()){
            throw new PartidaNotFoundException();
        }
        return result;
    }

    @GetMapping("/partida/{id}")
    public Partida getPartida(@PathVariable Long id) {
        return partidaService.findById(id).orElseThrow(() -> new PartidaNotFoundException(id));
    }
    @GetMapping("/jugador/{id}/puntos")
    public int getPuntosTotalesJugador(@PathVariable Long id) {
            return  partidaService.getTotalPuntosByJugadorId(id);

    }
    @PostMapping("/partida")
    public Partida newPartida(@RequestBody Partida newPartida)
    {
        return partidaService.save(newPartida);
    }

    @PutMapping("/partida/{id}")
    public Partida updatePartida(@RequestBody Partida partidaUpdate, @PathVariable Long id) {
        if (partidaService.existsById(id)) {
            Partida partida = partidaService.findById(id).orElseThrow(() -> new PartidaNotFoundException(id));
            partida.setStartTime(partidaUpdate.getStartTime());
            partida.setEndTime(partidaUpdate.getEndTime());
            partida.setIntentos(partidaUpdate.getIntentos());
            partida.setPalabraRonda(partidaUpdate.getPalabraRonda());
            partida.setPuntuacion(partidaUpdate.getPuntuacion());

            return partidaService.save(partida);
        } else {
            throw new PartidaNotFoundException(id);
        }
    }

    @DeleteMapping("partida/{id}")
    public Partida deletePartida(@PathVariable Long id) {
        if(partidaService.existsById(id)){
            Partida result = partidaService.findById(id).get();
            partidaService.deleteById(id);
            return result;
        }else{
            throw new PartidaNotFoundException(id);
        }

    }
}

