package cg.repository;

import cg.model.ClassRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassRoomRepository extends CrudRepository<ClassRoom, Integer> {
}
