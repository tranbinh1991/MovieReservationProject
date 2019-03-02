/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Room;
import com.bh08.movieproject.models.User;
import com.bh08.movieproject.services.RoomService;
import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.viewmodels.RoomCreationFormData;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Zoli
 */
@Controller
public class RoomCreationController {

    @Autowired
    private RoomService roomService;
    
    @RequestMapping(value = "roomcreation", method = RequestMethod.GET)
    public String createRoom(Model model) {
        model.addAttribute("roomCreationFormData", new RoomCreationFormData());
        return "roomcreation.html";
    }
    
    @RequestMapping(value = "createRoom", method = RequestMethod.POST)
    public String addNewRoom(@ModelAttribute("roomCreationFormData") @Valid RoomCreationFormData roomCreationFormData,
            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (roomService.findByRoomNumber(Integer.parseInt(roomCreationFormData.getRoomNumber())).isEmpty()) {
                Room room = new Room();
                room.setRoomNumber(Integer.parseInt(roomCreationFormData.getRoomNumber()));
                room.setColumnCount(Integer.parseInt(roomCreationFormData.getColumnCount()));
                room.setRowCount(Integer.parseInt(roomCreationFormData.getRowCount()));
                roomService.saveRoom(room);
                return "adminpage.html";
            } else {
                bindingResult.rejectValue("roomNumber", "", "Már létezik terem ezzel a számmal!");
            }
        }
        return "roomcreation.html";
    }

//    @RequestMapping(value = "createRoom", method = RequestMethod.POST)
//    public @ResponseBody
//    Room createRoom(@RequestBody Room room) {
//        return roomService.saveRoom(room);
//    }
//
    
    
}
