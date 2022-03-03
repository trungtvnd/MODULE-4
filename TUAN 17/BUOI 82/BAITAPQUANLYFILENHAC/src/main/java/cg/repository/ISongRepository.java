package cg.repository;

import cg.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISongRepository extends PagingAndSortingRepository<Song,Integer> {
    Page<Song> findAllByNameContaining(String name, Pageable pageable);
}
