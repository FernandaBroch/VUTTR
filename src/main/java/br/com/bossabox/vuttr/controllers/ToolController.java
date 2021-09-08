package br.com.bossabox.vuttr.controllers;

import br.com.bossabox.vuttr.models.Tool;
import br.com.bossabox.vuttr.services.ToolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api
@RestController
@RequestMapping("api/v1/tools")
public class ToolController {

    @Autowired
    ToolService toolService;

    @PostMapping
    @ApiOperation(value = "Create Tool")
    @ApiResponses({
            @ApiResponse( code = HttpServletResponse.SC_CREATED, message = "Creating tool", response = Long.class)
    })
    public ResponseEntity<Tool> create (@RequestBody Tool tool){
        Tool toolResponse = toolService.create(tool);
        return ResponseEntity.status(HttpStatus.CREATED).body(toolResponse);
    }

    @GetMapping
    @ApiOperation(value = "List of Tools")
    @ApiResponses({
            @ApiResponse( code = HttpServletResponse.SC_OK, message = "List of tools", response = List.class),
            @ApiResponse( code = HttpServletResponse.SC_NO_CONTENT, message = "No tools found", response = List.class)
    })
    public ResponseEntity<List<Tool>> list(@RequestParam(required = false) String tag){
        List<Tool> returnedList;
        if(StringUtils.hasText(tag)){
            returnedList = toolService.findByTags(tag);
        }else{
            returnedList = toolService.list();
        }

        if(returnedList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(returnedList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation( value = "Delete tool by Id")
    @ApiResponses({
            @ApiResponse( code = HttpServletResponse.SC_OK, message = "Delete tool", response = String.class)
    })
    public ResponseEntity<Tool> delete(@PathVariable(name = "id")Long id) {
        toolService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
