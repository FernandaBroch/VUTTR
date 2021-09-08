package br.com.bossabox.vuttr.repositories;

import br.com.bossabox.vuttr.models.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    List<Tool> findByTags(String tag);
}
