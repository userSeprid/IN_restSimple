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

    @GetMapping(path = "/files/")
    List<String> getProcessedFiles() {
        //List<LineObject> objectList = lineObjectRepository.countDistinctByContainerNameOrderByContainerName();
        //List<String> files = new ArrayList<>();
        //for (LineObject object :
       //         objectList) {
        //    files.add(object.getContainerName());
        //}
        //return files;
        return null;
    }

    @GetMapping(path = "/files/{fileName}")
    List<LineObject> getLinesOfSelectedFile(@PathVariable String fileName) {
        return lineObjectRepository.findByContainerName(fileName);
    }

    @GetMapping(path="/files/all")
    public @ResponseBody Iterable<LineObject> getAllLines() {
        return lineObjectRepository.findAll();
    }

    @GetMapping(path = "files/allNames")
    public @ResponseBody LineObject getFileNames() {
        List<LineObject> objects = (List<LineObject>) lineObjectRepository.findAll();
        LinkedHashSet hashSet;
        return lineObjectRepository.findDistinctTopByContainerNameNotNull();
    }

    @GetMapping(path = "/files/hash")
    public @ResponseBody HashMap<String, Iterable<String>> get() {
        HashMap<String, Iterable<String>> map = new HashMap<>();

        map.put("So", new ArrayList<>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata")));
        return map;
    }
}
