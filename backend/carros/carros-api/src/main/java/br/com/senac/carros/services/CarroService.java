package br.com.senac.carros.services;

import br.com.senac.carros.dtos.CarroDto;
import br.com.senac.carros.entities.Carro;
import br.com.senac.carros.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> carroList() {
        return carroRepository.findAll();
    }

    public Carro adicionar(CarroDto carro) {
        Carro carroPersist = new Carro();

        carroPersist.setMarca(carro.getMarca());
        carroPersist.setModelo(carro.getModelo());

        return carroRepository.save(carroPersist);
    }

    public Carro atualizar(Long id, CarroDto carro) {
        Carro carroPersist = carroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Carro nao encontrado"));

        carroPersist.setMarca(carro.getMarca());
        carroPersist.setModelo(carro.getModelo());

        return carroRepository.save(carroPersist);
    }

    public void deletar(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new NoSuchElementException("Carro nao encontrado com id: " + id);
        }
        carroRepository.deleteById(id);
    }

    public Carro listarPorId(Long id) {
        Optional<Carro> carrosResult = carroRepository.findById(id);
        if (carrosResult.isPresent()) {
            return carrosResult.get();
        }

        throw new RuntimeException("Carro nao encontrado");
    }
}
