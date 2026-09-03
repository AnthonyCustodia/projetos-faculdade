package br.com.senac.api.service;

import br.com.senac.api.dto.AnimaisDto;
import br.com.senac.api.entity.Animais;
import br.com.senac.api.repository.AnimaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnimaisService {

    @Autowired
    private AnimaisRepository animaisRepository;

    public List<Animais> listar() {
        return animaisRepository.findAll();
    }

    public Animais criar(AnimaisDto animais) {

        this.validarAnimal(animais);

        Animais animaisPersist = new Animais();

        animaisPersist.setNome(animais.getNome());
        animaisPersist.setEspecie(animais.getEspecie());
        animaisPersist.setPeso(animais.getPeso());
        animaisPersist.setIdade(animais.getIdade());

        return animaisRepository.save(animaisPersist);
    }

    private void validarAnimal(AnimaisDto animais) {
        if (animais.getNome() == null || animais.getNome().isBlank()) {
            throw new RuntimeException("Campo nome é obrigatorio");
        }

        if (animais.getEspecie() == null || animais.getEspecie().isBlank()) {
            throw new RuntimeException("Campo especia é obrigatorio");
        }

        if (animais.getPeso() == null) {
            throw new RuntimeException("Campo peso é obrigatorio");
        }
    }

    public Animais atualizar(Long id, AnimaisDto animais) {
        Animais animaisPersist = animaisRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("ID de animal não encontrado!"));

        animaisPersist.setNome(animais.getNome());
        animaisPersist.setEspecie(animais.getEspecie());
        animaisPersist.setPeso(animais.getPeso());
        animaisPersist.setIdade(animais.getIdade());

        return animaisRepository.save(animaisPersist);
    }

    public void deletar(Long id) {
        if (!animaisRepository.existsById(id)) {
            throw new NoSuchElementException("ID de animal não encontrado!");
        }
        animaisRepository.deleteById(id);
    }

}
