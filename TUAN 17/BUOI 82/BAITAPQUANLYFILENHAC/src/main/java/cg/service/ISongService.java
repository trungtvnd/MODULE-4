package cg.service;

import cg.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISongService {
    Page<Song> findAll(Pageable pageable);
    Page<Song> findAllByName(String name, Pageable pageable);
    void deleteById(int id);
    Optional<Song> findById(int id);
    void save(Song song);
}
