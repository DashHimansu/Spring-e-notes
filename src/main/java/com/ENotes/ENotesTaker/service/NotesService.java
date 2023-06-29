package com.ENotes.ENotesTaker.service;

import com.ENotes.ENotesTaker.entity.Notes;
import com.ENotes.ENotesTaker.entity.UserInfo;
import com.ENotes.ENotesTaker.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public Page<Notes> findNotesByUser(UserInfo user, Integer id, Pageable pageable) {
        Page<Notes> notes = notesRepository.findNotesByUser(user.getId(),pageable);
        return notes;
    }

    public Optional<Notes> findNotesById(int id) {
        Optional<Notes> notes = notesRepository.findById(id);
        return notes;
    }

    public Notes save(UserInfo user, Notes notes) {
        notes.setUser(user);
        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
        System.out.println(notes);
        notes.setUpdateDate(date);
        System.out.println(date);
        Notes updateNotes = notesRepository.save(notes);
        return updateNotes;
    }

    public void delete(Notes notes) {
        notesRepository.delete(notes);
    }
}
