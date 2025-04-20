package com.proclus.climasphere.controller;

import com.proclus.climasphere.service.PexelsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pexels")
@CrossOrigin(origins = "*")
public class PexelsController {

    private final PexelsService pexelsService;

    public PexelsController(PexelsService pexelsService) {
        this.pexelsService = pexelsService;
    }

    @GetMapping
    public ResponseEntity<?> getPexelsVideo(@RequestParam String query,
                                            @RequestParam(defaultValue = "0") int videoIndex,
                                            @RequestParam(defaultValue = "2") int resolutionIndex) {
        try {
            String pexelVideoUrl = pexelsService.getPexelVideo(query, videoIndex, resolutionIndex);
            return ResponseEntity.status(200).body(pexelVideoUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching video");
        }
    }
}
