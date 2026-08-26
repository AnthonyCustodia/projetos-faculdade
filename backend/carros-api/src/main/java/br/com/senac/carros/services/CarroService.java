package br.com.senac.carros.services;

import br.com.senac.carros.dtos.CarroDto;
import br.com.senac.carros.entities.Carro;
import br.com.senac.carros.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Carro atualizar(Long id, CarroDto carroDto) {
        Carro carro = carroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Carro nao encontrado"));

        carro.setMarca(carroDto.getMarca());
        carro.setModelo(carroDto.getModelo());

        return carroRepository.save(carro);
    }

    public void deletar(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new NoSuchElementException("Carro nao encontrado com id: " + id);
        }
        carroRepository.deleteById(id);
    }
}
