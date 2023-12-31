package com.example.juegopalabras.service;
import com.example.juegopalabras.modelo.Equipo;
import com.example.juegopalabras.repos.RepositorioEquipo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl{
    @Autowired
    private RepositorioEquipo equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> findById(Long id) {
        return equipoRepository.findById(id);
    }

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public void deleteById(Long id) {
        equipoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return equipoRepository.existsById(id);
    }

}
