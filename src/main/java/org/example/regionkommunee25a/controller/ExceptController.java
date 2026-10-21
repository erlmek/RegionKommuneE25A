package org.example.regionkommunee25a.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/exp")
public class ExceptController {

    @GetMapping("")
    public String hello() {
        return "hej";
    }

    @GetMapping("file/{filename}")
    public String getFilename(@PathVariable String filename) {
        String fileContent = "";
        try {
            FileInputStream fs = new FileInputStream("c:/AFILE/" + filename);
            try {
                byte[] b = fs.readAllBytes();
                fileContent = new String(b);
            } catch (IOException io) {
                fileContent = io.getMessage();
            } finally {
                try {
                    fs.close();
                } catch (IOException io) {
                    fileContent = "kunne ikke lukke filen";
                }
            }
        } catch(FileNotFoundException e) {
            fileContent = e.getMessage();
        }
        return filename;
    }

    @GetMapping("div/{divnum1}/{divnum2}")
    public int getDivnum(@PathVariable int divnum1, @PathVariable int divnum2) {
        int i1 = divnum1 / divnum2;
        return i1;
    }

    @GetMapping("/loopInt/{loopnum}")
    public int getLoopnumInt(@PathVariable int loopnum) {
        int x = 0;
        for (int i = 0; i < loopnum; i++) {
            x++;
        }
        return x;
    }

    @GetMapping("/loop/{loopnum}")
    public int getLoopnum(@PathVariable String loopnum) {
        int x = 0;
        int loopnum2 = Integer.parseInt(loopnum);
        for (int i = 0; i < loopnum2; i++) {
            x++;
        }
        return x;
    }



}
