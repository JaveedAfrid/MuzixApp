package com.learn.recommendationservice.service;

import com.learn.recommendationservice.model.Tracks;
import com.learn.recommendationservice.repository.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService{

    private TrackRepo repo;

    @Autowired
    public TrackServiceImpl(TrackRepo repo) {
        this.repo = repo;
    }

    @Override
    public String addTracks(Tracks track) {
        System.out.println("Track Id outside "+track.getTrackId());
        if(repo.findByTrackId(track.getTrackId()).isEmpty()){
            System.out.println("Track Id inside "+track.getTrackId());
            repo.save(track);
            int count =1;
            repo.updateByTrackId(count,track.getTrackId());
        }else{
            int count = repo.getCountByTrackId(track.getTrackId());
            count++;
            repo.updateByTrackId(count,track.getTrackId());
        }
        return track.getTrackId();
    }

    @Override
    public List<String> getTopTenTracks() {
        return repo.findAll().stream()
                .sorted(Comparator.comparing(Tracks::getCount).reversed())
                .limit(10)
                .map(Tracks::getTrackId)
                .collect(Collectors.toList());
    }
}