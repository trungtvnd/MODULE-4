package cg.controllers;

import cg.model.Category;
import cg.model.Song;
import cg.service.ICategoryService;
import cg.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("song")
public class SongController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private ISongService songService;
    @Autowired
   private ICategoryService categoryService;
    @ModelAttribute
    private Iterable<Category> categories(){
       return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView display(@PageableDefault(2)Pageable pageable,@RequestParam("search") Optional<String>  search){
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Song> songs;

        if(search.isPresent()){
            songs = songService.findAllByName(search.get(), pageable);
        }else {
            songs = songService.findAll(pageable);
        }

        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("song", new Song());
        modelAndView.addObject("categories", categories());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView create(@ModelAttribute("song") Song song){
        ModelAndView modelAndView =new ModelAndView("create");
        MultipartFile multipartFile = song.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(song.getMultipartFile().getBytes(), new File(fileUpload+fileName));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        song.setFile(fileName);

      songService.save(song);
      modelAndView.addObject("categories",categories());
      return modelAndView;
    }

    @GetMapping("edit")
    public ModelAndView edit(){
        ModelAndView modelAndView = new ModelAndView("edit");
        return  modelAndView;
    }

}
