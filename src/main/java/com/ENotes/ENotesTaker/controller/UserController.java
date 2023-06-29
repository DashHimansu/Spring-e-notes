package com.ENotes.ENotesTaker.controller;

import com.ENotes.ENotesTaker.entity.Notes;
import com.ENotes.ENotesTaker.entity.UserInfo;
import com.ENotes.ENotesTaker.service.NotesService;
import com.ENotes.ENotesTaker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotesService notesService;

    @ModelAttribute
    public void addCommonData(Principal p, Model model){
        String email = p.getName();
        UserInfo user = userService.findByEmail(email);
        model.addAttribute("user",user);
    }
    @GetMapping(value = {"/", "/home"})
    public String getHomePage(){
        return "user/home";
    }
    @GetMapping("/addnotes")
    public String addNotes(){
        return "user/add_notes";
    }
    @GetMapping("/viewnotes/{page}")
    public String viewNotes(@PathVariable int page, Model model, Principal p){

        String email = p.getName();
        UserInfo user = userService.findByEmail(email);
        Pageable pageable = PageRequest.of(page, 4, Sort.by("id").descending());
        Page<Notes> notes = notesService.findNotesByUser(user,user.getId(),pageable);
        model.addAttribute("pageNo",page);
        model.addAttribute("totalPage",notes.getTotalPages());
        model.addAttribute("Notes",notes);
        model.addAttribute("totalElement",notes.getTotalElements());

        return "user/view_notes";
    }

    @GetMapping("/viewprofile")
    public String viewProfile(){
        return "user/view_profile";
    }
    @GetMapping("/editnotes/{id}")
    public String editNotes(@PathVariable("id") int id, Model m){
        Optional<Notes> n = notesService.findNotesById(id);

        if (n != null) {
            Notes notes = n.get();
            m.addAttribute("notes", notes);
        }

        return "user/edit_notes";
    }
    @PostMapping("/updatenotes")
    public String updateNotes(@ModelAttribute Notes notes, HttpSession session,Principal p){
        String email = p.getName();
        UserInfo user = userService.findByEmail(email);
        Notes updateNotes = notesService.save(user,notes);
        if(updateNotes!=null){
            session.setAttribute("msg","Note Updated Successfully");
        }
        else{
            session.setAttribute("msg","Something wrong on server");
        }
        return "redirect:/user/viewnotes/0";
    }
    @PostMapping("/savenotes")
    public String saveNotes(@ModelAttribute Notes notes, HttpSession session,Principal p){
        String email = p.getName();
        UserInfo user = userService.findByEmail(email);
        //System.out.println(notes);
        Notes n = notesService.save(user,notes);
        if(n!=null){
            session.setAttribute("msg","Note Added Successfully");
        }
        else{
            session.setAttribute("msg","Something wrong on server");
        }
        return "redirect:/user/addnotes";
    }
    @GetMapping("/deletenotes/{id}")
    public String deleteNotes(@PathVariable int id,HttpSession session) {

        Optional<Notes> notes=notesService.findNotesById(id);
        if(notes!=null)
        {
            notesService.delete(notes.get());
            session.setAttribute("msg", "Notes Deleted Successfully");
        }

        return "redirect:/user/viewnotes/0";
    }
    @PostMapping("/updateuser")
    public String updateUser(@ModelAttribute UserInfo user,HttpSession session,Model m) {
        UserInfo updateUser = userService.updateUser(user);
        if (updateUser != null) {
            m.addAttribute("user", updateUser);
            session.setAttribute("msg", "Profile Updated Successfully..");
        }
        return "redirect:/user/viewprofile";
    }
}
