package com.learn.recommendationservice.repository;

import com.learn.recommendationservice.model.Tracks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TrackRepo extends JpaRepository<Tracks,Integer> {

    @Query("select t from Tracks t where t.trackId=:trackId ")
    Optional<String> findByTrackId(String trackId);

    @Query("select t.count from Tracks t where t.trackId=:trackId ")
    int getCountByTrackId(String trackId);

    @Transactional
    @Modifying
    @Query("update Tracks t set t.count=:count where t.trackId=:trackId ")
    void updateByTrackId(int count,String trackId);

}
