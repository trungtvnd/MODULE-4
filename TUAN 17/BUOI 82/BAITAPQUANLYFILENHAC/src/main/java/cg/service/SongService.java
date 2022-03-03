package cg.service;

import cg.model.Song;
import cg.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository repository;

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Song> findAllByName(String name, Pageable pageable) {
        return repository.findAllByNameContaining(name, pageable);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void save(Song song) {
        repository.save(song);
    }
}
