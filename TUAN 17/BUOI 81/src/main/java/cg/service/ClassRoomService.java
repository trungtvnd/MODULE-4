package cg.service;

import cg.model.ClassRoom;
import cg.repository.IClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassRoomService implements IClassRoomService{
    @Autowired
    IClassRoomRepository repository;
    @Override
    public List<ClassRoom> findAll() {
        return (List<ClassRoom>) repository.findAll();
    }

    @Override
    public ClassRoom findById(int id) {
       if(repository.findById(id).isPresent()){
        return repository.findById(id).get();
       }return null;
    }
}
