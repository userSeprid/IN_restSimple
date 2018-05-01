package com.seprid.lineservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class LineServiceRestController {

    private final LineObjectRepository lineObjectRepository;

    @Autowired
    public LineServiceRestController(LineObjectRepository lineObjectRepository) {
        this.lineObjectRepository = lineObjectRepository;
    }

    @PostMapping(path = "/{lineID}/")
    LineObject add(@PathVariable String lineID, @RequestBody LineObject input) {
        return this.lineObjectRepository.save(input);
    }

    @GetMapping(path = "/{lineID}/")
    Optional<LineObject> getLine(@PathVariable int lineID) {
        return this.lineObjectRepository.findById(lineID);
    }

    @GetMapping(path = "/files/{fileName}")
    Iterable<LineObject> getLinesOfSelectedFile(@PathVariable String fileName) {
        return lineObjectRepository.findByContainerName(fileName);
    }

    @GetMapping(path="/files/all")
    public @ResponseBody Iterable<LineObject> getAllLines() {
        return lineObjectRepository.findAll();
    }

    @GetMapping(path = "files/allNames")
    public @ResponseBody Iterable<LineObject> getFileNames() {
        return lineObjectRepository.findDistinctTopByContainerNameNotNull();
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping(path = "/files/hash")
    public @ResponseBody HashMap<String, Iterable<LineObject>> get() {
        HashMap<String, Iterable<LineObject>> map = new HashMap<>();
        Iterable<LineObject> fileNames = getFileNames();
        for (LineObject object :
                fileNames) {
            map.put(object.getContainerName(), getLinesOfSelectedFile(object.getContainerName()));
        }
        return map;
    }
}
