package com.proclus.climasphere.service;

import java.util.List;

import com.proclus.climasphere.dto.PexelsResponse;
import com.proclus.climasphere.dto.Video;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PexelsService {

    @Value("${pexels.api.key}")
    private String pexelsApiKey;

    @Value("${pexels.videoquery.api.url}")
    private String pexelsVideoQueryUrl;

    private final RestTemplate restTemplate;

    public PexelsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPexelVideo(String videoQuery) {
        return getPexelVideo(videoQuery, 0, 2, 1);
    }

    // The videoIndex makes it fetch different picture everytime it's changed; the bigger the number, the less relevancy to the query
    public String getPexelVideo(String videoQuery, int videoIndex) {
        return getPexelVideo(videoQuery, videoIndex, 2, 1);
    }

    // The resolutionIndex (choices 0 to 6) signals the resolution of the video.
    public String getPexelVideo(String videoQuery, int videoIndex, int resolutionIndex) {
        return getPexelVideo(videoQuery, videoIndex, resolutionIndex, 1);
    }

    public String getPexelVideo(String videoQuery, int videoIndex, int resolutionIndex, int perPage) {
        String url = String.format("%s?query=%s&per_page=%s", pexelsVideoQueryUrl, videoQuery, perPage);
        try {
            // Set 'Authorization' header and pass in the api
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", pexelsApiKey);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<PexelsResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, PexelsResponse.class);
            PexelsResponse responseBody = response.getBody();

            if (responseBody != null && responseBody.getVideos() != null) {
                List<Video> videos = responseBody.getVideos();
                if (videoIndex < videos.size() && resolutionIndex < videos.get(videoIndex).getVideoFiles().size()) {
                    return videos.get(videoIndex).getVideoFiles().get(resolutionIndex).getLink();
                }
            }

            return "Video not found";

        } catch (Exception e) {
            e.printStackTrace();
            return "Video not found";
        }
    }
}
