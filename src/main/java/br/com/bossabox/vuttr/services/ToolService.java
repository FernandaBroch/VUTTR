package br.com.bossabox.vuttr.services;

import br.com.bossabox.vuttr.models.Tool;
import br.com.bossabox.vuttr.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {
    @Autowired
    ToolRepository toolRepository;

    public Tool create(Tool tool){
        toolRepository.save(tool);
        return tool;
    }

    public List<Tool> list(){
        return toolRepository.findAll();
    }

    public List<Tool> findByTags(String tag){
        return toolRepository.findByTags(tag);
    }

    public void delete(Long id){ toolRepository.deleteById(id); }

}
