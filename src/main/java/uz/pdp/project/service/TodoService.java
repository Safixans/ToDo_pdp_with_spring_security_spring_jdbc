package uz.pdp.project.service;


import org.springframework.stereotype.Service;
import uz.pdp.project.model.Todo;

import java.util.List;


@Service
public interface TodoService {
    List<Todo> getAllTodo();

    Todo getById(int id);

    void update(int id,Todo todo);

    void delete(int id);

    void addTodo(Todo todo);

}
