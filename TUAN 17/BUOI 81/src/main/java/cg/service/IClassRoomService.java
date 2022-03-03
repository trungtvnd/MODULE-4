package cg.service;

import cg.model.ClassRoom;
import cg.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClassRoomService {
    List<ClassRoom> findAll();
    ClassRoom findById(int id);
}
