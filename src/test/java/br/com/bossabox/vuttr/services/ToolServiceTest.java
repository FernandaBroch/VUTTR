package br.com.bossabox.vuttr.services;

import br.com.bossabox.vuttr.models.Tool;
import br.com.bossabox.vuttr.repositories.ToolRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ToolServiceTest {
    @Autowired
    ToolService toolService;

    @MockBean
    ToolRepository toolRepository;

    @Test
    public void creatingSuccess(){
        //Scenery
        Tool tool = new Tool();
        List<String> tagList = new ArrayList<String>();

        tool.setTitle("Notion");
        tool.setLink("https://notion.so");
        tool.setDescription("All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized. ");
        tagList.add("organization");
        tagList.add("planning");
        tagList.add("collaboration");
        tagList.add("writing");
        tagList.add("calendar");
        tool.setTags(tagList);

        when(toolRepository.save(Mockito.any(Tool.class))).thenAnswer( i -> {
            Tool toolToReturn = i.getArgument(0);
            toolToReturn.setId(1L);
            return toolToReturn;
        });

        //Action
        Tool returnedTool = toolService.create(tool);

        //Check
        assertThat(returnedTool).isEqualTo(tool);

    }



}
